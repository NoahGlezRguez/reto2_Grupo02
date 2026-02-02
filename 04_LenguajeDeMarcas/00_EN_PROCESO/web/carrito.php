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
$esta_sesion = array();
$cantidad = 0;
if (isset($_POST['idses']) && isset($_POST['resbot'])){
    $idses = $_POST['idses']; 
    if(isset($_POST['cantidad'])){
        $cantidad = intval($_POST['cantidad']);
    }
    else{
        $cantidad = 1;
    }
    $esta_sesion[] = $idses;   //guardamos para esa sesion esa cantidad // si no se pone por defecto 1 no funciona bien
    $esta_sesion[] = $cantidad;
    if($esta_sesion != null){
        $carrito[] = $esta_sesion;
    }
}
// Vaciamos el carrito si la pagina recibe el boton vaciar
if(isset($_POST['vaciar'])){
    unset($carrito);
    $_SESSION['carrito'] = null;
} else{
    // Volcamos a la sesión el contenido actual del Carrito
    $_SESSION['carrito'] = $carrito;
}
// Añadimos el header
$tit="Mi Carrito";
require('./include/header.php');

// De aqui para abajo va la parte visual de la pagina
$valid = true;
foreach($carrito as $i => $sesion){
    echo 'Esta sesion: ' . $sesion[0] . ' y la cantidad es ' . $sesion[1] . '<br>';
    $sqlses = "SELECT * FROM sesion where IDSesion=". $sesion[0] .";";
    $result = $conn->query($sqlses);

    if($rrftp = $result -> fetch_assoc()){
        $sqlpel = "SELECT * FROM pelicula where IDpeli=". $rrftp['IDPeli'] .";";
        $respe = $conn->query($sqlpel);

        if($rowpe = $respe -> fetch_assoc()){
            $valid = false;
            echo'<div class="sesdiv">' .
                '<p> Pelicula: '. $rowpe['NomPeli'] .'</p>
                <p> Fecha: '. $rrftp['fec'] .'</p>
                <p> Horario: '. $rrftp['hora_ini']. ' - ' . $rrftp['hora_fin'] .'</p>
                <p> Sala: ' . $rrftp['NumSala'] . '</p>
                <p> Precio: ' . $rrftp['precio'] . '€</p>
                <p> Cantidad: ' . $sesion[1] . '</p>
                </div>';/*Corregir cantidad que al seleccionarla se pone la misma en todas las entradas*/
        } 
    }

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