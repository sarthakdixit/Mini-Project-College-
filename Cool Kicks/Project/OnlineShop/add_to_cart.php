<?php
      include 'Include/config.php';
      $id = $_GET['add_to_cart'];
      $arr = explode(",", $id);
      $query = "INSERT INTO cart VALUES(DEFAULT, '$arr[0]', '$arr[1]', '$arr[2]', '$arr[3]', '$arr[4]')";
      $data = mysqli_query($com,$query);
      header('location: carts.php');
?>
