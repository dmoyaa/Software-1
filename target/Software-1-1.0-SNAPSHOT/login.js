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
    window.location.href="http://localhost:8080/software-1.0-SNAPSHOT/inicio.html";
  }else{
    alert("Usuario no encontrado o contraseña incorrecta");

  }


}