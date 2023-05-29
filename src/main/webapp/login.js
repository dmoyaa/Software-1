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
