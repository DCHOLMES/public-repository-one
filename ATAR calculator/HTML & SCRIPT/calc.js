//updates on class selection
"use strict";
var alerts_on; // If alerts are currently allowed. Used to reduce alerts if they've already been given

// Hackish way of fixing a refresh issue
function getAgg(form, data) {
	alerts_on = true;
	getAgg2(form, data);
	alerts_on = false;
	getAgg2(form, data);
}

// Only sends alerts if they are switched on by sending a value of 1 to alerts_on
// Useful to reduce code needed for checking if alerts are on
function sendAlert(string) {
	if(alerts_on == true) {
		alert(string);
	}
}

function getAgg2(form, data) {
    var MAX_CLASSES = 8;
    var scaled = Array.apply(null, [MAX_CLASSES]).map(Number.prototype.valueOf, 0);	// Scaled score
	var scaled2 = Array.apply(null, [MAX_CLASSES]).map(Number.prototype.valueOf, 0);// Copy of scaled scores; they are not touched by sorting
    var hsclasses = data.hsclasses;
    var completeInfo = [0];
    
    // For all the classes, check if number and class has been selected then change the final score and scaling columns
    var i = 0;
    var theClass;
    var theScore;
    for (i = 1; i < MAX_CLASSES + 1; i += 1) {
        theClass = form['class' + i].value;
        theScore = form['raw' + i].value;
        
        if (!(theClass == "" || theScore == "") || theClass == "VV-O") {
			// Class has a score and class name
            scaled[parseInt(i - 1)] = setScaling(hsclasses, theClass, theScore, i);
			scaled2[i - 1] = scaled[i - 1];
            completeInfo.push(i);
        } else {
			// Clear scaled data to avoid scaling retention
			document.getElementById("scaling" + i).innerHTML = "<span>+0</span>";
			document.getElementById("class" + i + "result").value = "0";
		}
    }

    
    // The classes are rearranged in order of score and certain rules below
    // Some classes have limits on max and min and some are only allowed as a 10% subject
    
    // These variables store the "best" score(s) for each category
    var englishTop;
    var otherTop3;
    var fiveAndSix;
    
    // Running tallies of max class limited subjects, and 
    var engCount = 0;
    var mathCount = 0;
    var musicCount = 0;
    var industryCount = 0;
    var historyCount = 0;
    var ausCount = 0;
    var csitCount = 0;
	
	// Need to do special stuff if a VCE VET - Other class is found
	var vvoFound = false;
    
    // Top scores in limited class types
    var english = [0, 0];
    var math = [0, 0];
    var music = [0, 0];
    var industry = [0, 0];
    var history = [0, 0];
    var aus = [0, 0];
    var csit = [0, 0];
    
    var max = [0, 0];
	var max_class = [0, 0];
    var thisClass;
    var temp = [0, 0];
    var j;
    
    for (i = 1; i < completeInfo.length; i += 1) {
        theClass = form['class' + completeInfo[i]].value;
        theScore = form['raw' + completeInfo[i]].value;
    
        // Check if an English Subject
        if (theClass == "EN" || theClass == "EF" || theClass == "EG" || theClass == "LI" || theClass == "HE-EN") {
            engCount += 1;
            english.push(i);
            //Is English class, these rules apply
            // At most 2 in top 6
            // At least 1 in top 6 (Position 1)
            if (engCount > 1) {
                // Determine which english class to keep
                max = [0, 0];
                max_class = [0, 0];
				
                for (j = 0; j < english.length; j += 1) {
                    if (english[j] != 0) {
                        thisClass = scaled2[completeInfo[english[j]] - 1];

                        if (parseInt(thisClass) > parseInt(max[0])) {
                            temp[0] = max[0];
							temp[1] = max_class[0];
							
							max_class[0] = english[j];
                            max[0] = thisClass;
							
							if(max[1] != 0) {
								scaled[-1 +(completeInfo[max_class[1]])]=0;
							}
							
                            max[1] = temp[0];
							max_class[1] = temp[1];
							
                        } else if (parseInt(thisClass) > parseInt(max[1])) {
							
							if(max[1] != 0) {
								scaled[-1 +(completeInfo[max_class[1]])]=0;
							}
							
							max_class[1] = english[j];
                            max[1] = thisClass;
                        } else {
							scaled[-1 +(completeInfo[english[j]])]=0;
						}
					}
                }
                english = [max_class[0], max_class[1]];
            } else {
                english = [i, 0];
            }
		
		// Check if an math Subject
		} else if (theClass == "NF" || theClass == "NJ" || theClass == "NS" || theClass == "HE-MA") {
            mathCount += 1;
            math.push(i);
            //Is math class, these rules apply
            // At most 2 in top 6
            if (mathCount > 1) {
                // Determine which math class to keep
                max = [0, 0];
                max_class = [0, 0];
				
                for (j = 0; j < math.length; j += 1) {
                    if (math[j] != 0) {
                        thisClass = scaled2[completeInfo[math[j]] - 1];
                        if (parseInt(thisClass) > parseInt(max[0])) {
						
                            temp[0] = max[0];
							temp[1] = max_class[0];
							
							max_class[0] = math[j];
                            max[0] = thisClass;
							
							if(max[1] != 0) {
								scaled[-1 +(completeInfo[max_class[1]])]=0;
							}
							
                            max[1] = temp[0];
							max_class[1] = temp[1];
							
                        } else if (parseInt(thisClass) > parseInt(max[1])) {
							
							if(max[1] != 0) {
								scaled[-1 +(completeInfo[max_class[1]])]=0;
							}
							
							max_class[1] = math[j];
                            max[1] = thisClass;
                        } else {
								scaled[-1 +(completeInfo[math[j]])]=0;
						}
					}
                }
                math = [max_class[0], max_class[1]];
            } else {
                math = [i, 0];
            }
		
		
		
		
		
		// Check if an music Subject
		} else if (theClass == "MD" || theClass == "MC04" || theClass == "MC05" || theClass == "MI13" || theClass == "MI15" || theClass == "HE-MU") {
            musicCount += 1;
            music.push(i);
            //Is music class, these rules apply
            // At most 2 in top 6
            if (musicCount > 1) {
                // Determine which music class to keep
                max = [0, 0];
                max_class = [0, 0];
				
                for (j = 0; j < music.length; j += 1) {
                    if (music[j] != 0) {
                        thisClass = scaled2[completeInfo[music[j]] - 1];
                        if (parseInt(thisClass) > parseInt(max[0])) {
							
                            temp[0] = max[0];
							temp[1] = max_class[0];
							
							max_class[0] = music[j];
                            max[0] = thisClass;
							
							if(max[1] != 0) {
								scaled[-1 +(completeInfo[max_class[1]])]=0;
							}
							
                            max[1] = temp[0];
							max_class[1] = temp[1];
							
                        } else if (parseInt(thisClass) > parseInt(max[1])) {
							
							if(max[1] != 0) {
								scaled[-1 +(completeInfo[max_class[1]])]=0;
							}
							
							max_class[1] = music[j];
                            max[1] = thisClass;
                        } else {
							scaled[-1 +(completeInfo[music[j]])]=0;
						}
					}
                }
                music = [max_class[0], max_class[1]];
            } else {
                music = [i, 0];
            }

			
			
			
			
		// Check if an history Subject
		} else if (theClass == "HA" || theClass == "HR" || theClass == "H9" || theClass == "HE-HI") {
            historyCount += 1;
            history.push(i);
            //Is history class, these rules apply
            // At most 2 in top 6
            if (historyCount > 1) {
                // Determine which history class to keep
                max = [0, 0];
                max_class = [0, 0];
				
                for (j = 0; j < history.length; j += 1) {
                    if (history[j] != 0) {
                        thisClass = scaled2[completeInfo[history[j]] - 1];
                        if (parseInt(thisClass) > parseInt(max[0])) {
                            temp[0] = max[0];
							temp[1] = max_class[0];
							
							max_class[0] = history[j];
                            max[0] = thisClass;
							
							if(max[1] != 0) {
								scaled[-1 +(completeInfo[max_class[1]])]=0;
							}
							
                            max[1] = temp[0];
							max_class[1] = temp[1];
							
                        } else if (parseInt(thisClass) > parseInt(max[1])) {
							
							if(max[1] != 0) {
								scaled[-1 +(completeInfo[max_class[1]])]=0;
							}
							
							max_class[1] = history[j];
                            max[1] = thisClass;
                        } else {
							scaled[-1 +(completeInfo[history[j]])]=0;
						}
					}
                }
                history = [max_class[0], max_class[1]];
            } else {
                history = [i, 0];
            }
			
		
		
		
		
		
		// Check if a Contemporary Australian Subject
		} else if (theClass == "SO03" || theClass == "HE-AU") {
            ausCount += 1;
            aus.push(i);
            //Is aus class, these rules apply
            // At most 2 in top 6
            if (ausCount > 1) {
                // Determine which aus class to keep
                max = [0, 0];
                max_class = [0, 0];
				
                for (j = 0; j < aus.length; j += 1) {
                    if (aus[j] != 0) {
                        thisClass = scaled2[completeInfo[aus[j]] - 1];
                        if (parseInt(thisClass) > parseInt(max[0])) {
                            temp[0] = max[0];
							temp[1] = max_class[0];
							
							max_class[0] = aus[j];
                            max[0] = thisClass;
							
							if(max[1] != 0) {
								scaled[-1 +(completeInfo[max_class[1]])]=0;
							}
							
                            max[1] = temp[0];
							max_class[1] = temp[1];
							
                        } else if (parseInt(thisClass) > parseInt(max[1])) {
							
							if(max[1] != 0) {
								scaled[-1 +(completeInfo[max_class[1]])]=0;
							}
							
							max_class[1] = aus[j];
                            max[1] = thisClass;
                        } else {
							scaled[-1 +(completeInfo[aus[j]])]=0;
						}
					}
                }
                aus = [max_class[0], max_class[1]];
            } else {
                aus = [i, 0];
            }
		
		
		
		
		
		// Check if an CSIT Subject
		} else if (theClass == "SO03" || theClass == "HE-CS") {
            csitCount += 1;
            csit.push(i);
            //Is csit class, these rules apply
            // At most 2 in top 6
            if (csitCount > 1) {
                // Determine which csit class to keep
                max = [0, 0];
                max_class = [0, 0];
				
                for (j = 0; j < csit.length; j += 1) {
                    if (csit[j] != 0) {
                        thisClass = scaled2[completeInfo[csit[j]] - 1];
                        if (parseInt(thisClass) > parseInt(max[0])) {

                            temp[0] = max[0];
							temp[1] = max_class[0];
							
							max_class[0] = csit[j];
                            max[0] = thisClass;
							
							if(max[1] != 0) {
								scaled[-1 +(completeInfo[max_class[1]])]=0;
							}
							
                            max[1] = temp[0];
							max_class[1] = temp[1];
							
                        } else if (parseInt(thisClass) > parseInt(max[1])) {
							
							if(max[1] != 0) {
								scaled[-1 +(completeInfo[max_class[1]])]=0;
							}
							
							max_class[1] = csit[j];
                            max[1] = thisClass;
                        } else {
							scaled[-1 +(completeInfo[csit[j]])]=0;
						}
					}
                }
                csit = [max_class[0], max_class[1]];
            } else {
                csit = [i, 0];
            }
		
		// Is a VCE VET - Other class, will need to sort after everything else
		} else if (theClass == "VV-O") {
            vvoFound = true;
		} else {
            // Another subject was found

        }
		
		
		
		// Rearrange subjects
		// First English
		if(engCount > 0) {
			if(engCount == 1) {
				// Only one english, check if its not already in class1 slot, then move it
				if(completeInfo[english[0]]!=1)
				{
					// Set class1 values to the only english value
					//Dont worry about what was in its place as only english can exist there so it must be empty
					document.getElementById("class1").value = document.getElementById("class" + completeInfo[english[0]]).value;
					document.getElementById("raw1").value = document.getElementById("raw" + completeInfo[english[0]]).value;
					document.getElementById("scaling1").innerHTML = document.getElementById("scaling" + completeInfo[english[0]]).innerHTML;
					document.getElementById("class1result").value = document.getElementById("class" + completeInfo[english[0]] + "result").value;
					
					// Remove old value
					document.getElementById("class" + completeInfo[english[0]]).value = "";
					document.getElementById("raw" + completeInfo[english[0]]).value = "";
					document.getElementById("scaling" + completeInfo[english[0]]).innerHTML = "<span>+0</span>";
					document.getElementById("class" + completeInfo[english[0]] + "result").value = "0";
					
					// Set new location
					//scaled[0] = scaled[completeInfo[english[0]] - 1];
					//scaled[completeInfo[english[0]] - 1] = 0;
				}
			} else if(engCount > 1) {
			// More than one English, swap best one to position 1
				var tempOrder;
				var tempScaled;
				var tempClass;
				var tempRaw;
				var tempScaling;
				var tempResult;
				
				// Put the best english in position 1
				if(completeInfo[english[0]]!=1)
				{
					// Store current position 1 data
					tempScaled = scaled[0];
					tempOrder = completeInfo[english[0]];
					tempClass = document.getElementById("class1").value;
					tempRaw = document.getElementById("raw1").value;
					tempScaling = document.getElementById("scaling1").innerHTML;
					tempResult = document.getElementById("class1result").value;
					
					// Replace position 1 data with best English
					document.getElementById("class1").value = document.getElementById("class" + completeInfo[english[0]]).value;
					document.getElementById("raw1").value = document.getElementById("raw" + completeInfo[english[0]]).value;
					document.getElementById("scaling1").innerHTML = document.getElementById("scaling" + completeInfo[english[0]]).innerHTML;
					document.getElementById("class1result").value = document.getElementById("class" + completeInfo[english[0]] + "result").value;
					
					// Replace best English with old position 1
					document.getElementById("class" + completeInfo[english[0]]).value = tempClass;
					document.getElementById("raw" + completeInfo[english[0]]).value = tempRaw;
					document.getElementById("scaling" + completeInfo[english[0]]).innerHTML = tempScaling;
					document.getElementById("class" + completeInfo[english[0]] + "result").value = tempResult;
					
					// Set new locations
					//scaled[0] = scaled[completeInfo[english[0]] - 1];
					//scaled[completeInfo[english[0]] - 1] = tempScaled;
				}
			
			}
		}
    }
	
	// Rearrange other subjects
	// For classes 2 - 8
	var k;
	var tempOrder;
	var tempScaled;
	var tempClass;
	var tempRaw;
	var tempScaling;
	var tempResult;
	var swap = true;
	// Bubble sort
	while(swap)
	{
		swap=false;
		for(j = 1; j < completeInfo.length; j += 1){
			if(completeInfo[j] !== 1) {
				
				for(k = j+1; k < completeInfo.length; k += 1){
				
					var moveUp_Score = scaled[completeInfo[k]-1];
					var moveUp_Name = form['class' + completeInfo[k]].value;
					var moveUp_NameStart = moveUp_Name.substring(0, 3);
					var moveUp_Position = completeInfo[k];
					
					var moveDown_Score = scaled[completeInfo[j]-1];
					var moveDown_Name = form['class' + completeInfo[j]].value;
					var moveDown_NameStart = moveDown_Name.substring(0, 3);
					var moveDown_Position = completeInfo[j];
					
					// Rule 1: If both are not HE- classes, sort normally
					// Rule 2: If HE class is Position 4-, and comparing to non HE class, move it down.
					// Rule 3: If HE class is Position 5+, if position below is not HE, compare 10% to the HE value.
					// Rule 4: If two HE classes,compare normally.
					var rule1 = moveDown_NameStart != "HE-" && moveUp_NameStart != "HE-" && parseInt(100 * moveUp_Score) > parseInt(100 * moveDown_Score);
					var rule2 = moveDown_NameStart == "HE-" && moveUp_NameStart != "HE-" && moveDown_Position < 5;
					var rule3 = moveDown_NameStart == "HE-" && moveUp_NameStart != "HE-" && moveDown_Position >= 5 && parseInt(10 * moveUp_Score) > parseInt(100 * moveDown_Score);
					var rule4 = moveDown_NameStart == "HE-" && moveUp_NameStart == "HE-" && moveUp_Score > moveDown_Score;
				
					if( rule1 || rule2 || rule3 || rule4 ) {
						
						
						// Log which rules applied
						if(rule1) {
							log("Rule 1: Moved "+moveUp_Name+" ("+moveUp_Score+") up over "+moveDown_Name+" ("+moveDown_Score+")");
							log("If both are not HE- classes, sort normally");
						}
						if(rule2) {
							log("Rule 2: Moved "+moveUp_Name+" ("+moveUp_Score+") up over "+moveDown_Name+" ("+moveDown_Score+")");
							log("If HE class is Position 4-, and comparing to non HE class, move it down.");
						}
						if(rule3) {
							log("Rule 3: Moved "+moveUp_Name+" ("+moveUp_Score+") up over "+moveDown_Name+" ("+moveDown_Score+")");
							log("If HE class is Position 5+, if position below is not HE, compare 10% to the HE value.");
						}
						if(rule4) {
							log("Rule 4: Moved "+moveUp_Name+" ("+moveUp_Score+") up over "+moveDown_Name+" ("+moveDown_Score+")");
							log("If two HE classes,compare normally.");
						}
					
						// Do the swap
						swap=true;
						
						// Store data
						tempScaled = scaled[completeInfo[j] - 1];
						tempOrder = completeInfo[j];
						tempClass = document.getElementById("class" + completeInfo[j]).value;
						tempRaw = document.getElementById("raw" + completeInfo[j]).value;
						tempScaling = document.getElementById("scaling" + completeInfo[j]).innerHTML;
						tempResult = document.getElementById("class" + completeInfo[j] + "result").value;
						
						// Replace with new data
						document.getElementById("class" + completeInfo[j]).value = document.getElementById("class" + completeInfo[k]).value;
						document.getElementById("raw" + completeInfo[j]).value = document.getElementById("raw" + completeInfo[k]).value;
						document.getElementById("scaling" + completeInfo[j]).innerHTML = document.getElementById("scaling" + completeInfo[k]).innerHTML;
						document.getElementById("class" + completeInfo[j] + "result").value = document.getElementById("class" + completeInfo[k] + "result").value;
						
						// Replace old data with temp data
						document.getElementById("class" + completeInfo[k]).value = tempClass;
						document.getElementById("raw" + completeInfo[k]).value = tempRaw;
						document.getElementById("scaling" + completeInfo[k]).innerHTML = tempScaling;
						document.getElementById("class" + completeInfo[k] + "result").value = tempResult;
						
						// Set new locations
						scaled[completeInfo[j] - 1] = scaled[completeInfo[k] - 1];
						scaled[completeInfo[k] - 1] = tempScaled;

					}
				}
			}
		}
	}
	

	// Move classes to fill empty sections
	var last = 1;
	var difference;
	for(j = 1; j < completeInfo.length; j += 1){
		// When not english-slot1
		if (completeInfo[j] !== 1) {
			// If difference is greater than 1, then there is an empty section
			if (document.getElementById("class" + (last + 1)).value == "")
			{
				difference = completeInfo[j] - last;
			
				// Store data
				tempScaled = 0;
				tempClass = document.getElementById("class" + (last + 1)).value;
				tempRaw = document.getElementById("raw" + (last + 1)).value;
				tempScaling = document.getElementById("scaling" + (last + 1)).innerHTML;
				tempResult = document.getElementById("class" + (last + 1) + "result").value;
				
				// Replace with new data
				document.getElementById("class" + (last + 1)).value = document.getElementById("class" + completeInfo[j]).value;
				document.getElementById("raw" + (last + 1)).value = document.getElementById("raw" + completeInfo[j]).value;
				document.getElementById("scaling" + (last + 1)).innerHTML = document.getElementById("scaling" + completeInfo[j]).innerHTML;
				document.getElementById("class" + (last + 1) + "result").value = document.getElementById("class" + completeInfo[j] + "result").value;
				
				// Replace old data with temp data
				document.getElementById("class" + completeInfo[j]).value = tempClass;
				document.getElementById("raw" + completeInfo[j]).value = tempRaw;
				document.getElementById("scaling" + completeInfo[j]).innerHTML = tempScaling;
				document.getElementById("class" + completeInfo[j] + "result").value = tempResult;
				
			} else {
				last += 1;
			}
		} else {
			last = completeInfo[j];
		}
	}

	// Fix scores for disqualified subjects and 10% subjects
	for(j = 1; j < completeInfo.length; j += 1)
	{
		if(scaled[completeInfo[j] - 1] === 0)
		{
			document.getElementById("class" + completeInfo[j] + "result").value = 0;
		}
		
		if(completeInfo[j] == 5 || completeInfo[j] == 6)
		{
			theClass = form['class' + completeInfo[j]].value;
			if (theClass.substring(0, 3) == "HE-" || theClass.substring(0, 3) == "VV-") {
				// "HE-" = Higher Education class, it counts as a 10% already, dont make it 1%!
				// "VV-" = VET - Other class, change value to 0 (in case is in top 4) and then add top 4 and change score to 10%
			} else {
				// Positions 5 and 6 are only worth 10%
				document.getElementById("class" + completeInfo[j] + "result").value = document.getElementById("class" + completeInfo[j] + "result").value / 10;
			}
		} else if( completeInfo[j] >= 7) {
			document.getElementById("class" + completeInfo[j] + "result").value = 0;
		}
	
	}
	
	if(vvoFound == true) {
		// Update scores for all vvo subjects
		
		var top4 = 0;
		var amount = 0;
		
		// Go through the finished classes and add the top 4
		for(var k=1; k<completeInfo.length; k++) {
			
			if(completeInfo[k] > 4) {
				// If no longer in the top 4, tell loop to end
				k=100;
				
			} else if(scaled[completeInfo[k]-1] != -1) {
				// If not a VCE VET class (score of -1), add to top 4
				top4 +=  scaled[completeInfo[k]-1];
				amount += 1;
			} 
		}
		
		// Get 10% of the top 4
		if(top4 != 0) {
			top4 = top4/(10*amount);
		}
		
		// Set all VV-O's to the top4 value
		for(var k=1; k<completeInfo.length; k++) {
			if(scaled[completeInfo[k]-1] == -1) {
				// Found a VV-O
				scaled[completeInfo[k]-1] = top4;

				document.getElementById("scaling" + completeInfo[k]).innerHTML = "<span class='grey'> N/a </span>";
				document.getElementById("class" + completeInfo[k] + "result").value = parseInt(top4*100)/100;
				
			}
		}
		
		// Re-sort those subjects
		swap = true;
		while(swap)
		{
			swap=false;
			for(j = 1; j < completeInfo.length; j += 1){
				if(completeInfo[j] !== 1) {
					
					for(k = j+1; k < completeInfo.length; k += 1){
					
						var moveUp_Score = scaled[completeInfo[k]-1];
						var moveUp_Name = form['class' + completeInfo[k]].value;
						var moveUp_NameStart = moveUp_Name.substring(0, 3);
						var moveUp_Position = completeInfo[k];
						
						var moveDown_Score = scaled[completeInfo[j]-1];
						var moveDown_Name = form['class' + completeInfo[j]].value;
						var moveDown_NameStart = moveDown_Name.substring(0, 3);
						var moveDown_Position = completeInfo[j];
						
						// Rule 1: If both are not HE- or VV- classes, sort normally
						// Rule 2: If HE class is Position 4-, and comparing to non HE class, move it down.
						// Rule 3: If HE class is Position 5+, if position below is not HE, compare 10% to the HE value.
						// Rule 4: If two HE classes,compare normally.
						// Rule 5: If VV-O class could move up, it can only move to position 5 at highest
						// noVVO: Only true if there is no VV-O class in this comparison, helps limit rules 1-4
						var rule1 = moveDown_NameStart != "HE-" && moveUp_NameStart != "HE-" && parseInt(100 * moveUp_Score) > parseInt(100 * moveDown_Score);
						var rule2 = moveDown_NameStart == "HE-" && moveUp_NameStart != "HE-" && moveDown_Position < 5;
						var rule3 = moveDown_NameStart == "HE-" && moveUp_NameStart != "HE-" && moveDown_Position >= 5 && parseInt(10 * moveUp_Score) > parseInt(100 * moveDown_Score);
						var rule4 = moveDown_NameStart == "HE-" && moveUp_NameStart == "HE-" && moveUp_Score > moveDown_Score;
						var rule5 = moveUp_NameStart == "VV-" && moveDown_Position >= 5 && parseInt(100 * moveUp_Score) > parseInt(100 * moveDown_Score);
						
						var noVVO = !(moveUp_NameStart == "VV-" || moveDown_NameStart == "VV-");
						
						if( (noVVO && (rule1 || rule2 || rule3 || rule4)) || rule5 ) {
							
							
							// Log which rules applied
							if(rule1) {
								log("Rule 1: Moved "+moveUp_Name+" ("+moveUp_Score+") up over "+moveDown_Name+" ("+moveDown_Score+")");
								log("If both are not HE- classes, sort normally");
							}
							if(rule2) {
								log("Rule 2: Moved "+moveUp_Name+" ("+moveUp_Score+") up over "+moveDown_Name+" ("+moveDown_Score+")");
								log("If HE class is Position 4-, and comparing to non HE class, move it down.");
							}
							if(rule3) {
								log("Rule 3: Moved "+moveUp_Name+" ("+moveUp_Score+") up over "+moveDown_Name+" ("+moveDown_Score+")");
								log("If HE class is Position 5+, if position below is not HE, compare 10% to the HE value.");
							}
							if(rule4) {
								log("Rule 4: Moved "+moveUp_Name+" ("+moveUp_Score+") up over "+moveDown_Name+" ("+moveDown_Score+")");
								log("If two HE classes,compare normally.");
							}
							if(rule5) {
								log("Rule 5: Moved "+moveUp_Name+" ("+moveUp_Score+") up over "+moveDown_Name+" ("+moveDown_Score+")");
								log("If VV-O class could move up, it can only move to position 5 at highest.");
							}
						
							// Do the swap
							swap=true;
							
							// Store data
							tempScaled = scaled[completeInfo[j] - 1];
							tempOrder = completeInfo[j];
							tempClass = document.getElementById("class" + completeInfo[j]).value;
							tempRaw = document.getElementById("raw" + completeInfo[j]).value;
							tempScaling = document.getElementById("scaling" + completeInfo[j]).innerHTML;
							tempResult = document.getElementById("class" + completeInfo[j] + "result").value;
							
							// Replace with new data
							document.getElementById("class" + completeInfo[j]).value = document.getElementById("class" + completeInfo[k]).value;
							document.getElementById("raw" + completeInfo[j]).value = document.getElementById("raw" + completeInfo[k]).value;
							document.getElementById("scaling" + completeInfo[j]).innerHTML = document.getElementById("scaling" + completeInfo[k]).innerHTML;
							document.getElementById("class" + completeInfo[j] + "result").value = document.getElementById("class" + completeInfo[k] + "result").value;
							
							// Replace old data with temp data
							document.getElementById("class" + completeInfo[k]).value = tempClass;
							document.getElementById("raw" + completeInfo[k]).value = tempRaw;
							document.getElementById("scaling" + completeInfo[k]).innerHTML = tempScaling;
							document.getElementById("class" + completeInfo[k] + "result").value = tempResult;
							
							// Set new locations
							scaled[completeInfo[j] - 1] = scaled[completeInfo[k] - 1];
							scaled[completeInfo[k] - 1] = tempScaled;
						}
					}
				}
			}
		}
	}
    
    // Calc aggregate score and atar and update page
    getFinalScore(scaled, data.scaled_agg);
}

//Interpolates between the closest scale scores, linearly weighted by their nearness and then alters the page to reflect changes
function setScaling(hsclasses, theClass, theScore, i) {
    var colour;
    var plusminus;
    var change;
	var score_weighting
	var j;
	var scaled;

	if (theClass.substring(0, 3) == "VV-") {
		// VET-Other
		//No Score required, gets 10% of top 4 classes
		
		var newScore = -1;
		
		// Write out the changes
        document.getElementById("scaling" + i).innerHTML = "<span class='grey'> N/a </span>";
        document.getElementById("class" + i + "result").value = newScore;
        return newScore;
		
    } else if(theClass.substring(0, 3) == "HE-") {
		// Higher Education class
		// Score just gets transferred as a 10% class. No scaling.
		if(theScore < 0 || theScore > 5) {
			// The score is invalid
			sendAlert("Higher Education scores are only between 0 and 5");
		}
		
		// Write out the changes
        document.getElementById("scaling" + i).innerHTML = "<span class='grey'> N/a </span>";
        document.getElementById("class" + i + "result").value = parseInt(theScore*100)/100;
        return theScore;
	
	} else if (theScore < 0 || theScore > 50) {
		// If score is invalid, notify user
        sendAlert("Raw scores are only between 0 and 50");
	
	} else if (theClass === "") {
		log("Error: Class sent to setScaling() was null");
		return 0;
	} else {
	
		// Look up the nearest scales in hsclasses to get the scaled score
		var upper = 0;
		var lower = 0;
		var scaled_lower = 0;
		var scaled_upper = 0;
		
		// Find the upper and lower min_agg's
		for (j = 20; j <= 50; j += 5) {
			if (theScore < j && upper == 0) {
				upper = j;
				scaled_upper = hsclasses['raw_' + j][theClass];
			}
				
			if (theScore >= j) {
				lower = j;
				scaled_lower = hsclasses['raw_' + j][theClass];
			}
		}
		
		if (lower == theScore)
		{
			scaled = scaled_lower;
		} else if (lower == 0) {
			// Score is < 20
			// Assume that 0 is scaled to 0 and use that as the lower value
			score_weighting = (theScore - lower) / (upper - lower);
			scaled = score_weighting * scaled_upper + (1 - score_weighting) * scaled_lower;
		} else if (upper == 0) {
			// Score of 50
			// Should already have been sorted.
			sendAlert("Logic error 634");
		} else {
			// Score is 20 or above, but less than or equal to 50
			
			// Gives percentage of weighing
			score_weighting = (theScore - lower) / (upper - lower);
			
			// Takes the weightings and applies to to the scaled score linearly
			scaled = score_weighting * scaled_upper + (1 - score_weighting) * scaled_lower;
		}
        
        // Set appropriate colour and value
        if (scaled == theScore) {
            colour = "grey";
            plusminus = "+";
            change = 0;
        } else if (scaled < theScore) {
            colour = "red";
            plusminus = "";
            change = scaled - theScore;
        } else {
            colour = "green";
            plusminus = "+";
            change = scaled - theScore;
        }
        
        // Check score is not negative
        if (+theScore + +change < 0) {
            change = - theScore;
        }
        
        // Write out the changes
        document.getElementById("scaling" + i).innerHTML = "<span class='" + colour + "'>" + plusminus + Math.round(change) + "</span>";
        document.getElementById("class" + i + "result").value = parseInt(100 * (+theScore + +change)) / 100;
        return parseInt(100 * (+theScore + +change)) / 100;
    }
    
    return 0;

}

function setFinalScore(score, atar) {
    // Round to 2 decimal points
    atar = parseInt(atar * 100) / 100;
    score = parseInt(score * 100) / 100;
    
    // Update page
    document.getElementById("final_score").innerHTML = score;
    document.getElementById("final_atar").innerHTML = atar;
    document.getElementById("atar_submit").value = atar;
}

function getFinalScore(scaled, scaled_agg) {
    var score = 0;
    var i;
    
	// Check if there is an English in class 1
	if(document.getElementById("class1").value == "") {
		document.getElementById("final_score").innerHTML = parseInt(score*100)/100;
        document.getElementById("final_atar").innerHTML = "<span class='red'>An English class is required</span>";
        return 0;
	}
	
    // Sum all the scores to get the aggregate score
	// First 4 subjects
    for (i = 0; i < 4; i += 1) {
		if (scaled[i] > 0) {
			score = score + scaled[i];
		}
    }
	// 10% of subjects 5 and 6
	for (; i < 6; i += 1) {
		if (scaled[i] > 0) {
			score = score + (scaled[i] / 10);
		}
	}
    
    // Look up the aggregate score in scaled_agg to get the ATAR score
    var upper = 0;
    var lower = 0;
    var atar_lower = 0;
    var atar_upper = 0;
    
    // Find the upper and lower min_agg's
    for (i in scaled_agg.min_aggregate) {
        if (score < scaled_agg.min_aggregate[i] && upper == 0) {
            upper = scaled_agg.min_aggregate[i];
            atar_upper = scaled_agg.ATAR[i];
        }
            
        if (score >= scaled_agg.min_aggregate[i]) {
            lower = scaled_agg.min_aggregate[i];
            atar_lower = scaled_agg.ATAR[i];
        }
    }
    
    if (lower == 0) {
        // Score is < 40 ATAR
        document.getElementById("final_score").innerHTML = parseInt(score*100)/100;
        document.getElementById("final_atar").innerHTML = "< 40";
        return 0;
    } else if (upper == 0) {
        // Score is a 99.95 ATAR
        document.getElementById("final_score").innerHTML = parseInt(score*100)/100;
        document.getElementById("final_atar").innerHTML = "99.95";
        return 99.95;
    } else {
        // ATAR score is 40 or above, but less than or equal to 99.95
        
        // Gives percentage of weighing
        var score_weighting = (score - lower) / (upper - lower);
        
        // Takes the weightings and applies to to the atar scores linearly
        var atar = score_weighting * atar_upper + (1 - score_weighting) * atar_lower;
		
		if(atar <= 0) {
			log("ATAR freaked out.");
			document.getElementById("final_score").innerHTML = parseInt(score*100)/100;
			document.getElementById("final_atar").innerHTML = "< 40";
			return 0;
		}
        
        // Update page
        setFinalScore(score, atar);
        return atar;
    }
}

// Sends the string to the dev console
function log(var1) {
	if(alerts_on == true) {
		console.log(var1);
	}
}