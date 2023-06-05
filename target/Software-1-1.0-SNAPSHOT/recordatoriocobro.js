async function actualizarCobrosPendientes() {
    event.preventDefault();
    try {
        let response = await fetch(`./api/sells/`, {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
            }
        });

        if (response.ok) {
            const data = await response.json();
            let html = '';

            data.forEach((registro) => {

                const idventa = registro.id_venta;
                const cliente = registro.cliente;
                const total = registro.total;
                const fechaPago = registro.fecha_pago;
                const stringCompleto = `Factura:${idventa} Cliente: ${cliente}  Total: ${total}  Fecha óptima de pago: ${fechaPago}`;

                html += `<div class="item">
                            <input type="checkbox" onclick="actualizarVenta(event, '${idventa}')" onclick="event.stopPropagation()">
                            <span>${stringCompleto}</span>
                         </div>`;

            });

            const contenedor = document.getElementById('contenedor');
            contenedor.innerHTML = html;
        } else {
            console.log('Error en la respuesta de la petición');
        }
    } catch (error) {
        console.log(error);
    }
}

async function actualizarVenta(event, idVenta) {
    event.stopPropagation();
    console.log(idVenta)
    try {
        let response = await fetch(`./api/sells/${idVenta}`, {
            method: 'PUT',
            headers: {

                "Content-Type": "application/json",
            },

        });
        let respuesta = await response.json();
        if (respuesta.errorCode == 200) {
            window.alert("Cliente:  ¡editado exitosamente!.");
            window.location.reload();
        } else {
            window.alert(resultado.message);
        }
    } catch (r) {
        alert("Server Error: " + respuesta);
        console.log(r + "!error.!");
    }

}