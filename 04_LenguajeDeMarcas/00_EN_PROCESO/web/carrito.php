<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php -->
<?php $tit="Mi Carrito";?>
<?php require('./include/header.php'); ?>
<?php include('./include/dbconnect.php'); ?>

culo
<?php
    if (isset($_POST['versesbot'])){
        $idpe = $_POST['idpeli'];
    }
    
?>

<!-- footer -->
<?php require('./include/footer.php'); ?>
<?php $conn->close(); ?>