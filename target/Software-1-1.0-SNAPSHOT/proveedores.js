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

    desplegableActualizar.innerHTML = 'Seleccione un proveedor';
    desplegableEliminar.innerHTML='Seleccione un proveedor';
    desplegableConsultar.innerHTML = 'Seleccione un proveedor';
    let option = document.createElement('option');
    option.value = "DEFAULT";
    option.text = "SELECCIONE UN PROVEEDOR";
    desplegableActualizar.appendChild(option);

    let option2 = document.createElement('option');
    option2.value = "DEFAULT";
    option2.text = "SELECCIONE UN PROVEEDOR";
    desplegableEliminar.appendChild(option2);

    let option3 = document.createElement('option');
    option3.value = "DEFAULT";
    option3.text = "SELECCIONE UN PROVEEDOR";
    desplegableConsultar.appendChild(option3);


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

}



async function actualizar() {
    const form = document.querySelector("form")
    event.preventDefault();
    const formData = new FormData(form);
    console.log(formData.get("name"));
    let nit = document.getElementById("lista-proveedores2").value;
    console.log(nit);

    if (nit!="DEFAULT"){
        let response = await fetch(`./api/proveedor/${nit}`,{
            method: 'PUT',
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: new URLSearchParams(formData),
        });
        console.log("Terminé");
        let resultado = await response.json();
        console.log(resultado);
        if (resultado.errorCode == 200){
            window.alert("Proveedor: "+formData.get("name")+", ¡editado exitosamente!.");
        }
        else{
            window.alert(resultado.message);
        }
    }else{
        window.alert("Por favor, selecciona primero un proveedor");
    }


}

async function eliminarProveedor() {
    const form = document.querySelector("form")
    event.preventDefault();
    const formData = new FormData(form);
    let nit = document.getElementById("lista-proveedores1").value;
    if (nit!="DEFAULT"){
        var r = window.confirm("¿Desea ejecutar la eliminación?");
        if (r == true){
            let response = await fetch(`./api/proveedor/eliminar/${nit}`,{
                method: 'PUT',
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                }
            });
            let resultado = await response.json();
            console.log(resultado);
            if (resultado.errorCode == 200){
                window.alert("¡Proveedor eliminado exitosamente!");
            }
            else{
                window.alert(resultado.message);
            }
        }
    }else{
        window.alert("Por favor, selecciona primero un proveedor.")
    }

}

async function insertarProveedor(){
    event.preventDefault();
    const form = document.getElementById("form-registrar");
    const formData = new FormData(form);
    let nit = formData.get("nit")
    console.log(nit);
    var r = window.confirm("¿Desea ejecutar este ingreso?");
    if(r == true){
        let response = await fetch(`./api/proveedor/agregar/${nit}`,{
            method: 'POST',
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: new URLSearchParams(formData),
        });
        let resultado = await response.json();
        console.log(resultado);
        if (resultado.errorCode == 200){
            window.alert("Proveedor: "+formData.get("name")+", ¡editado exitosamente!.");
        }
        else{
            window.alert(resultado.message);
        }
    }
}

async function consultarProveedor(){
    event.preventDefault();
    let nit_proveedor = document.getElementById("lista-proveedores").value;
    console.log(nit_proveedor);
    if (nit_proveedor!="DEFAULT"){
        let response = await fetch(`./api/proveedor/${nit_proveedor}`, {
            method: 'GET',
            Headers: {

                "Content-Type": "APPLICATION_JSON",
            }
        });
        let resultado = await response.json();
        console.log(resultado);
        $("#nit_lista").text("NIT: "+resultado.nit);
        $("#nombre_list").text("NOMBRE: "+ resultado.nombre);
        $("#direccion_list").text("DIRECCIÓN: "+resultado.direccion);
        $("#correo_list").text("CORREO: "+resultado.correo);
        $("#telefono_list").text("TELEFONO: "+resultado.telefono);
    }else{
        window.alert("Por favor, primero selecciona un proveedor");
    }
}