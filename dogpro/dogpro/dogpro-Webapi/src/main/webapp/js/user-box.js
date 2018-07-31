var params;//
    var delId;
    function newUser(){
        $('#dlg').dialog('open').dialog('center').dialog('setTitle','新增用户');
        $('#fm').form('clear');
        //params是全局
        params = `{
    		"module":"addUser",
    		"action":"AdminService"
    	}`;//新增样本
    }
    function editUser(){
        var row = $('#tb1').datagrid('getSelected');
        if (row){
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','编辑用户');
            $('#fm').form('load',row);
            
            console.log(row.state)
            params = `{
        		"module":"editUser",
        		"action":"AdminService"
        	}`;//修改样本 
        }
    }
    
    $(function () {
        var param="{'action': 'AdminService','module': 'userList',"+
                "'paras': {'pageNO':'1','token':'safsfdsf','pageSize':'5'}}";//参数
                var columns = [[
                                { field: 'checkbox', checkbox: true },
                                { field: 'userId', title: '用户编号', width: 150},
                                { field: 'nickname', title: '用户昵称', width: 150 },
                                { field: 'phone', title: '电话', width: 150},
                                { field: 'state', title: '状态', width: 150,formatter:function(value,row,index){
                                	return  `<label class="switch switch-green">
                                		<input type="hidden" value="${row.state}">
        	        					<input type="checkbox" id="user_${row.userId}" data-userId="${row.userId}" data-state="${row.state}" class="switch-input" ${row.state == 1?'checked':''}> 
        	        					<span class="switch-label" data-on="On" data-off="Off"  data-state= "${row.state}"></span> 
        	        					<span class="switch-handle"></span>
                					</label>`;
                                } }//写多一个隐藏的列 记录 state
                            ]];//字段 
        initDataGrid($("#tb1"),param,'#toolbar','用户管理','/dogpro-Webapi/UsercenterApi/ApivDataGrid.do',columns);
        $(".pagination-load").click();//触发刷新时间
        $("#delUser").click(function(){
        	params = `{
        	"module":"delUser",
        	"action":"AdminService"
        	}`;
            var row = $('#tb1').datagrid('getSelected');
        	delId = row.userId;
        });
        $(document).on('click',"[id^=user_]",function(){
        	var state = -1;
        	if($(this).attr('data-state')==0){
        		state = 0;
        		$(this).attr('data-state','1');
        	}else{
        		state = 1;
        		$(this).attr('data-state','0');
        	}
        	var userId = $(this).attr('data-userId');
        	//alert($(this).attr('data-userId')+">>>>>>"+$(this).attr('data-state'));
        	var param = `{
        		"module":"disableUser",
        		"action":"AdminService",
        		"paras":{
        		"userId":${userId},
        		"state":${state},
        		"token":"asssssssssssssssssssssssssssssssssssssssssssssssssss"
        		}
        		}`;
        	$.post('/dogpro-Webapi/UsercenterApi/Apiv1.do',{params:param},function(data){
        		if (data.state == 'success') {
        			$.messager.alert('提示信息',data.result.msg,'info');
				}else{
					$.messager.alert('提示信息',data.result.msg,'error');
				} 
        	});
        });
       //----------------------------------------------------------------------------------------------------------
        $('.search-box .search-span').click(function(){//点击搜索
        	var phone = $('.search-input').val();
        	console.log(phone);
        	var searchParam=`{
        		"module":"searchUserByPhone",
        		"action":"AdminService",
        		"paras":{
        		"pageNO":"0",
        		"pageSize":"5",
        		"phone":'${phone}',
        		"token":"asssssssssssssssssssssssssssssssssssssssssssssssssss"
        		}
        		}`;//参数
        	//find(1, 5,param,$("#tb1"),'/dogpro-Webapi/UsercenterApi/ApivDataGrid.do');//搜索   1...只数据库从0开始  
        	initDataGrid($("#tb1"),searchParam,'#toolbar','用户管理','/dogpro-Webapi/UsercenterApi/ApivDataGrid.do',columns);
        });
        
        
    });