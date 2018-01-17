/* global url, ff, fff, ffff, emess */

var y = 5; // altura inicial y0=10%, debe leerse al iniciar si queremos que tenga alturas diferentes dependiendo del dispositivo
var v = 0;
var g = 1.622;
var a = g; //a= -g es para motor encendido
var dt = 0.016683;
var timer;
var gasolina = 100;
var configurations = [];
var gasolinaTotal = 100;
var intentos = 1;
var timerFuel = null;
var configXML = {id: " ", dificultad: " ", modeloLuna: " ", modeloNave: " "};
var emess = "Error desconoido";
var userName;


var dificultad = 1;
var modeloLuna = 1;
var modeloNave = 1;

var tiempoInicio = 0;
var tiempoFinal = 0;

window.onload = function arrancarJuego() {
//CAPTURA SI EL DISPOSITIVO RECIBE EVENTOS OUNTOUCH (SMARTPHONE)
    function is_touch_device() {
        if ('ontouchstart' in window) {
            document.getElementById("botonOn").style.display = "inline-block";
        }
    }
    is_touch_device();
    //CAPTURANDO EVENTOS DEL PANEL DERECHA
    document.getElementById("reanudar").onclick = function () {
        reanudar();
    };
    document.getElementById("pausa").onclick = function () {
        pausar();
    };
    document.getElementById("reinicia").onclick = function () {
        reiniciarJuego();
    };
    document.getElementById("instrucciones").onclick = function () {
        mostrarInstrucciones();
    };
    document.getElementById("botonScores").onclick = function () {
        mostrarScores();
    };
    document.getElementById("botonJug").onclick = function () {
        mostrarJug();
    };
    document.getElementById("botonAjustes").onclick = function () {
        mostrarAjustes();
    };
    //CAPTURANDO EVENTOS PARA EL PANEL DERECHO EN SMARTPHONE
    document.getElementById("reanudaSmartphone").onclick = function () {
        reanudarSmartphone();
    };
    document.getElementById("pausaSmartphone").onclick = function () {
        pausarSmartphone();
    };
    document.getElementById("reiniciaSmartphone").onclick = function () {
        reiniciarJuegoSmartphone();
    };
    document.getElementById("ayudaSmartphone").onclick = function () {
        mostrarInstruccionesSmartphone();
    };
    document.getElementById("botonScoresSmartphone").onclick = function () {
        mostrarJugSmartphone();
    };
    document.getElementById("botonJugSmartphone").onclick = function () {
        mostrarScoresSmartphone();
    };
    document.getElementById("botonAjustesSmartphone").onclick = function () {
        mostrarAjustesSmartphone();
    };
    //EVENTOS DE FIN DEL JUEGO
    document.getElementById("jugarOtraVez").onclick = function () {
        reiniciarJuego();
    };
    document.getElementById("jugarOtraVezSmartphone").onclick = function () {
        reiniciarJuegoSmartphone();
    };
    document.getElementById("jugarAgain").onclick = function () {
        reiniciarJuego();
    };
    document.getElementById("jugarAgainSmartphone").onclick = function () {
        reiniciarJuegoSmartphone();
    };
    document.getElementById("siGuardar").onclick = function () {
        setPuntos();
        reiniciarJuego();
    };
    document.getElementById("noGuardar").onclick = function () {
        reiniciarJuego();
    };



    var ad = document.getElementById('dificultad');
    var al = document.getElementById('modeloLuna');
    var an = document.getElementById('modeloNave');

    var add = ["facil", "media", "dificil"];
    var all = ["gris", "azul"];
    var ann = ["roja", "azul"];


    $(function () {
        for (var i = 0; i < add.length; i++) {
            ad.options[i] = new Option(add[i]);
        }
        for (var j = 0; j < all.length; j++) {
            al.options[j] = new Option(all[j]);
            an.options[j] = new Option(ann[j]);
        }


    });


    //Guardar configuraciones
    $("#guardar").click(function () {
        var dificultad = document.getElementById("dificultad").selectedIndex + 1;
        var modeloLuna = document.getElementById("modeloLuna").selectedIndex + 1;
        var modeloNave = document.getElementById("modeloNave").selectedIndex + 1;
        var emess = "Error";
        var username = sessionStorage.getItem("_userN");
        console.log(username);
        $.ajax({
            type: "POST",
            url: "Guardar", //canviar al Servlet després de comprovar que funciona.
            data: {username: username, dificultad: dificultad, modeloLuna: modeloLuna, modeloNave: modeloNave},
            success: function (u) {
                if (u["mess"] === "Error al guardar configuraciones") {
                    alert(u["mess"]);
                    return;
                }

            },
            error: function (e) {

                if (e["responseJSON"] === undefined)
                    alert(emess);
                else
                    alert(e["responseJSON"]["error"]);
            }
        });

    });

    $(function () {
        var emess = "Error desconocido";
        var $select = $('#configs');
        var username = sessionStorage.getItem("_userN");

        $.ajax({
            type: "POST",
            url: "Cargar",
            dataType: "json",
            data: {username: username},
            success: function (jsn) {
                $.each(jsn, function (i) {
                    var id = jsn[i].id;
                    $('#configs').append($('<option>', {
                        value: id,
                        text: "Configuracion: " + (i + 1)}));
                });

            },
            error: function (e) {
                $select.html('<option id="-1">none available</option>');
                alert(e["responseJSON"]["error"]);
            }
        });

    });


    $("#cargo").click(function () {
        var emess = "Error desconocido";
        var $select = $('#configs').val();
        var username = sessionStorage.getItem("_userN");

        $.ajax({
            type: "POST",
            url: "Cargar",
            dataType: "json",
            data: {username: username},
            success: function (jsn) {
                $.each(jsn, function (i) {
                    if ($select == jsn[i].id) {
                        dificultad = jsn[i].difId;
                        modeloLuna = jsn[i].lunaId;
                        modeloNave = jsn[i].naveId;

                        cambiarDificultad(dificultad);
                        cambiarLuna(modeloLuna);
                        cambiarNave(modeloNave);
                        restart();
                    }

                });

            },
            error: function (e) {
                $select.html('<option id="-1">none available</option>');
                alert(e["responseJSON"]["error"]);
            }
        });

    });

    $("#modeloLuna").change(function () {
        modeloLuna = document.getElementById("modeloLuna").selectedIndex + 1;
        cambiarLuna(modeloLuna);
    });

    $("#dificultad").change(function () {
        dificultad = document.getElementById("dificultad").selectedIndex + 1;
        cambiarDificultad(dificultad);
    });
    $("#modeloNave").change(function () {
        modeloNave = document.getElementById("modeloNave").selectedIndex + 1;
        cambiarNave(modeloNave);
    });



    inicioPartida();
    //Empezar a mover nave
    start();
    //ASIGNAR EVENTOS TOUCH SCREEN PARA LA VERSION SMARTPHONE
    var botonOnSmartphone = document.getElementById("botonOn");
    botonOnSmartphone.addEventListener("touchstart", handlerFunction, false);
    botonOnSmartphone.addEventListener("touchend", endingFunction, false);
    function handlerFunction(event) {
        encenderMotor();
    }
    function endingFunction(event) {
        apagarMotor();
    }

//CON TECLADO (tecla ESPACIO)
    window.onkeydown = function (e) {
        var claveTecla;
        if (window.event)
            claveTecla = window.event.keyCode;
        else if (e)
            claveTecla = e.which;
        if ((claveTecla == 32))
        {
            encenderMotor();
        }
    }
    window.onkeyup = apagarMotor;
}//TERMINA EL WINDOW.ONLOAD


//FUNCION EMPEZAR EL JUEGO
function start() {

    timer = setInterval(function () {

        moverNave();
    }, dt * 1000);
}

//FUNCION PARAR NAVE Y CONTROLADORES
function stop() {
    clearInterval(timer);
}

//FUNCION PARA QUE CAIGA LA NAVE A TRAVES DE LA PANTALLA
function moverNave() {
    v += a * dt;
    document.getElementById("velocidad").innerHTML = v.toFixed(2);
    y += v * dt;
    document.getElementById("altura").innerHTML = y.toFixed(2);
    //mover hasta que top sea un 70% de la pantalla
    if (y < 70) {
        document.getElementById("nave").style.top = y + "%";
    } else {
        stop();
        finalizarJuego();
    }
}

//HACER QUE LOS DIVS IZQUIERDA Y DERECHA NO RECIBAN EVENTOS ONCLICK
function eventosOff() {
    document.getElementById("izquierda").style.pointerEvents = 'none';
    document.getElementById("derecha").style.pointerEvents = 'none';
}
//HACER QUE LOS DIVS IZQUIERDA Y DERECHA SI RECIBAN EVENTOS ONCLICK
function eventosOn() {
    document.getElementById("izquierda").style.pointerEvents = 'auto';
    document.getElementById("derecha").style.pointerEvents = 'auto';
}

//FUNCION PARA ACABAR EL JUEGO
function finalizarJuego() {
    finalPartida();

    if (v > 5) {
        switch (modeloNave) {
            case 1:
                eventosOff();
                document.getElementById("imgNave").src = "img/nave_rota.gif";
                document.getElementById("gameOver").style.display = "block";
                document.getElementById("intentos").innerHTML = intentos;

                break;
            case 2:
                eventosOff();
                document.getElementById("imgNave").src = "img/mod2rota.gif";
                document.getElementById("gameOver").style.display = "block";
                document.getElementById("intentos").innerHTML = intentos;
                break;
        }
    } else {
        document.getElementById("userWin").style.display = "block";
        eventosOff();
    }


}

//FUNCION QUE ACTUA EN CUANTO SE ENCIENDE EL MOTOR
function encenderMotor() {
    a = -g;
    document.getElementById("fuel").innerHTML = porcentajeGasolina();
    document.getElementById("fuel").style.color = "rgb(" + 0 + (100 - porcentajeGasolina()) + "%, 0%, 0%)";
    document.getElementById("imgMotor").style.display = "block";
    if (timerFuel == null) {
        timerFuel = setInterval(function () {
            actualizarGasolina();
        }, 100);
    }
    if (gasolina <= 0) {
        apagarMotor();
        document.getElementById("fuel").innerHTML = 0;
    }
}

//CALCULO EL PORCENTAJE DE GASOLINA QUE QUEDA
function porcentajeGasolina() {
    var result = gasolina * 100 / gasolinaTotal;
    return result.toFixed(0);
}


//FUNCION QUE ACTUALIZA EL MARCADOR DE FUEL
function actualizarGasolina() {
    gasolina -= 1;
    document.getElementById("fuel").innerHTML = porcentajeGasolina();
    document.getElementById("fuel").style.color = "rgb(" + 0 + (100 - porcentajeGasolina()) + "%, 0%, 0%)";
    if (gasolina <= 0) {
        apagarMotor();
        document.getElementById("fuel").innerHTML = 0;
    }
}
//FUNCION QUE RESPONDE AL MOMENTO DE APAGAR EL MOTOR DE LA NAVE
function apagarMotor() {
    a = g;
    document.getElementById("imgMotor").style.display = "none";
    clearInterval(timerFuel);
    timerFuel = null;
}

function mostrarAjustes() {
    pausar();
    eventosOff();
    document.getElementById("settings").style.display = "block";
}
function ocultarAjustes() {
    document.getElementById("settings").style.display = "none";
    eventosOn();
}

function mostrarInstrucciones() {
    pausar();
    eventosOff();
    document.getElementById("menuInstrucciones").style.display = "block";
}

function mostrarScores() {
    pausar();
    eventosOff();
    document.getElementById("menuScores").style.display = "block";
}
function mostrarJug() {
    pausar();
    eventosOff();
    document.getElementById("menuJug").style.display = "block";
}

function ocultarInstrucciones() {
    document.getElementById("menuInstrucciones").style.display = "none";
    eventosOn();
}

function ocultarJug() {
    document.getElementById("menuJug").style.display = "none";
    eventosOn();
}

function ocultarScores() {
    document.getElementById("menuScores").style.display = "none";
    eventosOn();
}

function restart() {
    stop();
    y = 5;
    v = 0;
    g = 1.622;
    a = g;
    dt = 0.016683;
    gasolina = gasolinaTotal;
    document.getElementById("fuel").innerHTML = porcentajeGasolina();
    document.getElementById("fuel").style.color = "black";
}
//OJO COMPORTAMIENTO ESCRITORIO
function reiniciarJuego() {
    stop();
    document.getElementById("reanudar").style.display = "none";
    document.getElementById("pausa").style.display = "inline-block";
    intentos++;
    y = 5; // altura inicial y0=10%, debe leerse al iniciar si queremos que tenga alturas diferentes dependiendo del dispositivo
    v = 0;
    g = 1.622;
    a = g;
    dt = 0.016683;
    gasolina = gasolinaTotal;
    document.getElementById("fuel").innerHTML = porcentajeGasolina();
    document.getElementById("fuel").style.color = "black";
    reanudar();
    clearInterval(timer);
    start();
    eventosOn();
    document.getElementById("intentos").innerHTML = intentos;
    document.getElementById("gameOver").style.display = "none";
    document.getElementById("userWin").style.display = "none";
    if (modeloNave == 1) {
        document.getElementById("imgNave").src = "img/nave.png";
    } else {
        document.getElementById("imgNave").src = "img/mod2nave.gif";
    }
}

function reanudar() {
    stop();
    start();
    document.getElementById("reanudar").style.display = "none";
    document.getElementById("pausa").style.display = "inline-block";
}
function pausar() {
    stop();
    document.getElementById("pausa").style.display = "none";
    document.getElementById("reanudar").style.display = "inline-block";
}

//OJO COMPORTAMIENTO SMARTPHONE
function reanudarSmartphone() {
    start();
    document.getElementById("reanudaSmartphone").style.display = "none";
    document.getElementById("pausaSmartphone").style.display = "inline-block";
    document.getElementById("reiniciaSmartphone").style.display = "none";
    document.getElementById("ayudaSmartphone").style.display = "none";
    document.getElementById("botonAjustesSmartphone").style.display = "none";
    document.getElementById('izquierda').style.display = "inline-block";
    document.getElementById('nave').style.display = "inline-block";
    document.getElementById('zonaAterrizaje').style.display = "inline-block";
    document.getElementById('derechaSmartphone').style.backgroundImage = 'url(img/sol.png)';
    document.getElementById('derechaSmartphone').style.backgroundSize = '60%';
    document.getElementById('derechaSmartphone').style.backgroundRepeat = 'no-repeat';
    document.getElementById('derechaSmartphone').style.width = '35%';
}

function pausarSmartphone() {
    stop();
    document.getElementById("pausaSmartphone").style.display = "none";
    document.getElementById("reanudaSmartphone").style.display = "inline-block";
    document.getElementById("reiniciaSmartphone").style.display = "inline-block";
    document.getElementById("ayudaSmartphone").style.display = "inline-block";
    document.getElementById("botonAjustesSmartphone").style.display = "inline-block";
    document.getElementById('derechaSmartphone').style.backgroundImage = 'url(img/fondo_menu.jpg)';
    document.getElementById('derechaSmartphone').style.backgroundSize = 'auto';
    document.getElementById('derechaSmartphone').style.backgroundRepeat = 'repeat';
    document.getElementById('derechaSmartphone').style.width = '100%';
}

function reiniciarJuegoSmartphone() {
    stop();
    intentos++;
    y = 5; // altura inicial y0=10%, debe leerse al iniciar si queremos que tenga alturas diferentes dependiendo del dispositivo
    v = 0;
    g = 1.622;
    a = g;
    dt = 0.016683;
    gasolina = gasolinaTotal;
    document.getElementById("fuel").innerHTML = porcentajeGasolina();
    document.getElementById("fuel").style.color = "black";
    reanudarSmartphone();
    clearInterval(timer);
    start();
    eventosOn();
    document.getElementById("intentos").innerHTML = intentos;
    document.getElementById("gameOver").style.display = "none";
    document.getElementById("userWin").style.display = "none";
    if (modeloNave == 1) {
        document.getElementById("imgNave").src = "img/nave.png";
    } else {
        document.getElementById("imgNave").src = "img/mod2nave.gif";
    }
}

function mostrarAjustesSmartphone() {
    pausarSmartphone();
    document.getElementById("settings").style.display = "block";
}

function mostrarInstruccionesSmartphone() {
    pausarSmartphone();
    document.getElementById("menuInstrucciones").style.display = "block";
}

function mostrarScoresSmartphone() {
    pausarSmartphone();
    document.getElementById("menuScores").style.display = "block";
}

function mostrarJugSmartphone() {
    pausarSmartphone();
    document.getElementById("menuJug").style.display = "block";
}

function cambiarDificultad(dificultad) {
    switch (dificultad) {
        case 1:
            gasolina = 100;
            gasolinaTotal = 100;
            document.getElementById("dificultad").selectedIndex = "0";
            dificultad = 1
            restart();
            break;
        case 2:
            gasolina = 50;
            gasolinaTotal = 50;
            document.getElementById("dificultad").selectedIndex = "1";
            dificultad = 2
            restart();
            break;
        case 3:
            gasolina = 25;
            gasolinaTotal = 35;
            document.getElementById("dificultad").selectedIndex = "2";
            dificultad = 3
            restart();
            break;
    }

}

function cambiarLuna(modeloLuna) {
    switch (modeloLuna) {
        case 1:
            document.getElementById("luna").src = "img/luna.png";
            document.getElementById("modeloLuna").selectedIndex = "0";
            modeloLuna = 1;

            restart();
            break;
        case 2:
            document.getElementById("luna").src = "img/mod2luna.png";
            document.getElementById("modeloLuna").selectedIndex = "1";
            ;
            modeloLuna = 2;
            break;
    }

}

function cambiarNave(modeloNave) {
    switch (modeloNave) {
        case 1:
            document.getElementById("imgNave").src = "img/nave.png";
            document.getElementById("imgMotor").src = "img/motor.gif";
            document.getElementById("modeloNave").selectedIndex = "0";
            modeloNave = 2;
            restart();
            break;
        case 2:
            document.getElementById("imgNave").src = "img/mod2nave.gif";
            document.getElementById("imgMotor").src = "img/mod2motor.gif";
            document.getElementById("modeloNave").selectedIndex = "1";
            modeloNave = 1;
            restart();
            break;

    }

}
function inicioPartida() {

    var today = new Date();
    var mm = today.getMinutes();
    var ss = today.getSeconds();
    var time = mm + ":" + ss;
    tiempoInicio = time;

}

function finalPartida() {

    var today = new Date();
    var mm = today.getMinutes();
    var ss = today.getSeconds();
    var time = mm + ":" + ss;
    tiempoFinal = time;

}


function setPuntos() {
    var conf_id = $('#configs').val();
    var speed = document.getElementById("velocidad").textContent;
    var fuel = document.getElementById("fuel").textContent;
    var iniTime = tiempoInicio;
    var endTime = tiempoFinal;
    $.ajax({
        type: "POST",
        url: "GuardarScore",
        data: {conf_id: conf_id, speed: speed, fuel: fuel, iniTime: iniTime, endTime: endTime},
        success: function (u) {
            if (u["mess"] === "Error al guardar configuraciones") {
                alert(u["mess"]);
                return;
            }


        },
        error: function (e) {

            if (e["responseJSON"] === undefined)
                alert(emess);
            else
                alert(e["responseJSON"]["error"]);
        }
    });

}

