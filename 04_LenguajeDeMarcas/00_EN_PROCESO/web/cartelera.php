<?php
session_start();

$servername = "localhost";
$username = "root";
$password = "root";
$dbname = "cine_elorrieta";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM pelicula";
$result = $conn->query($sql);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="language" content="es">
    <meta name="description" content="cine elorrieta, venta de entradas para películas actuales">
    <meta name="rating" content="general">
    <meta name="keywords" content="cine, entradas, venta, sesiones, cartelera, peliculas, terror, acción, españa, entretenimiento, ocio">
    <title>Cartelera</title>
    <link rel="icon" type="image/jpeg" href="img/logo1.jpeg">
    <link rel="stylesheet" href="css/css.css">
    <script type="javascript" src="js/js.js"></script>
</head>
<body>

    <header> 
        <div>
            <a href="index.html"><img src="img/logo.png" alt="Cines Elorrieta Logo" class="logoheader"></a>
            <h1 id="title">Elorrieta <br/> Web</h1>
        </div>

        <div class="socialMedia">
            <a href="https://www.facebook.com" target="_blank"><img src="img/fblogo.png" alt="logo de facebook" ></a>
            <a href="https://www.instagram.com" target="_blank"><img src="img/iglogo.png" alt="logo de instagram" ></a>
            <a href="https://www.twitter.com" target="_blank"><img src="img/xlogo.png" alt="logo de x" ></a>
            <a href="https://www.tiktok.com" target="_blank"><img src="img/tiktoklogo.png" alt="logo de tiktok" ></a>
        </div>

    </header>


        <ul class="menu">
            <li><a href="index.html" >Inicio</a></li>
            <li class="cartelera"><a href="cartelera.html">Cartelera</a></li>
            <li><a href="promociones.html">Promociones</a></li>
            <li><a href="contacto.html">Contacto</a></li>
            <li><a href="sesion.html"> 👤 Iniciar sesión</a></li>
        </ul>
    <main id= "cartelera"> 
       

        <?php
        if ($result && $result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {
        ?>
            <div class="peliculas">
                <img src="<?= $row['Caratula'] ?>" alt="pelicula" width="150" height="200">
                <p>
                    <b>Título:</b> <?= $row['NomPeli'] ?><br>
                    <b>Género:</b> <?= $row['IDGen'] ?><br>
                    <b>Duración:</b> <?= $row['Duracion'] ?> min<br>
                </p>
            </div>
        <?php
            }
        } else {
            echo "<p>No hay películas disponibles</p>";
        }

        $conn->close();
        ?>
    </main>

    <footer>
        <p>© 2024 Cines Elorrieta. Todos los derechos reservados.</p> <br/>
        <p> <a id="preguntas" href="preguntas.html">Preguntas frecuentes</a></p>
    </footer>

</body>
</html>
</body>
</html>