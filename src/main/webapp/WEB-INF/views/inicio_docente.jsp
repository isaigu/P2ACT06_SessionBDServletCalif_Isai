<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel del docente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <header class="barra">
        <h1>Sistema de Calificaciones</h1>
        <nav>
            <span>Hola, ${sessionScope.nombreUsuario}
                <% if (Boolean.TRUE.equals(session.getAttribute("esAdmin"))) { %>
                    <span class="etiqueta-admin">ADMIN</span>
                <% } %>
            </span>
            <a href="${pageContext.request.contextPath}/sesion?accion=logout">Cerrar sesion</a>
        </nav>
    </header>
    <main>
        <div class="tarjeta">
            <h2>Panel del docente</h2>
            <p>Selecciona una opcion:</p>
            <p>
                <a href="${pageContext.request.contextPath}/datos?accion=materias"><button type="button">Materias</button></a>
                <a href="${pageContext.request.contextPath}/datos?accion=alumnos"><button type="button">Alumnos</button></a>
                <a href="${pageContext.request.contextPath}/datos?accion=calificaciones"><button type="button">Calificaciones</button></a>
                <% if (Boolean.TRUE.equals(session.getAttribute("esAdmin"))) { %>
                    <a href="${pageContext.request.contextPath}/sesion?accion=validar"><button type="button">Validar cuentas</button></a>
                <% } %>
            </p>
        </div>
    </main>
</body>
</html>
