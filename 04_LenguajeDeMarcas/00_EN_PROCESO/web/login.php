<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php-->
<?php $tit="Iniciar Sesion";?>
<?php include('./include/dbconnect.php');?>
<?php require('./include/header.php');?>

  <main>
    <!-- this area is the one the you will work with -->

  
    <h2 class="subtitulo">Mi cuenta</h2>

    
    
    <div class="Loginform">
      <form action="include/dbconnect.php" method="post">
        
          <label for="usuario">DNI:</label>
          <input type="text" name="user" id="usuario" placeholder="DNI" required /><br /><br />
          <label for="password">Contraseña:</label>
          <input type="password" name="pass" id="password" placeholder="Contraseña" required /><br /><br />
          <input type="submit" value="Iniciar sesion" id="formISB" class="botonchachipiruli" name="iniciar_sesion"/>
          
          <div id="textoIS">
              <p>¿Aún no estás registrado?<br><a href="crearCuenta.php">Crear nueva cuenta</a><br></p>
              
          </div>
      </form>

    </div>
 

    <!-- end of the area -->
  </main>


<!-- footer
<?php require('./include/footer.php');
$conn->close();?>
