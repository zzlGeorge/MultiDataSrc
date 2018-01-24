<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: George
  Date: 2018/1/18
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    function refreshMaterials() {
        $.ajax({
            url: window.location.href,  //本页面请求url
            method: 'get',
            data: {rqId: '1111'}, //参数
            success: function (data, textStatus, jqXHR) {
                alert(data);
                console.info(data);
                window.location.reload();
            },
            error: function (data) {
                alert("failure");
            }
        });
    }
    <%
      String requestIdStr = request.getParameter("rqId");
      if (requestIdStr != null) {
          PrintWriter writer = response.getWriter();
          writer.write("success!");
          writer.close();
      }
    %>
</script>
</body>
</html>
