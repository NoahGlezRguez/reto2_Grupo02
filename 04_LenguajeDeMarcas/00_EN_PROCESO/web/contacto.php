<?php $tit="Contactame";?>
<?php ?>
<?php include('./include/dbconnect.php');?>
<?php $active3 = "class='menlisel'"; require('./include/header.php');?>
        <!-- please do not modify this part 👆-->

        <!-- modificarlo a php para que se envíe en correo correctamente -->
    
    <main id="pagcontacto">  
        <!-- this area is the one the you will work with -->
        <div class="contactform">
            <form  id="fromcon">
                <h2>Contacto</h2>
                <label for="name">Nombre:</label><br/>
                <input type="text" id="name" name="name" required><br/><br/>
                
                <label for="email">Correo electrónico:</label><br/>
                <input type="email" id="email" name="email" required><br/><br/>
                
                <label for="text">Mensaje:</label><br/>
                <textarea id="text" name="text" rows="4" cols="40" required></textarea><br/><br/>
                
                <input type="submit" class="botonchachipiruli" value="Enviar" id="enviar">
                <input type="reset" class="botonchachipiruli" value="Borrar" id="resetar">
            </form>
        </div>
        
        <div class="map">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d5808.909144976444!2d-2.9673868234129497!3d43.283801276243636!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd4e507052ed4917%3A0xec69922dea02910b!2sCIFP%20Elorrieta%20Erreka%20Mari%20LHII!5e0!3m2!1ses!2ses!4v1768988742800!5m2!1ses!2ses" 
             allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
        <!-- end of the area -->
    </main>
    
    <!-- please do not modify this part 👇-->
<?php require('./include/footer.php');
$conn->close();?>