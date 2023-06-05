async function cargarUsuarios() {
    let desplegableActualizar = document.getElementById("lista-actualizar");
    let desplegableEliminar = document.getElementById("lista-eliminar");
    let desplegableConsultar = document.getElementById("lista-consultar");

    let response = await fetch(`./api/users`,{
        method:'GET',
        Headers: {

            "Content-Type": "APPLICATION_JSON",
        }
    })
    let result = await response.json();
    console.log(result);

    console.log("inicio blucle");

    desplegableActualizar.innerHTML = 'Seleccione un usuario';
    desplegableEliminar.innerHTML='Seleccione un usuario';
    desplegableConsultar.innerHTML = 'Seleccione un usuario';
    let option = document.createElement('option');
    option.value = "DEFAULT";
    option.text = "SELECCIONE UN USUARIO";
    desplegableActualizar.appendChild(option);

    let option2 = document.createElement('option');
    option2.value = "DEFAULT";
    option2.text = "SELECCIONE UN USUARIO";
    desplegableEliminar.appendChild(option2);

    let option3 = document.createElement('option');
    option3.value = "DEFAULT";
    option3.text = "SELECCIONE UN USUARIO";
    desplegableConsultar.appendChild(option3);


    result.forEach(result => {
        console.log(result);
        let option = document.createElement('option');
        option.value = result.login;

        desplegableActualizar.appendChild(option);
        let option2 = document.createElement('option');
        option2.value = result.login;

        desplegableEliminar.appendChild(option2);
        let option3 = document.createElement('option');
        option3.value = result.login;

        desplegableConsultar.appendChild(option3);
    });


}