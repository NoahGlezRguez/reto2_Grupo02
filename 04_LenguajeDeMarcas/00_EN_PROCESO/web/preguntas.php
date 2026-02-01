<?php $tit="FAQ";?>
<?php include('./include/dbconnect.php');?>
<?php require('./include/header.php'); ?>
<main>  
    <!-- this area is the one the you will work with -->
    <br> <br> <br>
    <div id="faqdiv">
        <h1 id="preguntasf">Preguntas Frecuentes</h1> <br/>
        <h3>¿Cuál es nuestro horario?</h3>
        <p>Nuestro horario es de 15:00 a 00:00 horas, todos los días.</p>
        <br/> <br>
        <h3>¿Como puedo contactaros/como puedo encontraros?</h3>
        <p>¡En la pagina de Contacto tienes toda la informacion para contactarnos!</p>
        <br/> <br>
        <h3>¿Puedo conseguir descuentos en la pagina web?</h3>
        <p>¡Claro que si! Toda la informacion relacionada a descuentos se encuentra mas detallada en la pagina de Promociones.</p>
        <br/> <br>
        <h3>¿Que le pasa a mi informacion cuando creo una cuenta?</h3>
        <p>¡En Elorrieta Cines no recopilamos los datos de nuestrxs clientes! ¡Tu informacion personal nunca será usada para nada mas que para identificarte!</p>
        <br/> <br>
        <h3>¡Me equivoqué en mi contraseña al crear mi cuenta! ¿Que puedo hacer para solucionarlo?</h3>
        <p>Contactanos, y un experto se encargará de tu caso.</p>
        <br/> <br>
        <h3>¿Como puedo borrar mi cuenta?</h3>
        <p>¡No puedes! :D</p>
        <br/> <br>
        
        <br>
    </div>
  
    <!-- end of the area -->
</main>
<?php require('./include/footer.php');
$conn->close();?>