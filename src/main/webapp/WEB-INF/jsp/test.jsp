<%--
  Created by IntelliJ IDEA.
  User: George
  Date: 2017/12/11
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="${assets}/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${assets}/bootstrap-table/css/bootstrap-table.min.css">

    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div style="margin: 10% 18%;">
    <table id="table"></table>
</div>
<script src="${assets}/jquery/jquery.min.js"></script>
<script src="${assets}/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="${assets}/bootstrap-table/js/bootstrap.min.js"></script>
<script src="${assets}/bootstrap-table/js/bootstrap-table.js"></script>
<script src="${assets}/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>
<script>
    $(function () {
        var cols = [{
            field: 'id',
            title: 'ID'
        }, {
            field: 'name',
            title: '名称'
        }, {
            field: 'price',
            title: '价格'
        }, {
            field: 'address',
            title: '地址'
        }];

        var data = [];
        for (var i = 0; i < 28; i++) {
            data.push({
                id: i + 1,
                name: 'Item' + (i + 1),
                price: '￥' + Math.random() * 1000,
                address: '地址' + Math.random() * 10
            });
        }

        initTable(cols, data);
    });

    function initTable(cols, data) {
        $('#table').bootstrapTable({
            pagination: true,
            pageNumber: 1,
            pageSize: 5,
            pageList: [5, 10, 15],
            sidePagination: 'client',
            columns: cols,
            data: data
        });
    }
</script>
</body>
</html>