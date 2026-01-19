<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php-->
<?php require('./include/header.php');?>

  <main>
    <!-- this area is the one the you will work with -->

  
    <h1>Mi cuenta</h1>
    <div>
       ¿Aún no estás registrado?
      <a href="registrar.php">Crear cuenta</a><br>
    </div>
    
    <div id="Loginform">
      <form action="conexion.php" method="post">
        <fieldset>
          <label for="usuario">Usuario:</label>
          <input type="text" name="user" id="usuario" placeholder="Nombre de usuario" required /><br /><br />
          <label for="password">Contraseña:</label>
          <input type="password" name="pass" id="password" placeholder="contraseña" required /><br /><br />
          <input type="submit" value="Iniciar sesion" />
        </fieldset>
      </form>

    </div>
 

    <!-- end of the area -->
  </main>


<!-- footer
<?php require('./include/footer.php'); ?>
