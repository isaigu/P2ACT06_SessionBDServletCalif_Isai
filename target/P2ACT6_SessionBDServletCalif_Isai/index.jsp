<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Sistema de Calificaciones</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <main>
        <div class="tarjeta tarjeta-login">
            <h2>Sistema de Calificaciones</h2>
            <p>Bienvenido. Inicia sesion si ya tienes una cuenta o registrate como docente.</p>
            <p style="text-align:center; margin-top: 20px;">
                <a href="${pageContext.request.contextPath}/sesion?accion=login"><button type="button">Iniciar sesion</button></a>
                &nbsp;
                <a href="${pageContext.request.contextPath}/sesion?accion=registro"><button type="button">Registrarse</button></a>
            </p>
        </div>
    </main>
</body>
</html>
