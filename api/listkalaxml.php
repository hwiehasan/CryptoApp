<?php
// دستور اتصال به سرور پایگاه داده و ایجاد لینک ارتباطی
$link = mysqli_connect("localhost","root","","shopdb");
/* change character set to utf8mb4 */
mysqli_set_charset($link, "utf8mb4");
// بررسی صحت اتصال به سرور پایگاه داده
if(!$link)
	exit("fail");

$catID = $_POST['CATEGORYID'];
if($catID == 0)
{	
	//نمایش لیست همه ی کالاهای ثبت شده
	$queryConditions = "";
}
else{
	//نمایش لیست کالای به خصوص
	$queryConditions = " where catID = $catID ";
}

$query = "SELECT kID , price , kName , image, description , submitDate FROM `kala` $queryConditions";
$result = mysqli_query($link , $query);

$tedadKala = mysqli_num_rows($result);
if( $tedadKala >= 1 )
{	
	print('<?xml version="1.0"?>');
	print("<listkala>"); //تنها تفاوت با جیسون
	$i=0;
	while($row = mysqli_fetch_array($result))
	{
		$kID = $row['kID'];
		$price = $row['price'];	
		$kName = $row['kName'];
		$image = $row['image'];	
		$description = $row['description'];
		$submitDate = $row['submitDate'];
		$i++;
		//چاپ با فرمت جی سون برای ارسال به سرور
		print("<kala>");//تنها تفاوت با جیسون
			print('<KID>'.$kID.'</KID>');
			print('<KPRICE>'.$price.'</KPRICE>');
			print('<KNAME>'.$kName.'</KNAME>');
			print('<KIMAGE>'.$image.'</KIMAGE>');
			print('<KDESCRIPTION>'.$description.'</KDESCRIPTION>');
			print('<KSUBMITDATE>'.$submitDate.'</KSUBMITDATE>');
		print("</kala> ");
	}
	print("</listkala>"); //فرمت xml برای چندین رشته
	
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