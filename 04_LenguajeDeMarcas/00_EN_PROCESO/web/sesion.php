<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php -->
<?php $tit="Sesiones";?>
<?php require('./include/header.php'); ?>
<?php include('./include/dbconnect.php'); ?>

<main>

    <form>
        <Label>¿Para que cine?</Label>
        <select>
            <option value="">Cine Elorrieta</option>
        </select>
    </form>
    <?php
        isset($_POST['versesbot']);
        $idpe = $_POST['idpeli'];
        $sqlfec = "SELECT fec FROM sesion s JOIN pelicula p ON s.IDPeli = p.IDPeli WHERE p.IDPeli='$idpe' AND fec>=current_date;";
        $sqltit = "SELECT NomPeli FROM pelicula WHERE IDPeli = '$idpe';";
        $resluet = $conn->query($sqlfec);
        $result = $conn->query($sqltit);
        
        if($rown = $result->fetch_assoc()){
            echo "<h1 class='subtitulo'>" . $rown['NomPeli'] . "</h1>";
        }
        while($row = $resluet->fetch_assoc()){
            echo "<hr/> <h1>". $row['fec'] ."</h1> <hr/>";
            echo "<details>
                <summary>Ver</summary>
                <div id='sesmaindiv'>";
                $fecsel = $row['fec'];
                $sqlses = "SELECT * FROM sesion where fec='$fecsel';";
                $reslut = $conn->query($sqlses);
                $numsa = 0;
                while($rowf = $reslut->fetch_assoc()){
                    $numsa = $numsa + 1;
                    echo "<div class='sesdiv'>";
                        echo "<p>" . $numsa . "</p><p> De ". $rowf['hora_ini']. " hasta " . $rowf['hora_fin'] ."</p> <p> Sala " . $rowf['NumSala'] . "</p> <p> Precio: " . $rowf['precio'] . "€</p>";
                        echo "<form action='carrito.php' method='post'>
                        <input type='hidden' name='' value=''/>
                        <input type='submit' class='botonchachipiruli' value='Reservar' name='resbot'/></form>";
                    echo "</div>";
                }
            echo "</details></div>";
        }
        echo "<img src='img/skrunkly.jpg' width='150em' height='150em' />";
    ?>
</main>

<!-- footer -->
<?php require('./include/footer.php'); ?>
<?php $conn->close(); ?>