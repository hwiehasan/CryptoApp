<?php
// دستور اتصال به سرور پایگاه داده و ایجاد لینک ارتباطی
$link = mysqli_connect("localhost","root","","shopdb");
// بررسی صحت اتصال به سرور پایگاه داده
if(!$link)
	exit("<p>Connect to database failed</p>");

// ذخیره اطلاعات دریافت شده از سمت کلاینت در متغیرها
$kname = $_POST['KNAME'];
$kprice = $_POST['KPRICE'];
$kcatid = $_POST['KCATID'];
$kimage = $_POST['KIMAGE'];
$kdescription = $_POST['DESCRIPTION'];
$userid = $_POST['USERID'];




// نوشتن کوئری مورد نیاز
$query = "INSERT INTO `kala` (`kID`, `kName`, `price`, `weight`, `num`, `color`, `catID` ,`image`, `description`, `userID`, `submitDate`) 
VALUES (NULL, '$kname', '$kprice',NULL,NULL,NULL,  '$kcatid'    , '$kimage' , '$kdescription', '$userid', NULL)";

// اجرای کوئری در پایگاه داده
if(mysqli_query($link , $query)) 
	print("success");
else print("fail");

mysqli_close($link);

?>