<%-- 
    Document   : game
    Created on : 22-nov-2017, 19:05:00
    Author     : dam2a07
--%>


<!DOCTYPE html>
<html>
    <head>
        <title>Lunar Lander</title>
        <meta charset="UTF-8">
        <meta name="description" content="Minijuego Lunar Lander con html, css, y JavaScript">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel='stylesheet' media='screen and (min-width: 601px)' href='css/responsive.css'>
        <link rel='stylesheet' media='screen and (max-width: 600px)' href='css/smartphone.css'>
        <script src="jsjuego.js"></script>



    </head>

    <body id="contenedor">

        <div id="nave">
            <img id ="imgNave" src="img/nave.png" alt="img nave">
            <img id ="imgMotor" src="img/motor.gif" alt="img motor">
        </div>

        <div id="izquierda">
            <div id="cpanel">
                <div class="controlesNave">Velocidad:<br><b><span id="velocidad">100</span></b> m/s</div>
                <div class="controlesNave">Fuel:<br><b><span id="fuel">100</span></b> %</div>
                <div class="controlesNave">Altura:<br><b><span id="altura">60</span></b> m</div>
                <div id="botonOn"></div>
            </div>
        </div>

        <div id="zonaAterrizaje"><img src="img/luna.png" alt="luna" id="luna"></div>

        <div id="derechaSmartphone">
            <div id="reanudaSmartphone"><p>Play</p></div>
            <div id="pausaSmartphone">Menú</div>
            <div id="reiniciaSmartphone"><p>Reiniciar</p></div>
            <div id="ayudaSmartphone"><p>Ayuda</p></div>
            <div id="botonAjustesSmartphone"><p>Ajustes</p></div>
        </div>

        <div id="derecha">
            <div id="reanudar"></div>
            <div id="pausa"></div>
            <div id="reinicia"></div>
            <div id="instrucciones"></div>
            <div id="botonAjustes"></div>
        </div>

        <div id="gameOver">
            <h2>¡¡¡GAME OVER!!!</h2>
            ¡Inténtalo de nuevo! <br>
            La velocidad de la nave no debe superar los 5 metros/segundo<br><br>
            <button id="jugarOtraVez"><h3>Probar otra vez</h3></button>
            <button id="jugarOtraVezSmartphone"><h3>Probar otra vez</h3></button>
            <br><br>

            ¿Guardar puntuacion?<br><br>
            <button id="siGuardar"><h3>Si</h3></button>
            <button id="noGuardar"><h3>No</h3></button>

            <br><br>
            Intentos realizados: <b><span id="intentos">0</span></b><br><br>
        </div>

        <div id="userWin">
            <h2>¡¡¡ENHORABUENA!!!</h2>
            La NASA estaría orgullosa de contar con pilotos como tú...<br><br>
            <button id="jugarAgain"><h3>Jugar otra vez</h3></button>
            <button id="jugarAgainSmartphone"><h3>Jugar otra vez</h3></button><br><br>
            ¿Guardar puntuacion?<br><br>
            <button id="siGuardar"><h3>Si</h3></button>
            <button id="noGuardar"><h3>No</h3></button>
        </div>

        <div id="menuInstrucciones">
            <a href="#" onclick="ocultarInstrucciones();"><img id="close" src="img/close.png" alt="close"></a>
            <h3>INSTRUCCIONES</h3>
            <p>El juego consiste en frenar la caída de la nave mediante el uso del motor, utiliza la tecla <b>&nbsp;&nbsp;espacio&nbsp;&nbsp;</b> o el botón <b>ON</b> para la version smartphone, para que esta pueda aterrizar adecuadamente sobre la superficie lunar.<br><br>Si el jugador no frena lo suficientemente la caída de la nave ,a una <b>velocidad inferior a los 5 m/s</b>, esta se estrellará y el jugador no superará el juego.<br><br> Además hay que tener en cuenta que la nave cuenta con un medidor de gasolina que se acabará si el jugador abusa del uso del motor de la nave.</p>
            <a href="acerca.html"><button><h3>Acerca de...</h3></button></a>
        </div>

        <div id="settings">

            <a href="#" onclick="ocultarAjustes();"><img id="close" src="img/close.png" alt="close"></a>
            <h3>AJUSTES</h3>

            <p> Dificultad</p> 
            <select id="dificultad">

            </select>




            <br><br>
            <p>Modelo Luna</p>
            <select id="modeloLuna">


            </select> <br><br>

            <p>Modelo Nave</p>
            <select id="modeloNave">

            </select> <br><br>


            <button id="guardar">Guardar</button>  <br><br>


            <select id="configs">
            </select> <br><br>


            <button id="cargo">Cargar</button>  <br><br>


        </div

    </body>
</html>
