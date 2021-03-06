function find(pageNumber, pageSize,data,doc,url)
{
//	console.log(data);
//	data =JSON.parse("'"+data+"'");
		data = eval('(' + data + ')');
    	data.paras.pageNO=pageNumber<=0?0:pageNumber-1;//因为数据读取从零开始
    	data.paras.pageSize=pageSize;
    	var toStr = JSON.stringify(data);
    	doc.datagrid('getPager').pagination({pageSize : pageSize, pageNumber : pageNumber});//重置
    	doc.datagrid("loading"); //加屏蔽
//    	//跨域
//    	var jsonpUrl =  'http://192.168.199.140:5200/dogpro-Webapi/UsercenterApi/ApivDataGridP.do';
//    	$.ajax({
//            url: jsonpUrl,
//            type: "get",
//            dataType: "jsonp",
//            jsonp: "callback", //服务端用于接收callback调用的function名的参数
//            jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
//            data:{params:toStr},
//            success : function(data) {
//            	doc.datagrid('loadData',pageData(data.value.rows,data.value.total));//这里的pageData是我自己创建的一个对象，用来封装获取的总条数，和数据，data.rows是我在控制器里面添加的一个map集合的键的名称
//                doc.datagrid("loaded"); //移除屏蔽
//            },
//            error : function(err) {
//                $.messager.alert('操作提示', '获取信息失败...请联系管理员!', 'error');
//                doc.datagrid("loaded"); //移除屏蔽
//            }
//        });
         $.ajax({
             type : "POST",
             dataType : "json",
             url : url,
             data : {params:toStr},
             success : function(data) {
            	 console.log("result>>>>"+JSON.stringify(data));
             	doc.datagrid('loadData',pageData(data.rows,data.total));//这里的pageData是我自己创建的一个对象，用来封装获取的总条数，和数据，data.rows是我在控制器里面添加的一个map集合的键的名称
                 doc.datagrid("loaded"); //移除屏蔽
             },
             error : function(err) {
                 $.messager.alert('操作提示', '获取信息失败...请联系管理员!', 'error');
                 doc.datagrid("loaded"); //移除屏蔽
             }
         });
}

function pageData(list,total){
    var obj=new Object(); 
    obj.total=total; 
    obj.rows=list; 
    return obj; 
}
/**
 * obj -- > 例如；$("#xxx") datagrid document（对象）
 * data --> 参数
 * columns 字段列表
 * serverPath = 访问路径
 * toolbar 工具栏ID
 * title 标题
 */
var title;
var tableWidth = 800;
function initDataGrid(obj,data,toolbar,title,serverPath,columns,tableWidth) {

    basePath = serverPath;
    doc = obj;
    obj.datagrid({
        url : null,
        width: tableWidth,
        singleSelect : true, /*是否选中一行*/
        pagination : true,/*是否显示下面的分页菜单*/
        border:false,
        rownumbers:true,
        title: title,
        method: 'post',
        toolbar: toolbar,
        fitColumns: 'true',
        checkOnSelect: 'false',
        SelectOnCheck: 'false',
        columns : columns,
        fitColumns:'true',
        loadMsg : '数据加载中,请稍候......'
    });
    //分页
    var pager=obj.datagrid('getPager');
    pager.pagination({
    	pageSize: 10,
        pageList : [ 5,10,20,50 ],// 可以设置每页记录条数的列表
        onBeforeRefresh: function () {  
        },  
        onSelectPage: function (pageNumber, pageSize) {//分页触发  
            find(pageNumber, pageSize,data,doc,basePath);  
        }
    });
    find(1, 10 ,data,doc,basePath);//默认第一页数据
}

//----------------------------弹框保存和删除---------------------------------->
function saveUser(){
	var a = $("#fm").serializeJSON();//Object对象
	a.token ='token64位码';
	var p = eval('(' + params + ')');//转为object对象
	p.paras = a;
	//console.log(JSON.stringify(p));
	
	$.post('/dogpro-Webapi/UsercenterApi/Apiv1.do',{params:JSON.stringify(p)},function(result){
		if(result.state == 'success'){
			$.messager.alert('提示信息','操作成功','info');//error、question、info、warning。
  			$('#dlg').dialog('close');
  		    $('#tb1').datagrid('reload');
		}else{
			$.messager.alert('错误','操作失败','error');
	    		$('#dlg').dialog('close');
		}
	});
}
function destroyUser(){
        $.messager.confirm('Confirm','确定要删除?',function(r){
            if (r){
            	var p = eval('(' + params + ')');//转为object对象
            	p.paras ={id:delId,token:'token64位码'};
                $.post('/dogpro-Webapi/UsercenterApi/Apiv1.do',{params:JSON.stringify(p)},function(result){
                    if (result.success){
                        $('#tb1').datagrid('reload');    // reload the user data
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    }
                },'json');
            }
        });
}