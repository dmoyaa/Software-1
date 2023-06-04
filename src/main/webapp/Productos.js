async function listaProductos(){
    let desplegableConsultar = document.getElementById("referencia-consultar");
    let desplegableEliminar = document.getElementById("referencia-eliminar");
    let desplegableActualizar = document.getElementById("referencia-actualizar");
    let response = await fetch(`./api/productos`,{
        method:'GET',
        Headers: {

            "Content-Type": "APPLICATION_JSON",
        }
    })
    let result = await response.json();
    console.log(result);

    console.log("inicio blucle");

    desplegableActualizar.innerHTML = 'Seleccione un producto';
    desplegableEliminar.innerHTML='Seleccione un producto';
    desplegableConsultar.innerHTML = 'Seleccione un producto';
    let option = document.createElement('option');
    option.value = "DEFAULT";
    option.text = "SELECCIONE UN PRODUCTO";
    desplegableActualizar.appendChild(option);

    let option2 = document.createElement('option');
    option2.value = "DEFAULT";
    option2.text = "SELECCIONE UN PRODUCTO";
    desplegableEliminar.appendChild(option2);

    let option3 = document.createElement('option');
    option3.value = "DEFAULT";
    option3.text = "SELECCIONE UN PRODUCTO";
    desplegableConsultar.appendChild(option3);


    result.forEach(result => {
        console.log(result);
        let option = document.createElement('option');
        option.value = result.id_producto;
        option.text = result.nombre;
        desplegableActualizar.appendChild(option);
        let option2 = document.createElement('option');
        option2.value = result.id_producto;
        option2.text = result.nombre;
        desplegableEliminar.appendChild(option2);
        let option3 = document.createElement('option');
        option3.value = result.id_producto;
        option3.text = result.nombre;
        desplegableConsultar.appendChild(option3);
    });
}

async function cargarInformacion() {
    let id_producto = document.getElementById("referencia-actualizar").value;
    console.log(id_producto);
    let response = await fetch(`./api/productos/actualizar/{id_producto}`, {
        method: 'GET',
        Headers: {

            "Content-Type": "APPLICATION_JSON",
        }
    })
    let resultado = await response.json();
    console.log(resultado);
    $("#name_nombre").val(resultado.nombre);
    $("#stock").val(resultado.stock);
    $("#precio_venta2").val(resultado.precio_venta);
    $("#precio_compra").val(resultado.precio_compra);

}