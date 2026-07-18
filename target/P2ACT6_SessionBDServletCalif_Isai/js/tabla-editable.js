function activarEdicion(botonEditar) {
    var fila = botonEditar.closest('tr');
    var campos = fila.querySelectorAll('.campo-editable');
    campos.forEach(function (campo) {
        campo.readOnly = false;
    });
    if (campos.length > 0) {
        campos[0].focus();
    }
    botonEditar.style.display = 'none';
    var botonGuardar = fila.querySelector('.btn-guardar');
    if (botonGuardar) {
        botonGuardar.style.display = '';
    }
}
