<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, modelo.Materia, modelo.Calificacion" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Calificaciones</title>
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
        <%
            List<Materia> materias = (List<Materia>) request.getAttribute("materias");
            Object idMateriaAttr = request.getAttribute("idMateria");
            Object parcialAttr = request.getAttribute("parcial");
        %>

        <div class="tarjeta">
            <h2>Registro de calificaciones</h2>
            <p>Elige la materia y el parcial para ver la lista de alumnos.</p>

            <% if (request.getAttribute("error") != null) { %>
                <div class="mensaje-error"><%= request.getAttribute("error") %></div>
            <% } %>

            <form method="get" action="${pageContext.request.contextPath}/datos" class="form-inline">
                <input type="hidden" name="accion" value="calificaciones">
                <div>
                    <label for="idMateria">Materia</label>
                    <select id="idMateria" name="idMateria" required>
                        <option value="">-- Selecciona --</option>
                        <% if (materias != null) {
                            for (Materia m : materias) {
                                boolean seleccionada = idMateriaAttr != null && ((Integer) idMateriaAttr) == m.getIdMateria();
                        %>
                            <option value="<%= m.getIdMateria() %>" <%= seleccionada ? "selected" : "" %>><%= m.getNombre() %></option>
                        <% } } %>
                    </select>
                </div>
                <div>
                    <label for="parcial">Parcial</label>
                    <select id="parcial" name="parcial" required>
                        <option value="">-- Selecciona --</option>
                        <% for (int n = 1; n <= 3; n++) {
                            boolean seleccionado = parcialAttr != null && ((Integer) parcialAttr) == n;
                        %>
                            <option value="<%= n %>" <%= seleccionado ? "selected" : "" %>>Parcial <%= n %></option>
                        <% } %>
                    </select>
                </div>
                <button type="submit">Ver alumnos</button>
            </form>
        </div>

        <% if (idMateriaAttr != null && parcialAttr != null) {
            int parcial = (Integer) parcialAttr;
            List<Calificacion> calificaciones = (List<Calificacion>) request.getAttribute("calificaciones");
        %>
        <div class="tarjeta">
            <h2>Alumnos - Parcial <%= parcial %></h2>
            <% if (calificaciones == null || calificaciones.isEmpty()) { %>
                <p>No hay alumnos capturados todavia.</p>
            <% } else { %>
                <table>
                    <thead>
                        <tr>
                            <th>Matricula</th>
                            <th>Alumno</th>
                            <th>Calificacion</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Calificacion c : calificaciones) {
                            String fGuardar = "guardar-calif-" + c.getIdCalificacion();
                        %>
                            <tr>
                                <td><%= c.getMatricula() %></td>
                                <td><%= c.getNombreAlumno() %></td>
                                <td>
                                    <input class="parcial-input campo-editable" type="number" step="0.1" min="0" max="10"
                                           name="nota" form="<%= fGuardar %>" value="<%= c.getParcial(parcial) %>" readonly>
                                </td>
                                <td>
                                    <button type="button" onclick="activarEdicion(this)">Editar</button>
                                    <form id="<%= fGuardar %>" method="post" action="${pageContext.request.contextPath}/datos">
                                        <input type="hidden" name="accion" value="calificaciones">
                                        <input type="hidden" name="idMateria" value="<%= c.getIdMateria() %>">
                                        <input type="hidden" name="parcial" value="<%= parcial %>">
                                        <input type="hidden" name="idCalificacion" value="<%= c.getIdCalificacion() %>">
                                        <button type="submit" form="<%= fGuardar %>" class="btn-guardar" style="display:none">Guardar</button>
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="${pageContext.request.contextPath}/datos"
                                          onsubmit="return confirm('¿Eliminar esta calificacion?');">
                                        <input type="hidden" name="accion" value="calificaciones">
                                        <input type="hidden" name="operacion" value="eliminar">
                                        <input type="hidden" name="idMateria" value="<%= c.getIdMateria() %>">
                                        <input type="hidden" name="parcial" value="<%= parcial %>">
                                        <input type="hidden" name="idCalificacion" value="<%= c.getIdCalificacion() %>">
                                        <button type="submit">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } %>
        </div>
        <% } %>
    </main>
    <script src="${pageContext.request.contextPath}/js/tabla-editable.js"></script>
</body>
</html>
