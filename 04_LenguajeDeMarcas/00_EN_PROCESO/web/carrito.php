<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php -->
<?php 
include('./include/dbconnect.php');

// Recuperamos el carrito
if(!isset($_SESSION['carrito'])){
    $carrito = array();
}
else {
    $carrito = $_SESSION['carrito'];
}

// Metemos en el carrito lo nuevo
if (isset($_POST['idses']) && isset($_POST['resbot'])){
    $idses = intval($_POST['idses']); // id de la sesión convertido en int
    $cantidad = isset($_POST['cantidad']) ? intval($_POST['cantidad']) : 1; // cantidad convertida en int, por defecto 1 
    $carrito[$idses] = $cantidad;   //guardamos para esa sesion esa cantidad // si no se pone por defecto 1 no funciona bien
}

// Volcamos a la sesión el contenido actual del Carrito
$_SESSION['carrito'] = $carrito;

// Log en consola por si acaso
echo '<script>console.log('. implode($carrito) .');</script>';

// Añadimos el header
$tit="Mi Carrito";
require('./include/header.php');

// De aqui para abajo va la parte visual de la pagina

foreach($carrito as $idses => $cantidad){
    $sqlses = "SELECT * FROM sesion where IDSesion=". $idses .";";
    $result = $conn->query($sqlses);

    if($rrftp = $result -> fetch_assoc()){
        $sqlpel = "SELECT * FROM pelicula where IDpeli=". $rrftp['IDPeli'] .";";
        $respe = $conn->query($sqlpel);

        if($rowpe = $respe -> fetch_assoc()){

            

            echo'<div class="sesdiv">' .
                '<p> Pelicula: '. $rowpe['NomPeli'] .'</p>
                <p> Fecha: '. $rrftp['fec'] .'</p>
                <p> Horario: '. $rrftp['hora_ini']. ' - ' . $rrftp['hora_fin'] .'</p>
                <p> Sala: ' . $rrftp['NumSala'] . '</p>
                <p> Precio: ' . $rrftp['precio'] . '€</p>
                <p> Cantidad: ' . $cantidad . '</p>
                </div>';/*Corregir cantidad que al seleccionarla se pone la misma en todas las entradas*/
        } 
    }
<<<<<<< Updated upstream
}

echo '<form method="post">
    <input type="submit" value="Pagar" name="pagar"/>
    <input type="submit" value="Vaciar Carrito" name="vaciar"/>
</form>'; /* corregir vaciar carrito */ 
/* revisar */ 
if(isset($_POST['vaciar'])){
    $_SESSION['carrito'] = array();
    $carrito = array();
    
=======

    $sqlConfirm = "SELECT distinct IDPeli FROM pelicula where IDSesion in (".$sesion[0] ;
}
// Aqui se guradarn todas las sesiones del carrito en un string para la consulta (●'◡'●)
$sesiontoString='';
foreach($carrito as $i => $sesion){
    if($i < count($carrito)-1){
        $sesiontoString = $sesiontoString . "'".$sesion[0]."', ";
    } else{
        $sesiontoString = $sesiontoString . "'".$sesion[0]."'";
    }
}

echo $sesiontoString;

if(!$valid){ 

    echo '<form method="post">
            <inut disabled type="" value="" name="descuento"
            <input disabled type="" value"" name="total">
            <input type="submit" value="Pagar" name="pagar"/>
            <input type="submit" value="Vaciar Carrito" name="vaciar"/>
        </form>'; /* corregir vaciar carrito */ 
        /* revisar */ 

>>>>>>> Stashed changes
}

else if(isset($_POST['pagar'])){
    /* Aquí iría la lógica de pago*/
    echo "<p>Pago realizado con éxito. ¡Gracias por su compra!</p>";
    $_SESSION['carrito'] = array();
    $carrito = array();

    /*falta poner el insert */
}

// Footer y cerramos la conexion
require('./include/footer.php');
$conn->close(); 
?>