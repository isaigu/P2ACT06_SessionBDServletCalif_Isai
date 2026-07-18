package servlet;

import dao.AlumnoDAO;
import dao.CalificacionDAO;
import dao.MateriaDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Alumno;
import modelo.Materia;

/**
 * Segundo (y ultimo) servlet: captura de alumnos, materias y
 * calificaciones (alta, edicion y baja). Que hacer se decide con
 * los parametros "accion" (que seccion) y "operacion" (agregar,
 * editar o eliminar).
 */
@WebServlet("/datos")
public class DatosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        accion = accion == null ? "alumnos" : accion;
        switch (accion) {
            case "materias":
                mostrarMaterias(req, resp);
                break;
            case "calificaciones":
                mostrarCalificaciones(req, resp);
                break;
            case "alumnos":
            default:
                mostrarAlumnos(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        String operacion = req.getParameter("operacion");
        accion = accion == null ? "" : accion;
        operacion = operacion == null ? "agregar" : operacion;

        switch (accion) {
            case "materias":
                if ("editar".equals(operacion)) {
                    editarMateria(req, resp);
                } else if ("eliminar".equals(operacion)) {
                    eliminarMateria(req, resp);
                } else {
                    agregarMateria(req, resp);
                }
                break;
            case "alumnos":
                if ("editar".equals(operacion)) {
                    editarAlumno(req, resp);
                } else if ("eliminar".equals(operacion)) {
                    eliminarAlumno(req, resp);
                } else {
                    agregarAlumno(req, resp);
                }
                break;
            case "calificaciones":
                if ("eliminar".equals(operacion)) {
                    eliminarCalificacion(req, resp);
                } else {
                    guardarParcial(req, resp);
                }
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Accion desconocida.");
        }
    }

    // ----- Materias -----

    private void mostrarMaterias(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("materias", new MateriaDAO().listar());
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error de conexion con la base de datos.");
        }
        forward(req, resp, "materias.jsp");
    }

    private void agregarMateria(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nombre = req.getParameter("nombre");
        try {
            if (nombre != null && !nombre.isBlank()) {
                new MateriaDAO().agregar(new Materia(0, nombre.trim()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/datos?accion=materias");
    }

    private void editarMateria(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nombre = req.getParameter("nombre");
        try {
            int idMateria = Integer.parseInt(req.getParameter("idMateria"));
            if (nombre != null && !nombre.isBlank()) {
                new MateriaDAO().editar(idMateria, nombre.trim());
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/datos?accion=materias");
    }

    private void eliminarMateria(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int idMateria = Integer.parseInt(req.getParameter("idMateria"));
            new MateriaDAO().eliminar(idMateria);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/datos?accion=materias");
    }

    // ----- Alumnos -----

    private void mostrarAlumnos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("alumnos", new AlumnoDAO().listar());
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error de conexion con la base de datos.");
        }
        forward(req, resp, "alumnos.jsp");
    }

    private void agregarAlumno(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String matricula = req.getParameter("matricula");
        String nombre = req.getParameter("nombre");
        String grupo = req.getParameter("grupo");
        try {
            if (matricula != null && !matricula.isBlank() && nombre != null && !nombre.isBlank()) {
                String matriculaLimpia = matricula.trim();
                Alumno a = new Alumno(matriculaLimpia, nombre.trim(), grupo == null ? "" : grupo.trim(), LocalDate.now());
                a.setCorreo(matriculaLimpia + "@utrng.edu.mx");
                new AlumnoDAO().agregar(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "No se pudo agregar el alumno. Verifica que la matricula no este repetida.");
        }
        resp.sendRedirect(req.getContextPath() + "/datos?accion=alumnos");
    }

    private void editarAlumno(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String matricula = req.getParameter("matricula");
        String nombre = req.getParameter("nombre");
        String grupo = req.getParameter("grupo");
        try {
            if (matricula != null && !matricula.isBlank() && nombre != null && !nombre.isBlank()) {
                new AlumnoDAO().editar(matricula.trim(), nombre.trim(), grupo == null ? "" : grupo.trim());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/datos?accion=alumnos");
    }

    private void eliminarAlumno(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String matricula = req.getParameter("matricula");
        try {
            if (matricula != null && !matricula.isBlank()) {
                new AlumnoDAO().eliminar(matricula.trim());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/datos?accion=alumnos");
    }

    // ----- Calificaciones -----

    private void mostrarCalificaciones(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("materias", new MateriaDAO().listar());

            String idMateriaParam = req.getParameter("idMateria");
            String parcialParam = req.getParameter("parcial");

            if (idMateriaParam != null && parcialParam != null) {
                int idMateria = Integer.parseInt(idMateriaParam);
                int parcial = Integer.parseInt(parcialParam);
                CalificacionDAO dao = new CalificacionDAO();
                dao.generarFilasParaMateria(idMateria);
                req.setAttribute("calificaciones", dao.listarPorMateria(idMateria));
                req.setAttribute("idMateria", idMateria);
                req.setAttribute("parcial", parcial);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error de conexion con la base de datos.");
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Materia o parcial invalido.");
        }
        forward(req, resp, "calificaciones.jsp");
    }

    private void guardarParcial(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idMateria = req.getParameter("idMateria");
        String parcial = req.getParameter("parcial");
        try {
            int idCalificacion = Integer.parseInt(req.getParameter("idCalificacion"));
            int numParcial = Integer.parseInt(parcial);
            double nota = req.getParameter("nota") == null || req.getParameter("nota").isBlank()
                    ? 0 : Double.parseDouble(req.getParameter("nota"));
            new CalificacionDAO().actualizarUnParcial(idCalificacion, numParcial, nota);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/datos?accion=calificaciones&idMateria=" + idMateria + "&parcial=" + parcial);
    }

    private void eliminarCalificacion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idMateria = req.getParameter("idMateria");
        String parcial = req.getParameter("parcial");
        try {
            int idCalificacion = Integer.parseInt(req.getParameter("idCalificacion"));
            new CalificacionDAO().eliminar(idCalificacion);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/datos?accion=calificaciones&idMateria=" + idMateria + "&parcial=" + parcial);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, String vista)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + vista);
        rd.forward(req, resp);
    }
}
