$(".info-item .btn").click(function(){
  // Cuando se hace clic en cualquier botón dentro de un elemento con la clase "info-item"
  $(".container").toggleClass("log-in");
  // Se selecciona el elemento con la clase "container" y se agrega o elimina la clase "log-in" según su estado actual
});

$(".container-form .btn").click(function(){
  // Cuando se hace clic en cualquier botón dentro de un elemento con la clase "container-form"
  $(".container").addClass("active");
  // Se selecciona el elemento con la clase "container" y se le agrega la clase "active"
});


async function verificarIngreso() {

  let username = 'default';
  let password = 'password';
  console.log(username);
  username = document.getElementById("username-form").value;
  console.log(password);
  password = document.getElementById("password-form").value;
  console.log(username);
  console.log(password);
  sessionStorage.setItem('username', username);
  sessionStorage.setItem('password',password);
  let response = await fetch(`api/users/${username}`, {
    method: 'GET',
    Headers: {

      "Content-Type": "APPLICATION_JSON",
    }




  });
  let result =await response.json();
  console.log(result);



  if(result.password== document.getElementById("password-form").value){
    alert("Bienvenido a el sistema: "+username);
    window.location.href="http://localhost:8080/Software-1-1.0-SNAPSHOT/inicio.html";
  }else{
    alert("Usuario no encontrado o contraseña incorrecta");
    window.location.href="http://localhost:8080/Software-1-1.0-SNAPSHOT/index.html";

  }
}

async function registrar(){
  event.preventDefault();
  const form = document.getElementById("form-register");
  const formData = new FormData(form);
  let username = formData.get("Username");

  let response = await fetch(`./api/users/agregar/${username}`,{
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