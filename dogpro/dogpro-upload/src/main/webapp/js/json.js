
function handle(){

}
function ajaxMethod(url,params,handle){
    $.ajax({
        url:'http://192.168.199.81:8080/Webapi/UsercenterApi/Apiv1.do',
        type: "get",
        dataType: "jsonp",
        jsonp: "callback", //服务端用于接收callback调用的function名的参数
        jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
        data:{params:params},
        success: function(json) {
            console.log(json);
        	handle();
        },
        error: function(){alert('Error');}
    });
}

var token = sessionStorage.getItem("token");
var params="{'action': 'ActivityService','module': 'GetActivityById'," +
    "'paras': {'activityID':'"+activeID+"','token':'"+token+"'}}";
$.ajax({
    url:'http://192.168.199.81:8080/Webapi/UsercenterApi/Apiv1.do',
    type: "get",
    dataType: "jsonp",
    jsonp: "callback", //服务端用于接收callback调用的function名的参数
    jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
    data:{params:params},
    success: function(json) {
        console.log(json)
    },
    error: function(){alert('Error');}
});