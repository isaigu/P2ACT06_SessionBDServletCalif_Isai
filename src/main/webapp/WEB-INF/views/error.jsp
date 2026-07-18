<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ocurrio un error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <main>
        <div class="tarjeta tarjeta-login">
            <h2>Ocurrio un error</h2>
            <p>No fue posible completar la operacion. Intenta de nuevo o vuelve al inicio.</p>
            <p style="text-align:center; margin-top:20px;">
                <a href="${pageContext.request.contextPath}/index.jsp"><button type="button">Volver al inicio</button></a>
            </p>
        </div>
    </main>
</body>
</html>
