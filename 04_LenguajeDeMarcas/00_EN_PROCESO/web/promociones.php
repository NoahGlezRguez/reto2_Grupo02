<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php -->
<?php $tit="Promociones";?>
<?php include('./include/dbconnect.php');?>
<?php require('./include/header.php'); ?>

<!-- footer -->
<?php require('./include/footer.php'); ?>
<?php $conn->close();?>