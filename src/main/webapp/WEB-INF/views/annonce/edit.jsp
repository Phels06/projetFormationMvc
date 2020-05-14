<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"></c:set>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx}/bootstrap/css/bootstrap.min.css">
<script type="text/javascript"
	src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
<title>edition</title>
</head>
<body>
	<div class="container">
		<h1>edition</h1>
		<c:url var="action" value="/personne/save"></c:url>
		<form:form action="${action}" method="get" modelAttribute="annonce">
			<form:hidden path="version" />
			<div class="form-group">
				<form:label path="id">id:</form:label>
				<form:input path="id" readonly="true"
					placeholder="generer automatiquement" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="dateAnnonce">dateAnnonce:</form:label>
				<form:input path="dateAnnonce" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="note">note:</form:label>

				<form:select path="note" items="${notes}" itemLabel="label"
					cssClass="form-control"></form:select>
			</div>
			<div>
				<button type="submit" class="btn btn-outline-success">enregistrer</button>
				<a href="${ctx}/annonce" class="btn btn-outline-warning">annuler</a>
			</div>
		</form:form>
	</div>
</body>
</html>