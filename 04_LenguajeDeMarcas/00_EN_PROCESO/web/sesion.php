<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php -->
<?php $tit="Sesiones";?>
<?php include('./include/dbconnect.php');?>
<?php require('./include/header.php'); ?>

<main>

    
    <?php
        isset($_POST['versesbot']);
        $idpe = $_POST['idpeli'];
        $sqlfec = "SELECT fec FROM sesion s JOIN pelicula p ON s.IDPeli = p.IDPeli WHERE p.IDPeli='$idpe' AND fec>=current_date
        OR (s.fec = CURRENT_DATE AND s.hora_ini >= CURRENT_TIME);"; /*Falta que muestre solo las que tienen aforo*/

        $sqltit = "SELECT NomPeli FROM pelicula WHERE IDPeli = '$idpe';";
        $resluet = $conn->query($sqlfec);
        $result = $conn->query($sqltit);
        
        $sql = "SELECT * FROM pelicula natural join sesion natural join entrada natural join sala";
        

        if($rown = $result->fetch_assoc()){
            echo "<div class='sesionContenedor'><h1 class='title'>" . $rown['NomPeli'] . "</h1>";
        }
        echo '<div class="adolf">';
        echo "<form class='cinefechaform'>
                <Label>Seleccione cine</Label> <br>
                <select>
                    <option value=''>Cine Elorrieta</option>
                </select> <br>
            </form>";
        /*Seleccion de fecha*/
        echo "<form method='post' class='cinefechaform'>
        <Label>Seleccione una fecha</Label> <br>
        <select name='fecsel'>";
        while($row = $resluet->fetch_assoc()){
            echo "<option value='".$row['fec']."'>".$row['fec']."</option>";
        }
        echo "</select> <br>
        <input type='hidden' name='idpeli' value='".$idpe."'/>
        <input type='submit' value='Mostrar sesiones' class='botonchachipiruli' name='recogersesion'>
        </form>";
        echo '</div> <br>';

        if(isset($_POST['recogersesion']) && isset($_POST['fecsel'])){
            $fecseleccionada = $_POST['fecsel'];
            $sqlfec = "SELECT fec FROM sesion s JOIN pelicula p ON s.IDPeli = p.IDPeli WHERE p.IDPeli='$idpe' AND fec='$fecseleccionada';";

            $resluet = $conn->query($sqlfec);

            while($row = $resluet->fetch_assoc()){

                echo " <h1 class='title'>Sesiones para: "  . $row['fec'] . "</h1> ";

            
                echo "
                    
                    <div id='sesmaindiv'>";
                    $fecsel = $row['fec'];
                    $sqlses = "SELECT * FROM sesion where fec='$fecsel' and IDPeli='$idpe';";
                    $reslut = $conn->query($sqlses);
                    $haySesiones = false;

                    while($rowf = $reslut->fetch_assoc()){
                        $haySesiones = true;
                        $sqlAforo = "SELECT s.aforo - ifnull(sum(e.CantPersonas),0) as aforo_disponible
                                     FROM sala s
                                     LEFT JOIN sesion se ON s.NumSala = se.NumSala
                                     LEFT JOIN entrada e ON se.IDsesion = e.IDsesion
                                     WHERE se.IDsesion = " . $rowf['IDsesion'] . "
                                     GROUP BY s.aforo;";

                        $resAforo = $conn->query($sqlAforo);
                        $rowAforo = $resAforo->fetch_assoc();
                        /*Falta elegir la cantidad de personas haciendo una consulta en la bd*/
                        echo "<div class='sesdiv'>";
                            echo "<p> De ". $rowf['hora_ini']. " hasta " . $rowf['hora_fin'] ."</p> <p> Sala " . $rowf['NumSala'] . "</p> <p> Precio: " . $rowf['precio'] . "€</p>";
                            echo "<form action='carrito.php' method='post'>
                            <input type='hidden' name='idses' value='". $rowf['IDsesion'] ."'/>
                            <label> Cantidad de entradas: </label>
                            <select name='cantidad'>";
                            $valid = true;
                            for($i = 1; $i <= $rowAforo['aforo_disponible']; $i++){
                                echo "<option value='$i'>$i</option>";
                                if($i>1){$valid= false;}
                                
                            }
                            
                            echo "</select><br><br>";

                        if(!$valid){
                            echo "<input type='submit' class='botonchachipiruli' value='Reservar' name='resbot'/></form>";
                        }
                        else{
                            echo "<P class='red'> Los sentimos, el aforo está agotado.</P>";
                        }

                        echo "</div>"; /* aquí en el select se debe consultar el aforo disponible para cada sesión */
                    }
                echo "</details></div> </div>";
                if(!$haySesiones){
                    echo "<p> No hay sesiones disponibles para la fecha seleccionada. </p>";    
                }
            }
        }

        else{
            echo "<p> Por favor, seleccione una fecha para ver las sesiones disponibles. </p>";
        }
    
    ?>
</main>

<!-- footer -->
<?php require('./include/footer.php'); ?>
<?php $conn->close();?>