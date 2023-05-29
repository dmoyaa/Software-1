$(document).ready(function() {
    'use strict';
  
  
    // Comprobar si el navegador es Firefox y aplicar una clase adicional
    if (navigator.userAgent.toLowerCase().indexOf('firefox') > -1) {
      $('.form form label').addClass('fontSwitch');
    }
  
    // Manejador de eventos cuando se enfoca en un campo de entrada
    $('input').focus(function() {
      $(this).siblings('label').addClass('active');
    });
  
    

    // Manejador de eventos cuando se hace clic en el enlace de cambio de formulario
    $('a.switch').click(function(e) {
      $(this).toggleClass('active');
      e.preventDefault();
  
      if ($('a.switch').hasClass('active')) {
        $(this).parents('.form-peice').addClass('switched').siblings('.form-peice').removeClass('switched');
      } else {
        $(this).parents('.form-peice').removeClass('switched').siblings('.form-peice').addClass('switched');
      }
    });

    
    // Manejador de eventos cuando se hace clic en el enlace de perfil
    $('a.profile').on('click', function() {
      location.reload(true);
    });
  });
  