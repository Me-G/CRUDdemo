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

        <link href="https://cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.1/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.1/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap-table/1.15.4/extensions/editable/bootstrap-table-editable.min.js"></script>

        <!--对于Bootstrap v4，使用Font Awesome作为默认图标，因此需要导入Font Awesome链接。-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
        <%--4、页面Js文件的引用<script src="${pageContext.request.contextPath}/myData.js"></script>--%>


        <title>Hello, Bootstrap Table!</title>

        <style>
            .select,
            #locale {
                width: 100%;
            }
            /*            .save {
                            margin-right: 10px;
                        }*/
            .glyphicon-ok::before {
                content: "\f00c";
            }
            .glyphicon-remove::before {
                content: "\f00d";
            }
            .glyphicon {
                font-family: 'Font Awesome 5 Free';
                font-weight: 900;
                font-style: normal;
            }
        </style>

    </head>

    <body>
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

            <button id="insert" class="btn btn-danger">
                <i class="fas fa-plus"></i> 插入
            </button>
        </div>
<!--        <div class="float-right search btn-group">
        </div>-->
        <table
            id="table"
            data-toggle="table"
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
            data-id-field="id"
            data-show-footer="false"
            data-show-pagination-switch="true"
            data-pagination="true"
            data-side-pagination="server"       
            data-page-list="[10, 25, 50, 100, all]"
            data-cache="false" 
            data-response-handler="responseHandler">
        </table>


        <script>
            var $table = $('#table');
            var $remove = $('#remove');
            var $insert = $('#insert');
//            var $search = $('#search');
            var selections = [];
            function getIdSelections() {
                return $.map($table.bootstrapTable('getSelections'), function (row) {
                    return row.id;
                });
            }

            function responseHandler(res) {
                $.each(res.rows, function (i, row) {
                    row.state = $.inArray(row.id, selections) !== -1;
                });
                return res;
            }

            function detailFormatter(index, row) {
                var html = [];
                $.each(row, function (key, value) {
                    html.push('<p><b>' + key + ':</b> ' + value + '</p>');
                });
                return html.join('');
            }

            //保存图标
            function saveFormatter(value, row, index) {
                return [
                    '<a id="save" href="javascript:void(0)" class="save" title="Save"><i class="far fa-save"></i></a>'
                ].join("");
            }
            //更新或者插入数据
            window.saveEvents = {
                'click .save': function (e, value, row, index) {
                    $.ajax({
                        type: "POST",
                        url: "updateORinsert",
                        contentType: "application/json;charset=utf-8",
                        /* 特别需要注意这里，需要现将json数组通过JSON.stringify()处理一下之后，才能作为我们需要的参数传过去*/
                        data: JSON.stringify(row),
                        dataType: 'json',
                        message: function () {
                            alert('data: ' + JSON.stringify(row));
                        },
                        success: function (data, status) {
                            if (status === "success") {
                                $table.bootstrapTable('refresh'); //确保前端界面与数据库一
                            }
                        },
                        error: function () {
                            $table.bootstrapTable('refresh'); //确保前端界面与数据库一
                            alert('编辑失败');
                        },
                        complete: function () {
                            $insert.prop('disabled', false);
                            $remove.prop('disabled', true);
                        }
                    });
                }
            };
            //删除图标
            function deleteFormatter(value, row, index) {
                return [
                    '<a id="delete" class="delete" href="javascript:void(0)" title="Delete"><i class="fa fa-trash"></i></a>'
                ].join("");
            }
            //删除数据
            window.deleteEvents = {
                'click .delete': function (e, value, row, index) {
                    var ids = [row.id];
                    $.ajax({
                        type: "POST",
                        url: "delete",
                        contentType: "text/xml;charset=utf-8",
                        /* 特别需要注意这里，需要现将json数组通过JSON.stringify()处理一下之后，才能作为我们需要的参数传过去*/
                        data: JSON.stringify(ids),
                        traditional: true,
                        dataType: 'text',
                        success: function (msg) {
                            $table.bootstrapTable('refresh'); //确保前端界面与数据库一
                        },
                        error: function () {
                            $table.bootstrapTable('refresh'); //确保前端界面与数据库一
                            alert('编辑失败');
                        },
                        complete: function () {
                        }
                    });
                }
            };
            //初始化表格
            function initTable() {
                $table.bootstrapTable('destroy').bootstrapTable({
                    cache: false, //是否使用缓存，默认为true
                    height: 550,
                    locale: $('#locale').val(),
                    clickToSelect: true, //是否启用点击选中行
                    ajax: function (request) {
                        $.ajax({//contentType是传输过去的时候的数据类型，dataType是接收服务器的时候的数据类型
                            type: "GET",
                            url: "tableData",
                            contentType: "application/json;charset=utf-8",
                            dataType: "json",
                            success: function (msg) {
                                msg = {
                                    "total": msg.length, //总页数
                                    "rows": msg   //数据
                                };
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
                    columns: [
                        [{
                                field: 'state',
                                checkbox: true,
                                rowspan: 2,
                                align: 'center',
                                valign: 'middle'
                            }, {
                                title: 'Detail',
                                colspan: 4,
                                align: 'center'
                            }, {
                                field: 'operate',
                                title: 'Operate',
                                colspan: 2,
                                align: 'center',
                                valign: 'middle'
                            }],
                        [{
                                title: 'ID',
                                field: 'id',
                                align: 'center',
                                valign: 'middle',
                                sortable: true
                            }, {
                                field: 'name',
                                title: 'Name',
                                sortable: true,
                                editable: {
                                    type: "text", //编辑框的类型。支持text|textarea|select|date|checklist等
                                    title: "Name", //编辑框的标题
                                    disabled: false, //是否禁用编辑
                                    emptytext: "", //空值的默认文本
                                    mode: "inline", //编辑框的模式：支持popup和inline两种模式，默认是popup
                                    validate: function (v) { //字段验证
                                        if (!$.trim(v)) {
                                            return '不能为空';
                                        }
                                    }
                                },
                                align: 'center'
                            }, {
                                field: 'value',
                                title: 'Value',
                                sortable: true,
                                editable: {
                                    type: "text", //编辑框的类型。支持text|textarea|select|date|checklist等
                                    title: "Value", //编辑框的标题
                                    disabled: false, //是否禁用编辑
                                    emptytext: "", //空值的默认文本
                                    mode: "inline" //编辑框的模式：支持popup和inline两种模式，默认是popup
                                },
                                align: 'center'
                            }, {
                                field: 'remark',
                                title: 'Remark',
                                sortable: true,
                                editable: {
                                    type: "text", //编辑框的类型。支持text|textarea|select|date|checklist等
                                    title: "Remark", //编辑框的标题
                                    disabled: false, //是否禁用编辑
                                    emptytext: "", //空值的默认文本
                                    mode: "inline" //编辑框的模式：支持popup和inline两种模式，默认是popup
                                },
                                align: 'center'
                            }, {
                                field: 'saveOperator',
                                title: 'Save',
                                align: 'center',
                                events: saveEvents,
                                formatter: saveFormatter
                            }, {
                                field: 'deleteOperator',
                                title: 'Delete',
                                align: 'center',
                                events: deleteEvents,
                                formatter: deleteFormatter
                            }
                        ]
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
                //删除多行
                $remove.click(function () {
                    var ids = getIdSelections();
                    $.ajax({
                        type: "POST",
                        url: "delete",
                        contentType: "text/xml;charset=utf-8",
                        /* 特别需要注意这里，需要现将json数组通过JSON.stringify()处理一下之后，才能作为我们需要的参数传过去*/
                        data: JSON.stringify(ids),
                        traditional: true,
                        dataType: 'text',
                        success: function (msg) {
                            $table.bootstrapTable('refresh'); //确保前端界面与数据库一
                        },
                        error: function () {
                            $table.bootstrapTable('refresh'); //确保前端界面与数据库一
                            alert('编辑失败');
                        },
                        complete: function () {
                        }
                    });
                    $remove.prop('disabled', true);
                });
                //插入按钮
                $insert.click(function () {
                    var data = {'id': 0};
                    $table.bootstrapTable('prepend', data);
                    $insert.prop('disabled', true);
                });
                //搜索
                $('.search').on('changed.bs.select',function(e){
                    alert("搜索");
                });
            }

            $(function () {
                initTable();
                $('#locale').change(initTable);
            });

        </script>


        <a href="logout">登出</a>
    </body>
</html>





