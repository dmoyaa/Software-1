// Variable para almacenar los elementos ya cargados
const elementosCargados = {};
async function cargarClientes(elemento) {
    // Verificar si el elemento ya ha sido cargado
    if (elementosCargados[elemento]) {
        return; // Salir de la función si ya ha sido cargado
    }

    let dropdown = document.getElementById(elemento);

    // Crear opción "Seleccione"
    var optionSeleccione = document.createElement('option');
    optionSeleccione.value = "DEFAULT";
    optionSeleccione.text = "Seleccione";
    dropdown.appendChild(optionSeleccione);

    // Realizar la petición AJAX para obtener los clientes
    fetch(`./api/clients`)
        .then(response => response.json())
        .then(data => {
            // Agregar una opción para cada cliente en la respuesta
            data.forEach(cliente => {
                var option = document.createElement('option');
                option.value = cliente.cedula;
                option.text = cliente.cedula;
                dropdown.appendChild(option);
            });

            // Marcar el elemento como cargado
            elementosCargados[elemento] = true;
        });
}

async function ingresar() {
    const form = document.getElementById("form-registrar");
    event.preventDefault();
    const formData = new FormData(form);
    let seleccionado = document.getElementById("cedula").value;
    try {
        let response = await fetch(`./api/clients/agregar/${seleccionado}`, {
            method: 'POST',
            headers: {

                "Content-Type": "application/x-www-form-urlencoded",
            },

            body: new URLSearchParams(formData),
        });
        let respuesta = await response.json();
        if (respuesta.errorCode == 200) {
            window.alert("Cliente: " + formData.get("name") + ", ¡INGRESADO exitosamente!.");
        } else {
            window.alert(resultado.message);
        }
    } catch (r) {
        alert("Server Error: " + respuesta);
        console.log(r + "!error.!");
    }

}

async function actualizarDatosClienteConsulta() {
    const form = document.getElementById("formConsulta");
    event.preventDefault();
    const formData = new FormData(form);
    let seleccionado = document.getElementById("fechap").value;
    if (seleccionado) {
        try {
            let response = await fetch(`./api/sells/${seleccionado}`, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: new URLSearchParams(formData),
            });

            if (response.ok) {
                let data = await response.json();
                let parametros = JSON.stringify(data, null, 2);
                console.log(parametros);
                window.alert(parametros);
            } else {
                console.log('Error en la respuesta de la petición');
            }
        } catch (error) {
            console.log(error);
        }
    } else {
        window.alert("Por favor, ingresa una fecha válida");
    }
}




