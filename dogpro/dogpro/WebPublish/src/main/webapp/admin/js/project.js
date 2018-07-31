// var userId = sessionStorage.getItem("userId");
var userId = 1;
var token = sessionStorage.getItem("token");
var uploadurl = "";

$(function () {
//	$('#upload_form').attr('action',uploadUrl);
    var params = `{
            "module":"getProjectList",
            "action":"ProjectService",
            "paras": {
                "userId":${userId},
                "token":"${token}",
                "pageNO":0,
                "pageSize":15
                }
            }`;
    $.ajax({
        url: WebPublishjsonpUrl,
        type: "get",
        dataType: "jsonp",
        jsonp: "callback", //服务端用于接收callback调用的function名的参数
        jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
        data: {params: params},
        success: function (data) {
            console.log(data);
            if (data.code == 101) {
                $.messager.alert('提示', data.message, 'error');
            } else {
                var html = '';
                $.each(data.rows, function (i, list) {
                    html += `<tr>
                                <td class="projectId">${list.projectId}</td>
                                <td>${list.builderName}</td>
                                <td>${list.buildTimes}</td>
                                <td>${list.projectName}</td>
                                <td><a href="javascript:;;" class="project-detail">展开详情</a></td>
                            </tr>`;
                });
                $('#alternatecolor').append(html);
            }
        }
    });
});

/////////////项目操作/////////////
function newUser() {
    $('#dlg').dialog('open').dialog('center').dialog('setTitle', 'New Project');
    $('#fm').form('clear');
}

function saveUser() {
    var data = $("#fm").serializeJSON();
    var params = `{
            "module":"addProject",
            "action":"ProjectService",
            "paras": {
                "projectName": "${data.projectName}",
                "userId": "${userId}",
                "token": "${token}"
                    }
            }`;
    $.ajax({
        url: WebPublishjsonpUrl,
        type: "get",
        dataType: "jsonp",
        jsonp: "callback", //服务端用于接收callback调用的function名的参数
        jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
        data: {params: params},
        success: function (data) {
            window.location.reload();
        }
    });
}

// function removeUser() {
//     $.messager.prompt('Prompt', '请输入需要删除的项目ID:', function (r) {
//         if (r) {
//             var params = `{
//                     "module":"deleteProject",
//                     "action":"ProjectService",
//                     "paras": {
//                         "userId":"${userId}",
//                         "token":"${token}",
//                         "projectId":"${r}"
//                             }
//                     }`;
//             $.ajax({
//                 url: jsonpUrl,
//                 type: "get",
//                 dataType: "jsonp",
//                 jsonp: "callback", //服务端用于接收callback调用的function名的参数
//                 jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
//                 data: {params: params},
//                 success: function (data) {
//                     window.location.reload();
//                 }
//             });
// //                alert('删除项目Id为 ' + r + ' 成功!');
//         }
//     });
// }

/////////////版本操作/////////////
function newVersion() {
	uploadurl = "";
    $('#vdlg').dialog('open').dialog('center').dialog('setTitle', 'New Version');
    $('#vfm').form('clear');
    $('#upload_form').form('clear');
    $('.upload-url').html('');
    $('.url-box').hide();
    $('#upload-box input').show();
    console.log(userId);
    $('#upload_userId').val(userId);
    $('#upload_token').val(token);
    $('#restart-on').click();
    
}

$(document).ready(function() {  
    $("#upload_form").ajaxForm(function(data){  
          console.log(data);
          //Alert("post success."); 
          if (data.code == 101) {
              $.messager.alert('提示', data.message, 'error');
          }else if(data.result.flag == 0){
        	  $.messager.alert('提示', data.result.msg, 'error');
          }else{
        	  $.messager.alert('提示', data.result.msg, 'info');
        	  uploadurl = data.result.downloadUrl;
          }
    });            
});

function saveVersion() {
    var downloadUrl = $('.upload-url').html();
    var data = $("#vfm").serializeJSON();
    console.log(data);
    
    
    var isRestart = $('#restart-box input[name="restart"]:checked').val();
//    data.downloadUrl = downloadUrl;
    data.downloadUrl = uploadurl;
    console.log(data);
    data.isRestart = isRestart;
    if (!data.versionNO) {
        $.messager.alert('提示', '版本编号不能为空', 'error');
    } else if (!data.versionName) {
        $.messager.alert('提示', '版本名不能为空', 'error');
    } else if (!uploadurl) {
        $.messager.alert('提示', '版本文件还未上传', 'error');
    } else {
        var params = `{
            "module":"publishVersion",
            "action":"PublishService",
            "paras": {
                "userId":"${userId}",
                "token":"${token}",
                "projectId":${projectId},
                "versionNO":"${data.versionNO}",
                "versionName":"${data.versionName}",
                "downloadUrl":"${data.downloadUrl}",
                "isRestart":"${data.isRestart}"
                    }
            }`;
        $.ajax({
            url: WebPublishjsonpUrl,
            type: "get",
            dataType: "jsonp",
            jsonp: "callback", //服务端用于接收callback调用的function名的参数
            jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
            data: {params: params},
            success: function (data) {
                console.log(data);
                $('#vdlg').dialog('close');
                $(".pagination-load").click();//触发刷新时间
            }
        });
    }
}

// function destroyVersion() {
//     var row = $('#tb').datagrid('getSelected');
//     var versionId = row.versionId;
//     console.log(versionId);
//     if (row) {
//         $.messager.confirm('Confirm', 'Are you sure you want to destroy this version?', function (r) {
//             if (r) {
//                 var params = `{
//                         "module":"deleteVersion",
//                         "action":"PublishService",
//                         "paras": {
//                             "userId":"${userId}",
//                             "token":"${token}",
//                             "versionId":${versionId}
//                                 }
//                         }`;
//                 $.ajax({
//                     url: jsonpUrl,
//                     type: "get",
//                     dataType: "jsonp",
//                     jsonp: "callback", //服务端用于接收callback调用的function名的参数
//                     jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
//                     data: {params: params},
//                     success: function (data) {
//                         console.log(data);
//                         $(".pagination-load").click();//触发刷新时间
//                     }
//                 });
//             }
//         });
//     }
// }

$('.hidden-bg .close').click(function () {
    $('.hidden-bg').fadeOut();
});
var projectId = 0;
$('#alternatecolor').on('click', '.project-detail', function () {
    $('.hidden-bg').fadeIn();
    projectId = $(this).parent().siblings('.projectId').html();
    var params = `{
            "module":"getVersionList",
            "action":"ProjectService",
            "paras": {
                "userId":"${userId}",
                "token":"${token}",
                "projectId":"${projectId}"
                }
            }`;
    var columns = [[
        {field: 'versionId', title: '版本id', width: 50},
        {field: 'versionNO', title: '版本编号', width: 50},
        {field: 'versionName', title: '版本名', width: 100},
        {field: 'publishTime', title: '发布时间', width: 100},
        {
            field: 'downloadUrl', title: '下载链接', width: 250,
            formatter: function (value, row, index) {
                return `<a href="${value}">${value}</a>`;
            }
        },
        {field: 'publisherName', title: '发布者名称', width: 50}
    ]];//字段
    initDataGrid($("#tb"), params, '#toolbar', '版本列表', '/dogpro-Webapi/UsercenterApi/ApivDataGrid.do', columns);
    //$(".pagination-load").click();//触发刷新时间
});

function ajaxFileUpload() {
    var formData = new FormData();
    formData.append('file', $("#file_upload")[0].files[0]);    //将文件转成二进制形式
    formData.append("userId", userId);
    formData.append("token", token);
    $.ajax({
        type: "post",
        url: uploadUrl,
        async: false,
        contentType: false,    //这个一定要写
        processData: false, //这个也一定要写，不然会报错
        data: formData,
        dataType: 'text',    //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
        success: function (data) {
            console.log(data);
            var json = JSON.parse(data);
            if (json.result.flag == 1) {
                var url = json.result.downloadUrl;
                $('.upload-url').html(url);
                $('.url-box').show();
                $('#upload-box input').hide();
            } else {
                $.messager.alert('上传提示', json.result.msg, 'error');
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown, data) {
            console.log(errorThrown);
        }
    });
}

$("#upload").click(function () {
    ajaxFileUpload();
});