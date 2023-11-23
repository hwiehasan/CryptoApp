<?php
// دستور اتصال به سرور پایگاه داده و ایجاد لینک ارتباطی
$link = mysqli_connect("localhost","root","","shopdb");
// بررسی صحت اتصال به سرور پایگاه داده
if(!$link)
	exit("<p>اتصال به پایگاه داده با خطا مواجه شد !</p>");
//else print("<p>اتصال به پایگاه داده با موفقیت انجام شد.</p>");	

$user = $_POST['USERNAME'];

// نوشتن کوئری مورد نیاز
$query = "SELECT profileimage FROM `user` where username = '$user' ";

// اجرای کوئری در پایگاه داده - ذخیره ی نتیجه در یک متغیر
$result = mysqli_query($link , $query); 

// بررسی تعداد ریف های برگشت داده شده از پایگاه داده
if(mysqli_num_rows($result) == 1 )
{
	//print("success");
	$row = mysqli_fetch_array($result);


	print("/files/userimages/".$row['profileimage']);
}
else print("fail");

mysqli_close($link);
?>