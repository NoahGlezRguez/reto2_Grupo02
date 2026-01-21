<?php require('./include/header.php');?>

    <main>  
        <!-- this area is the one the you will work with -->
        <h2 class="subtitulo">Registro de nuevo usuario</h2>
       
        <div class="Loginform">
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

                <input type="submit" name="registrarse" value="Crear cuenta" id="submitnewuser">
                <input type="reset" id="resetnewuser">

            </form>
        </div>
        <!-- end of the area -->
    </main>
    
    <!-- please do not modify this part 👇-->
<?php require('./include/footer.php');?>