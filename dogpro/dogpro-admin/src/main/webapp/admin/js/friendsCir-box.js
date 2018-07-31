//评论------>朋友圈Id --->查出评论
function disussDetail(id) {
    $(".hidden-dialog").show();
    $('#datail').datagrid('unselectAll');
    //var friendCirId = $("#tb1").datagrid("getSelected").friendCirId;
    var param1 = `{
    		"module":"findDisussByFriendCir",
    		"action":"AdminService",
    		"paras":{
    		    "adminUserId":${userId},
                "pageNO":"0",
                "pageSize":"5",
                "friendCirId":${id},
                "token":"${token}"
                }
    		}`;//参数
    var columns1 = [[
        {field: 'nickName', title: '用户昵称', width: 20},
        {field: 'content', title: '评论内容', width: 110},
        {field: 'takeComment', title: '受评论方', width: 20},//用户，或是朋友圈
        {field: 'disussTime', title: '评论时间', width: 50},
        {field: 'state', title: '信息状态', width: 150},//评论信息的状态
    ]];//字段

    initDataGrid($("#detail"), param1, '#toolba', '评论', '/dogpro-Webapi/UsercenterApi/ApivDataGrid.do', columns1);
    $('.hidden-bg').fadeIn();
}

//点赞------>朋友圈Id --->点赞 (先忽略)
function praiseDetail(id) {
    $(".hidden-dialog").show();
    $('#datail').datagrid('unselectAll');
    $('.hidden-bg').fadeIn();
}

//详情朋友圈
function friendCirDetail(id) {
    console.log(222)
    //此处通过朋友圈id查询出对应的媒体资源和详细内容
    var row = $("#tb1").datagrid("getSelected");
    var friendCirId = row.friendCirId;


    var paras = `{
    		"module":"getFriendCirsMediaByFriendCirId",
    		"action":"AdminService",
    		"paras":{
    		    "adminUserId":${userId},
    		    "pageNO":"0",
                "pageSize":"5",
    		    "friendCirId":${friendCirId},
    		    "token":"${token}"
    		}
		}`;


    var jsonpUrl = jsonUrl;
    $.ajax({
        url: jsonpUrl,
        type: "get",
        dataType: "jsonp",
        jsonp: "callback", //服务端用于接收callback调用的function名的参数
        jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
        data: {params: paras},
        success: function (data) {
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '详情朋友圈');
            data = data.value;
            var Urlhtml = `<p>朋友圈内容:</p><p style="word-wrap: break-word">${row.content}</p></p><p>图片内容</p><ul id="friContent-img">`;
            $.each(data.result, function (idx, item) { //循环对象取值

                Urlhtml += `<li><img src="${item.mediaUrl}"></li>`;
//								<li><img src="http://192.168.1.200:8081${item.mediaSubUrl}"></li>`;
                // Urlhtml += `<li><img src="../img/4.jpg"></li>
                // 		<li><img src="../img/4.jpg"></li>`;
            });
            Urlhtml += '</ul>';
            $(".medias").html(Urlhtml);
            $('.panel-htop').css('top', '2rem');
        },
        error: function (err) {
            $.messager.alert('操作提示', '获取信息失败...请联系管理员!', 'error');
            doc.datagrid("loaded"); //移除屏蔽
        }
    });

    // $.post('/dogpro-Webapi/UsercenterApi/Apiv1.do',{params:paras},function(data){
    //    console.log(data);
    //    $.each(data.result,function(idx,item){ //循环对象取值
    //        Urlhtml += `<img src="${item.mediaUrl}"><br/>
    // 		  <img src="${item.mediaSubUrl}"><br/>`
    //    });
    //    $(".medias").html(Urlhtml);
    // });
}

var searchData;//
$(function () {
    var params = `{
        	"module":"allianceList",
        	"action":"AdminService",
        	"paras":{
        	"adminUserId":${userId},
        	"pageNO":"0",
        	"pageSize":"5",
        	"token":"${token}"
        	}
        	}`;//参数
    var columns = [[
        // { field: 'checkbox', checkbox: true },
        {field: 'friendCirId', title: '朋友圈id', width: 150},
        {field: 'userNickName', title: '用户昵称', width: 150},
        {field: 'content', title: '朋友圈内容', width: 200, fixed: true},
        {
            field: 'disuss', title: '评论', width: 150,
            formatter: function (value, row, index) {
                return '<a href="javascript:disussDetail(\'' + row.friendCirId + '\')" >点击详情</a>';
            }
        }/* ,
                        { field: 'praise', title: '点赞',width:100,
                        	formatter:function(value,row,index){
                            	return '<a href="javascript:praiseDetail(\''+row.friendCirId+'\')" >点击详情</a>';
                            }} */,
        {
            field: 'type', title: '朋友圈内容类型', width: 100,
            formatter: function (value, row, index) {
                if (row.type == 1) {
                    return '普通';
                } else if (row.type == 2) {
                    return '求助';
                } else {
                    return '未知';
                }
            }
        },//类型（普通1求助2）
        {field: 'publishTime', title: '发布时间', width: 200},
        {
            field: 'state', title: '状态', width: 100,
            formatter: function (value, row, index) {
                if (row.state == 1) {
                    return '未删除';
                } else if (row.state == 0) {
                    return '已删除';
                } else {
                    return '未知';
                }
            }
        }, {
            field: 'detail', title: '详情', width: 150,
            formatter: function (value, row, index) {
                return '<a href="javascript:friendCirDetail(\'' + row.friendCirId + '\')" >详情朋友圈</a>';
            }
        }
    ]];//字段
    initDataGrid($("#tb1"), params, '#toolbar', '朋友圈', '/dogpro-Webapi/UsercenterApi/ApivDataGrid.do', columns, '1000');
    /* $('.pagination-load').click();  */
    $('.search-box .search-span').click(function () {
        //模糊搜索
        var content = $(".search-box .search-input").val();
        var param1 = `{
        		"module":"searchFriendsCir",
        		"action":"AdminService",
        		"paras":{
        		"adminUserId":${userId},
        		"pageNO":"0",
        		"pageSize":"5",
        		"content":'${content}',
        		"token":"${token}"
        		}
        		}`;//参数
        initDataGrid($("#tb1"), param1, '#toolbar', '朋友圈', '/dogpro-Webapi/UsercenterApi/ApivDataGrid.do', columns, '1000');
    });
    $('.hidden-bg .close').click(function () {
        $('.hidden-bg').fadeOut();
    })
});
// $(function(){
//    // var Urlhtml =`朋友圈内容:${row.content}<br/>图片内容<br/>`;
//    var paras = `{
// 	"module":"getFriendCirsMediaByFriendCirId",
// 	"action":"AdminService",
// 	"paras":{
// 	    "pageNO":"0",
// 	     "pageSize":"5",
// 	"friendCirId":${friendCirId},
// 	"token":"sersdd123dsew456dsed78sef9...d12"
// 	}
// 	}`;
//
//
//    var jsonpUrl = '192.168.199.140:5200/dogpro-Webapi/UsercenterApi/Apiv1JP.do';
//    $.ajax({
//        url: jsonpUrl,
//        type: "get",
//        dataType: "jsonp",
//        jsonp: "callback", //服务端用于接收callback调用的function名的参数
//        jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
//        data:{params:paras},
//        success : function(data) {
//            console.log(data);
//
//        },
//        error : function(err) {
//            console.log(333)
//            $.messager.alert('操作提示', '获取信息失败...请联系管理员!', 'error');
//            doc.datagrid("loaded"); //移除屏蔽
//        }
//    });
// })