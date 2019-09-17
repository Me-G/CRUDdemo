<%-- 
    Document   : bootstraptable
    Created on : 2019年9月15日, 下午3:53:42
    Author     : ME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

        <script src="https://cdn.bootcss.com/popper.js/1.15.0/umd/popper.min.js"></script>
        <%--引入Bootstrap的脚本和样式--%>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://unpkg.com/jquery@3.4.1/dist/jquery.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

        <link href="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.css" rel="stylesheet">
        <script src="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap-table/1.15.4/locale/bootstrap-table-zh-CN.min.js"></script>
        <script src="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table-locale-all.min.js"></script>
        
        

        <!--对于Bootstrap v4，使用Font Awesome作为默认图标，因此需要导入Font Awesome链接。-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
        <%--4、页面Js文件的引用<script src="${pageContext.request.contextPath}/myData.js"></script>--%>


        <title>Hello, Bootstrap Table!</title>

        <style>
            .select,
            #locale {
                width: 100%;
            }
            .like {
                margin-right: 10px;
            }
        </style>

    </head>

    <body>
        <h1>Table</h1>
        <br/>
        <div class="select">
            <select class="form-control" id="locale">
                <option value="af-ZA">af-ZA</option>
                <option value="ar-SA">ar-SA</option>
                <option value="ca-ES">ca-ES</option>
                <option value="cs-CZ">cs-CZ</option>
                <option value="da-DK">da-DK</option>
                <option value="de-DE">de-DE</option>
                <option value="el-GR">el-GR</option>
                <option value="en-US">en-US</option>
                <option value="es-AR">es-AR</option>
                <option value="es-CL">es-CL</option>
                <option value="es-CR">es-CR</option>
                <option value="es-ES">es-ES</option>
                <option value="es-MX">es-MX</option>
                <option value="es-NI">es-NI</option>
                <option value="es-SP">es-SP</option>
                <option value="et-EE">et-EE</option>
                <option value="eu-EU">eu-EU</option>
                <option value="fa-IR">fa-IR</option>
                <option value="fi-FI">fi-FI</option>
                <option value="fr-BE">fr-BE</option>
                <option value="fr-FR">fr-FR</option>
                <option value="he-IL">he-IL</option>
                <option value="hr-HR">hr-HR</option>
                <option value="hu-HU">hu-HU</option>
                <option value="id-ID">id-ID</option>
                <option value="it-IT">it-IT</option>
                <option value="ja-JP">ja-JP</option>
                <option value="ka-GE">ka-GE</option>
                <option value="ko-KR">ko-KR</option>
                <option value="ms-MY">ms-MY</option>
                <option value="nb-NO">nb-NO</option>
                <option value="nl-NL">nl-NL</option>
                <option value="pl-PL">pl-PL</option>
                <option value="pt-BR">pt-BR</option>
                <option value="pt-PT">pt-PT</option>
                <option value="ro-RO">ro-RO</option>
                <option value="ru-RU">ru-RU</option>
                <option value="sk-SK">sk-SK</option>
                <option value="sv-SE">sv-SE</option>
                <option value="th-TH">th-TH</option>
                <option value="tr-TR">tr-TR</option>
                <option value="uk-UA">uk-UA</option>
                <option value="ur-PK">ur-PK</option>
                <option value="uz-Latn-UZ">uz-Latn-UZ</option>
                <option value="vi-VN">vi-VN</option>
                <option value="zh-CN" selected>zh-CN</option>
                <option value="zh-TW">zh-TW</option>
            </select>
        </div>

        <div id="toolbar">
            <button id="remove" class="btn btn-danger" disabled>
                <i class="glyphicon glyphicon-remove"></i> Delete
            </button>
        </div>
        <table
            id="table"
            data-toolbar="#toolbar"
            data-search="true"
            data-show-refresh="true"
            data-show-toggle="true"
            data-show-fullscreen="true"
            data-show-columns="true"
            data-detail-view="true"
            data-show-export="true"
            data-click-to-select="true"
            data-detail-formatter="detailFormatter"
            data-minimum-count-columns="2"
            data-show-pagination-switch="true"
            data-pagination="true"
            data-id-field="id"
            data-page-list="[10, 25, 50, 100, all]"
            data-show-footer="true"
            data-side-pagination="server"
            data-url="tableData">
            <!--data-response-handler="responseHandler"-->

        </table>


        <script>
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
                    '<a class="edit" href="javascript:void(0)" title="Edit">',
                    '<i class="fas fa-edit"></i>',
                    '</a>  ',
                    '<a class="remove" href="javascript:void(0)" title="Remove">',
                    '<i class="fa fa-trash"></i>',
                    '</a>'
                ].join('');
            }
            
            function insertFormatter() {
                return [
                    '<a class="insert" href="javascript:void(0)" title="Insert">',
                    '<i class="fas fa-edit"></i>',
                    '</a>  '
                ].join('');
            }

            window.operateEvents = {
//                'click .edit': function (e, value, row, index) {
//                    $element.attr('contenteditable', true);
                    'click .edit': function (e, value, row, index) {
                    alert('You click edit action, row: ' + JSON.stringify(row));
                     
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
                                class:'editable',
                                align: 'center'
                            }, {
                                field: 'value',
                                title: 'Value',
                                sortable: true,
                                class:'editable',
                                align: 'center'
//                    footerFormatter: totalPriceFormatter
                            }, {
                                field: 'remark',
                                title: 'Remark',
                                sortable: true,
                                class:'editable',
                                align: 'center'
//                    footerFormatter: totalPriceFormatter
                            }, {
                                field: 'operate',
                                title: 'Operate',
                                align: 'center',
                                clickToSelect: false,
                                events: window.operateEvents,
                                formatter: operateFormatter,
                                
                                
                                footerFormatter: insertFormatter
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










            //可显示数据的代码
//            var $table = $('#table');
//
//            var res = function (request) { //加载服务器数据之前的处理程序，可以用来格式化数据。参数：res为从服务器请求到的数据。
//                return {
//                    "rows": request.data.rows,
//                    "total": request.data.total
//                };
//            };
//
//            function initTable() {
//                $table.bootstrapTable('destroy').bootstrapTable({
//
//                    ajax: function (res) {
//
//                        $.ajax({
//                            type: "GET",
//                            url: "tableData",
//                            contentType: "application/json;charset=utf-8",
//                            dataType: "json",
//
//                            success: function (msg) {
//                                res.success({
//                                    row: msg
//                                });
//                                $table.bootstrapTable('load', msg);
//                            },
//                            error: function () {
//                                alert("错误");
//                            }
//                        });
//                    },
//                    height: 550,
//                    locale: $('#locale').val(),
//                    columns: [
//                        [{
//                                field: 'state',
//                                checkbox: true,
//                                rowspan: 2,
//                                align: 'center',
//                                valign: 'middle'
//                            }, {
//                                title: 'ID',
//                                field: 'id',
//                                rowspan: 2,
//                                align: 'center',
//                                valign: 'middle',
//                                sortable: true
//                                        //                    footerFormatter: totalTextFormatter
//                            }, {
//                                title: 'Detail',
//                                colspan: 4,
//                                align: 'center'
//                            }],
//                        [{
//                                field: 'name',
//                                title: 'Name',
//                                sortable: true,
//                                //                    footerFormatter: totalNameFormatter,
//                                align: 'center'
//                            }, {
//                                field: 'value',
//                                title: 'Value',
//                                sortable: true,
//                                align: 'center'
//                                        //                    footerFormatter: totalPriceFormatter
//                            }, {
//                                field: 'remark',
//                                title: 'Remark',
//                                sortable: true,
//                                align: 'center'
//                                        //                    footerFormatter: totalPriceFormatter
//                            }, {
//                                field: 'operate',
//                                title: 'Operate',
//                                align: 'center',
//                                clickToSelect: false
////                                events: window.operateEvents,
////                                formatter: operateFormatter
//                            }]
//                    ]
//                });
//            }
//
//            $(function () {
//                initTable();
//            });

        </script>


        <a href="logout">登出</a>
    </body>
</html>
