<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, modelo.Materia" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Materias</title>
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
            <h2>Capturar materias</h2>

            <% if (request.getAttribute("error") != null) { %>
                <div class="mensaje-error"><%= request.getAttribute("error") %></div>
            <% } %>

            <form method="post" action="${pageContext.request.contextPath}/datos" class="form-inline">
                <input type="hidden" name="accion" value="materias">
                <input type="hidden" name="operacion" value="agregar">
                <div>
                    <label for="nombre">Nombre de la materia</label>
                    <input type="text" id="nombre" name="nombre" required>
                </div>
                <button type="submit">Agregar</button>
            </form>
        </div>

        <div class="tarjeta">
            <h2>Materias registradas</h2>
            <%
                List<Materia> materias = (List<Materia>) request.getAttribute("materias");
            %>
            <% if (materias == null || materias.isEmpty()) { %>
                <p>No hay materias registradas.</p>
            <% } else { %>
                <table>
                    <thead>
                        <tr><th>ID</th><th>Nombre</th><th></th><th></th></tr>
                    </thead>
                    <tbody>
                        <% for (Materia m : materias) {
                            String fEditar = "editar-materia-" + m.getIdMateria();
                        %>
                            <tr>
                                <td><%= m.getIdMateria() %></td>
                                <td><input type="text" name="nombre" form="<%= fEditar %>" class="campo-editable"
                                           value="<%= m.getNombre() %>" readonly required></td>
                                <td>
                                    <button type="button" onclick="activarEdicion(this)">Editar</button>
                                    <form id="<%= fEditar %>" method="post" action="${pageContext.request.contextPath}/datos">
                                        <input type="hidden" name="accion" value="materias">
                                        <input type="hidden" name="operacion" value="editar">
                                        <input type="hidden" name="idMateria" value="<%= m.getIdMateria() %>">
                                        <button type="submit" form="<%= fEditar %>" class="btn-guardar" style="display:none">Guardar</button>
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="${pageContext.request.contextPath}/datos"
                                          onsubmit="return confirm('¿Eliminar esta materia? Tambien se borran sus calificaciones.');">
                                        <input type="hidden" name="accion" value="materias">
                                        <input type="hidden" name="operacion" value="eliminar">
                                        <input type="hidden" name="idMateria" value="<%= m.getIdMateria() %>">
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
