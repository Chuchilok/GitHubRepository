/**
 * 
 */
var url;
    //评论------>朋友圈Id --->查出评论
    function disussDetail(id){
    	$(".hidden-dialog").show();
		$('#datail').datagrid('unselectAll');
		//var friendCirId = $("#tb1").datagrid("getSelected").friendCirId;
    	var param1=`{
    		"module":"findDisussByFriendCir",
    		"action":"AdminService",
    		"paras":{
    		"pageNO":"0",
    		"pageSize":"5",
    		"friendCirId":${id},
    		"token":"sdfasdfsafasdf"
    		}
    		}`;//参数
    	var columns1 = [[
                         { field: 'checkbox', checkbox: true },
                         { field: 'nickName', title: '用户昵称', width: 50 },
                         { field: 'content', title: '评论内容', width: 50},
                         { field: 'takeComment', title: '受评论方', width: 150},//用户，或是朋友圈
                         { field: 'disussTime', title: '评论时间', width: 150},
                         { field: 'state', title: '信息状态', width: 150},//评论信息的状态
                     ]];//字段
    	
    	initDataGrid($("#detail"),param1,'#toolba','评论','/dogpro-Webapi/UsercenterApi/ApivDataGrid.do',columns1);
    	$('.hidden-bg').fadeIn();
    }
    //点赞------>朋友圈Id --->点赞 (先忽略)
    function praiseDetail(id){
    	$(".hidden-dialog").show();
		$('#datail').datagrid('unselectAll');
    	$('.hidden-bg').fadeIn();
    }
    //详情朋友圈
    function friendCirDetail(id){
    	//此处通过朋友圈id查询出对应的媒体资源和详细内容
    	var row = $("#tb1").datagrid("getSelected");
    	var friendCirId = row.friendCirId;
    	
    	var paras = `{
    		"module":"getFriendCirsMediaByFriendCirId",
    		"action":"AdminService",
    		"paras":{
    		    "pageNO":"0",
    		     "pageSize":"5",
    		"friendCirId":${friendCirId},
    		"token":"sersdd123dsew456dsed78sef9...d12"
    		}
    		}`;
    	var Urlhtml =`朋友圈内容:${row.content}<br/>图片内容<br/>`;
    	$.post('/dogpro-Webapi/UsercenterApi/Apiv1.do',{params:paras},function(data){
    		console.log(data);
    		  $.each(data.result,function(idx,item){ //循环对象取值
    			  Urlhtml += `<img src="${item.mediaUrl}"><br/>
    			  <img src="${item.mediaSubUrl}"><br/>`
         });
    		  $(".medias").html(Urlhtml);  
    	});
    	$('#dlg').dialog('open').dialog('center').dialog('setTitle','详情朋友圈');
    	
    }
    var searchData ;//
    $(function () {
        var params=`{
        	"module":"allianceList",
        	"action":"AdminService",
        	"paras":{
        	"pageNO":"0",
        	"pageSize":"5",
        	"token":"asssssssssssssssssssssssssssssssssssssssssssssssssss"
        	}
        	}`;//参数
        	var columns = [[
                        { field: 'checkbox', checkbox: true },
                        { field: 'friendCirId', title: '朋友圈id'},
                        { field: 'userNickName', title: '用户昵称'},
                        { field: 'content', title: '朋友圈内容',width:100,fixed:true},
                        { field: 'disuss', title: '评论',width:100,
                        	formatter:function(value,row,index){
                        	return '<a href="javascript:disussDetail(\''+row.friendCirId+'\')" >点击详情</a>';
                        }}/* ,
                        { field: 'praise', title: '点赞',width:100,
                        	formatter:function(value,row,index){
                            	return '<a href="javascript:praiseDetail(\''+row.friendCirId+'\')" >点击详情</a>';
                            }} */,
                        { field: 'type', title: '朋友圈内容类型',width:100,
                            	formatter:function(value,row,index){
                            		if(row.type == 1){
                            			return '普通';
                            		}else if(row.type == 2){
                            			return '求助';
                            		}else{
                            			return '未知';
                            		}
                                } },//类型（普通1求助2）
                        { field: 'publishTime', title: '发布时间'},
                        { field: 'state', title: '状态',
                        	formatter:function(value,row,index){
                        		if(row.state == 1){
                        			return '未删除';
                        		}else if(row.state == 0){
                        			return '已删除';
                        		}else{
                        			return '未知';
                        		}
                            }},{field:'detail',title:'详情',
                            	formatter:function(value,row,index){
                                	return '<a href="javascript:friendCirDetail(\''+row.friendCirId+'\')" >详情朋友圈</a>';
                                }}
                    ]];//字段
        initDataGrid($("#tb1"),params,'#toolbar','朋友圈','/dogpro-Webapi/UsercenterApi/ApivDataGrid.do',columns,'1000');
        /* $('.pagination-load').click();  */
        $('.search-box .search-span').click(function(){
			//模糊搜索
			var content = $(".search-box .search-input").val();
			var param1=`{
        		"module":"searchFriendsCir",
        		"action":"AdminService",
        		"paras":{
        		"pageNO":"0",
        		"pageSize":"5",
        		"content":'${content}',
        		"token":"sdfasdfsafasdf"
        		}
        		}`;//参数
        		initDataGrid($("#tb1"),param1,'#toolbar','朋友圈','/dogpro-Webapi/UsercenterApi/ApivDataGrid.do',columns,'1000');
    	});
    	$('.hidden-bg .close').click(function () {
    	    $('.hidden-bg').fadeOut();
    	})
    })