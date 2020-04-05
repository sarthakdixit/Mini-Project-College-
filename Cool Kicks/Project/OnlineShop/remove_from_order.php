<?php
    include 'Include/config.php';
    $id = $_GET['id'];
    $query = "DELETE FROM `orders` WHERE `ID` = '$id' ";
    $data = mysqli_query($com,$query);
    if($data){
        header('location: order.php');
    }else{
        echo "Problem ".$com->error ;
    }
?>
