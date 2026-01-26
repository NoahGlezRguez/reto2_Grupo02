<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php -->
<?php $tit="Promociones";?>
<?php require('./include/header.php'); ?>
<?php include('./include/dbconnect.php'); ?>

<!-- footer -->
<?php require('./include/footer.php'); ?>
<?php $conn->close(); ?>