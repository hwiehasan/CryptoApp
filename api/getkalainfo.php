<?php
// دستور اتصال به سرور پایگاه داده و ایجاد لینک ارتباطی
$link = mysqli_connect("localhost","root","","shopdb");
/* change character set to utf8mb4 */
mysqli_set_charset($link, "utf8mb4");
// بررسی صحت اتصال به سرور پایگاه داده
if(!$link)
	exit("fail");


$kalaID = $_POST['KALAID'];
//کوئری چک شود شاید اشتباه تایپی
$query = "SELECT `kID`, `kName`, `price`, `weight`, `num`, `color`, kala.`catID` ,catName, `image`, `description`, kala.`userID`, username, `submitDate`, `view` FROM `kala` , user , category
WHERE kala.catID = category.catID and  kala.userID = user.uID and kID = $kalaID";
$result = mysqli_query($link , $query);

$tedadKala = mysqli_num_rows($result);
if( $tedadKala >= 1 )
{
	print("[");
	$i=0;
	while($row = mysqli_fetch_array($result))
	{
		$kID = $row['price'];
		$kName = $row['view'];
		$image = $row['image'];	
		$description = $row['description'];
		$submitDate = $row['submitDate'];
		$price = $row['price'];
		$i++;
		//چاپ با فرمت جی سون برای ارسال به سرور
		print("{");
			print('"KPRICE":"'.$kID.'",');
			print('"KVIEW":"'.$kName.'",');
			print('"KIMAGE":"'.$image.'",');
			print('"KDESCRIPTION":"'.$description.'",');
			print('"KPRICE":"'.$price.'",');
			print('"KSUBMITDATE":"'.$submitDate.'"');
		print("} ");
		if($i < $tedadKala)
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
 
//نباید اینتر اسپیس بعد از قسمت های پی اچ پی باشه
*/



?>