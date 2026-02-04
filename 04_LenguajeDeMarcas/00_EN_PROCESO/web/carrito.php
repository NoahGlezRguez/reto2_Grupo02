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
$importe = 0;
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
    //usamos un array bidimensaional para guardar las sesiones y la cantidad de personas
    // para esa sesión
    $esta_sesion[] = $idses;   
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

$valid = true;

if(isset($_POST['pagar'])){
    /* Aquí iría la lógica de pago*/

    $dni = $_POST['dnii'];
    $plat = $_POST['plat'];
    $desc = $_POST['descuento'];
    $tot = $_POST['total'];

    //prepare statement como en java para mayor seguridad
    $sqlcom = $conn->prepare (
        "INSERT INTO compra (plataforma, descuento, total, DNI)
         VALUES (?, ?, ?, ?)"
    );

     $sqlcom->bind_param("sdds", $plat, $desc, $tot, $dni);

     $sqlIdcom= "SELECT max(IDCompra) as num
                FROM compra";

    if($sqlcom->execute()){
        $valid =false;
    }

    $idcmax = $conn->query($sqlIdcom);
    $row= $idcmax -> fetch_assoc();
    $idcmax = $row['num'];

    //for each para hacer los inserts de las entradas
    foreach($carrito as $i => $sesion){

        $sqlses = "SELECT * FROM sesion where IDSesion=". $sesion[0] .";";
        $result = $conn->query($sqlses);

        if($rrftp = $result -> fetch_assoc()){

            $import = $sesion[1] * $rrftp['precio'];

            $sqlEntradas = "INSERT INTO entrada VALUES(NULL, ".$sesion[1].", " . $import . ", ". $sesion[0] .", ".$idcmax. ");";
            
            if(!$conn->query($sqlEntradas)){
                $valid = false;
            }
           
        }

    }

    /* si se ejecuta la inserción de compra y también la de entradas correctamente*/
    if( !$valid){
        $_SESSION['carrito'] = array();
         $carrito = array();
        echo'<script> window.alert("Compra realizada correctamente");</script>';
    }
    else{
       echo'<script> window.alert("error en los datos");</script>'; 
    }
}

// Añadimos el header
$tit="Mi Carrito";
$active4 = "class='menlisel'";
require('./include/header.php');

// De aqui para abajo va la parte visual de la pagina

//el valid es para verificar si entra en la sesión, es decir si el carrito tiene algo para mostrar los botones de pagar y vaciar
$valid = true;
echo '<h1 class="title">Carrito</h1>';
echo '<div id="megacardiv">';
foreach($carrito as $i => $sesion){
    $sqlses = "SELECT * FROM sesion where IDSesion=". $sesion[0] .";";
    $result = $conn->query($sqlses);

    if($rrftp = $result -> fetch_assoc()){
        $sqlpel = "SELECT * FROM pelicula where IDpeli=". $rrftp['IDPeli'] .";";
        $respe = $conn->query($sqlpel);

        if($rowpe = $respe -> fetch_assoc()){
            $valid = false; //valid diciendo que pasó por aquí
            $importe =  $rrftp['precio'] * $sesion[1];
            echo'<div class="sesdiv">' .
                '<h1>'. $rowpe['NomPeli'] .'</h1>
                <p> Cine: Elorrieta Cines</p>
                <p> Fecha: '. $rrftp['fec'] .'</p>
                <p> Horario: '. $rrftp['hora_ini']. ' - ' . $rrftp['hora_fin'] .'</p>
                <p> Sala: ' . $rrftp['NumSala'] . '</p>
                <p> Precio: ' . $importe . '€</p>
                <p> Cantidad: ' . $sesion[1] . '</p>
                </div>';/*Corregir cantidad que al seleccionarla se pone la misma en todas las entradas*/

                $total = $total + $importe;
        } 
    }
}
echo '</div>';
// Aqui se guradarn todas las sesiones del carrito en un string para la consulta (●'◡'●)
$sesiontoString='';
foreach($carrito as $i => $sesion){
    if($i < count($carrito)-1){
        $sesiontoString = $sesiontoString .$sesion[0].", ";
    } else{
        $sesiontoString = $sesiontoString .$sesion[0];
    }
}

//cálculo del descuento 
if(count($carrito)>0){

    $sqlConfirm = "SELECT distinct IDPeli FROM sesion where IDSesion in (". $sesiontoString . ");";
    $rowsids = $conn->query($sqlConfirm);

    //cuenta el número de filas distintas que hay en la consulta
    $numrows  = $rowsids -> num_rows;
    $descuento = 0;

    if($numrows < 2){

        $descuento = 0;
        
    }

    else if($numrows == 2){

        $descuento = 20;
        //se calcula el descuento en función del total
        $descuento = $total * ($descuento/100);
        $total = $total - $descuento;
    }

    else{

        $descuento = 30;
        $descuento = $total * ($descuento/100);
        $total = $total - $descuento;
    }

    


// si el carrito tiene algo muestra los siguientes botones y valores 
    if(!$valid){ 

        isset($_SESSION["dni"]);
        // aquí el uso de reandoly para que no se pueda modificar el valor del dni, descuento y total
        // antes se usaba el disabled pero no se enviaba el valor por post al php
        echo '<div id="boomshakalaka">
                <form method="post">

                <input type="hidden" value="web" name="plat">
                <input readonly type="hidden" value="'. $_SESSION["dni"] .'" name="dnii">
                <label name=""> Descuento: </label>
                <input readonly type="number" value="'.$descuento.'" name="descuento"> <br>
                <label>Total: </label>
                <input readonly type="number" value="'.$total.'" name="total"> <br>
                <input type="submit" value="Pagar" name="pagar" class="botonchachipiruli"/> <br>
                <input type="submit" value="Vaciar Carrito" name="vaciar" class="botonchachipiruli"/>

            </form>
            </div>'; 
    }

}



// Footer y cerramos la conexion
require('./include/footer.php');
$conn->close(); 
?>