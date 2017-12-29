<#include "commonFtlVal.ftl" encoding="UTF-8" parse=true>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>springMVC freemarker测试</title>

    <!-- Bootstrap -->
    <link href="/assets/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/assets/bootstrap-table/css/bootstrap-table.min.css">

    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<h1>${"hello,"+username}</h1> ${"项目根路径："+request.contextPath}
<script src="/js/jquery/jquery.min.js"></script>
<script src="/assets/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="/assets/bootstrap-table/js/bootstrap.min.js"></script>
<script src="/assets/bootstrap-table/js/bootstrap-table.js"></script>
<script src="/assets/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>

</body>
</html>