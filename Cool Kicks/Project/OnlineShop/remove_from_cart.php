<?php
    include 'Include/config.php';
    $id = $_GET['id'];
    $query = "DELETE FROM `cart` WHERE `ID` = '$id' ";
    $data = mysqli_query($com,$query);
    if($data){
        header('location: carts.php');
    }else{
        echo "Problem ".$com->error ;
    }
?>
