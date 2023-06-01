const form = document.querySelector("form")

form.onsubmit= async (e) =>{
    e.preventDefault();

    try{
        let response = await fetch(`./api/cliente`, {
            method: 'GET',
            headers: {

                "Content-Type": "application/JSON",
            },

        });
        let result = await response.json();
        console.log(result);




    }catch (e) {
        console.log(e+" ERROR")
    }
}
