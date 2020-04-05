<?php
    include 'Include/config.php';
    if(!isset($_SESSION)){
      session_start();
    }
    if(isset($_SESSION['Email'])){
      $user = $_SESSION['Email'];
    }else{
      $user = "guest";
    }
    $item = $_GET['item'];
    $array = explode(" ", $item);
    $id = $array[0] ;
    $DB = $array[1];
?>

<!DOCTYPE html>
<html>

<head>
    <title>Cool Kicks</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

     <script src="jquery-3.4.1.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="index.css">
</head>

<body>
  <header>
      <nav class="navbar navbar-expand-lg fixed-top navbar-light bg-white" id="firstnav">

          <img src="Images/logo.jpg" class="rounded float-left" alt="Logo" id="logo" width="3.1%" height="3.1%">

          <h2 class="text-dark" id="shop">Cool Kicks</h2>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="sr-only"></span>
              <i class="fa fa-align-justify"></i>
          </button>
          <form action="product_display.php" method="post" class="form-inline my-2 my-lg-0" style="padding-left:10%;">
    <input class="form-control mr-sm-2" type="text" name="se" placeholder="Search" aria-label="Search">
    <button class="btn btn-dark" type="submit" name="search">Search</button>
  </form>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav mr-5 ml-auto">
                  <li>
                    <a href="login.php">
                      <img src="Images/guest.jpg" class="rounded float-left" alt="" width="40px" height="40px" style="display: <?php if($user != "guest"){echo "none";} ?>;">
                    </a>
                  </li>
                  <li>
                    <a href="profile.php">
                      <img src="<?php echo $_SESSION['image']; ?>" class="rounded float-left" alt="" width="40px" height="40px" style="display: <?php if($user == "guest"){echo "none";} ?>;">
                    </a>
                  </li>
              </ul>
          </div>

      </nav>

      <nav class="navbar navbar-expand-lg navbar-light bg-light" id="secondtop">

          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent1" aria-controls="navbarSupportedContent1" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent1">
              <ul class="navbar-nav ml-auto mr-auto">
                  <li class="nav-item active">
                      <a class="nav-link" href="index.php">Home</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="order.php">My Orders</a>
                  </li>
                  
                  <li class="nav-item">
                      <a class="nav-link" href="#contact">Contact Us</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link btn btn-primary" href="carts.php">
                          <i class="fa fa-shopping-cart text-light"></i>
                          <span id="cart-item" style="color:#000000;"> Items in Cart</span>
                      </a>
                  </li>
              </ul>
          </div>
      </nav>
  </header>

  <div id="content" class="container-fluid jumbotron">
      <div class="text">
          <h3>Selected</h3>
      </div>
      <?php
        $query = "SELECT * FROM ".$DB." WHERE ID='$id' ";
        $data = mysqli_query($com,$query);
        $row = mysqli_num_rows($data);
        if($row){
          while($result = mysqli_fetch_assoc($data)){
      ?>
      <div class="container">
        <div class="row">
          <div class="col">
            <div class="row">
              <div class="col">
                <img src="<?php echo $result['Image1']; ?>" class="rounded float-left" alt="" width="220px" height="220px">
              </div>
              <div class="col">
                <img src="<?php echo $result['Image2']; ?>" class="rounded float-left" alt="" width="220px" height="220px">
              </div>
            </div>
            <div class="row">
              <div class="col">
                <img src="<?php echo $result['Image3']; ?>" class="rounded float-left" alt="" width="220px" height="220px">
              </div>
              <div class="col">
                <img src="<?php echo $result['Image4']; ?>" class="rounded float-left" alt="" width="220px" height="220px">
              </div>
            </div>
          </div>
          <div class="col">
            <h6 style="color:black"><b>Name</b></h6>
            <div class="form-group">
                <h6 style="color:black"><?php echo $result['Name']; ?></h6>
            </div>
            <h6 style="color:black"><b>Brand</b></h6>
            <div class="form-group">
                <h6 style="color:black"><?php echo $result['Brand']; ?></h6>
            </div>
            <?php if($result['Discount'] != 0){
              $dis = $result['Price'] - ($result['Discount']/100)*$result['Price'] ;
            ?>
            <h6 style="color:black"><b><?php echo "Price(".$result['Discount']."% off)";?></b></h6>
            <div class="form-group">
                <h6 style="color:black"><del><?php echo "Rs. ".$result['Price']; ?></del></h6>
                <h6 style="color:black"><?php echo "Rs. ".$dis; ?></h6>
            </div>
            <?php
            }else{
            ?>
            <h6 style="color:black"><b><?php echo "Price";?></b></h6>
            <div class="form-group">
                <h6 style="color:black"><?php echo "Rs. ".$result['Price']; ?></h6>
            </div>
            <?php
            }
            if($result['Quantity'] < 100){
            ?>
            <h6 style="color:black"><b><?php echo "Quantity";?></b></h6>
            <div class="form-group">
                <h6 style="color:black"><?php echo "Limited"; ?></h6>
            </div>
            <?php
            }else{
            ?>
            <h6 style="color:black"><b><?php echo "Quantity";?></b></h6>
            <div class="form-group">
                <h6 style="color:black"><?php echo "In Stock"; ?></h6>
            </div>
            <?php
            }
            ?>
            <h6 style="color:black"><b><?php echo "Size";?></b></h6>
            <div class="form-group">
                <h6 style="color:black"><?php echo $result['Size']; ?></h6>
            </div>
            <h6 style="color:black"><b><?php echo "For";?></b></h6>
            <div class="form-group">
                <h6 style="color:black"><?php echo $result['Type']; ?></h6>
            </div>
            <h6 style="color:black"><b><?php echo "Type";?></b></h6>
            <div class="form-group">
                <h6 style="color:black"><?php echo $result['UsedIn']; ?></h6>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="form-group">
              <h6 style="color:black"><b><?php echo "Description";?></b></h6>
              <h6 style="color:black"><?php echo $result['Description']; ?></h6>
          </div>
        </div>
        <?php
        if($user != "guest"){
        ?>
        <div class="row">
          <div class="col">
            <a href="add_to_cart.php?add_to_cart=<?php echo $user.",".$result['Image1'].",".$result['Name'].",".$result['Brand'].",".$result['Price'];?>" class="btn btn-dark">Add To cart</a>
          </div>
          <div class="col">
            <a href="add_to_order.php?id=<?php echo $user.",".$result['Image1'].",".$result['Name'].",".$result['Brand'].",".$result['Price'];?>" class="btn btn-dark">Order</a>
          </div>
        </div>
        <?php
      }else{
      ?>
      <div class="row">
        <div class="col">
          <a href="login.php" class="btn btn-dark">Add To cart</a>
        </div>
        <div class="col">
          <a href="login.php" class="btn btn-dark">Order</a>
        </div>
      </div>
      <?php
      }
        ?>
      </div>
      <?php
    }}
      ?>
  </div>



  <footer class="page-footer font-small mdb-color bg-dark pt-4" id="footerimage">

  <!-- Footer Links -->
  <div class="container text-center text-md-left" id="contact">

    <!-- Footer links -->
    <div class="row text-center text-md-left mt-3 pb-3">

      <!-- Grid column -->
      <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
        <h6 class="text-uppercase mb-4 font-weight-bold">Cool Kicks</h6>
        <p>Every type of shoe.</p>
      </div>
      <!-- Grid column -->

      <hr class="w-100 clearfix d-md-none">

      <hr class="w-100 clearfix d-md-none">

      <!-- Grid column -->
      <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mt-3">
        <h6 class="text-uppercase mb-4 font-weight-bold">Useful links</h6>
        <p>
          <a href="order.php">Your Orders</a>
        </p>
        <p>
          <a href="carts.php">Cart</a>
        </p>
        <p>
          <a href="index.php">Home</a>
        </p>
      </div>

      <!-- Grid column -->
      <hr class="w-100 clearfix d-md-none">

      <!-- Grid column -->
      <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
        <h6 class="text-uppercase mb-4 font-weight-bold">Contact</h6>
        <p>
          <i class="fa fa-home mr-3"></i>GLA University</p>
        <p>
          <i class="fa fa-envelope mr-3"></i>cool_kicks@gmail.com</p>
        <p>
          <i class="fa fa-phone mr-3"></i>xxxxxxxxxx</p>
        <p>
          <i class="fa fa-print mr-3"></i>xxxxxxxxxx</p>
      </div>
      <!-- Grid column -->

    </div>
    <!-- Footer links -->

    <hr style="background-color:white;">

    <!-- Grid row -->
    <div class="row d-flex align-items-center">

      <!-- Grid column -->
      <div class="col-md-7 col-lg-8">

        <!--Copyright-->
        <p class="text-center text-md-left">© 2019 Copyright:
          <a href="https://mdbootstrap.com/education/bootstrap/">
            <strong> MDBootstrap.com</strong>
          </a>
        </p>

      </div>
      <!-- Grid column -->

      <!-- Grid column -->
      <div class="col-md-5 col-lg-4 ml-lg-0">

        <!-- Social buttons -->
        <div class="text-center text-md-right">
          <ul class="list-unstyled list-inline">
            <li class="list-inline-item">
              <a class="btn-floating btn-sm rgba-white-slight mx-1">
                <i class="fa fa-facebook"></i>
              </a>
            </li>
            <li class="list-inline-item">
              <a class="btn-floating btn-sm rgba-white-slight mx-1">
                <i class="fa fa-twitter"></i>
              </a>
            </li>
            <li class="list-inline-item">
              <a class="btn-floating btn-sm rgba-white-slight mx-1">
                <i class="fa fa-instagram"></i>
              </a>
            </li>
            <li class="list-inline-item">
              <a class="btn-floating btn-sm rgba-white-slight mx-1">
                <i class="fa fa-linkedin"></i>
              </a>
            </li>
          </ul>
        </div>

      </div>
      <!-- Grid column -->

    </div>
    <!-- Grid row -->

  </div>
  <!-- Footer Links -->

  </footer>
  <!-- Footer -->

</body>

</html>
