# FinishLanderTeam
Integrantes: Fabian Carreras, Joan Socias, Andres Rivero.

# Luna Lander Version 0.0
enlace: https://github.com/urbinapro/lunar-landing-javascript

La version 0.0 del videojuego hace referencia a todo el codigo desde que partimos para el desarrollo de la aplicacion.
Este código consta de varios de archivos:
 
 Documentos HTML:
  
     index.html : es la página principal del juego y contiene todos los objetos que lo conforman.
    
  Documentos CSS:
  
 	    d.css: es la hoja de estilo importada al archivo index.html siempre y cuando el ancho de la pantalla del ordenador supere los           961pxpx.
      
      m.css: es la hoja de estilo importada al archivo index.html siempre y cuando el ancho de la pantalla del ordenador esté por debajo de los 960px.
   Documento javascript:
      
      La funcion del archivo de javascript dar la animacion correspondiente a la nave en funcion de la velocidad y la altura



# Luna Lander Version 1.0

Tareas a desarrollar:

* Incorporar las diferentes imágenes al proyecto: Naves, Luna, fondo y las diferentes herramientas de menu.
* Dificultad del juego: Diferentes modos de juego.
* Menú: Diferentes menus según las pantallas.
* Instrucciones del juego


### Aportaciones al HTML:
* UTF-8
* Head: descripción incluida en el meta. En la instancia al CSS,  
* Body
* Menu instrucciones: Muestra las instrucciones
* Menu opciones: Muestra las opciones y permite cambiarlas.
* Menu about: Muestra el About

### Aportaciones al CSS
* Discriminación entre PC y móvil según el tamaño de la pantalla.
* Estilos menús (Pausa, Instrucciones, Opciones y About): En el móvil, ocupan un 100% de la pantalla, en el ordenador aparecen centrados en el centro.

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

# Luna Lander Version 2.0
 Tareas a desarrollar : 

* Añadiremos un Login y un SignUp.
* Crearemos una Base de Datos.

## SignUp

Registrar Usuario:
 
 Se hace una peticion al Servelt "Register" y se envian los diferentes parametros :
  
   * name : nombre real del usuario
   * username: nombre con el que el usuario quiere que se le identifique en el juego.
   * password : contraseña del usuario.
  
  Si el registro es un exito el Servlet devuelve el mensaje "Usuario registrado correctamente".
  
  Si el usuario existe el Servlet devuelve el mensaje "Usuario ya está regitrado".
  
  ## Login
  
  Iniciar Session: El usuario ha de estar previamente registrado para poder hacer Login. 
  
  Se hace una peticion al Servelt "Login" y comprobamos los parametros "Username" y "Password" :
   
    * Username: nombre del usuario.
    * Password: contraseña con el que el usuario se ha registrado.
    
   ## Base de Datos
   
   La base de datos del videojuego se compone de tres tablas relacionadas:
     
     * Tabla **user** : En esta tabla se almacenará los datos de los usuarios registrados.
     * Tabla **configuration** : En esta tabla se guaradaran las diferentes configuraciones por usuario.
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



# Luna Lander Version 3.0

  Tareas a desarrollar : 

   * Configuracion
   
   En esta version desarrollaremos toda la etapa de configuracion del juego. Esta etapa está compuesta por principalmente por dos apatartados que son:
   * Guardar configuracion del usuario.
   * Cargar configuracion del usuario.
  
   
   #### Guardar Configuracion ####
   
   Para guardar la configuracion del usuario emplearemos el servlet "guardarConfiguracion"
   
   mostrar los parametros y metodos con los que trabajamos
   
   #### Cargar Configuracion #### 
   
   Para cargar la configuracion del usuario emplearemos el servlet "cargarConfiguracion"
   
    mostrar los parametros y metodos con los que trabajamos
   
  
   
   # Luna Lander Version 4.0
   
   Score
   
   
   # Luna Lander Version 5.0
   
   frontend
