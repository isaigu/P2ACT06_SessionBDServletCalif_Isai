package servlet;

import dao.UsuarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;
import util.EmailUtil;
import util.PasswordUtil;
import util.TokenUtil;

/**
 * Un solo servlet para todo lo relacionado a cuentas: login,
 * registro, confirmacion de correo, logout, aprobacion del admin
 * y el panel de inicio del docente. Que accion ejecutar se decide
 * con el parametro "accion".
 */
@WebServlet("/sesion")
public class SesionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if (accion == null) {
            accion = "login";
        }
        switch (accion) {
            case "registro":
                forward(req, resp, "registro.jsp");
                break;
            case "logout":
                cerrarSesion(req, resp);
                break;
            case "confirmarCorreo":
                confirmarCorreo(req, resp);
                break;
            case "validar":
                mostrarPendientes(req, resp);
                break;
            case "inicio":
                forward(req, resp, "inicio_docente.jsp");
                break;
            case "login":
            default:
                forward(req, resp, "login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if ("registro".equals(accion)) {
            procesarRegistro(req, resp);
        } else if ("validar".equals(accion)) {
            procesarValidacion(req, resp);
        } else {
            procesarLogin(req, resp);
        }
    }

    private void procesarLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        String contrasena = req.getParameter("contrasena");
        String error = null;

        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario u = dao.buscarPorUsuario(usuario);

            if (u == null || !u.getContrasena().equals(PasswordUtil.hash(contrasena))) {
                error = "Usuario o contrasena incorrectos.";
            } else if (!u.isCorreoValidado()) {
                error = "Todavia no confirmas tu correo. Revisa tu bandeja de entrada.";
            } else if (!u.isEstado()) {
                error = "Ya confirmaste tu correo. Falta que el administrador te de acceso.";
            } else {
                HttpSession sesion = req.getSession(true);
                sesion.setAttribute("idUsuario", u.getIdUsuario());
                sesion.setAttribute("nombreUsuario", u.getNombre());
                sesion.setAttribute("esAdmin", u.isEsAdmin());
                resp.sendRedirect(req.getContextPath() + "/sesion?accion=inicio");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            error = "Error de conexion con la base de datos.";
        }

        req.setAttribute("error", error);
        forward(req, resp, "login.jsp");
    }

    private void procesarRegistro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        String contrasena = req.getParameter("contrasena");
        String nombre = req.getParameter("nombre");
        String correo = req.getParameter("correo");
        String error = null;
        String mensaje = null;

        try {
            UsuarioDAO dao = new UsuarioDAO();
            if (usuario == null || usuario.isBlank() || contrasena == null || contrasena.isBlank()
                    || nombre == null || nombre.isBlank() || correo == null || correo.isBlank()) {
                error = "Todos los campos son obligatorios.";
            } else if (dao.existeUsuario(usuario)) {
                error = "Ese nombre de usuario ya existe.";
            } else {
                String token = TokenUtil.generar();
                Usuario u = new Usuario(usuario, PasswordUtil.hash(contrasena), nombre, correo);
                u.setTokenConfirmacion(token);
                dao.registrar(u);

                String link = urlBase(req) + "/sesion?accion=confirmarCorreo&token=" + token;
                EmailUtil.enviarConfirmacionCorreo(correo, nombre, link);

                mensaje = "Registro exitoso. Revisa tu correo para confirmarlo y despues espera a "
                        + "que el administrador te de acceso.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            error = "Error de conexion con la base de datos.";
        }

        req.setAttribute("error", error);
        req.setAttribute("mensaje", mensaje);
        forward(req, resp, "registro.jsp");
    }

    private void confirmarCorreo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        String mensaje;
        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario u = token == null ? null : dao.buscarPorToken(token);
            if (u == null) {
                mensaje = "El link de confirmacion no es valido.";
            } else {
                dao.confirmarCorreo(token);
                mensaje = "Correo confirmado. Ahora espera a que el administrador te de acceso.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error de conexion con la base de datos.";
        }
        req.setAttribute("mensaje", mensaje);
        forward(req, resp, "confirmacion.jsp");
    }

    private void mostrarPendientes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!esAdmin(req)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Solo el administrador puede dar acceso a cuentas.");
            return;
        }
        try {
            List<Usuario> pendientes = new UsuarioDAO().listarPendientes();
            req.setAttribute("pendientes", pendientes);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error de conexion con la base de datos.");
        }
        forward(req, resp, "validacion.jsp");
    }

    private void procesarValidacion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!esAdmin(req)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Solo el administrador puede dar acceso a cuentas.");
            return;
        }
        try {
            int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
            UsuarioDAO dao = new UsuarioDAO();
            Usuario u = dao.buscarPorId(idUsuario);
            dao.darAcceso(idUsuario);
            if (u != null) {
                EmailUtil.enviarNotificacionAcceso(u.getCorreo(), u.getNombre());
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("error", "No se pudo dar acceso a la cuenta.");
        }
        resp.sendRedirect(req.getContextPath() + "/sesion?accion=validar");
    }

    private void cerrarSesion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession sesion = req.getSession(false);
        if (sesion != null) {
            sesion.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/sesion?accion=login");
    }

    private boolean esAdmin(HttpServletRequest req) {
        HttpSession sesion = req.getSession(false);
        return sesion != null && Boolean.TRUE.equals(sesion.getAttribute("esAdmin"));
    }

    private String urlBase(HttpServletRequest req) {
        return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, String vista)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + vista);
        rd.forward(req, resp);
    }
}
