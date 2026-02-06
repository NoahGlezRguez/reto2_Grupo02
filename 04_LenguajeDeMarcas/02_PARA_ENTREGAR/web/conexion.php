<script src="js/js.js"></script>
<?php
include('./include/dbconnect.php');

// https://www.php.net/manual/en/function.error-reporting.php
ini_set('error_reporting', E_ALL);

// Para trabajar con $_SESSION
//session_start();
// incluir datos para la conexion de la bd
//include('./include/dbconnect.php');

//Los dos de arriba son ahora innecesarios

// recoger datos del formulario de login aplicando la condición de que se ha pulsado el botón de iniciar sesión
if (isset($_POST['iniciar_sesion'])) {
    // Recuperar datos del formulario
    $usuario = $_POST['user'];
    $contrasena = md5($_POST['pass'], false);

    // Consulta SQL
    $sql = "SELECT * FROM Cliente WHERE mail = '$usuario' AND userpassword = '$contrasena'";

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

        echo "<script> alert('Usuario y/o contraseña incorrectos');window.location='login.php';</script>";


        exit();
    }
}


// recoger datos del formulario de ceunta nueva aplicando la condición de que se ha pulsado el botón de crear cuenta
if(isset($_POST['registrarse'])){

    $nombre = $_POST['newname'];
    $apellido = $_POST['newape'];
    $mail = $_POST['newmail'];
    $pass = md5($_POST['newpass'], false); //password encriptada con hash con md5, el false es para especificar que no es un valor binario
    $dni = $_POST['dni'];

    $sql = "INSERT INTO cliente VALUES('$dni', '$nombre', '$apellido', '$mail', '$pass');";

    //condición de que si la consulta se ejecuta correctamente
    if($conn->query($sql) === true){
        echo "<script>
        winale('Usuario nuevo guardado correctamente');
        tp('cartelera.php');
        </script>";
    }

    else{
    // mensaje de error con la base de datos
        echo "<script>
        winale('Error');
        tp('crearCuenta.php');
        </script>";
    }
} // ALERTA ERROR ALERTA ERROR SI PONES EL MISMO DNI DOS VECES, LA WEB SE MUERE, HAY QUE CONTROLARLO
    // no se ha controlado porque la página se creó extra sin que lo pidieran y quedamos cortos de tiempo

// Cerrar conexion
$conn->close();

?>