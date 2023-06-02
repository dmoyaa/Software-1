var elemento = 'consultas';
 cargarClientes();
function showForm(formId) {
    // Oculta todos los formularios

    var forms = document.getElementsByClassName("form-peice");
    for (var i = 0; i < forms.length; i++) {
        forms[i].style.display = "none";
    }

    // Muestra el formulario seleccionado
    var form = document.getElementById(formId);
    form.style.display = "block";
    if (form.id.toString ='formConsultar'){
        elemento = 'consultas';
        cargarClientes();
    } if(form.id.toString ='formEliminar'){
        elemento = 'eliminar';
        cargarClientes();
    } if (form.id.toString ='formActualizar'){
        elemento='actualizar';
        cargarClientes();
        //document.getElementById("actualizar").addEventListener("change", actualizarDatosCliente);
        actualizarDatosCliente();
    }else{

        console.log('----MONDA ASÍ NO ES-----');
    }
}

function cargarClientes() {
    var dropdown = document.getElementById(elemento);
    // Reali zar la petición AJAX para obtener los clientes
    fetch(`./api/clients`)
        .then(response => response.json())
        .then(data => {
            // Limpiar el dropdown
            dropdown.innerHTML = '';

            // Agregar una opción para cada cliente en la respuesta
            data.forEach(cliente => {
                var option = document.createElement('option');
                option.value = cliente.cedula;
                option.text = cliente.nombre;
                dropdown.appendChild(option);
            });
        });
}

function actualizarDatosCliente() {
    var seleccionado = document.getElementById("actualizar").value;

    // Realizar la petición AJAX para obtener los detalles del cliente seleccionado
    fetch(`./api/clients/${seleccionado}`)
        .then(response => response.json())
        .then(data => {
            // Actualizar los campos del formulario con los datos recibidos
            document.getElementById("address").value = data.direccion;
            document.getElementById("email").value = data.correo;
            document.getElementById("phone").value = data.telefono;
        })
        .catch(error => console.log(error));
}

// Cargar los clientes cuando se cargue la página
//window.onload = cargarClientes;