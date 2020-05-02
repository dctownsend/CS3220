<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Room Reservation</title>
</head>
<body>

	<h3>Room Reservations for Senior Design Room B10</h3>
	<table border="1">
		<tr>
			<td></td>
			<c:forEach items="${finalDays}" var="days">
				<td>${days}</td>
			</c:forEach>
		</tr>
		<c:forEach items="${finalTimes}" var="times">
		<tr>
			<td>${times}</td>
			<c:forEach items="${finalDays}" var="days">
				<td><c:forEach items="${finalList}" var="list">
					<c:if test="${list.day==days && list.time==times}"><a href="DeleteReservation?time=${list.time}&day=${list.day}"> ${list.name}</a></c:if>
				</c:forEach></td>
			</c:forEach>
		</tr>
		</c:forEach>
	</table>
	<br>
	<form action="MakeReservation" method="post">
		<select name="selectedDay">
			<c:forEach items="${finalDays}" var="days">
				<option>${days}</option>
			</c:forEach>
		</select>
		
		<select name="selectedTime">
			<c:forEach items="${finalTimes}" var="times">
				<option>${times}</option>
			</c:forEach>
		</select>
		
		<input type="text" name="name"/>
		<input type="submit" value="Reserve"/>
	</form>
</body>
</html>