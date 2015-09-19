function check() {
	if (document.getElementById("file").value != "") {
		document.getElementById("upload").disabled = false;
	} else {
		document.getElementById("upload").disabled = true;
	}
}

function ajax(id, url) {
	var xmlhttp;
	xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	    	document.getElementById(id).innerHTML = xmlhttp.responseText;
	    }
	}
	xmlhttp.open("GET", url, false);
	xmlhttp.send();
}

function fillCourse(course) {
	ajax(course, "/score/CourseServlet");
}

function fillGrade() {
	ajax("grade","/score/GradeServlet");
}

function fillClass() {
	var grade = document.getElementById("grade").value;
	ajax("class", "/score/ClassServlet?grade=" + grade);
}

function fillStudent() {
	var classNo = document.getElementById("class").value;
	ajax("student", "/score/StudentServlet?classNo=" + classNo);
}

function drawLine(context, color, x0, y0, x1, y1) {
	context.strokeStyle = color;
	context.lineWidth = 0.5;
	context.beginPath();
	context.moveTo(x0, y0);
	context.lineTo(x1, y1);
	context.stroke();
}

function drawGrid(context, color, stepx, stepy) {
	for (var i = stepx + 0.5; i < context.canvas.width; i += stepx) {
		drawLine(context, color, i, 0, i, context.canvas.height);
	}

	for (var i = stepy + 0.5; i < context.canvas.height; i += stepy) {
		drawLine(context, color, 0, i, context.canvas.width, i);
	}
}

function drawSingle() {
	var canvas = document.getElementById('myCanvas');
	var context = canvas.getContext('2d');
	var width = canvas.width;
	var height = canvas.height;
	context.clearRect(0,0,width,height);
	var stepx = width / 32;
	var stepy = height / 25;
	var color = 'lightgray';
	drawGrid(context, color, stepx, stepy);
	drawLine(context, 'steelblue', 3 * stepx, height - 2 * stepy, 26 * stepx, height - 2 * stepy);
	drawLine(context, 'steelblue', 3 * stepx, height - 2 * stepy, 3 * stepx, 3 * stepy);
	context.font = '10pt "Microsoft YaHei"';
	context.font = '10pt';
	context.fillStyle = "steelblue";
	context.fillText('分数段', 26.5 * stepx, height - 1.5 * stepy);
	context.fillText('百分比(%)', stepx, 2.5 * stepy);
	context.fillText('A: [0:60)', 25 * stepx, 2 * stepy);
	context.fillText('B: [60:70)', 25 * stepx, 4 * stepy);
	context.fillText('C: [70:80)', 25 * stepx, 6 * stepy);
	context.fillText('D: [80:90)', 25 * stepx, 8 * stepy);
	context.fillText('E: [90:100]', 25 * stepx, 10 * stepy);

	var count = new Array();
	count[0] = document.getElementById("count1_percent").innerHTML;
	count[1] = document.getElementById("count2_percent").innerHTML;
	count[2] = document.getElementById("count3_percent").innerHTML;
	count[3] = document.getElementById("count4_percent").innerHTML;
	count[4] = document.getElementById("count5_percent").innerHTML;
	
	var max = 0;
	for (var i = 1; i < count.length; i++) {
		if (max - count[i] < 0)
			max = count[i];
	}

	var num = new Number(max);
	num /= 10;
	num += 1;
	max = num.toFixed(0);
	max *= 10;

	var per = 200 / max;
	var text;
	for (var i = 0; i < count.length; i++) {
		context.fillRect((4 * i + 6) * stepx, height - 2 * stepy - count[i] * per, 2 * stepx, count[i] * per);
		context.fillText(count[i], (4 * i + 6.2) * stepx, height - 2.5 * stepy - count[i] * per);
		switch(i) {
			case 0: text = 'A'; break;
			case 1: text = 'B'; break;
			case 2: text = 'C'; break;
			case 3: text = 'D'; break;
			case 4: text = 'E'; break;
		}
		context.fillText(text, (4 * i + 6.5) * stepx, height - stepy);
	}
}

function drawRelative() {
	var canvas = document.getElementById('myCanvas');
	
	var width = canvas.width;
	var height = canvas.height;
	var stepx = width / 32;
	var stepy = height / 25;
	
	var context = canvas.getContext('2d');
	context.clearRect(0, 0, width, height);
	
	drawGrid(context, 'lightgray', stepx, stepy);
	drawLine(context, 'steelblue', 2 * stepx, height - 2 * stepy, 26 * stepx, height - 2 * stepy);
	drawLine(context, 'steelblue', 2 * stepx, height - 2 * stepy, 2 * stepx, 2 * stepy);
	
	context.font = '10pt "Microsoft YaHei"';
	context.fillStyle = "steelblue";
	
	context.fillText('科目', 26.5 * stepx, height - 1.5 * stepy);
	context.fillText('相关系数', stepx, 1.5 * stepy);
	
	var per = 200;
	for (var i = 1; 1; i++) {
		var c = document.getElementById("c" + i);
		if (c == null) break;
		var cn = c.innerHTML;
		context.fillText("C" + i + " " + cn, 25 * stepx, 2 * i * stepy);
		var r = document.getElementById("r" + i).innerHTML;
		if (r < 0) r *= (-1);
		context.fillRect((3 * (i - 1) + 4) * stepx, height - 2 * stepy - r * per, 2 * stepx, r * per);
		context.fillText(r, (3 * (i - 1) + 3.7) * stepx, height - 2.2 * stepy - r * per);
		context.fillText("C" + i, (3 * (i - 1) + 4.5) * stepx, height - stepy);
	}
}

function drawDots() {
	
	var canvas = document.getElementById('myCanvas');
	
	var width = canvas.width;
	var height = canvas.height;
	var stepx = width / 12;
	var stepy = height / 12;
	
	var context = canvas.getContext('2d');
	context.clearRect(0, 0, width, height);
	
	drawGrid(context, 'lightgray', stepx, stepy);
	drawLine(context, 'steelblue', stepx, height - stepy, width - stepx, height - stepy); //x
	drawLine(context, 'steelblue', stepx, height - stepy, stepx, stepy); //y

    if (document.getElementById("pors").value == "Pearson") {
    	drawLine(context, 'steelblue', stepx, height - 7 * stepy, width - stepx, height - 7 * stepy);
		drawLine(context, 'steelblue', 7 * stepx, 11 * stepy, 7 * stepx, stepy);
	}
	
	context.font = '10pt "Microsoft YaHei"';
	context.fillStyle = "steelblue";
	
	var c1 = document.getElementById("cn1").innerHTML;
	var c2 = document.getElementById("cn2").innerHTML;
	var r = document.getElementById("r").innerHTML;
	context.fillText(c1, width - stepx - 20, height - stepy + 20);
	context.fillText(c2, stepx - 20, stepy - 10);
	context.fillText("相关系数：" + r, width / 2 - 30, stepy - 10);
	
	context.fillText("0", stepx - 10, height - stepy + 10);
	context.fillText("60", stepx - 20, height - 7 * stepy + 5);
	context.fillText("60", 7 * stepx - 5, height - stepy + 15);
	
	context.strokeStyle = "steelblue";
	context.lineWidth = '2';
	
	for (var i = 1; 1; i++) {
		var x = document.getElementById("x" + i).innerHTML;
		if (x == null)
			break;
		var y = document.getElementById("y" + i).innerHTML;
	    context.beginPath();
	    context.arc(x * (stepx / 10) + stepx, height - y * (stepy / 10) - stepy, 1, 0, Math.PI * 2, false); 
	    context.fill();
	}
}

function drawRank(str) {
	var canvas = document.getElementById(str + 'RankCanvas');
	
	var width = canvas.width;
	var height = canvas.height;
	var stepx = width / 32;
	var stepy = height / 25;
	
	var context = canvas.getContext('2d');
	context.clearRect(0, 0, width, height);

	drawGrid(context, 'lightgray', stepx, stepy);
	drawLine(context, 'steelblue', 2 * stepx, height - 2 * stepy, 26 * stepx, height - 2 * stepy);
	drawLine(context, 'steelblue', 2 * stepx, height - 2 * stepy, 2 * stepx, 2 * stepy);
	
	context.font = '10pt "Microsoft YaHei"';
	context.fillStyle = "steelblue";
	
	context.fillText('科目', 26.5 * stepx, height - 1.5 * stepy);
	if (str == 'class') {
		context.fillText('班级排名', stepx, 1.5 * stepy);
	} else if(str == 'grade') {
		context.fillText('年级排名', stepx, 1.5 * stepy);
	} 
	
	var per = 2;
	for (var i = 1; 1; i++) {
		var c = document.getElementById("c" + i);
		if (c == null) break;
		var cn = c.innerHTML;
		
		context.fillStyle = "steelblue";
		context.fillText("C" + i + " " + cn, 25 * stepx, 2 * i * stepy);
		context.fillText("C" + i, (3 * (i - 1) + 4.5) * stepx, height - stepy);
		
		var count = new Array();
		count[0] = document.getElementById(str + '_percent[' + (i - 1) + '][0]').innerHTML;
		count[1] = document.getElementById(str + '_percent[' + (i - 1) + '][1]').innerHTML;
		count[2] = document.getElementById(str + '_percent[' + (i - 1) + '][2]').innerHTML;

		context.fillStyle = "#ADD8E6";
		context.fillRect((3 * (i - 1) + 4) * stepx, 3 * stepy, 2 * stepx, count[0] * per);
		
		context.fillStyle = "#DC143C";
		context.fillRect((3 * (i - 1) + 4) * stepx, 3 * stepy + count[0] * per, 2 * stepx, count[1] * per);
		
		context.fillStyle = "#ADD8E6";
		context.fillRect((3 * (i - 1) + 4) * stepx, 3 * stepy + count[0] * per + count[1] * per, 2 * stepx, count[2] * per);
	}
}

function draw() {
	drawRank('class');
	drawRank('grade');
}

function associationMining() {
    var chk = 0;
    var chkObjs = document.getElementsByName("table");
    for (var i = 0; i < chkObjs.length; i++) {
        if (chkObjs[i].checked) {
            chk = i;
            break;
        }
    }
    var table;
    if (chk == 0)
        table = "score";
    else
        table = "rank";
    var num = document.getElementById("numRules").value;
    var min = document.getElementById("minConfidence").value;
    var upper = document.getElementById("upperBoundMinSupport").value;
    var lower = document.getElementById("lowerBoundMinSupport").value;
    var delta = document.getElementById("delta").value;
    var xmlhttp;
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            document.getElementById("bestRules").innerHTML = xmlhttp.responseText;
        }
    }
    var url = "/score/AprioriServlet?table=" + table + "&num=" + num + "&min=" + min + "&upper=" + upper + "&lower=" + lower + "&delta=" + delta;
    xmlhttp.open("GET", url, false);
    xmlhttp.send();
}

//function exportExcel(tableId) {
//    var xmlhttp;
//    xmlhttp = new XMLHttpRequest();
//    var url = "/score/ExportExcelServlet?tableId=" + tableId;
//    xmlhttp.open("GET", url, false);
//    xmlhttp.send();
//}

function exportExcel() {
    var xmlhttp;
    xmlhttp = new XMLHttpRequest();
    var url = "/score/ExportExcelServlet";
    xmlhttp.open("GET", url, false);
    xmlhttp.send();
}