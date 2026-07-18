<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de docente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <main>
        <div class="tarjeta tarjeta-login">
            <h2>Registro de docente</h2>

            <% if (request.getAttribute("error") != null) { %>
                <div class="mensaje-error"><%= request.getAttribute("error") %></div>
            <% } %>
            <% if (request.getAttribute("mensaje") != null) { %>
                <div class="mensaje-exito"><%= request.getAttribute("mensaje") %></div>
            <% } %>

            <form method="post" action="${pageContext.request.contextPath}/sesion">
                <input type="hidden" name="accion" value="registro">
                <label for="nombre">Nombre completo</label>
                <input type="text" id="nombre" name="nombre" required>

                <label for="correo">Correo electronico</label>
                <input type="email" id="correo" name="correo" required>

                <label for="usuario">Usuario</label>
                <input type="text" id="usuario" name="usuario" required>

                <label for="contrasena">Contrasena</label>
                <input type="password" id="contrasena" name="contrasena" required>

                <button type="submit">Registrarme</button>
            </form>

            <p class="enlaces">
                ¿Ya tienes cuenta? <a href="${pageContext.request.contextPath}/sesion?accion=login">Inicia sesion</a>
            </p>
        </div>
    </main>
</body>
</html>
