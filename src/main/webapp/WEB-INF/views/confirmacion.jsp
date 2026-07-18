<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Confirmacion de correo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <main>
        <div class="tarjeta tarjeta-login">
            <h2>Confirmacion de correo</h2>
            <p><%= request.getAttribute("mensaje") %></p>
            <p class="enlaces">
                <a href="${pageContext.request.contextPath}/sesion?accion=login">Ir a iniciar sesion</a>
            </p>
        </div>
    </main>
</body>
</html>
