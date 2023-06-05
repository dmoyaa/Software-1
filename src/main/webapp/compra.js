let total = 0;
let lista = new Array();
let lastSelectedR = document.getElementById("proveedores-registro");
let resumenCompra = new Array();

async function cargarProveedoresCompra(){

    let desplegableRegistrar = document.getElementById("proveedores-registro");
    let desplegableConsultar = document.getElementById("provedores-consultar");


    let response = await fetch(`./api/proveedor`,{
        method:'GET',
        Headers: {

            "Content-Type": "APPLICATION_JSON",
        }
    });

    let result = await response.json();
    console.log(result);
    console.log("inicio blucle");

    desplegableRegistrar.innerHTML = 'Seleccione un proveedor';
    desplegableConsultar.innerHTML = 'Seleccione un proveedor';

    let option = document.createElement('option');
    option.value = "DEFAULT";
    option.text = "SELECCIONE UN PROVEEDOR";
    desplegableConsultar.appendChild(option);

    let option2 = document.createElement('option');
    option2.value = "DEFAULT";
    option2.text = "SELECCIONE UN PROVEEDOR";
    desplegableRegistrar.appendChild(option2);

    result.forEach(result => {
        console.log(result);
        let option = document.createElement('option');
        option.value = result.nit;
        option.text = result.nombre;
        desplegableRegistrar.appendChild(option);
        console.log("VALUE ="+option.value)

        let option3 = document.createElement('option');
        option3.value = result.nit;
        option3.text = result.nombre;
        desplegableConsultar.appendChild(option3);
    });

}

function confirmarReenvio(){
    let select = document.getElementById("proveedores-registro");
    if(total != 0) {
        let confirm = window.confirm("¿Desea cambiar de proveedor? pederá el progreso de su venta actual");
        if (confirm == true) {
            total = 0;
            lista = new Array();
            resumenCompra = new Array();
            let label_total = document.getElementById("total_pagar");
            label_total.innerText = "Total a pagar";
            productosProveedor();
        }else{
            select = lastSelectedR;
        }
    }else{
        productosProveedor();
    }
}

async function productosProveedor(){

    lastSelectedR = document.getElementById("proveedores-registro");
    let desplegableRegistrar = document.getElementById("producto-proveedor");
    let nit =document.getElementById("proveedores-registro").value;
    console.log(nit);
    if (nit != "DEFAULT"){

        let response = await fetch(`./api/compra/${nit}`,{
            method:'GET',
            Headers: {

                "Content-Type": "APPLICATION_JSON",
            }
        });

        let resultado = await response.json();
        desplegableRegistrar.innerHTML="SELECCIONE UN PRODUCTO";
        let option = document.createElement('option');
        option.value = "DEFAULT";
        option.text = "SELECCIONE UN PRODUCTO";
        desplegableRegistrar.appendChild(option);


        resultado.forEach(result => {
            console.log(result);
            let option = document.createElement('option');
            option.value = result.id_producto;
            option.text = result.nombre;
            desplegableRegistrar.appendChild(option);
            lista.push(result)
        });

        lista.forEach( productos => {
            console.log(productos.precio_venta);
        });
    }
}

function totalizar(){
    let proveedor = document.getElementById("proveedores-registro").value;
    let producto =  document.getElementById("producto-proveedor");
    if (proveedor != "DEFAULT" && producto != "DEFAULT" ){
        let id = document.getElementById("producto-proveedor").value;
        let cantidad = document.getElementById("cantidad").value;
        
        lista.forEach( productos => {
            if (productos.id_producto == id){
                total = (total + (cantidad*productos.precio_compra));
                let prouctoAgregdo = {
                    "id_producto": productos.id_producto,
                    "nombre": productos.nombre,
                    "cantidad": cantidad,
                    "precio": (cantidad*productos.precio_compra)
                }
                resumenCompra.push(prouctoAgregdo);
            }
        });
        let label_total = document.getElementById("total_pagar");
        label_total.innerText = total;
    }else{
        window.alert("Por favor ingrese al menos proveedor, producto y cantidad");
    }

}

async function insertarCompra() {
    event.preventDefault();
    const form = document.getElementById("forminsertar");
    const formData = new FormData(form);
    let proveedor = formData.get("lista-proveedor");

    var checkboxContado = document.getElementById('estadoCheckbox1').checked;
    let pago;
    if (checkboxContado == true) {
        pago = "contado";
    } else {
        pago = "acredito";
    }

    let response = await fetch(`./api/compra/post/${proveedor}/${pago}`, {
        method: 'POST',
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: new URLSearchParams(formData),
    });

    let resultado = await response.json();
    console.log(resultado);
}


async function consultar(){
    event.preventDefault();
    event.preventDefault();
    const form = document.getElementById("consulta-form");
    const formData = new FormData(form);
    let fechaI = document.getElementById("fecha_inicio").value ;
    let fechaF = document.getElementById("fecha_final").value;
    console.log(fechaF);
    console.log(fechaI);

    let response = await fetch(`./api/compra/fecha`,{
        method : 'PUT',
        headers:{
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body : new URLSearchParams(formData),
    });

    let result = await response.json();
    console.log(result);

    if (result.errorCode == 200){
        window.alert("CONSULTA OK");
    }



}


