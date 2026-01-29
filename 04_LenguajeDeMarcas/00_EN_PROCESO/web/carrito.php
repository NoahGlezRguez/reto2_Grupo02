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
if (isset($_POST['idses'])){
    $idses = $_POST['idses'];
    $carrito[] = $idses;
}

// Volcamos a la sesión el contenido actual del Carrito
$_SESSION['carrito'] = $carrito;

// Log en consola por si acaso
echo '<script>console.log('. implode($carrito) .');</script>';

// Añadimos el header
$tit="Mi Carrito";
require('./include/header.php');

// De aqui para abajo va la parte visual de la pagina

foreach($carrito as $i => $value){
    $sqlses = "SELECT * FROM sesion where IDSesion=". $value .";";
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
                </div>';
        }
    }
}

echo '<form>
    <input type="submit" value="Pagar"/>
</form>';

// Footer y cerramos la conexion
require('./include/footer.php');
$conn->close(); 
?>