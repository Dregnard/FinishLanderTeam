# FinishLanderTeam
Integrantes: Fabian Carreras, Joan Socias, Andres Rivero.

# Luna Lander 
## Version 0.0
enlace: https://github.com/urbinapro/lunar-landing-javascript

La versión 0.0 del videojuego hace referencia al código desde que partimos para el desarrollo de la aplicación.
Este código consta de varios de archivos:
 
 **Documentos HTML:**
  
     index.html : es la página principal del juego y contiene todos los objetos que lo conforman.
    
  **Documentos CSS:**
  
 	    d.css: es la hoja de estilo importada al archivo index.html siempre y cuando el ancho de la pantalla del ordenador supere los           961pxpx.
      
      m.css: es la hoja de estilo importada al archivo index.html siempre y cuando el ancho de la pantalla del ordenador esté por debajo de los 960px.
   
   **Documentos javascript:**
      
       js.js: La función del archivo de javascript dar la animación correspondiente a la nave en función de la velocidad y la altura



# Luna Lander 
## Version 1.0

**Tareas a desarrollar:**

* Incorporar las diferentes imágenes al proyecto: Naves, Luna, fondo y las diferentes herramientas de menu.
* Dificultad del juego: Diferentes modos de juego.
* Menú: Diferentes menus según las pantallas.
* Instrucciones del juego.

### Aportaciones al CSS

* Discriminación entre PC y móvil según el tamaño de la pantalla.
* Estilos menús (Pausa, Instrucciones, Opciones y About): En el móvil, ocupan un 100% de la pantalla, en el ordenador aparecen centrados en el centro.

 **responisve.css**: es la hoja de estilo importada al archivo game.jsp siempre y cuando el ancho de la pantalla del ordenador supere los 601px.
      
 **smartphone.css**: es la hoja de estilo importada al archivo game.jsp siempre y cuando el ancho de la pantalla del ordenador esté por debajo de los 600px.
 
  **text.css** : es la hoja de estilo del login y el register.
  
  **acerca_responsive.css**: la hoja de estilo de la información del juego

### Aportaciones al JS:

* Version Móvil: La nave se mueve al poner el dedo en la pantalla (ontouch).
* Version PC: La nave se mueve SOLO al pulsar la barra espaciadora (no funciona con ninguna otra tecla).
* Mostrar/Ocultar los divs de los elementos del menú (instrucciones, opciones, about...):
* Móvil: Al estar en el menú principal y clicar sobre los botones nos lleva a los divs correspondientes. Cada div contiene un botón "Volver" que nos permite volver al menú principal.
* PC: Cada vez que se da clic en un elemento del menú superior, se cierran los otros y se abre el nuevo que queremos ver. 
* El Menu pausa el juego
* Nivel dificultad: Fácil, Medio y Difícil.
* Imagen Nave: La nave expulsará fuego de los motores cuando aceleremos y explotará si no aterrizamos adecuadamente.
* Lugar Aterrizaje: superficie Luna.

**jsjuego.js** : La función del archivo de javascript dar la animación correspondiente a la nave en funcion de la velocidad y la altura, así como el control de la misma siempre teniendo en cuenta la cantidad de fuel que hay en el despósito.

### Imagenes:
 
 En la carpeta **img** se encuentran todas las imagenes que intervienen en el desarrollo del juego.

# Luna Lander 
## Version 2.0
 Tareas a desarrollar : 

* Añadiremos un Login y un SignUp.
* Crearemos una Base de Datos.

### cambios en el HTML:
  
  **index.html** : contiene el login y el resgiter.
 
### Documento JSP
 
 **game.jsp**: es la página principal del juego y contiene todos los objetos que lo conforman.
     
     * cabecera JSP
     * UTF-8
     * Head: descripción incluida en el meta. En la instancia al CSS,  
     * Body
     * Menu instrucciones: Muestra las instrucciones
     * Menu opciones: Muestra las opciones y permite cambiarlas.
     * Menu about: Muestra el About
     
### Aportaciones al JS:
  
  **js.js**: La función del archivo de javascript es permitir al usario registrarse y hacer login en el juego.


### SignUp

Registrar Usuario:
 
 Se hace una petición al Servelt **"Register"** y se envian los diferentes parámetros :
  
   * name : nombre real del usuario
   * username: nombre con el que el usuario quiere que se le identifique en el juego.
   * password : contraseña del usuario.
  
  Si el registro es un éxito el Servlet devuelve el mensaje "Usuario añadido correctamente".
  
  Si el usuario existe el Servlet devuelve el mensaje "El usuario ya existe".
  
  Si ocurre una excepción, devolver un mensaje de error: "Error de registro"
  
  ### Login
  
  Iniciar Session: El usuario ha de estar previamente registrado para poder hacer Login. 
  
  Se hace una petición al Servelt **"Login"** y comprobamos los parametros "Username" y "Password" :
   
    * Username: nombre del usuario.
    * Password: contraseña con el que el usuario se ha registrado.
    * Si ocurre una excepción, devolver un mensaje de error: "Error en el login"
    
   ## AutoLogin
   
   Se hace una petición al Servelt **"AutoLogin"** y mediante el objeto cookies el usuario podría hacer auto login:
   
   **Parametros recibidos:**
   
    * name : nombre real del usuario
    * username: nombre con el que el usuario quiere que se le identifique en el juego.
    * password : contraseña del usuario.
    * Si ocurre una excepcion, devolver un mensaje.

    
   ### Base de Datos
   
   La base de datos del videojuego se compone de tres tablas relacionadas:
     
     * Tabla **user** : En esta tabla se almacenará los datos de los usuarios registrados.
     * Tabla **configuration** : En esta tabla se guaradarán las diferentes configuraciones por usuario.
     * Tabla **score** : En esta tabla se guardarán la puntuacion de cada usuario.
  
  #### Tabla user ####
  La tabla user consta de los siguientes campos:
     
     
| Nombre del Campo  |    Tipo     |Informacion  |
| -------------   |:-----------:|:------------:|
| id                |    int(11)      | Primary Key, Not Null, AI |
| nombre            |  varchar(10)    |         |
| username          |  varchar(15)    |        |
| password          | varchar(10)     |        |

#### Tabla configuration ####
  La tabla configuration consta de los siguientes campos:
     
     
| Nombre del Campo  |    Tipo     |Informacion  |
| -------------     |:-----------:|:------------:|
| id                |    int(11)  | Primary Key, Not Null, AI |
| user_id           |    int(11)  |  Not Null, reference user (id)  |
| dif_id            |    int(11)  |  Not Null      |
| nave_id           |    int(11)  |  Not Null      |
| luna_id           |    int(11)  |  Not Null      |

#### Tabla score ####
  La tabla score consta de los siguientes campos:
     
     
| Nombre del Campo  |    Tipo     |Informacion  |
| -------------     |:-----------:|:------------:|
| id                |    int(11)  | Primary Key, Not Null, AI |
| conf_id           |    int(11)  |  Not Null, reference configuration (id)  |
| speed             |    float  |  Not Null      |
| fuel              |    float  |  Not Null      |
| inTime            |    time   |  Not Null      |
| endTime           |    time   |  Not Null      |



# Luna Lander 
## Version 3.0

  Tareas a desarrollar : 

   * Configuración
   
   En esta version desarrollaremos toda la etapa de configuracion del juego. Esta etapa está compuesta por principalmente por dos apatartados que son:
   
   * Guardar configuración del usuario.
   * Cargar configuración del usuario.
  
   
   ### Guardar Configuracion ###
   
   Para guardar la configuracion del usuario emplearemos el metodo doPost() del servlet **"Guardar"**.
   
   **Parametros recibidos:**
   * username: nombre del usuario al cual se le va crear una nueva configuración.
   
   **Definicion del proceso**
   
    * Si el usuario no existe, devolver el mensaje: "error", "Usuario no encontrado"
    * Si existe, crear un String en formato json y devolver el mensaje: "mess", "Configuracion añadida"
    * Si ocurre una excepción, devolver el mensaje de error: error", "Error en configuraciones"

   
   ### Cargar Configuracion ### 
   
   Para cargar la configuración del usuario emplearemos el método doPost() del servlet **"Cargar"**. 
   
   **Parametros recibidos:**
   * username: nombre del usuario con el cual se desea obtener todas las configuraciones.
   
   **Definicion del proceso:**
   
    * Si el usuario no existe devuelve el mensaje de error "error", "User not found".
    * Si existe, devolver un String en formato json.
    * Si ocurre un a excepción, devolver el mensaje de error:  emess.put("error", e.toString());
    
   
   # Luna Lander 
   ## Version 4.0
   
   Tareas a desarrollar : 

   * Score
   
   En esta versión desarrollaremos la etapa de puntuacion del juego. Esta etapa está compuesta principalmente por el apatartado: 
   * Guardar puntuación del usuario.
   
 
  ### Guardar Score ###
  Para guardar la puntuación del usuario emplearemos el metodo doPost() del servlet **"guardarScore"**.
   
 **Definicion del proceso:**
   
    * La configuración la obtenemos a partir de los parámetros. 
    * Creamos el objeto "core" con la configuracion obtenida.
    * Realizamos un insert de la tabla Score de la base de datos.
    * Nos devuelve el mensaje: "Puntuacion añadida""
    * Si ocurre una excepción, devolver un mensaje de error.
   
  
