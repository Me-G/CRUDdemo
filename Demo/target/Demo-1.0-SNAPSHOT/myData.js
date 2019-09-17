var $table = $('#table');
var $remove = $('#remove');
var selections = [];

function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id;
    });
}

function detailFormatter(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    });
    return html.join('');
}

function operateFormatter(value, row, index) {
    return [
        '<a class="like" href="javascript:void(0)" title="Like">',
        '<i class="fa fa-heart"></i>',
        '</a>  ',
        '<a class="remove" href="javascript:void(0)" title="Remove">',
        '<i class="fa fa-trash"></i>',
        '</a>'
    ].join('');
}

window.operateEvents = {
    'click .like': function (e, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .remove': function (e, value, row, index) {
        $table.bootstrapTable('remove', {
            field: 'id',
            values: [row.id]
        });
    }
};

function initTable() {
    $table.bootstrapTable('destroy').bootstrapTable({
        ajax: function (request) {

            $.ajax({
                type: "GET",
                url: "tableData",
                contentType: "application/json;charset=utf-8",
                dataType: "json",

                success: function (msg) {
                    request.success({
                        row: msg
                    });
                    $table.bootstrapTable('load', msg);
                },
                error: function () {
                    alert("错误");
                }
            });
        },
        height: 550,
        locale: $('#locale').val(),
        columns: [
            [{
                    field: 'state',
                    checkbox: true,
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: 'ID',
                    field: 'id',
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle',
                    sortable: true
//                    footerFormatter: totalTextFormatter
                }, {
                    title: 'Detail',
                    colspan: 4,
                    align: 'center'
                }],
            [{
                    field: 'name',
                    title: 'Name',
                    sortable: true,
//                    footerFormatter: totalNameFormatter,
                    align: 'center'
                }, {
                    field: 'value',
                    title: 'Value',
                    sortable: true,
                    align: 'center'
//                    footerFormatter: totalPriceFormatter
                }, {
                    field: 'remark',
                    title: 'Remark',
                    sortable: true,
                    align: 'center'
//                    footerFormatter: totalPriceFormatter
                }, {
                    field: 'operate',
                    title: 'Operate',
                    align: 'center',
                    clickToSelect: false,
                    events: window.operateEvents,
                    formatter: operateFormatter
                }]
        ]
    });
    $table.on('check.bs.table uncheck.bs.table ' +
            'check-all.bs.table uncheck-all.bs.table',
            function () {
                $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);

                // save your data, here just save the current page
                selections = getIdSelections();
                // push or splice the selections if you want to save all data selections
            });
    $table.on('all.bs.table', function (e, name, args) {
        console.log(name, args);
    });
    $remove.click(function () {
        var ids = getIdSelections();
        $table.bootstrapTable('remove', {
            field: 'id',
            values: ids
        });
        $remove.prop('disabled', true);
    });
}

$(function () {
    initTable();
    $('#locale').change(initTable);
});














//var $table = $('#table');
//var $remove = $('#remove');
//var selections = [];
//
//function getIdSelections() {
//    return $.map($table.bootstrapTable('getSelections'), function (row) {
//        return row.id;
//    });
//}
//
//function responseHandler(res) {
//    $.each(res.rows, function (i, row) {
//        //$.inArray()函数用于在数组中搜索指定的值,并返回其索引值。如果数组中不存在该值,则返回-1;
//        //$.inArray(value,array)    --value是要查找的值，array是被查找的数组。
//        row.state = $.inArray(row.id, selections) !== -1;
//    });
//    return res;
////    return {
////                    "total": res.total, //总页数
////                    "rows": res.rows   //数据
////                };
//}
//
//function detailFormatter(index, row) {
//    var html = [];
//    $.each(row, function (key, value) {
//        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
//    });
//    return html.join('');
//}
//
//function operateFormatter(value, row, index) {
//    return [
//        '<a class="like" href="javascript:void(0)" title="Like">',
//        '<i class="fa fa-heart"></i>',
//        '</a>  ',
//        '<a class="remove" href="javascript:void(0)" title="Remove">',
//        '<i class="fa fa-trash"></i>',
//        '</a>'
//    ].join('');
//}
//
//window.operateEvents = {
//    'click .like': function (e, value, row, index) {
//        alert('You click like action, row: ' + JSON.stringify(row));
//    },
//    'click .remove': function (e, value, row, index) {
//        $table.bootstrapTable('remove', {
//            field: 'id',
//            values: [row.id]
//        });
//    }
//};
//
//function totalTextFormatter(data) {
//    return 'Total';
//}
//
//function totalNameFormatter(data) {
//    return data.length;
//}
//
//function totalPriceFormatter(data) {
//    var field = this.field;
//    return '$' + data.map(function (row) {
//        return +row[field].substring(1);
//    }).reduce(function (sum, i) {
//        return sum + i;
//    }, 0);
//}
//
//function initTable() {
//    $table.bootstrapTable('destroy').bootstrapTable({
//        height: 550,
//        locale: $('#locale').val(),
//        columns: [
//            [{
//                    field: 'state',
//                    checkbox: true,
//                    rowspan: 2,
//                    align: 'center',
//                    valign: 'middle'
//                }, {
//                    title: 'ID',
//                    field: 'id',
//                    rowspan: 2,
//                    align: 'center',
//                    valign: 'middle',
//                    sortable: true,
//                    footerFormatter: totalTextFormatter
//                }, {
//                    title: 'Detail',
//                    colspan: 4,
//                    align: 'center'
//                }],
//            [{
//                    field: 'name',
//                    title: 'Name',
//                    sortable: true,
//                    footerFormatter: totalNameFormatter,
//                    align: 'center'
//                }, {
//                    field: 'value',
//                    title: 'Value',
//                    sortable: true,
//                    align: 'center',
//                    footerFormatter: totalPriceFormatter
//                }, {
//                    field: 'remark',
//                    title: 'Remark',
//                    sortable: true,
//                    align: 'center',
//                    footerFormatter: totalPriceFormatter
//                }, {
//                    field: 'operate',
//                    title: 'Operate',
//                    align: 'center',
//                    clickToSelect: false,
//                    events: window.operateEvents,
//                    formatter: operateFormatter
//                }]
//        ]
//    });
//    $table.on('check.bs.table uncheck.bs.table ' +
//            'check-all.bs.table uncheck-all.bs.table',
//            function () {
//                $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
//
//                // save your data, here just save the current page
//                selections = getIdSelections();
//                // push or splice the selections if you want to save all data selections
//            });
//    $table.on('all.bs.table', function (e, name, args) {
//        console.log(name, args);
//    });
//    $remove.click(function () {
//        var ids = getIdSelections();
//        $table.bootstrapTable('remove', {
//            field: 'id',
//            values: ids
//        });
//        $remove.prop('disabled', true);
//    });
//}
//
//$(function () {
//    initTable();
//    $('#locale').change(initTable);
//});
