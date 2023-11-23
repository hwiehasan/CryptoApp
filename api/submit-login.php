<?php
// دستور اتصال به سرور پایگاه داده و ایجاد لینک ارتباطی
$link = mysqli_connect("localhost","root","","shopdb");
// بررسی صحت اتصال به سرور پایگاه داده
if(!$link)
	exit("<p>اتصال به پایگاه داده با خطا مواجه شد !</p>");
//else print("<p>اتصال به پایگاه داده با موفقیت انجام شد.</p>");	

$user = $_POST['USERNAME'];
$pass = $_POST['PASSWORD'];

// نوشتن کوئری مورد نیاز
$query = "SELECT * FROM `user` where username = '$user' and password = '$pass' ";

// اجرای کوئری در پایگاه داده - ذخیره ی نتیجه در یک متغیر
$result = mysqli_query($link , $query); 

// بررسی تعداد ریف های برگشت داده شده از پایگاه داده
if(mysqli_num_rows($result) == 1 )
{

	//انتقال اطلاعات به سمت کلاینت
	$row = mysqli_fetch_array($result);
	//فرمتش بصورت زیر بوده که شکسته شده به پایینی، در اصل این فرمت جی سون سمت پی اچ پی هست
	// {"USERNAME":"alireza","NAME":"علیرضا","MOBILE":"09124569585","JENSIAT":"0","EMAIL":"alireza@gmail.com","ADDRESS":"fars-lar", "PROFILEIMAGE":"aaa.png"}
	print("{");
	print('"USERID":"'.$row['uID'].'",');
	print('"USERNAME":"'.$row['username'].'",');
	print('"NAME":"'.$row['name'].'",');
	print('"MOBILE":"'.$row['mobile'].'",');
	print('"JENSIAT":"'.$row['jensiat'].'",');
	print('"EMAIL":"'.$row['email'].'",');
	print('"ADDRESS":"'.$row['address'].'",');
	print('"PROFILEIMAGE":"'.$row['profileimage'].'"');
	print("}");
	




}
else print("fail");

mysqli_close($link);
?>