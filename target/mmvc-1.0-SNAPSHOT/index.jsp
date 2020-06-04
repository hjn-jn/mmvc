<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <script type="application/javascript">
        function firstInit() {
            this.testAction.submit();
        }
    </script>
</head>
<body onload="firstInit();">
<h2>123456</h2>

<form name="testAction" action="<%=basePath%>/config/selectAllMenu"></form>
</body>
</html>
