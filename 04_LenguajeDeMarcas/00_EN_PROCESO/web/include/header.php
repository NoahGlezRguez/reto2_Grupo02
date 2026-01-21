<?php
session_start();
?>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta name="language" content="es" />
  <meta name="description" content="cine elorrieta, venta de entradas para películas actuales" />
  <meta name="rating" content="general" />
  <meta name="keywords"
    content="cine, entradas, venta, sesiones, cartelera, peliculas, terror, acción, españa, entretenimiento, ocio" />
  <title>Iniciar sesión</title>
  <!-- luego crear una funcion que cambie dinamicamente el titulo ... -->
  <link rel="icon" type="image/jpeg" href="img/logo1.jpeg" />
  <link rel="stylesheet" href="css/css.css" />
  <script src="js/script.js"></script>
</head>

<body>

  <!-- page header -->
  <header>
    <div>
      <a href="index.php"><img src="img/logo.png" alt="Cines Elorrieta Logo" class="logoheader" /></a>
      <h1 id="title">
        Elorrieta <br />
        Web
      </h1>
    </div>

    <div class="socialMedia">
      <a href="https://www.facebook.com" target="_blank"><img src="img/fblogo.png" alt="logo de facebook" /></a>
      <a href="https://www.instagram.com" target="_blank"><img src="img/iglogo.png" alt="logo de instagram" /></a>
      <a href="https://www.twitter.com" target="_blank"><img src="img/xlogo.png" alt="logo de x" /></a>
      <a href="https://www.tiktok.com" target="_blank"><img src="img/tiktoklogo.png" alt="logo de tiktok" /></a>
    </div>
  </header>

  <ul class="menu">
    <li><a href="index.php">Inicio</a></li>
    <li><a href="cartelera.php">Cartelera</a></li>
    <li><a href="promociones.html">Promociones</a></li>
    <li><a href="contacto.php">Contacto</a></li>

    <!-- nombre de usuario cargado de la sesion, si existe-->
    <!-- https://www.php.net/manual/en/reserved.variables.session.php-->
    <?php
    if (isset($_SESSION["nombre"])) {
      echo "<li> 🙋 " . $_SESSION["nombre"] . "</li>";
      echo "<li><a href=\"logout.php\" class=\"CerSes\"> Cerrar sesion </a></li>";
    } else {
      echo "<li><a href=\"login.php\"> 👤 Iniciar sesión</a></li>";
    }
    ?>

  </ul>