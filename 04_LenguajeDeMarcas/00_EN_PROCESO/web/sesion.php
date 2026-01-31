<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php -->
<?php $tit="Sesiones";?>
<?php include('./include/dbconnect.php');?>
<?php require('./include/header.php'); ?>

<main>

    
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
        echo "
            <form>
                <Label>Seleccione cine:  </Label>
                <select>
                    <option value=''>Cine Elorrieta</option>
                </select>
            </form> </br></br>
                ";
        

        /*Seleccion de fecha*/
        echo "<form method='post'>
        <Label>Seleccione una fecha:  </Label>
        <select name='fecsel'>";
        while($row = $resluet->fetch_assoc()){
            echo "<option value='".$row['fec']."'>".$row['fec']."</option>";
        }
        echo "</select>
        <input type='hidden' name='idpeli' value='".$idpe."'/> </br> </br>
        <input type='submit' value='Mostrar sesiones' class='botonchachipiruli' name='recogersesion'>
        </form>";

        if(isset($_POST['recogersesion'])){
            $fecseleccionada = $_POST['fecsel'];
            $sqlfec = "SELECT fec FROM sesion s JOIN pelicula p ON s.IDPeli = p.IDPeli WHERE p.IDPeli='$idpe' AND fec='$fecseleccionada';";
            $resluet = $conn->query($sqlfec);

            while($row = $resluet->fetch_assoc()){

                echo " <h1>Sesiones para: "  . $row['fec'] . "</h1> ";

            
                echo "
                    
                    <div id='sesmaindiv'>";
                    $fecsel = $row['fec'];
                    $sqlses = "SELECT * FROM sesion where fec='$fecsel' and IDPeli='$idpe';";
                    $reslut = $conn->query($sqlses);
                    $numsa = 0;
                    while($rowf = $reslut->fetch_assoc()){
                        $numsa = $numsa + 1;
                        echo "<div class='sesdiv'>";
                            echo "<p>" . $numsa . "</p><p> De ". $rowf['hora_ini']. " hasta " . $rowf['hora_fin'] ."</p> <p> Sala " . $rowf['NumSala'] . "</p> <p> Precio: " . $rowf['precio'] . "€</p>";
                            echo "<form action='carrito.php' method='post'>
                            <input type='hidden' name='idses' value='". $rowf['IDsesion'] ."'/>
                            <input type='submit' class='botonchachipiruli' value='Reservar' name='resbot'/></form>";
                        echo "</div>";
                    }
                echo "</details></div>";
            }
        }
       
      
    
    ?>
</main>

<!-- footer -->
<?php require('./include/footer.php'); ?>
<?php $conn->close();?>