<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.servletContext.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.servletContext.contextPath}/resources/bootstrap/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link href="${pageContext.servletContext.contextPath}/resources/ext/select2/select2.min.css" rel="stylesheet">
<link href="${pageContext.servletContext.contextPath}/resources/ext/jqcloud/jqcloud.css" rel="stylesheet">
<link href="${pageContext.servletContext.contextPath}/resources/theme/css/AdminLTE.min.css" rel="stylesheet">
<link href="${pageContext.servletContext.contextPath}/resources/theme/css/skins/skin-blue.min.css" rel="stylesheet">
<link href="${pageContext.servletContext.contextPath}/resources/helloes/css/helloes.css" rel="stylesheet">

<script src="${pageContext.servletContext.contextPath}/resources/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
<!-- jQuery UI 1.11.4 -->
<script src="https://code.jquery.com/ui/1.11.2/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
$.widget.bridge('uibutton', $.ui.button);
</script>
<title><tiles:getAsString name="title" /></title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>