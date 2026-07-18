package filtro;

import java.io.IOException;
import java.util.Set;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/sesion", "/datos"})
public class SesionFiltro implements Filter 
{

    private static final Set<String> ACCIONES_PROTEGIDAS_SESION = Set.of("inicio", "validar");

    @Override
    public void init(FilterConfig filterConfig) 
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException 
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String ruta = req.getServletPath();
        String accion = req.getParameter("accion");

        boolean requiereSesion = ruta.equals("/datos")
                || (ruta.equals("/sesion") && ACCIONES_PROTEGIDAS_SESION.contains(accion));

        if (requiereSesion) 
        {
            HttpSession sesion = req.getSession(false);
            if (sesion == null || sesion.getAttribute("idUsuario") == null)
            {
                resp.sendRedirect(req.getContextPath() + "/sesion?accion=login");
                return;
            }
            if ("validar".equals(accion) && !Boolean.TRUE.equals(sesion.getAttribute("esAdmin"))) 
            {
                resp.sendRedirect(req.getContextPath() + "/sesion?accion=inicio");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() 
    {
    }
}
