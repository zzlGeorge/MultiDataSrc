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

function initTableServer($table, cols, url) {
    $($table).bootstrapTable({
        url: url,
        queryParams:{},
        pagination: true,
        pageNumber: 1,
        pageSize: 5,
        pageList: [5, 10, 15],
        sidePagination: 'server',
        columns: cols
    });
}