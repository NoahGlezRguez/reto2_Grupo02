<?php $tit="FAQ";?>
<?php include('./include/dbconnect.php');?>
<?php require('./include/header.php'); ?>
<main>  
    <!-- this area is the one the you will work with -->
    <br> <br> <br>
    <div id="faqdiv">
        <h1 id="preguntasf">Preguntas Frecuentes</h1> <br/>

        <div class="separador">
            <h3>¿Cuál es nuestro horario?</h3>
            <p>Nuestro horario es de 15:00 a 00:00 horas, todos los días.</p>
        </div>

        <div class="separador">
            <h3>¿Como puedo contactaros/como puedo encontraros?</h3>
            <p>¡En la pagina de Contacto tienes toda la informacion para contactarnos!</p>
        </div>

        <div class="separador">
            <h3>¿Donde estáis ubicados?</h3>
            <p>¡En la pagina de Contacto tienes toda la informacion sobre nuestra ubicacion!</p>
        </div>

        <div class="separador">
            <h3>¿Puedo conseguir descuentos en la pagina web?</h3>
            <p>¡Claro que si! Toda la informacion relacionada a descuentos se encuentra mas detallada en la pagina de Promociones.</p>
        </div>

        <div class="separador">
            <h3>¿Que le pasa a mi informacion cuando creo una cuenta?</h3>
            <p>¡En Elorrieta Cines no recopilamos los datos de nuestrxs clientes! ¡Tu informacion personal nunca será usada para nada mas que para identificarte!</p>
        </div>
        <div class="separador">
            <h3>¡Me equivoqué en mi contraseña al crear mi cuenta! ¿Que puedo hacer para solucionarlo?</h3>
            <p>Contactanos, y un experto se encargará de tu caso.</p>
        </div>
    
        <br>
    </div>
  
    <!-- end of the area -->
</main>
<?php require('./include/footer.php');
$conn->close();?>