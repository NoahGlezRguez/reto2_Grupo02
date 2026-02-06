/**
 * esta función pide como parámetros un mensaje el cual se va a escribir en un alert
 * @param {*} mesg 
 */
function winale(mesg){
    alert(mesg);
}

/**
 * Esta función pide como parámetros una página y te redirige a ella
 * @param {*} String 
 */
function cambiarPagina(plc){
    window.location=plc;
}

/**
 * Este método pide como parámetros el id y el mensaje 
 * a 
 * @param {*} id 
 * @param {*} msg 
 */
function anadirMensaje(id, msg){
    document.getElementById(id).innerHTML = msg;
}

/**
 * esta funciín muestra en la consola las veces que se le hace click a un elemento 
 * @param {*} id 
 */
function contarClicks(id) {
    
    let contador = 0;
    document.getElementById(id) .addEventListener("click", function() {
        contador++;
        console.log("Clicks:", contador);
    });
}
