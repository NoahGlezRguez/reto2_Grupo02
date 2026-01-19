<?php

// https://www.php.net/manual/en/function.error-reporting.php
ini_set('error_reporting', E_ALL);

// Para trabajar con $_SESSION
session_start();

// incluir datos para la conexion de la bd
include('./include/dbconnect.php');

// Recuperar datos del formulario
$usuario = $_POST['user'];
$contrasena = $_POST['pass'];

// Consulta SQL
$sql = "SELECT * FROM Cliente WHERE NomCli = '$usuario' AND userpassword = '$contrasena'";

// Ejecutar consulta
$result = $conn->query($sql);

// Comprobar si el usuario existe
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $_SESSION['nombre'] = $row['NomCli'];
        // Guardaremos el nombre del usuario para mostrarlo en el encabezado más tarde.
    }
    // Si hemos encontrado el registro, iniciamos sesión y procedemos: vamos a la pagina de seleccion de cine
    header("Location: cartelera.php");
    exit();
} else {
    // Si no existe, mensaje de error (lo resuelvo mediante url)
    // header("Location: ../index.html?errorea=1");
    echo "<script> alert('Usuario no existe');window.location='login.php';</script>";
    exit();
}

// Cerrar conexion
$conn->close();

?>