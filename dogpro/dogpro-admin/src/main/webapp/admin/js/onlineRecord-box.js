var params;//
var delId;

function newUser() {
    $('#dlg').dialog('open').dialog('center').dialog('setTitle', '新增用户');
    $('#fm').form('clear');
    // params是全局
    params = `{
    		"module":"addUser",
    		"action":"AdminService"
    	}`;// 新增样本
}

function editUser() {
    var row = $('#tb1').datagrid('getSelected');

    if (row) {
        $('#dlg').dialog('open').dialog('center').dialog('setTitle', '编辑用户');
        $('#fm').form('load', row);
    }
}

function destroyUser() {
    var row = $('#tb1').datagrid('getSelected');// 获取当前行
    console.log(row);

}

function refreshTotalOnline(){
	var param = "{'action': 'AdminService','module': 'getTotalOnlineUsers'," +
    "'paras': {'token':'" + token + "','adminUserId':'" + userId + "'}}";// 参数
	
	console.log(param)
    $.ajax({
        url: jsonUrl,
        type: "get",
        dataType: "jsonp",
        jsonp: "callback", // 服务端用于接收callback调用的function名的参数
        jsonpCallback: "refresh_jsonpCallback", // callback的function名称,服务端会把名称和data一起传递回来
        data: {params: param},
        success: function (data) {
            console.log(data)
            console.log(data.value.result.flag)
            if (data.value.result.flag == 1) {
            	var totalOnlineUsers = data.value.result.totalOnlineUsers
                console.log(data.value.result.totalOnlineUsers)
                $('#refresh-p').text(totalOnlineUsers+'人在线');
            }
        },
        error: function (err) {
            $.messager.alert('操作提示', '获取信息失败...请联系管理员!', 'error');
            doc.datagrid("loaded"); // 移除屏蔽
        }
    });
}


$('#refresh-btn').click(function(){
	refreshTotalOnline();
})
$(function(){
// $('#refresh-btn').click();
	
	refreshTotalOnline();
})
$(function () {
	
	var param = "{'action': 'AdminService','module': 'getOnlineRecord'," +
        "'paras': {'pageNO':'1','token':'" + token + "','pageSize':'5','adminUserId':'" + userId + "'}}";// 参数
    var columns = [[
        // { field: 'checkbox', checkbox: true },
        {field: 'recordNo', title: 'redis记录编号/db记录编号', width: 200},
        {field: 'totalOnlineUsers', title: '用户在线人数', width: 150},
        {field: 'recordTime', title: '记录时间', width: 200},
        {field: 'isDB', title: '是否已记录数据库', width: 150 },
       
        {
        	field: 'state', title: '', width: 100, formatter: function (value, row, index) {
        		if(row.isDB==0){
        		console.log(row.recordNo);
        		var recordNo = row.recordNo;
        		return`<button id="user_${row.recordNo}" data-recordNo="${recordNo}" style="border:none;background:#54B47E;color:#fff">记录数据库</button>`;
        		}
        		}
        }
    ]];// 字段
    initDataGrid($("#tb1"), param, '#toolbar', '在线用户数记录', '', columns,'1000');
    $(".pagination-load").click();// 触发刷新时间
    $("#delUser").click(function () {
        params = `{
        	"module":"delUser",
        	"action":"AdminService"
        	}`;
        var row = $('#tb1').datagrid('getSelected');
        delId = row.userId;
    });
    $(document).on('click', "[id^=user_]", function () {
       console.log($(this));
        var recordNo = $(this).attr('data-recordNo');
        console.log(recordNo)
        // alert($(this).attr('data-userId')+">>>>>>"+$(this).attr('data-state'));
        var param = `{
        		"module":"onlineRecordToDB",
        		"action":"AdminService",
        		"paras":{
        		"adminUserId":${userId},
        		"recordNO":"${recordNo}",
        		"token":"${token}"
        		}
        		}`;
        var jsonpUrl = jsonUrl;
        console.log(param)
        $.ajax({
            url: jsonpUrl,
            type: "get",
            dataType: "jsonp",
            jsonp: "callback", // 服务端用于接收callback调用的function名的参数
            jsonpCallback: "jsonpCallback", // callback的function名称,服务端会把名称和data一起传递回来
            data: {params: param},
            success: function (data) {
                console.log(data)
                if (data.value.state == 'success') {
                    $.messager.alert('提示信息', data.value.result.msg, 'info');
                    if(data.value.result.flag==1){
                    	window.location.reload();
                    }
                    console.log(111)
                } else {
                    $.messager.alert('提示信息', data.value.result.msg, 'error');
                }
            },
            error: function (err) {
                $.messager.alert('操作提示', '获取信息失败...请联系管理员!', 'error');
                doc.datagrid("loaded"); // 移除屏蔽
            }
        });
    });
});