function newXMLHTTP()
{
	//use this function for XMLHttp objects, to reduce code repetition
	var xmlhttp;
	if (window.XMLHttpRequest)
	{
	// modern browsers
		xmlhttp = new XMLHttpRequest();
	}
	else
	{
	// IE6
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	return xmlhttp;
}

function calculator()
{
	//instantiates the content div with calculator.php on load
	var xmlhttp = newXMLHTTP();
	xmlhttp.onreadystatechange = function()
	{
			document.getElementById("content").innerHTML = xmlhttp.responseText;	
	}
	xmlhttp.open("GET", "calculator.php", true);
	xmlhttp.send();
}

function aboutDet()
{
	//changes content div to about.php
	var xmlhttp = newXMLHTTP();
	xmlhttp.onreadystatechange = function()
	{
		document.getElementById("content").innerHTML = xmlhttp.responseText;
	}
	xmlhttp.open("GET", "about.php", true);
	xmlhttp.send();
}

function contactDet()
{
	//changes content div to contact.php
	var xmlhttp = newXMLHTTP();
	xmlhttp.onreadystatechange = function()
	{
		document.getElementById("content").innerHTML = xmlhttp.responseText;
	}
	xmlhttp.open("GET", "contact.php", true);
	xmlhttp.send();
}

function adminLogin()
{
	//to be completed if adminLogin is necessary, or removed otherwise
	alert("Login window placeholder.");
}

function addClassInput()
{
	var xmlhttp = newXMLHTTP();
	xmlhttp.onreadystatechange = function()
	{
		//to be implemented with function to add additional inputs in the calculator form if necessary
	}
	return;
}

// Shows uni classes available based upon atar score
function displayClasses(atar, offset, sort, dir)
{
	
	var keyword = document.getElementById("keyword_value").value;
	var limit = document.getElementById("limit_by").value;

	document.getElementById("calculator_submit").value="Update Courses";
	document.getElementById("uni_controls").innerHTML="<p>Limit by <select id=\"limit_box\"><option value=\"\">none</option><option value=\"UniName\">Institution</option><option value=\"Name\">Course name</option><option value=\"Location\">Location</option></select> by keyword <input id=\"keyword_box\" type=\"text\"><input type=\"button\" value=\"Refine\" onclick=\"setLimitAndKey()\"></br></p><input id=\"prev_butt\"type=\"button\" onclick=\"prevClasses()\"value=\"Prev\"><input id=\"next_butt\" type=\"button\" onClick=\"nextClasses()\" value=\"Next\" \>";

	document.getElementById("limit_box").value=limit;
	document.getElementById("keyword_box").value=keyword;

	var xmlhttp = newXMLHTTP();
	xmlhttp.onreadystatechange = function()
	{
		document.getElementById("uni_courses").innerHTML= xmlhttp.responseText;
	}
	
	xmlhttp.open("GET", "test.php?atar="+atar+"&&offset="+offset+"&&sort="+sort+"&&sort_direction="+dir+"&&limit="+limit+"&&keyword="+keyword, true);
	xmlhttp.send();
}

// onClick method for table controls. Gets next 50 rows from database.
function nextClasses() 
{
	var off = parseInt(document.getElementById("offset").value);
	var atar = document.getElementById("atar_submit").value;
	var sort = document.getElementById("sort_cat").value;
	var sort_dir = document.getElementById("sort_direction").value;
	off += 50;

	document.getElementById("offset").value=off;

	displayClasses(atar, off, sort, sort_dir);
}

function prevClasses() {
	
	var off = parseInt(document.getElementById("offset").value);
	var atar = document.getElementById("atar_submit").value;
	var sort = document.getElementById("sort_cat").value;
	var sort_dir = document.getElementById("sort_direction").value;
	off -= 50;

	if(off < 0) {
		off = 0;
	}
	
	document.getElementById("offset").value=off;

	displayClasses(atar, off, sort, sort_dir);
}

function sortAndDisplay(cat_in) {
	
	var direction = document.getElementById("sort_direction").value;
	var cat = document.getElementById("sort_cat").value;
	var atar = document.getElementById("atar_submit").value;
	var off = document.getElementById("offset").value;

	if(cat == cat_in) {
		
		if(direction == "0") {
			
			document.getElementById("sort_direction").value=1;
			direction = 1;
		} else {
			
			document.getElementById("sort_direction").value=0;
			direction = 0;
		}
	} else {
		
		document.getElementById("sort_direction").value=0;
		direction = 0;
	}

	document.getElementById("sort_cat").value=cat_in;
	off = 0;
	document.getElementById("offset").value=0;

	displayClasses(atar, off, cat_in, direction);
}

function updateCourses() {
	
	var direction = (document.getElementById("sort_direction").value=0);
	var cat = (document.getElementById("sort_cat").value=3);
	var atar = document.getElementById("atar_submit").value;
	var off = (document.getElementById("offset").value=0);
	document.getElementById("limit_by").value="";
	document.getElementById("keyword_value").value="";

	displayClasses(atar, off, cat, direction);
}

function setLimitAndKey() {
	
	var limit_box = document.getElementById("limit_box");
	var direction = (document.getElementById("sort_direction").value=0);
	var cat = (document.getElementById("sort_cat").value=3);
	var atar = document.getElementById("atar_submit").value;
	var off = (document.getElementById("offset").value=0);

	var selected = limit_box.options[limit_box.selectedIndex].value;

	document.getElementById("limit_by").value=selected;
	document.getElementById("keyword_value").value=document.getElementById("keyword_box").value;

	displayClasses(atar, off, cat, direction);
}
