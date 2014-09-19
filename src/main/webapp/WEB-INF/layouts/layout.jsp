
<%@page import="javax.naming.InitialContext"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>ARI <tiles:insertAttribute name="title" ignore="true" /></title>
	
	
	
</head>
<body>

<tiles:importAttribute name="showHeaderFooter"/>

<div id="tile-container">
    <c:if test="${showHeaderFooter == 'true'}">
	   <div id="tile-header"><tiles:insertAttribute name="header" /></div>
	</c:if>
	
	<div>
		<div id="notices" style="display: none;">
		</div>
		<div id="messageDialog" title="Dialog" style="display: none;">
			<ul class="messages">
			</ul>
		</div>
		<div id="body-inner" class="clearfix">
			<div id="tile-body"><tiles:insertAttribute name="body" /></div>
		</div>
	</div>
	
	<c:if test="${showHeaderFooter == 'true'}">
	   <div id="tile-footer"><tiles:insertAttribute name="footer" /></div>
	</c:if>
</div>

</body>
</html>