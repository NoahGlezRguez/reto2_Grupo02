<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php-->
<?php require('./include/header.php'); ?>

<main id="cartelera">

    <?php
    include('./include/dbconnect.php');

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

        echo "<div class=\"peliculas\">
            <img src=" . $caratula . " alt=" . $nombre . " width =\"150px\" height=\"200px\">
            <p><b>Título:</b> " . $nombre . "<br/>
            <b>Género:</b> " . $genero . " <br/>
            <b>Duración:</b> " . $duracion . " min.<br/>
           </p>
        </div>";
    }
    $conn->close();
    ?>
    <!-- end of the area -->
</main>

<!-- footer
<?php require('./include/footer.php'); ?>