<?php
// https://www.php.net/manual/en/function.session-destroy.php
session_start();
// Unset all of the session variables.
$_SESSION = array();

// Finally, destroy the session.
session_destroy();

// nos elimina la sesion y volvemos a la pagina de inicio...
header("location: index.php");
exit;
?>