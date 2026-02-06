<?php
session_start();
# cambiar: servidor, puerto, password
$db_server = '127.0.0.1';
$db_port = 3306;
$db_user_name = 'root';
$db_password = '10759148';
$db_name = 'cine_elorrieta';


// Conectar
$conn = new mysqli($db_server, $db_user_name, $db_password, $db_name, $db_port);
//https://www.php.net/manual/es/mysqli.construct.php


// Comprobar conexión
if ($conn->connect_error) {
    error_log('Connection error: ' . $conn->connect_error);
    die("Fallo en la conexión: " . $conn->connect_error);
}

// recoger datos del formulario de login aplicando la condición de que se ha pulsado el botón de iniciar sesión

if (isset($_POST['iniciar_sesion'])) {
    // Recuperar datos del formulario
    $usuario = $_POST['user'];
    $contrasena = md5($_POST['pass'], false);


    // Consulta SQL
    $sql = "SELECT * FROM Cliente WHERE DNI = '$usuario' AND userpassword = '$contrasena'";

    // Ejecutar consulta
    $result = $conn->query($sql);

    // Comprobar si el usuario existe
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $_SESSION['nombre'] = $row['NomCli'];
            $_SESSION['dni']= $row['DNI'];
            // Guardaremos el nombre del usuario para mostrarlo en el encabezado más tarde.
        }
        // Si hemos encontrado el registro, iniciamos sesión y procedemos: vamos a la pagina de seleccion de cine
        header("Location: ../cartelera.php");
        exit();
        
    } else {
        // Si no existe, mensaje de error (lo resuelvo mediante url)
        // header("Location: ../index.html?errorea=1");
        echo "<script> window.alert('Usuario y/o contraseña incorrectos'); 
        window.location='../login.php';</script>";
        exit();
    }
}

?>