<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, modelo.Alumno" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Alumnos</title>
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
            <h2>Capturar alumnos</h2>

            <% if (request.getAttribute("error") != null) { %>
                <div class="mensaje-error"><%= request.getAttribute("error") %></div>
            <% } %>

            <form method="post" action="${pageContext.request.contextPath}/datos" class="form-inline">
                <input type="hidden" name="accion" value="alumnos">
                <input type="hidden" name="operacion" value="agregar">
                <div>
                    <label for="matricula">Matricula</label>
                    <input type="text" id="matricula" name="matricula" required>
                </div>
                <div>
                    <label for="nombre">Nombre completo</label>
                    <input type="text" id="nombre" name="nombre" required>
                </div>
                <div>
                    <label for="grupo">Grupo</label>
                    <input type="text" id="grupo" name="grupo" value="9A" required>
                </div>
                <button type="submit">Agregar</button>
            </form>
        </div>

        <div class="tarjeta">
            <h2>Alumnos registrados</h2>
            <%
                List<Alumno> alumnos = (List<Alumno>) request.getAttribute("alumnos");
            %>
            <% if (alumnos == null || alumnos.isEmpty()) { %>
                <p>No hay alumnos registrados.</p>
            <% } else { %>
                <table>
                    <thead>
                        <tr><th>Matricula</th><th>Nombre</th><th>Grupo</th><th>Correo</th><th></th><th></th></tr>
                    </thead>
                    <tbody>
                        <% for (Alumno a : alumnos) {
                            String fEditar = "editar-alumno-" + a.getMatricula();
                        %>
                            <tr>
                                <td><%= a.getMatricula() %></td>
                                <td><input type="text" name="nombre" form="<%= fEditar %>" class="campo-editable"
                                           value="<%= a.getNombre() %>" readonly required></td>
                                <td><input type="text" name="grupo" form="<%= fEditar %>" class="campo-editable"
                                           value="<%= a.getGrupo() %>" readonly required></td>
                                <td><%= a.getCorreo() %></td>
                                <td>
                                    <button type="button" onclick="activarEdicion(this)">Editar</button>
                                    <form id="<%= fEditar %>" method="post" action="${pageContext.request.contextPath}/datos">
                                        <input type="hidden" name="accion" value="alumnos">
                                        <input type="hidden" name="operacion" value="editar">
                                        <input type="hidden" name="matricula" value="<%= a.getMatricula() %>">
                                        <button type="submit" form="<%= fEditar %>" class="btn-guardar" style="display:none">Guardar</button>
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="${pageContext.request.contextPath}/datos"
                                          onsubmit="return confirm('¿Eliminar este alumno? Tambien se borran sus calificaciones.');">
                                        <input type="hidden" name="accion" value="alumnos">
                                        <input type="hidden" name="operacion" value="eliminar">
                                        <input type="hidden" name="matricula" value="<%= a.getMatricula() %>">
                                        <button type="submit">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } %>
        </div>
    </main>
    <script src="${pageContext.request.contextPath}/js/tabla-editable.js"></script>
</body>
</html>
