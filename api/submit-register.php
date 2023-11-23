<?php
// دستور اتصال به سرور پایگاه داده و ایجاد لینک ارتباطی
$link = mysqli_connect("localhost","root","","shopdb");
// بررسی صحت اتصال به سرور پایگاه داده
if(!$link)
	exit("<p>Connect to database failed</p>");

// ذخیره اطلاعات دریافت شده از سمت کلاینت در متغیرها
$user = $_POST['USERNAME'];
$pass = $_POST['PASSWORD'];
$name = $_POST['NAME'];
$email = $_POST['EMAIL'];
$mobile = $_POST['MOBILE'];
$jensiat = $_POST['GENDER'];
$address = $_POST['ADDRESS'];


// نوشتن کوئری مورد نیاز
$query = "INSERT INTO `user` (`uID`, `username`, `password`, `name`, `email`, `mobile`, `jensiat`, `address`) 
VALUES (NULL, '$user', '$pass', '$name', '$email', '$mobile', '$jensiat', '$address')";

// اجرای کوئری در پایگاه داده
if(mysqli_query($link , $query)) 
	print("success");
else print("fail");

mysqli_close($link);

?>