<?php
  if(isset($_SESSION['carrito'])){
    $carrito = $_SESSION['carrito'];
  }
  else{
      $carrito = array();
      $_SESSION['carrito'] = $carrito;
  }
?>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="iso-8859-1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta name="language" content="es" />
  <meta name="description" content="cine elorrieta, venta de entradas para películas actuales" />
  <meta name="rating" content="general" />
  <meta name="keywords"
    content="cine, entradas, venta, sesiones, cartelera, peliculas, terror, acción, españa, entretenimiento, ocio" />
  <!-- luego crear una funcion que cambie dinamicamente el titulo ... -->
  <link rel="icon" type="image/jpeg" href="img/logo1.jpeg" />
  <link rel="stylesheet" href="css/css.css" />
  <script src="js/js.js"></script>

  <!-- variables importantes, mas o menos -->
  <?php
    echo "<title>" . $tit . "</title>";
  ?>
</head>

<body>
  <!-- page header -->
  <header>
    <div id="logoMobile">
      <a href="index.php"><img src="img/logo.png" alt="Cines Elorrieta Logo" class="logoheader" /></a>
      <h1 class="title">
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



  <!-- nombre de usuario cargado de la sesion, si existe-->
  <!-- https://www.php.net/manual/en/reserved.variables.session.php-->
  <?php
    if (isset($_SESSION["nombre"])) {
      
    //$active es el class="" y le das valor en la propia pag, el ?? '' es que si está vacio no hagas nada
        echo "
        <ul class='menu'>
          <li><a href='index.php' ". ($active ?? '') ." '>Inicio</a></li>
          <li><a href='cartelera.php' ". ($active1 ?? '') ." '>Cartelera</a></li>
          <li><a href='promociones.php' ". ($active2 ?? '') ." '>Promociones</a></li>
          <li><a href='contacto.php'  ". ($active3 ?? '') ." '>Contacto</a></li>
          <li> 🙋 " . $_SESSION["nombre"] . "</li>";
  
        echo "<li><a href='carrito.php'  ". ($active4 ?? '') ." '>🛒(" . count($carrito) . ")</a></li>";
        echo "<li><a href=\"logout.php\" class=\"CerSes\"> Cerrar sesion </a></li>";
    } else {
      echo "
      <ul class='menu'>
          <li><a href='index.php'>Inicio</a></li>
          <li><a href='login.php'>Cartelera</a></li>
          <li><a href='promociones.php'>Promociones</a></li>
          <li><a href='contacto.php'>Contacto</a></li>
          <li><a href=\"login.php\"> 👤 Iniciar sesión</a></li>";
    }

    
  ?>
</ul>