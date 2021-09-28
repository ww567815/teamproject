<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<title>2020년 코로나</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
google.load('visualization', '1', {packages: ['corechart']});
</script>
<script type="text/javascript">   
function drawVisualization() {
// Some raw data (not necessarily accurate)
var data = google.visualization.arrayToDataTable([
['날짜', 'Covid19', '6일', '7일'],
['1월 1주',  0,      40,         20],
['1월 2주',  0,      20,        30],
['1월 3주',  0,      30,        50],
['1월 4주',  0,      70,        80],
['2월 1주',  0,      70,         60],
['2월 2주',  0,      200,         150]
]);
  
var options = {
title : '2021년 ',
vAxis: {title: "인원"},
hAxis: {title: "날짜"},
seriesType: "bars",
series: {1: {type: "line"},2: {type: "line"}}
};
var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
chart.draw(data, options);
}
google.setOnLoadCallback(drawVisualization);
</script>
</head>
<body>
<div id="chart_div" style="width: 900px; height: 500px;"></div>
</body>
</html>
