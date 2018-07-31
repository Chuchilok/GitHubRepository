var params;//
var delId;

function newUser() {
    $('#dlg').dialog('open').dialog('center').dialog('setTitle', '新增用户');
    $('#fm').form('clear');
    //params是全局
    params = `{
    		"module":"addUser",
    		"action":"AdminService"
    	}`;//新增样本
}

function editUser() {
    var row = $('#tb1').datagrid('getSelected');

    if (row) {
        $('#dlg').dialog('open').dialog('center').dialog('setTitle', '编辑用户');
        $('#fm').form('load', row);
    }
}

function destroyUser() {
    var row = $('#tb1').datagrid('getSelected');//获取当前行
    console.log(row);

}

$(function () {
    var param = "{'action': 'AdminService','module': 'userList'," +
        "'paras': {'pageNO':'1','token':'" + token + "','pageSize':'5','adminUserId':'" + userId + "'}}";//参数
    var columns = [[
        // { field: 'checkbox', checkbox: true },
        {field: 'userId', title: '用户编号', width: 150},
        {field: 'nickname', title: '用户昵称', width: 150},
        {field: 'phone', title: '电话', width: 150},
        {
            field: 'state', title: '状态', width: 150, formatter: function (value, row, index) {
            return `<label class="switch switch-green">
                                		<input type="hidden" value="${row.state}">
        	        					<input type="checkbox" id="user_${row.userId}" data-userId="${row.userId}" data-state="${row.state}" class="switch-input" ${row.state == 1 ? 'checked' : ''}> 
        	        					<span class="switch-label" data-on="On" data-off="Off"  data-state= "${row.state}"></span> 
        	        					<span class="switch-handle"></span>
                					</label>`;
        }
        }//写多一个隐藏的列 记录 state
    ]];//字段
    initDataGrid($("#tb1"), param, '#toolbar', '用户管理', '', columns);
    $(".pagination-load").click();//触发刷新时间
    $("#delUser").click(function () {
        params = `{
        	"module":"delUser",
        	"action":"AdminService"
        	}`;
        var row = $('#tb1').datagrid('getSelected');
        delId = row.userId;
    });
    $(document).on('click', "[id^=user_]", function () {
        var state = -1;
        if ($(this).attr('data-state') == 0) {
            state = 0;
            $(this).attr('data-state', '1');
        } else {
            state = 1;
            $(this).attr('data-state', '0');
        }
        var uid = $(this).attr('data-userId');
        //alert($(this).attr('data-userId')+">>>>>>"+$(this).attr('data-state'));
        var param = `{
        		"module":"disableUser",
        		"action":"AdminService",
        		"paras":{
        		"adminUserId":${userId},
        		"userId":${uid},
        		"state":${state},
        		"token":"${token}"
        		}
        		}`;
        var jsonpUrl = jsonUrl;
        console.log(param)
        $.ajax({
            url: jsonpUrl,
            type: "get",
            dataType: "jsonp",
            jsonp: "callback", //服务端用于接收callback调用的function名的参数
            jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
            data: {params: param},
            success: function (data) {
                console.log(data)
                if (data.value.state == 'success') {
                    $.messager.alert('提示信息', data.value.result.msg, 'info');
                    console.log(111)
                } else {
                    $.messager.alert('提示信息', data.value.result.msg, 'error');
                }
            },
            error: function (err) {
                $.messager.alert('操作提示', '获取信息失败...请联系管理员!', 'error');
                doc.datagrid("loaded"); //移除屏蔽
            }
        });
    });
    //----------------------------------------------------------------------------------------------------------
    $('.search-box .search-span').click(function () {//点击搜索
        var phone = $('.search-input').val();
        console.log(phone);
        var searchParam = `{
        		"module":"searchUserByPhone",
        		"action":"AdminService",
        		"paras":{
        		"adminUserId":${userId},
        		"pageNO":"0",
        		"pageSize":"5",
        		"phone":'${phone}',
        		"token":"${token}"
        		}
        		}`;//参数
        initDataGrid($("#tb1"), searchParam, '#toolbar', '用户管理', '', columns);
    });


});