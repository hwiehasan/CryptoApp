<?php
$product_id = urldecode($_POST['PRODUCTID']);
$title2 = urldecode($_POST['TITLE']);
$title = (int)$title2
$description = urldecode($_POST['DESCRIPTION']);
$price2 = urldecode($_POST['PRICE']);
$price = (int)$price2

// connect to database
$db_connection = mysqli_connect("localhost", "root", "", "shopdb");
mysqli_set_charset($db_connection, "utf8mb4");

// get image 
$select_query = "SELECT image FROM `kala` WHERE `kID` = '$product_id'";
$result = mysqli_query($db_connection, $select_query);
$row = mysqli_fetch_array($result);
// set image
if(isset($_FILES['IMAGE'])) {
    $image_address =  "/files/images/user/". $_FILES['IMAGE']['name'];

    if(!move_uploaded_file($_FILES['IMAGE']['tmp_name'], "..".$image_address))
        exit('{"message" : "fail-upload image"}');

} else $image_address = $row['image'];

// update database
$update_query = "UPDATE `kala` SET `kName` = '$title', `description` = '$description', `price` = '$price', `image` = '$image_address' WHERE `kID` = '$product_id'";

if(mysqli_query($db_connection, $update_query))
    exit('{"message" : "success"}');
else exit('{"message" : "fail-query not match"}');

mysqli_close($db_connection);
?>