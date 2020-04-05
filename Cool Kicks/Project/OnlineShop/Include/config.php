<?php
    error_reporting(0);
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "shopping";

    $com = mysqli_connect($servername,$username,$password,$dbname);
    if($com){
        //echo "Connected" ;
    }else{
        die("Connection failed due to: ".mysqli_connect_error());
    }
?>