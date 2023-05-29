// Selección de elementos del DOM
const mainTabs = document.querySelector(".main-tabs");
const mainSliderCircle = document.querySelector(".main-slider-circle");
const roundButtons = document.querySelectorAll(".round-button");


// Obtener el botón de persona y el combobox
const personButton = document.querySelector('.person-button');
const combobox = document.querySelector('.combobox');
const selectOption = document.getElementById('select-option');

// Agregar evento de clic al botón de persona
personButton.addEventListener('click', function() {
  // Alternar la visibilidad del combobox al hacer clic en el botón de persona
  if (combobox.classList.contains('show')) {
    combobox.classList.remove('show');
  } else {
    combobox.classList.add('show');
  }
});

// Agregar controlador de eventos al documento para ocultar el combobox al hacer clic fuera de él
document.addEventListener('click', function(event) {
  const targetElement = event.target;
  if (!targetElement.matches('.person-button') && !targetElement.closest('.combobox')) {
    // El clic ocurrió fuera del botón de persona y del combobox, ocultar el combobox
    combobox.classList.remove('show');
  }
});





// Definición de colores para diferentes variantes
const colors = {
  blue: {
    50: {
      value: "#e3f2fd"
    },
    100: {
      value: "#bbdefb"
    }
  },
  purple: {
    50: {
      value: "#f3e5f5"
    },
    100: {
      value: "#e1bee7"
    }
  },
  green: {
    50: {
      value: "#cbffcb"
    },
    100: {
      value: "#23a340"
    }
  },
  pink: {
    50: {
      value: "#ffebee"
    },
    100: {
      value: "#ffcdd2"
    },
    // Cambio de color a #ff7ea8
    200: {
      value: "#ff7ea8"
    }
  }
  
};

// Función para obtener el valor de color de acuerdo a la variante
const getColor = (color, variant) => {
  return colors[color][variant].value;
};

// Función para manejar la pestaña activa
const handleActiveTab = (tabs, event, className) => {
  tabs.forEach((tab) => {
    tab.classList.remove(className);
  });

  if (!event.target.classList.contains(className)) {
    event.target.classList.add(className);
  }
};

// Evento click para las pestañas principales
mainTabs.addEventListener("click", (event) => {
  const root = document.documentElement;
  const targetColor = event.target.dataset.color;
  const targetTranslateValue = event.target.dataset.translateValue;

  if (event.target.classList.contains("round-button")) {
    // Remover y agregar clases para animar el círculo principal
    mainSliderCircle.classList.remove("animate-jello");
    void mainSliderCircle.offsetWidth;
    mainSliderCircle.classList.add("animate-jello");

    // Actualizar las variables CSS con los valores seleccionados
    root.style.setProperty("--translate-main-slider", targetTranslateValue);
    root.style.setProperty("--main-slider-color", getColor(targetColor, 50));

    // Manejar la pestaña activa de los botones redondos
    handleActiveTab(roundButtons, event, "active");

    // Mostrar u ocultar los contenedores de filtros según el botón seleccionado
    if (!event.target.classList.contains("gallery")) {
      root.style.setProperty("--filters-container-height", "0");
      root.style.setProperty("--filters-wrapper-opacity", "0");
    } else {
      root.style.setProperty("--filters-container-height", "3.8rem");
      root.style.setProperty("--filters-wrapper-opacity", "1");
    }

    // Verificar si se seleccionó la campana
    if (event.target.classList.contains("campana")) {
      // Redireccionar a "Recordatorio.html" después de la animación
      setTimeout(() => {
        window.location.href = "Recordatorio.html";
      }, 500); // Cambia el tiempo (en milisegundos) a tu preferencia
    }

    // Verificar si se seleccionó el cuaderno
    if (event.target.classList.contains("inventario")) {
      // Redireccionar a "Inventario.html" después de la animación
      setTimeout(() => {
        window.location.href = "Inventario.html";
      }, 500); // Cambia el tiempo (en milisegundos) a tu preferencia
    }
  }
});


// Selección de elementos del DOM
const filterTabs = document.querySelector(".filter-tabs");
const filterButtons = document.querySelectorAll(".filter-button");

// Evento click para las pestañas de filtros
filterTabs.addEventListener("click", (event) => {
  const root = document.documentElement;
  const targetTranslateValue = event.target.dataset.translateValue;

  if (event.target.classList.contains("filter-button")) {
    // Actualizar la posición del control deslizante de filtros
    root.style.setProperty("--translate-filters-slider", targetTranslateValue);

    // Manejar la pestaña activa de los botones de filtro
    handleActiveTab(filterButtons, event, "filter-active");
  }
});
