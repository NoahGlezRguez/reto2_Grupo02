<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php-->
<?php $tit="Hogar";?>

<?php include('./include/dbconnect.php');?>
<?php $active = "class='menlisel'"; require('./include/header.php');?>

    <main>
        <!-- this area is the one the you will work with -->
        <div id="bienvenida">
            <h2  id="titulo">Bienvenidos a Cines Elorrieta</h2>
            <p id="parrafotitulo">Le damos la bienvenida al espacio definitivo para los apasionados del séptimo arte.
            Aquí encontrará una selección rigurosa de estrenos, análisis detallados y las últimas novedades de la industria. <br>
            Explore nuestro catálogo y descubra historias que trascienden la pantalla. Estamos comprometidos con ofrecerle una
            experiencia informativa y visual de la más alta calidad.</p>
        </div>
        <div class="img">
            <img class="indeximg" src="img/presentacion.png" alt="cine">
        </div>

        <div>
            <h2 id="subproximo">Próximos Estrenos</h2>
          

            <div class="estrenos">
                <div class="peliestrenos" >
                    <img src="img/proximo2.png" alt="pelicula" width ="150px" height="200px">
                    <p><b>Título:</b> Secret admirer <br> <b>Estreno: </b> 22 de Agosto</p>
                </div>

                <div class="peliestrenos" >
                    <img src="img/proximo1.png" alt="pelicula" width ="150px" height="200px">
                    <p><b>Título:</b> El rey sapo <br> <b>Estreno: </b> 15 de Julio</p> 
                </div>

                <div class="peliestrenos" >
                    <img src="img/proximo3.png" alt="pelicula" width ="150px" height="200px">
                    <p><b>Título:</b> Cat-Man <br> <b>Estreno: </b> 30 de septiembre</p> 
                </div>
                <div class="peliestrenos" >
                    <img src="img/proximo4.png" alt="pelicula" width ="150px" height="200px">
                    <p><b>Título:</b> Los "THE SIMPSONS 2" <br> <b>Estreno: </b> 2026</p> 
                </div> 
            </div >


            <h2 id="prox">Proximamente en nuestros cines </h2>
            <div class="img">

                <img src="img/prox1.png" class="proximg" alt="4d">

            </div>
        </div>
        <!-- end of the area -->
    </main>

<!-- footer
<?php require('./include/footer.php');
$conn->close();?>
