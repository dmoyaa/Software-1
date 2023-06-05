    var elemento = 'consultas';
    cargarClientes();

    function showForm(formId) {
        // Oculta todos los formularios

        let forms = document.getElementsByClassName("form-peice");
        for (var i = 0; i < forms.length; i++) {
            forms[i].style.display = "none";
        }

        // Muestra el formulario seleccionado
        var form = document.getElementById(formId);
        form.style.display = "block";
        if (form.id.toString = 'formConsultar') {
            elemento = 'clientes';
            cargarClientes();
        }
        if (form.id.toString = 'formEliminar') {
            elemento = 'eliminar';
            cargarClientes();
        }
        if (form.id.toString = 'formActualizar') {
            elemento = 'actualizar';
            cargarClientes();
        }
    }

    function cargarClientes() {
        let dropdown = document.getElementById(elemento);
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
                    option.text = cliente.cedula;
                    dropdown.appendChild(option);
                });
            });
    }

    async function actualizarDatosCliente() {
        let seleccionado = document.getElementById("actualizar").value;
        console.log("lo que trajo fue " + seleccionado)
        // Realizar la petición AJAX para obtener los detalles del cliente seleccionado
        fetch(`./api/clients/${seleccionado}`)
            .then(response => response.json())
            .then(data => {
                // Actualizar los campos del formulario con los datos recibidos
                document.getElementById("namea").value = data.nombre;
                document.getElementById("lastnamea").value = data.apellido;
                document.getElementById("addressa").value = data.direccion;
                document.getElementById("phonea").value = data.telefono;
            })
            .catch(error => console.log(error));
    }

    async function actualizarDatosClienteConsulta() {
        let seleccionado = document.getElementById("consultas").value;
        console.log("lo que trajo fue " + seleccionado)
        // Realizar la petición AJAX para obtener los detalles del cliente seleccionado
        fetch(`./api/clients/${seleccionado}`)
            .then(response => response.json())
            .then(data => {
                // Actualizar los campos con los datos recibidos
                $("#namec").text("NOMBRE: "+data.nombre);
                $("#lastnamec").text("APELLIDO: "+ data.apellido);
                $("#addressc").text("DIRECCIÓN: "+data.direccion);
                $("#phonec").text("TELEFONO: "+data.telefono);
            })
            .catch(error => console.log(error));
    }

    async function actualizarConsultar() {
        const form = document.querySelector("form")
        event.preventDefault();
        const formData = new FormData(form);

        let seleccionado = document.getElementById("actualizar").value;
        try {
            let response = await fetch(`./api/clients/${seleccionado}`, {
                method: 'PUT',
                headers: {

                    "Content-Type": "application/x-www-form-urlencoded",
                },

                body: new URLSearchParams(formData),
            });
            let respuesta = await response.json();
            if (respuesta.errorCode == 200) {
                window.alert("Cliente: " + formData.get("name") + ", ¡editado exitosamente!.");
            } else {
                window.alert(resultado.message);
            }
        } catch (r) {
            alert("Server Error: " + respuesta);
            console.log(r + "!error.!");
        }
    }

    async function actualizarEliminar() {
        const form = document.querySelector("form")
        event.preventDefault();
        const formData = new FormData(form);

        let seleccionado = document.getElementById("eliminar").value;
        try {
            let response = await fetch(`./api/clients/delete/${seleccionado}`, {
                method: 'PUT',
                headers: {

                    "Content-Type": "application/x-www-form-urlencoded",
                },

                body: new URLSearchParams(formData),
            });
            let respuesta = await response.json();
            if (respuesta.errorCode == 200) {
                window.alert("Cliente: " + formData.get("name") + ", ¡editado exitosamente!.");
            } else {
                window.alert(resultado.message);
            }
        } catch (r) {
            alert("Server Error: " + respuesta);
            console.log(r + "!error.!");
        }

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