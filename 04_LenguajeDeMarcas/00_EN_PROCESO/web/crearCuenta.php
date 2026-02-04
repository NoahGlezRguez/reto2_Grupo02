<?php $tit="Crear Cuenta";?>
<?php include('./include/dbconnect.php');?>
<?php require('./include/header.php');?>

    <main>  
       
        <h2 class="subtitulo">Registro de nuevo usuario</h2>
       
        <div class="Loginform">
            <!-- ya que en la web no había que poderse "crear una cuenta" 
             no nos hemos enfocado en esto y no se realizan validaciones
             aunque funciona si introduces los datos correctamente-->
            <form action="conexion.php" method="post">

                <label>Nombre:</label>
                <input type="text" name="newname" class="inputcrear" required><br><br>

                <label>Apellido:</label>
                <input type="text" name="newape" class="inputcrear" required><br><br>

                <label>E-mail:</label>
                <input type="email" name="newmail" class="inputcrear" required><br><br>
                
                <label>DNI:</label>
                <input type="text" name="dni" class="inputcrear" required><br><br>

                <label>Contraseña:</label>
                <input type="password" name="newpass" class="inputcrear" required><br><br>

                <input type="submit" class="botonchachipiruli" name="registrarse" value="Crear cuenta" id="submitnewuser">
                <input type="reset" class="botonchachipiruli" id="resetnewuser">

            </form>
        </div>
        <!-- end of the area -->
    </main>
    
    <!-- please do not modify this part 👇-->
<?php require('./include/footer.php');
$conn->close();?>