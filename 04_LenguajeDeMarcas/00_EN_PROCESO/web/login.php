<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php-->
<?php require('./include/header.php');?>

  <main>
    <!-- this area is the one the you will work with -->

  
    <h2 class="subtitulo">Mi cuenta</h2>
    
    
    <div id="Loginform">
      <form action="conexion.php" method="post">
        
          <label for="usuario">Correo:</label>
          <input type="email" name="user" id="usuario" placeholder="Correo electrónico" required /><br /><br />
          <label for="password">Contraseña:</label>
          <input type="password" name="pass" id="password" placeholder="Contraseña" required /><br /><br />
          <input type="submit" value="Iniciar sesion" id="formISB" />
          
          <div id="textoIS">
              <p>¿Aún no estás registrado?<br><a href="registrar.php">Crear nueva cuenta</a><br></p>
              
          </div>
      </form>

    </div>
 

    <!-- end of the area -->
  </main>


<!-- footer
<?php require('./include/footer.php'); ?>
