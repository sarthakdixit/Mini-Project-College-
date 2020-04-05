<?php
    include 'Include/config.php';
    session_start();

    if(isset($_POST['add_record']) && is_numeric($_POST['phoneNumber'])){
      $fName = $_POST['first_name'];
      $lName = $_POST['last_name'];
      $phone = $_POST['phoneNumber'];
      $add = $_POST['address'];
      $em = $_SESSION['Email'];
      $query = "UPDATE `users` SET `FirstName`='$fName', `LastName`='$lName', `PhoneNumber`='$phone', `Address`='$add' WHERE `Email`='$em' " ;
      $data = mysqli_query($com,$query);
        if($data){
          $_SESSION['FirstName'] = $fName;
          $_SESSION['LastName'] = $lName;
          $_SESSION['PhoneNumber'] = $phone;
          $_SESSION['add'] = $add;
          header('location: profile.php');
        }else{
            header('location: edit_profile.php');
        }
    }else{
      header('location: edit_profile.php');
    }
?>
