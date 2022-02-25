<?php
	include("conn.php"); //we can include other php files like this!
	$u_firstname = $_POST['name']; //request de kullanabilirsin kontrol etmek için
	$u_lastname = $_POST['surname'];
	$u_email = $_POST['email'];
	$u_phonenumber = $_POST['phone'];
	$u_student = $_POST['student'];
	$u_department = $_POST['department'];
	$mysql_qry = "INSERT INTO USER_ (fname,sname,email,pnumber,student,department) VALUES ('$u_firstname','$u_lastname','$u_email','$u_phonenumber','$u_student','$u_department')";
	$result = mysqli_query($conn , $mysql_qry);	
	echo "Form submitted successfully!";
	
?>