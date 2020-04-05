<?php
    include 'Include/config.php';

    if(isset($_POST['add_record']) && is_numeric($_POST['phoneNumber'])){
      $sql = "SELECT Email FROM users WHERE `Email`=".$_POST['email']." ";
      $result = mysqli_query($sql);
      if(mysqli_num_rows($result) > 0){
        header('location: register.php');
      }else{
        $f_name = $_POST['first_name'];
        $l_name = $_POST['last_name'];
        $email = $_POST['email'];
        $pass = password_hash($_POST['password'], PASSWORD_DEFAULT);
        $phone = $_POST['phoneNumber'];
        $gender = $_POST['gender'];
        $add = $_POST['address'];
        $file = $_FILES['image'];

        $image = "Images/".$file['name'];
        move_uploaded_file($file['tmp_name'], $image);

        $query = "INSERT INTO users VALUE('$email', '$f_name', '$l_name', '$pass', '$phone', '$gender', '$add', '$image') ";
        $data = mysqli_query($com,$query);
        header('location: login.php');
      }
    }
?>
