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

$total = 0;
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

                $total = $total + $rrftp['precio'];
        } 
    }

   
}
// Aqui se guradarn todas las sesiones del carrito en un string para la consulta (●'◡'●)
$sesiontoString='';
foreach($carrito as $i => $sesion){
    if($i < count($carrito)-1){
        $sesiontoString = $sesiontoString .$sesion[0].", ";
    } else{
        $sesiontoString = $sesiontoString .$sesion[0];
    }
}

echo $sesiontoString;

if(count($carrito)>0){
    $sqlConfirm = "SELECT distinct IDPeli FROM sesion where IDSesion in (". $sesiontoString . ");";
    $rowsids = $conn->query($sqlConfirm);

    $numrows  = $rowsids -> num_rows;
    $descuento = 0;

    if($numrows < 2){

        $descuento = 0;
        
    }

    else if($numrows == 2){

        $descuento = 20;
        $descuento = $total * ($descuento/100);
        $total = $total - $descuento;
    }

    else{

        $descuento = 30;
        $descuento = $total * ($descuento/100);
        $total = $total - $descuento;
    }

    



    if(!$valid){ 

        isset($_SESSION["dni"]);
        echo '<form method="post">
                <input type="hidden" value="web" name="plat">
                <input disabled type="text" value="'. $_SESSION["dni"] .'" name="dni">
                <label>Descuento: </label>
                <input disabled type="number" value="'.$descuento.'" name="descuento">
                <label>Total: </label>
                <input disabled type="number" value="'.$total.'" name="total">
                <input type="submit" value="Pagar" name="pagar"/>
                <input type="submit" value="Vaciar Carrito" name="vaciar"/>
            </form>'; /* corregir vaciar carrito */ 
            /* revisar */ 
        
    }

}



//aquí guardo la compra 
else if(isset($_POST['pagar'])){
    /* Aquí iría la lógica de pago*/

    $dni = $_POST['dni'];
    $plat = $_POST['plat'];
    $desc = $_POST['descuento'];
    $tot = $_POST['total'];

    /*$sqlIdcom= "SELECT max(IDCompra)
                FROM compra";

    $idcmax = $conn->query($sqlIdcom);
    $idcmax = $idcmax -> fetch_assoc();
    $idcmax = $idcmax + 1;*/

    $sqlcom = "INSERT INTO compra VALUES( null, null, '".$plat."', " . $desc . ", ". $tot .", ". $dni .");";
    
    if($conn->query($sqlcom)){

        echo'<script> window.alert(holaaa);</script>';

    }

    echo "<p>Pago realizado con éxito. ¡Gracias por su compra!</p>";
    $_SESSION['carrito'] = array();
    $carrito = array();

    /*falta poner el insert */
}

// Footer y cerramos la conexion
require('./include/footer.php');
$conn->close(); 
?>