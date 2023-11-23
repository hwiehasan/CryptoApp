<?php
// دستور اتصال به سرور پایگاه داده و ایجاد لینک ارتباطی
$link = mysqli_connect("localhost","root","","shopdb");
/* change character set to utf8mb4 */
mysqli_set_charset($link, "utf8mb4");
// بررسی صحت اتصال به سرور پایگاه داده
if(!$link)
	exit("fail");


$userID = $_POST['USERID'];
$query = "SELECT username , name , email, mobile, jensiat , address , profileimage FROM `user` where uID = $userID";
$result = mysqli_query($link , $query);

$tedadUser = mysqli_num_rows($result);
if( $tedadUser >= 1 )
{
	print("[");
	$i=0;
	while($row = mysqli_fetch_array($result))
	{
		$profileimage = $row['profileimage'];
		$username = $row['username'];
		$name = $row['name'];
		$email = $row['email'];	
		$mobile = $row['mobile'];
		$gender = $row['jensiat'];
		$address = $row['address'];
		$i++;
		//چاپ با فرمت جی سون برای ارسال به سرور
		print("{");
			print('"UIMAGE":"'.$profileimage.'",');
			print('"USERNAME":"'.$username.'",');
			print('"UNAME":"'.$name.'",');
			print('"UEMAIL":"'.$email.'",');
			print('"UMOBILE":"'.$mobile.'",');
			print('"UGENDER":"'.$gender.'",');
			print('"UADDRESS":"'.$address.'"');
		print("} ");
		if($i < $tedadUser)
			print(","); //برای اینکه برای اخرین رشته و رو تایپ نکنه
	}
	print("]"); //فرمت جی سون برای ارایه ای از چند رشته
}
else {
	print("noproducts");
}


/*
تابع تریم
trim
 $str = "zahra,"; //, اضافیه
 
 print(   trim($str) , ","   );
 
 print(   str_replace(     ",]","",$str)    );
 

*/
//نباید اینتر اسپیس بعد از قسمت های پی اچ پی باشه
?>