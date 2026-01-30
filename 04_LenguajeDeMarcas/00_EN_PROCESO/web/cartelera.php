<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php-->
<?php $tit="Cartelera";?>
<?php include('./include/dbconnect.php');?>
<?php require('./include/header.php'); ?>

<main id="cartelera">

    <?php
    $result = $conn->query("SELECT * FROM pelicula natural join genero order by IDPeli asc");
    $rows = $result->fetch_all(MYSQLI_ASSOC);
    //https://www.php.net/manual/en/mysqli-result.fetch-all.php
    
    //https://www.php.net/manual/en/control-structures.foreach.php
    foreach ($rows as $pelicula) {
        $id = $pelicula["IDpeli"];
        $nombre = $pelicula["NomPeli"];
        $duracion = $pelicula["Duracion"];
        $caratula = $pelicula["Caratula"];
        $genero = $pelicula["NomGen"];

        echo '
        <div class="peliculas">
                <div class="titulopeli">
                    <h2 class="titulopelitexto">' . $nombre . '</h2>
                </div>
                <div class="contenido">

                    <div>
                        <img src=' . $caratula . ' alt=' . $nombre . ' width ="250px" height="350px">
                    </div>

                    <div>
                        <p class="detallepelicula"><b>Género:</b> ' . $genero . '<br/>
                        <b>Duración:</b> ' . $duracion . ' minutos.
                        </p>
                    </div>

                    <div>
                        <form action="sesion.php" method="post">
                            <input type="hidden" name="idpeli" value='. $id .'>
                            <input type="submit" class="botonchachipiruli" id="versesiones" name="versesbot" value="Ver sesiones"/>
                        </form>
                    </div>
            
                </div>
        </div>';
    }
    
    $conn->close();
    ?>
    <!-- end of the area -->
</main>

<!-- footer
<?php require('./include/footer.php'); ?>