<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, modelo.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Validar cuentas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <header class="barra">
        <h1>Sistema de Calificaciones</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/sesion?accion=inicio">Panel</a>
            <a href="${pageContext.request.contextPath}/sesion?accion=logout">Cerrar sesion</a>
        </nav>
    </header>
    <main>
        <div class="tarjeta">
            <h2>Cuentas pendientes de acceso</h2>
            <p>El correo se confirma solo cuando el docente da clic en el link que le llego
               por email; el acceso lo otorgas tu aqui, aunque el correo todavia no este confirmado.</p>

            <% if (request.getAttribute("error") != null) { %>
                <div class="mensaje-error"><%= request.getAttribute("error") %></div>
            <% } %>

            <%
                List<Usuario> pendientes = (List<Usuario>) request.getAttribute("pendientes");
            %>

            <% if (pendientes == null || pendientes.isEmpty()) { %>
                <p>No hay cuentas pendientes.</p>
            <% } else { %>
                <table>
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Usuario</th>
                            <th>Correo</th>
                            <th>Correo confirmado</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Usuario u : pendientes) { %>
                            <tr>
                                <td><%= u.getNombre() %></td>
                                <td><%= u.getUsuario() %></td>
                                <td><%= u.getCorreo() %></td>
                                <td><%= u.isCorreoValidado() ? "Si" : "No" %></td>
                                <td>
                                    <form method="post" action="${pageContext.request.contextPath}/sesion">
                                        <input type="hidden" name="accion" value="validar">
                                        <input type="hidden" name="idUsuario" value="<%= u.getIdUsuario() %>">
                                        <button type="submit">Dar acceso</button>
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } %>
        </div>
    </main>
</body>
</html>
