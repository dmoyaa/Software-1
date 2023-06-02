window.onloadstart = cargarProveedores();


async function cargarProveedores() {
    let desplegableActualizar = document.getElementById("lista-proveedores2");
    let desplegableEliminar = document.getElementById("lista-proveedores1");
    let desplegableConsultar = document.getElementById("lista-proveedores");

    let response = await fetch(`./api/proveedor`,{
        method:'GET',
            Headers: {

            "Content-Type": "APPLICATION_JSON",
        }
    })
    let result = await response.json();
    console.log(result);

    console.log("inicio blucle");

    desplegableActualizar.innerHTML = '';
    desplegableEliminar.innerHTML='';
    desplegableConsultar.innerHTML = '';


    result.forEach(result => {
        console.log(result);
        let option = document.createElement('option');
        option.value = result.nit;
        option.text = result.nombre;
        desplegableActualizar.appendChild(option);
        let option2 = document.createElement('option');
        option2.value = result.nit;
        option2.text = result.nombre;
        desplegableEliminar.appendChild(option2);
        let option3 = document.createElement('option');
        option3.value = result.nit;
        option3.text = result.nombre;
        desplegableConsultar.appendChild(option3);
    });


}

async function cargarInformacion() {
    let nit_proveedor = document.getElementById("lista-proveedores2").value;
    console.log(nit_proveedor);
    let response = await fetch(`./api/proveedor/${nit_proveedor}`, {
        method: 'GET',
        Headers: {

            "Content-Type": "APPLICATION_JSON",
        }
    })
    let resultado = await response.json();
    console.log(resultado);
    $("#name_actualizar").val(resultado.nombre);
    $("#address_actualizar").val(resultado.direccion);
    $("#email_actualizar").val(resultado.correo);
    $("#phone_actualizar").val(resultado.telefono);
    $("#label_actualizar").val("");

}