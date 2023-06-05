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
                const data = await response.json();


                data.forEach((registro) => {

                    const idventa = registro.id_venta;
                    const cliente = registro.cliente;
                    const total = registro.total;
                    const fechaPago = registro.fecha_pago;
                    const stringCompleto = `Factura:${idventa} Cliente: ${cliente}  Total: ${total}  Fecha óptima de pago: ${fechaPago}`;

                    window.alert(stringCompleto);
                });
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
