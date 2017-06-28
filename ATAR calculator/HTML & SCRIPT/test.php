<?php
/* 
Authors: Declan Holmes, Sean Ackerley, Jonathon Hein
Description: Script for returning database entries in the form of a table
*/
	include('includes/database.inc');

	// Variables
	$atar = $_GET["atar"];
	$offset = $_GET["offset"];
	$sort = $_GET["sort"];
	$dir = $_GET["sort_direction"];
	$dir_string = "DESC";
	$limit_by = $_GET["limit"];
	$keyword = $_GET["keyword"];
	$sql_refined = "";
	$sql;

	// Function for setting the refinement string
	function setRefinementStr($limit_by, $keyword, &$sql_refined) {
		
		if($keyword != "" && $limit_by != "") {
		
			if($limit_by == "UniName") {
				
				$sql_refined = "' AND LOWER(Uni.Name) LIKE '%" . $keyword . "%";
			} else {
			
				$sql_refined = "' AND LOWER(Degree." . $limit_by . ") LIKE '%" . $keyword . "%";
			}
		}
	}

	// Function for settiing the direction string
 	function setDirStr($dir, $sort, &$dir_string) {
		
		if($dir == 0 && $sort != 3) {
			$dir_string = "";
		} else if($sort != 3){
			$dir_string = "DESC";
		} else if($dir == 0) {
			$dir_string = "DESC";
		} else {
			$dir_string = "";
		}
	}

	// Function for setting SQL query string
	function setSQL($sort, $atar, $sql_refined, $dir_string, $offset, &$sql) {
		
		if($sort == 1) {
		
			$sql = "SELECT Degree.*, Uni.Name AS UniName FROM Degree INNER JOIN Uni ON" 
				. " Degree.Uni = Uni.uid WHERE ScoreReq <= '" . $atar . $sql_refined
					. "' ORDER BY Name " . $dir_string . " LIMIT 50 OFFSET " . $offset;
		} else if($sort == 2) {
		
			$sql = "SELECT Degree.*, Uni.Name AS UniName FROM Degree INNER JOIN Uni ON" 
				. " Degree.Uni = Uni.uid WHERE ScoreReq <= '" . $atar . $sql_refined
					. "' ORDER BY UniName " . $dir_string . " LIMIT 50 OFFSET " . $offset;
		} else if($sort == 4) {
			
			$sql = "SELECT Degree.*, Uni.Name AS UniName FROM Degree INNER JOIN Uni ON" 
				. " Degree.Uni = Uni.uid WHERE ScoreReq <= '" . $atar . $sql_refined
					. "' ORDER BY Degree.Location " . $dir_string . " LIMIT 50 OFFSET " . $offset;
		} else {

			$sql = "SELECT Degree.*, Uni.Name AS UniName FROM Degree INNER JOIN Uni ON" 
				. " Degree.Uni = Uni.uid WHERE ScoreReq <= '" . $atar . $sql_refined
					. "' ORDER BY Degree.ScoreReq " . $dir_string . " LIMIT 50 OFFSET " . $offset;
		}
	}

	setRefinementStr($limit_by, $keyword, $sql_refined);

	setDirStr($dir, $sort, $dir_string);

	if($offset < 0) {
		$offset = 0;
	}
	
	// query string based on sort variable
	setSQL($sort, $atar, $sql_refined, $dir_string, $offset, $sql);

	
	// get results of query
	$result = $connection->query($sql);

	while($result->num_rows == 0) {
		
		$sql = "SELECT Degree.*, Uni.Name AS UniName FROM Degree INNER JOIN Uni ON" 
			. " Degree.Uni = Uni.uid WHERE ScoreReq <= '" . $atar . $sql_refined
				. "' ORDER BY Degree.ScoreReq " . $dir_string . " LIMIT 50 OFFSET " . $offset -= 50;

		$result = $connection->query($sql);
	}

	// write the results to client
	echo "<div style=\"height:500px;overflow:auto;\">";
	echo "<table id=\"course_tab\">";
	echo "<th class=\"course_table_header\" onclick=\"sortAndDisplay(1)\"" 
		. "style=\"width:10px;\">Course Name</th><th class=\"course_table_header\" onclick=\"sortAndDisplay" 
			. "(2)\">Institution</th><th class=\"course_table_header\" onclick=\"sortAndDisplay(3)\"" 
					. " width=\"200\">ATAR Score</th><th class=\"course_table_header\" onclick=" 
						. "\"sortAndDisplay(4)\">Location</th>";

	if ($result->num_rows > 0) {
	
		// output data of each row
		while($row = $result->fetch_assoc()) {
			
			echo "<tr>";
			echo "<td><a href=\"" . $row["URL"] ."\">" . $row["Name"] 
				. "</a></td><td>" .$row["UniName"] . "</td><td>" 
					. $row["ScoreReq"] . "</td><td>" 
						. $row["Location"] . "</td>";
			echo "</tr>";
		}
	} else {
    
	}
	

	echo "</table></div>";
	$connection->close();
?>
