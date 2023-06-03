async function registrar(){
    event.preventDefault();
    const form = document.getElementById("form-register");
    const formData = new FormData(form);
    let username = formData.get("Username");

    let response = await fetch(`./api/users/${username}`,{
        method: 'POST',

        //El consumed es el header, ya que es lo que recibe como la url codificada
        headers:{
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams(formData),
    });

    let resultado = await response.json();
    if (resultado.errorCode ==200){
        window.alert("Usuario se ha generado exitosamente")
        window.location.href="http://localhost:8080/Software-1-1.0-SNAPSHOT/index.html";
    }
    else {
        window.alert(resultado.message);
        window.location.href="http://localhost:8080/Software-1-1.0-SNAPSHOT/index.html";
    }
}