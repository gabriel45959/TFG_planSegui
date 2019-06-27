<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${NombrePantalla}</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath}/js/jquery_1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap_3.3.7.min.js"></script> 
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
</head>
<body>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"></a>
			</div>
			  ${MenuOpcionExtras}
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" ><span
						class="glyphicon glyphicon-user"></span> <c:if
							test="${pageContext.request.userPrincipal.name != null}">
		
			${pageContext.request.userPrincipal.name} 
		
	</c:if> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="javascript:formSubmit()"> <span class="glyphicon glyphicon-log-in"></span> Salir</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<!-- CONTENIDOS -->
	sdadasdasdas

</body>
</html>