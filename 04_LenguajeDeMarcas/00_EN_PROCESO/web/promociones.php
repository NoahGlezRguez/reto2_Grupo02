<!-- header -->
<!-- https://www.php.net/manual/en/function.require.php -->
<?php $tit="Promociones";?>
<?php include('./include/dbconnect.php');?>
<?php $active2 = "class='menlisel'"; require('./include/header.php'); ?>

<div class="img"><img src="img/promo.png" class="indeximg" id="pImg"/></div>

<!-- footer -->
<?php require('./include/footer.php'); ?>
<?php $conn->close();?>