<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar sesion</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <main>
        <div class="tarjeta tarjeta-login">
            <h2>Iniciar sesion</h2>

            <% if (request.getAttribute("error") != null) { %>
                <div class="mensaje-error"><%= request.getAttribute("error") %></div>
            <% } %>

            <form method="post" action="${pageContext.request.contextPath}/sesion">
                <input type="hidden" name="accion" value="login">
                <label for="usuario">Usuario</label>
                <input type="text" id="usuario" name="usuario" required autofocus>

                <label for="contrasena">Contrasena</label>
                <input type="password" id="contrasena" name="contrasena" required>

                <button type="submit">Entrar</button>
            </form>

            <p class="enlaces">
                ¿No tienes cuenta? <a href="${pageContext.request.contextPath}/sesion?accion=registro">Registrate aqui</a>
            </p>
        </div>
    </main>
</body>
</html>
