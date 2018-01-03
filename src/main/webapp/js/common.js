function initTableClient($table, cols, data) {
    $($table).bootstrapTable({
        pagination: true,
        pageNumber: 1,
        pageSize: 5,
        pageList: [5, 10, 15],
        sidePagination: 'client',
        columns: cols,
        data: data
    });
}

function initTableServer($id, cols, url, params) {
    $($id).bootstrapTable({
        striped: true,
        url: url,
        method: 'post',
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        queryParams: params, //参数
        queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求
        sortable: false,//排序
        cache: false,//禁用缓存
        pageNumber: 1,
        pagination: true,//启动分页
        sidePagination: "server",
        pageSize: 10,
        pageList: [5, 10, 20],
        columns: cols,
        responseHandler: function (res) {
            return {
                "total": res.totalDisplaySize,//总页数
                "rows": res.dataList  //数据
            };
        }
    });
}

/**
 * 有列变化的刷新
 * */
function refreshBTable($id, params, cols) {
    $($id).bootstrapTable('refreshOptions', {
        pageNumber: 1,
        queryParams: params,
        columns: cols
    });
}

/**
 * 无列变化的刷新
 * */
function refresh($id, params) {
    $($id).bootstrapTable('refreshOptions', {
        pageNumber: 1,
        queryParams: params
    });
}

/**
 * 数据库下拉框初始化
 * */
function initSrcNames($selectId) {
    $.ajax({
        type: "POST",
        url: "/dbSrc/baseSrcInfo",
        data: {},
        success: function (msg) {
            var optionsHtml = '<option value="0">全部</option>';
            var data = msg.dataList;
            for (var i = 0; i < data.length; i++) {
                var obj = data[i];
                optionsHtml += '<option value="' + obj["id"] + '">' + obj["srcName"] + '</option>';
            }
            $($selectId).html(optionsHtml);
        }
    });
}


/**
 * 将日期时间戳转换为指定格式日期字符串
 * */
function timeStampToDateString(timeStamp, pattern) {
    var newDate = new Date();
    newDate.setTime(timeStamp);
    return newDate.format(pattern);
}

/**
 * 扩展日期格式化
 * */
Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
};