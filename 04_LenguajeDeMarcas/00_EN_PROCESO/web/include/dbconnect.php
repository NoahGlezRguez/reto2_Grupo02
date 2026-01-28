<?php
# cambiar: servidor, puerto, password
$db_server = '10.5.6.44';
$db_port = 3307;
$db_user_name = 'dam_v';
$db_password = 'Elorrieta00-';
$db_name = 'cine_elorrieta';


// Conectar
$conn = new mysqli($db_server, $db_user_name, $db_password, $db_name, $db_port);
//https://www.php.net/manual/es/mysqli.construct.php


// Comprobar conexión
if ($conn->connect_error) {
    error_log('Connection error: ' . $conn->connect_error);
    die("Fallo en la conexión: " . $conn->connect_error);
}

?>