<?php

	
	//polaczenie do bazy danych, jakie s¹ zmienne?
	$host = "";
	$db_user = "";
	$db_password = "";
	$db_name = "";
	
	$polaczenie = @new mysqli($host, $db_user, $db_password, $db_name);
	
	if ($polaczenie->connect_errno!=0)
	{
		echo "Error: ".$polaczenie->connect_errno;
	}
	else
	{
		$login = $_POST['login'];
		$haslo = $_POST['haslo'];
		
		$login = htmlentities($login, ENT_QUOTES, "UTF-8");
	
		if ($rezultat = @$polaczenie->query(
		sprintf("SELECT * FROM uzytkownicy WHERE user='%s' AND pass='%s'",
		mysqli_real_escape_string($polaczenie,$login))))
		{
			$user_w_db = $rezultat->num_rows;
			if($user_w_db==1)
			{
				$wiersz = $rezultat->fetch_assoc();
				
				if (password_verify($haslo, $wiersz['password']))
				{
					//utworzenie i przesylanie tokena?
					
					unset($blad);
					header('Location: index.php');
				}
				else {
					$blad= '<span style="color:red">Nieprawid³owy login lub has³o!</span>';
					header('Location: index.html');
				}
				
			}
			else {
				$blad= '<span style="color:red">Nieprawid³owy login lub has³o!</span>';
				header('Location: index.html');
				
			}
			
		}
		
		$polaczenie->close();
	}
	
?>