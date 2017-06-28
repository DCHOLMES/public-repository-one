<?php

	include('includes/database.inc');
	
	# 1. Query list of all possible classes and short codes, where year matches year (2014).
	$year=2014;
	$sql = "SELECT * FROM HSClasses WHERE year='".$year."' ORDER BY name";
	$result = mysqli_query($connection,$sql);
	$hsclasses['code'][] = array();
	$hsclasses['class'][] = array();
	$hsclasses['raw_20'][] = array();
	$hsclasses['raw_25'][] = array();
	$hsclasses['raw_30'][] = array();
	$hsclasses['raw_35'][] = array();
	$hsclasses['raw_40'][] = array();
	$hsclasses['raw_45'][] = array();
	$hsclasses['raw_50'][] = array();

	if ($result==false)
	{
		echo 'Highschool classes failed to load.';
	} else {
		
		$i=0;
		while($row = mysqli_fetch_array($result))
		{
			$code = $row['code'];
			$hsclasses['code'][$i] = $row['code'];
			$hsclasses['class'][$i] = $row['name'];
			$hsclasses['raw_20'][$code] = $row['raw_20'];
			$hsclasses['raw_25'][$code] = $row['raw_25'];
			$hsclasses['raw_30'][$code] = $row['raw_30'];
			$hsclasses['raw_35'][$code] = $row['raw_35'];
			$hsclasses['raw_40'][$code] = $row['raw_40'];
			$hsclasses['raw_45'][$code] = $row['raw_45'];
			$hsclasses['raw_50'][$code] = $row['raw_50'];
			$i++;
		}
		$hsclasses['size']=$i;
	}
	
	# Query the scaled_agg table
	$sql = "SELECT * FROM scaled_agg WHERE year='".$year."' ORDER BY ATAR";
	$result = mysqli_query($connection,$sql);
	$scaled_agg['ATAR'][] = array();
	$scaled_agg['min_aggregate'][] = array();

	
	if ($result==false)
	{
		echo 'Scaled Aggregate Scores failed to load.';
	} else {
		
		$i=0;
		while($row = mysqli_fetch_array($result))
		{
			$scaled_agg['ATAR'][$i] = $row['ATAR'];
			$scaled_agg['min_aggregate'][$i] = $row['min_aggregate'];
			$i++;
		}
		$scaled_agg['size']=$i;
	}
	
	$data['scaled_agg'] = $scaled_agg;
	$data['hsclasses'] = $hsclasses;
?>
	
	<h2>Calculate your ATAR:</h2>
		<form class="form-inline" role="form" id="calculator_form">
			<table class="table" id="calculator_table">
				<th></th><th>Highschool Subject</th><th>Raw</th><th>Scaling</th><th>Score</th>
<?php 
				for($i=1; $i<=8; $i++)
				{
					echo '
					<tr><td><label for="class'.$i.'label">Class '.$i.': </label></td><td><select id="class'.$i.'" name="class'.$i.'" onchange="getAgg(this.form, '.htmlspecialchars(json_encode($data)).')" class="form-control"><option value="">Select Class</option>';
					for($j=0; $j<$hsclasses['size']; $j++)
					{
						// Limit position 1 to English only
						if($i==1)
						{
							$theClass=$hsclasses['code'][$j];
							echo $theClass;
							if($theClass=="EN"||$theClass=="EF"||$theClass=="EG"||$theClass=="LI")
								echo "<option value='".$hsclasses['code'][$j]."'>".$hsclasses['class'][$j]."</option>";
						} else {
							echo "<option value='".$hsclasses['code'][$j]."'>".$hsclasses['class'][$j]."</option>";
						}
					}
					echo '</select></td>
					<td><label for="result'.$i.'label"> </label><input class="form-control" type="number" id="raw'.$i.'" name="raw'.$i.'" min="0" max="50" class="rawScore"  onchange="getAgg(this.form, '.htmlspecialchars(json_encode($data)).')"/></td>
					<td id="scaling'.$i.'">+0</td>
					<td><input class="form-control" type="text" id="class'.$i.'result" name="class'.$i.'result" disabled="disabled" value="0" size="3"/></td></tr>
					';
				}
?>
			</table>
			<span id="results_bit">
				Score: <span id="final_score">0</span><br/>
				ATAR: <span id="final_atar">0</span><br/>
			</span>
			<br />
			
			<input type="hidden" value="1" id="atar_submit" name="atar_submit">
			<input type="hidden" value="0" id="offset" name="offset">
			<input type="hidden" value="0" id="sort_direction" name="sort_direction">
			<input type="hidden" value="0" id="sort_cat" name="sort_cat">
			<input type="hidden" value="" id="limit_by" name="limit_by">
			<input type ="hidden" value="" id="keyword_value" name="keyword_value">
			
			<input id="calculator_submit" type="button" value="Find Courses" onClick="updateCourses()"/>
			<br/>
			<div id="uni_controls"> </div>
			<div id="uni_courses"> </div>
		</form>
