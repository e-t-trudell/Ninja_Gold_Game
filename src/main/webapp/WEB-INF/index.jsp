<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
 
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HERE BE GOLD ARGH!</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<!-- start here work on sizing image to fit background. 

may need to add image to an image tag then bootstrap the parent div-->
<!-- https://www.pirateshipvallarta.com/blog/wp-content/uploads/2016/03/Pirates-treasure.jpg -->

	<div id="img" class="has-bg-image" style="background-image:url('https://www.pirateshipvallarta.com/blog/wp-content/uploads/2016/03/Pirates-treasure.jpg');">
	<div class="container m-2 p-2 d-flex flex-column justify-content-start">
		<div class="col-4 m-2 p-2 d-flex align-items-center justify-content-between border border-info border-4">
			<h3>Your Gold</h3>
			<p class=" border border-success border-4 m-1 p-2 justify-text-center" style="width:50px;"><c:out value="${ gold }"></c:out></p>
		</div>
		<div class="container m-2  d-flex justify-content-around flex-wrap">
			<div class="form-group p-2 m-2 d-flex align-content-center justify-content-center border border-warning border-4">
   				<form action="/addgold" method="post">
   					<div class="form-group row">
   						<h4>Farm</h4>
   					</div>
   					<div class="form-group">
   						<label  for="farm">(Earn 20 Gold) </label>
   						<input class="form-control" type="hidden" name="farm" />
   						<button class="btn btn-primary" type="submit" value="Find Gold!">Find Gold!</button>
   					</div>
   				</form>
   			</div>
   			<div class="form-group p-2 m-2 d-flex align-content-center justify-content-center border border-warning border-4">
   				<form action="/addgold" method="post">
   					<div class="form-group row ">
   						<h4>Dragon Cave</h4>
   					</div>
   					<div class="form-group">
   						<label  for="cave">(Earn 40 Gold) </label>
   						<input class="form-control" type="hidden" name="cave"/>
   						<button class="btn btn-primary" type="submit" value="Find Gold!">Find Gold!</button>
   					</div>
   				</form>
   			</div>
   			<div class="form-group p-2 m-2 d-flex align-content-center justify-content-center border border-warning border-4">
   				<form action="/addgold" method="post">
   					<div class="form-group row">
   						<h4>Castle</h4>
   					</div>
   					<div class="form-group">
   						<label  for="castle">(Earn 20 Gold) </label>
   						<input class="form-control" type="hidden" name="castle"/>
   						<button class="btn btn-primary" type="submit" value="Find Gold!">Find Gold!</button>
   					</div>
   				</form>
   			</div>
   			<div class="form-group p-2 m-2 d-flex align-content-center justify-content-center border border-warning border-4">
   				<form action="/addgold" method="post">
   					<div class="form-group row">
   						<h4>Quest</h4>
   					</div>
   					<div class="form-group">
   						<label  for="quest">(Earn or Lose 50 Gold) </label>
   						<input class="form-control" type="hidden" name="quest"/>
   						<button class="btn btn-primary" type="submit" value="Find Gold!">Find Gold!</button>
   					</div>
   				</form>
   			</div>
   			<div class="form-group p-2 m-2 d-flex align-content-center justify-content-center border border-danger border-4">
   				<form action="/remove" method="post">
   					<div class="form-group row">
   						<h4>Remove</h4>
   					</div>
   					<div class="form-group">
   						<label  for="remove">(Remove all gold) </label>
   						<input class="form-control" type="hidden" name="remove"/>
   						<button class="btn btn-primary" type="submit" value="Find Gold!">Remove Gold!</button>
   					</div>
   				</form>
   			</div>
   		</div>
   		
		<div  class="row d-flex align-content-center justify-content-center">
			<h3>Activities</h3>
			<c:forEach var="item"  items="${activities}" >
				<p id="activities" class = "d-flex align-content-center justify-content-center border border-dark border-4"><c:out value="${ item }"></c:out></p>
			</c:forEach >
		</div>
		<%-- <div class="row">
			<h3>Model Loop</h3>
			<c:out value="${activitiesModel}"></c:out>
			<!-- add activities to session then loop through them here to add to display -->
			<c:forEach var="item"  items="${activityModel}" >
				<p class = "border border-dark border-4"><c:out value="${ item }"></c:out></p>
			</c:forEach >
		</div> --%>
	</div>
   </div>
</body>
</html>
