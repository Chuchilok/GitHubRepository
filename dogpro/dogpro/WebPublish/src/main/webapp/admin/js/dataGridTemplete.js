function find(pageNumber, pageSize, data, doc, url) {
    data = eval('(' + data + ')');
    data.paras.pageNO = pageNumber <= 0 ? 0 : pageNumber - 1;//因为数据读取从零开始
    data.paras.pageSize = pageSize;
    var toStr = JSON.stringify(data);
    doc.datagrid('getPager').pagination({pageSize: pageSize, pageNumber: pageNumber});//重置
    doc.datagrid("loading"); //加屏蔽
    //跨域
    $.ajax({
        url: WebPublishjsonpUrl,
        type: "get",
        dataType: "jsonp",
        jsonp: "callback", //服务端用于接收callback调用的function名的参数
        jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
        data: {params: toStr},
        success: function (data) {
            doc.datagrid('loadData', pageData(data.rows, data.total));//这里的pageData是我自己创建的一个对象，用来封装获取的总条数，和数据，data.rows是我在控制器里面添加的一个map集合的键的名称
            doc.datagrid("loaded"); //移除屏蔽
        },
        error: function (err) {
            $.messager.alert('操作提示', '获取信息失败...请联系管理员!', 'error');
            doc.datagrid("loaded"); //移除屏蔽
        }
    });
}

function pageData(list, total) {
    var obj = new Object();
    obj.total = total;
    obj.rows = list;
    return obj;
}

var title;
var tableWidth = 800;

function initDataGrid(obj, data, toolbar, title, serverPath, columns, tableWidth) {

    basePath = serverPath;
    doc = obj;
    obj.datagrid({
        url: null,
        width: 1000,
        fit: false,
        singleSelect: true, /*是否选中一行*/
        pagination: true, /*是否显示下面的分页菜单*/
        border: false,
        rownumbers: true,
        title: title,
        method: 'post',
        toolbar: toolbar,
        fitColumns: 'false',
        checkOnSelect: 'false',
        SelectOnCheck: 'false',
        columns: columns,
        loadMsg: '数据加载中,请稍候......'
    });
    //分页
    var pager = obj.datagrid('getPager');
    pager.pagination({
        pageSize: 10,
        pageList: [5, 10, 20, 50],// 可以设置每页记录条数的列表
        onBeforeRefresh: function () {
        },
        onSelectPage: function (pageNumber, pageSize) {//分页触发  
            find(pageNumber, pageSize, data, doc, basePath);
        }
    });
    find(1, 10, data, doc, basePath);//默认第一页数据
};
