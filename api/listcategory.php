<?php
// دستور اتصال به سرور پایگاه داده و ایجاد لینک ارتباطی
$link = mysqli_connect("localhost","root","","shopdb");
/* change character set to utf8mb4 */
mysqli_set_charset($link, "utf8mb4");
// بررسی صحت اتصال به سرور پایگاه داده
if(!$link)
	exit("fail");


$query = "SELECT catID , catName FROM `category`";
$result = mysqli_query($link , $query);

$tedadCategory = mysqli_num_rows($result);
if( $tedadCategory >= 1 )
{
	print("[");
	$i=0;
	//دسته یندی شماره صفر
	print("{");
			print('"CATID":"0",');
			print('"CATNAME":"All products"');
		print("}, ");
	while($row = mysqli_fetch_array($result))
	{
		$catID = $row['catID'];
		$catName = $row['catName'];
		$i++;
		//چاپ با فرمت جی سون برای ارسال به سرور
		print("{");
			print('"CATID":"'.$catID.'",');
			print('"CATNAME":"'.$catName.'"');
		print("} ");
		if($i < $tedadCategory)
			print(","); //برای اینکه برای اخرین رشته و رو تایپ نکنه
	}
	print("]"); //فرمت جی سون برای ارایه ای از چند رشته
}
else {
	print("nocategory");
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