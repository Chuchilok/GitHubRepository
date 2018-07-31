function message() {
    var a = $.blinkTitle.show();
    setTimeout(function () {
        $.blinkTitle.clear(a)
    }, 8e3)
}

var userId = sessionStorage.getItem("mqtt_userId");
var token = sessionStorage.getItem("mqtt_token");
var username = '小J';
var uPic = '2015.jpg';
var chatObjIndex;
var headImgArray = [];
var isChatRecord = 0;


//////////自己发送自己显示且发送到客户端开始//////////
function send() {
    if ($("#file_Img").val() || $("#file_Video").val() || $("#file_Voice").val()) {
        $("#file_Img").val(''), $("#file_Video").val(''), $("#file_Voice").val(''), $('#textarea').val('');
    }
    var content = document.getElementById("textarea").value;
    var dataObj, dataString;

    if (content) {
        var revUid = $(".chat03_content li.choosed").attr('data-userid');
        var txtContent = `{'content':'${content}'}`;
        sendMsg(revUid, txtContent, 3);

        // dataObj = {name: username, time: new Date().Format("yyyy-MM-dd hh:mm:ss"), content: content, uPic: uPic};
        // dataString = JSON.stringify(dataObj);
        // message = new Paho.MQTT.Message(dataString);
        // message.destinationName = talkUserId.toString();
        // client.send(message);

        document.getElementById("textarea").value = "";

        function h() {
            -1 != content.indexOf("*#emo_") && (content = content.replace("*#", "<img src='img/").replace("#*", ".gif'/>"), h(content))
        }

        h();
        var time = new Date().Format("yyyy-MM-dd hh:mm:ss");
        var html = `
            <div class='message clearfix right'>
                <div class='wrap-ri'>
                    <div clsss='clearfix'><span>${time}</span></div>
                </div>
                <div class='user-logo'><img src='img/head/${uPic}'/></div>
                <div class='wrap-text'>
                    <p>${content}</p>
                </div>
                
                <div style='clear:both;'></div>
            </div>
        `;
        $(".mes" + chatObjIndex).append(html);
        $(".chat01_content").scrollTop($(".mes" + chatObjIndex).height())
    }
}

function sendFileShow(content) {
    var time = new Date().Format("yyyy-MM-dd hh:mm:ss");
    var html = `
            <div class='message clearfix right'>
                <div class='wrap-ri'>
                    <div clsss='clearfix'><span>${time}</span></div>
                </div>
                <div class='user-logo'><img src='img/head/${uPic}'/></div>
                <div class='wrap-text'>
                    ${content}
                </div>
                
                <div style='clear:both;'></div>
            </div>
        `;
    $(".mes" + chatObjIndex).append(html);
    $(".chat01_content").scrollTop($(".mes" + chatObjIndex).height())
}

function sendMsg(revUid, content, type) {
    var params = `{
        "module"="sendMsg",
        "action"="IMMessageService",
        "paras":{
            "revUid":"${revUid}",
            "content":"${content}",
            "type":${type},
            "md5":"asdasdsadsas",
            "userId":${userId},
            "token":"${token}"
            }
        }`;
    console.log(params)
    $.ajax({
        url: jsonpUrl,
        type: "get",
        dataType: "jsonp",
        jsonp: "callback", //服务端用于接收callback调用的function名的参数
        jsonpCallback: "sendMsg_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
        data: {params: params},
        success: function (data) {
            console.log(data)
        }
    });
}

//////////自己发送自己显示且发送到客户端结束//////////

//////////接收信息方法开始//////////
function onMessageArrived(message) {

    var data = JSON.parse(decode(message.payloadString));
    console.log(data);

    showNewMsg(data.sendUid);
    showMsg(data, 1);
}

//////////接收信息方法结束//////////


//////////时间格式转换开始//////////
function turnTime(time) {
    time = new Date(time);
    return time.getFullYear() + "-" + (time.getMonth() + 1) + "-" + time.getDate() + "  " + time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
};
//////////时间格式转换结束//////////

$(".chat03_content").on('click', 'li', function () {
    var b = $(this).index() + 1;
    $(".chat01_content").scrollTop(0);
    $(this).addClass("choosed").siblings().removeClass("choosed");
    $(".talkTo a").text($(this).children(".chat03_name").text());

    chatObjIndex = $(this).attr('data-userId');
    $(".mes" + chatObjIndex).show().siblings().hide();
    $(".chat01_content").scrollTop($(".mes" + chatObjIndex).height())

    $(this).find('label').removeClass('unread');

    isChatRecord = 0;
    msgCode = 0;

    $('.recordState').remove();

    $("#file_Img").val(''), $("#file_Video").val(''), $("#file_Voice").val('');

    console.log(msgCode)
});


$(document).ready(function () {
    function e() {
        function h() {
            -1 != g.indexOf("*#emo_") && (g = g.replace("*#", "<img src='img/").replace("#*", ".gif'/>"), h())
        }

        var e = new Date, f = "";
        f += e.getFullYear() + "-", f += e.getMonth() + 1 + "-", f += e.getDate() + "  ", f += e.getHours() + ":", f += e.getMinutes() + ":", f += e.getSeconds();
        var g = $("#textarea").val();
        h();
        // var i = "<div class='message clearfix'><div class='user-logo'><img src='" + b + "'/>" + "</div>" + "<div class='wrap-text'>" + "<h5 class='clearfix'>\u5f20\u98de</h5>" + "<div>" + g + "</div>" + "</div>" + "<div class='wrap-ri'>" + "<div clsss='clearfix'><span>" + f + "</span></div>" + "</div>" + "<div style='clear:both;'></div>" + "</div>" + "<div class='message clearfix'>" + "<div class='user-logo'>" + "<img src='" + c + "'/>" + "</div>" + "<div class='wrap-text'>" + "<h5 class='clearfix'>" + d + "</h5>" + "<div>" + g + "\u7684\u56de\u590d\u5185\u5bb9</div>" + "</div>" + "<div class='wrap-ri'>" + "<div clsss='clearfix'><span>" + f + "</span></div>" + "</div>" + "<div style='clear:both;'></div>";
        var i = `
            <div class='message clearfix'>
                <div class='user-logo'><img src='${b}'/></div>
                <div class='wrap-text'><h5 class='clearfix'>${username}</h5>
                    <div>${g}</div>
                </div>
                <div class='wrap-ri'>
                    <div clsss='clearfix'><span>${f}</span></div>
                </div>
                <div style='clear:both;'></div>
            </div>
        `;
        null != g && "" != g ? (
            $(".mes" + a).append(i),
                $(".chat01_content").scrollTop($(".mes" + a).height()),
                $("#textarea").val(""), message()
        ) : alert("请输入聊天内容!!!")
    }

    var a = 3, b = "img/head/2024.jpg", c = "img/head/2015.jpg", d = "王旭";
    ////////// 关闭按钮开始//////////
    $(".close_btn").click(function () {
        $(".chatBox").hide()
    });
    $(".chat03_content").on('mouseover', 'li', function () {
        $(this).addClass("hover").siblings().removeClass("hover")
    }).on('mouseout', 'li', function () {
        $(this).removeClass("hover").siblings().removeClass("hover")
    });

    $(".ctb01").mouseover(function () {
        $(".wl_faces_box").show()
    }).mouseout(function () {
        $(".wl_faces_box").hide()
    });
    $(".wl_faces_box").mouseover(function () {
        $(".wl_faces_box").show()
    }).mouseout(function () {
        $(".wl_faces_box").hide()
    });
    $(".wl_faces_close").click(function () {
        $(".wl_faces_box").hide()
    });
    $(".wl_faces_main img").click(function () {
        var a = $(this).attr("src");
        $("#textarea").val($("#textarea").val() + "*#" + a.substr(a.indexOf("img/") + 4, 6) + "#*"), $("#textarea").focusEnd(), $(".wl_faces_box").hide()
    });

    // 发送按钮
    $(".chat02_bar img").click(function () {
        // e();
        send();
    });

    $('#txt').click(function () {
        send();
    })

    $('#sendTxt').click(function () {
        send();
    })

    document.onkeydown = function (a) {
        var b = document.all ? window.event : a;
        return 13 == b.keyCode ? (send(), !1) : void 0
    };
    $.fn.setCursorPosition = function (a) {
        return 0 == this.lengh ? this : $(this).setSelection(a, a)
    };
    $.fn.setSelection = function (a, b) {
        if (0 == this.lengh) return this;
        if (input = this[0], input.createTextRange) {
            var c = input.createTextRange();
            c.collapse(!0), c.moveEnd("character", b), c.moveStart("character", a), c.select()
        } else input.setSelectionRange && (input.focus(), input.setSelectionRange(a, b));
        return this
    }, $.fn.focusEnd = function () {
        this.setCursorPosition(this.val().length)
    }
}), function (a) {
    a.extend({
        blinkTitle: {
            show: function () {
                var a = 0, b = document.title;
                if (-1 == document.title.indexOf("\u3010")) var c = setInterval(function () {
                    a++, 3 == a && (a = 1), 1 == a && (document.title = "\u3010\u3000\u3000\u3000\u3011" + b), 2 == a && (document.title = "\u3010\u65b0\u6d88\u606f\u3011" + b)
                }, 500);
                return [c, b]
            }, clear: function (a) {
                a && (clearInterval(a[0]), document.title = a[1])
            }
        }
    })
}(jQuery);


var timer = null;
var isFlash = 1;

function newFlash() {
    timer = setInterval(function () {
        $("#new-box>img").fadeOut(300).fadeIn(300)
    }, 600);
    isFlash = 1;
}

$('#new-box>img').click(function () {
    if (isFlash == 0) {
        newFlash()
    } else {
        clearInterval(timer);
        isFlash = 0;
    }
})

//////////点击右下角人物开始//////////
$('#new-box ul').on('click', 'li', function () {
    $(this).remove();

    var child = $('#new-box ul li').length;
    ulBorder(child);

    var obj = [{userId: 20, nickname: '小接', headPic: "/dogpro-upload/uploadPicture/1511318823024_sub.PNG"}];
    objHtml(obj);
})
//////////点击右下角人物结束//////////

//////////双击右下角开始//////////
$('#new-box>img').dblclick(function () {
    $('#new-box ul li').remove();
    ulBorder(0)
})
//////////双击右下角结束//////////

//////////右下角边框开始//////////
function ulBorder(child) {
    if (child == 0) {
        $('#new-box ul').css('opacity', '0');
    } else {
        $('#new-box ul').css('opacity', '1');
    }
}

//////////右下角边框结束//////////


//////////请求用户列表开始//////////
$(function () {
    var params = `{
        "module":"getUserList",
        "action":"ServiceRecordService",
        "paras": {
            "userId":"${userId}",
            "token": "${token}",
            "pageNo":"0",
            "pageSize": 10
              }
        }`;
    console.log(params);
    $.ajax({
        url: jsonpUrl,
        type: "get",
        dataType: "jsonp",
        jsonp: "callback", //服务端用于接收callback调用的function名的参数
        jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
        data: {params: params},

        success: function (data) {
            console.log(data)
            if (data.value.code == 200) {
                var flist = data.value.result;
                objHtml(flist);
                $.each(flist, function (i, list) {
                    headImgArray.push(list.userId);
                    sessionStorage.setItem(list.userId, list.headPic);
                })
            } else {
                alert(data.value.message);
            }
        }
    });
})
//////////请求用户列表结束//////////

//////////请求未读信息开始//////////
$(function () {
    var params = `{
        "module":"getUnreadMsg",
        "action":"ServiceRecordService",
        "paras": {
            "pageNo":0,
            "pageSize":100,
            "userId":${userId},
            "token": "${token}"
              }
        }`;
    console.log(params);
    $.ajax({
        url: jsonpUrl,
        type: "get",
        dataType: "jsonp",
        jsonp: "callback", //服务端用于接收callback调用的function名的参数
        jsonpCallback: "jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
        data: {params: params},
        success: function (data) {
            console.log(data)

            if (data.value.code == 200) {
                setTimeout(function () {
                    $.each(data.value.result, function (i, list) {
                        showMsg(list, 1);
                    });
                }, 1000)
            } else {
                alert(data.value.message);
            }

            //////////测试收到消息开始//////////
            // showNewMsg(msg.sendUid);
            // showMsg(msg);
            //////////测试收到消息结束//////////
        }
    });
})
//////////请求未读信息结束//////////

//////////处理接收信息的显示方法开始//////////
function showMsg(obj, dir) {
    var content = obj.content;
    var sendUid = obj.sendUid;
    var voiceSrc, location, txt;
    var image;
    var phtml;
    if (obj.type == 3) {
        txt = eval('(' + content + ')');
        // function h() {
        //     -1 != content.indexOf("*#emo_") && (content = content.replace("*#", "<img src='img/").replace("#*", ".gif'/>"), h(content))
        // }
        //
        // h();
        phtml = `<p>${txt.content}</p>`;
    } else if (obj.type == 4) {
        image = eval('(' + content + ')');
        phtml = `<img class="image" src="${host}${image.subimage}" data-img="${host}${image.image}">`;
    } else if (obj.type == 5) {
        var vedioContent = eval('(' + content + ')');
        phtml = `<img class="vedioImg" src="${host}${vedioContent.imageUrl}" data-vedio="${host}${vedioContent.videoUrl}">`;
    } else if (obj.type == 6) {
        location = eval('(' + content + ')');
        console.log(location)
        phtml = `<p><span class="location" style="color: #2576FF;" data-latitude="${location.latitude}" data-longitude="${location.longitude}">点开显示地图所在位置</a></p>`;
    } else if (obj.type == 7) {
        voiceSrc = eval('(' + content + ')');
        phtml = `<p><i class="voice" data-src="${host}${voiceSrc.voiceUrl}"></i></p>`;
    }

    if (obj.sendUid == userId) {
        var html = `
            <div class='message clearfix right'>
                <div class='wrap-ri'>
                    <div clsss='clearfix'><span>${turnTime(obj.millisTime)}</span></div>
                </div>
                <div class='user-logo'><img src='img/head/${uPic}'/></div>
                <div class='wrap-text' data-msg="${obj.msgCode}">${phtml}</div>
                <div style='clear:both;'></div>
            </div>`;
    } else {
        var html = `
            <div class='message clearfix'>
                <div class='wrap-ri'>
                    <div clsss='clearfix'><span>${turnTime(obj.millisTime)}</span></div>
                </div>
                <div class='user-logo'><img src='${host}${sessionStorage.getItem(sendUid)}'/></div>
                <div class='wrap-text' data-msg="${obj.msgCode}">${phtml}</div>
                <div style='clear:both;'></div>
            </div>`;
    }
    if (dir == 1) {
        $(".mes" + sendUid).append(html);
        $(".chat01_content").scrollTop($(".mes" + sendUid).height());
        $(`.chat03_content li[data-userid='${sendUid}']`).find('label').addClass('unread');
        $(`.chat03_content li[data-userid='${chatObjIndex}']`).find('label').removeClass('unread');

    } else if (dir == -1) {
        $(".chat01_content").scrollTop(0);
        $(".mes" + chatObjIndex).prepend(html);
    }

}

//////////处理接收信息的显示方法结束//////////

//////////插入右下角用户列表开始(暂时不用)//////////
function addUserList(obj, type) {
    var html = '';
    $.each(obj, function (i, list) {
        html += `
            <li data-userid="${list.sendUid}">
                <img src="img/head/2013.jpg">
                <span>小杰1</span>
        </li>`;
    })
    if (type == 1) {
        $('#new-box ul').append(html)
    } else {
        $('#new-box ul').html(html)
    }
}

//////////插入右下角用户列表结束(暂时不用)//////////

//////////创建用户列表和对应聊天区域开始//////////
function objHtml(obj) {
    var ohtml = '';
    var mhtml = '';
    $.each(obj, function (i, list) {
        ohtml += `<li data-userId="${list.userId}">
                    <label class=""></label>
                    <a href="javascript:;">
                        <img src="${host}${list.headPic}">
                    </a>
                    <a href="javascript:;" class="chat03_name">${list.nickname}</a>
                </li>`;

        mhtml += `<div class="message_box mes${list.userId}"></div>`;
    });
    $('.chat01_content').append(mhtml);
    $(".chat03_content ul").prepend(ohtml);
    $(".chat03_content li").eq(0).click();
}

//////////创建用户列表和对应聊天区域结束//////////

//////////收到消息操作开始//////////
function showNewMsg(uid) {
    console.log(headImgArray);
    if (headImgArray.indexOf(uid) == -1) {
        console.log('没有')
        var ohtml = '';
        var mhtml = '';
        ohtml += `<li data-userId="${uid}">
                    <label class=""></label>
                    <a href="javascript:;">
                        <img src="img/unhead.jpg">
                    </a>
                    <a href="javascript:;" class="chat03_name">用户${uid}</a>
                </li>`;

        mhtml += `<div class="message_box mes${uid}"></div>`;
        $('.chat01_content').append(mhtml);
        $(".chat03_content ul").prepend(ohtml);
        if (!sessionStorage.getItem(uid)) {
            sessionStorage.setItem(uid, '/dogpro-upload/uploadPicture/unhead.jpg');
        }
    } else {
        console.log('有')
    }
}

//////////收到消息操作结束//////////


//////////语音点击播放开始//////////
var isAudioPlay = 1;
$('.chat01_content').on('click', '.voice', function () {
    var src = $(this).attr('data-src');
    $('#voice').attr('src', src);
    var myAudio = document.getElementById('voice');
    if (isAudioPlay == 1) {
        myAudio.play();
        isAudioPlay = 0;
    } else {
        myAudio.pause();
        isAudioPlay = 1;
    }

})

// $('.chat01_content').on('dblclick', '.voice', function () {
//     console.log(222)
//     var myAudio = document.getElementById('voice');
//     myAudio.pause();
// })
//////////语音点击播放结束//////////

////////// 图片放大缩小开始//////////
$('#larImage-box .close').click(function () {
    $(this).parent().fadeOut();
})
$('.chat01_content').on('click', '.image', function () {
    var src = $(this).attr('data-img');
    $('#larImage-box img').attr('src', src);
    $('#larImage-box').fadeIn();
})
////////// 图片放大缩小结束//////////

////////// 视频放大缩小开始//////////
$('#video-box .close').click(function () {
    $(this).parent().fadeOut();
    $('#video-box video').stop();
})
$('.chat01_content').on('click', '.vedioImg', function () {
    var src = $(this).attr('data-vedio');
    $('#video-box video').attr('src', src);
    $('#video-box').fadeIn();
})
////////// 视频放大缩小结束//////////

//////////显示地位开始//////////
$('#location-box .close').click(function () {
    $(this).parent().fadeOut();
})
$('.chat01_content').on('click', '.location', function () {
    var longitude = $(this).attr('data-longitude');
    var latitude = $(this).attr('data-latitude');
    $('#location-box').fadeIn();
    setTimeout(function () {
        var point = new BMap.Point(longitude, latitude);
        var marker = new BMap.Marker(point);  // 创建标注
        map.centerAndZoom(point, 13);
        map.addOverlay(marker);
    }, 1000)
});
//////////显示地位结束//////////

//////////查看个人资料开始//////////
$('.talkTo').on('click', 'a', function () {
    var obj = {userId: 1, nickname: '小V', headPic: '2013.jpg'};
    $('#person-box').fadeIn();

    var params = `{
            "module"="getUserInfo",
            "action"="ServiceRecordService",
            "paras":{
                "sendUid":"${chatObjIndex}",
                "userId":${userId},
                "token":"${token}"
                }
            }`;
    $.ajax({
        url: jsonpUrl,
        type: "get",
        dataType: "jsonp",
        jsonp: "callback", //服务端用于接收callback调用的function名的参数
        jsonpCallback: "jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
        data: {params: params},
        success: function (data) {
            console.log(data);
            var list = data.value.result;
            var parent = $('#person-box');
            parent.find('.headpic img').attr('src', host + list.headpic);
            parent.find('.nickname p').html(list.nickname);
            parent.find('.sex p').html(list.sex == 0 ? '女' : '男');
            parent.find('.address p').html(list.address);
            parent.find('.phone p').html(list.phone);
            parent.find('.townstreet p').html(list.townstreet);
        }
    });

});
$('#person-box .close').click(function () {
    $(this).parent().fadeOut();
});
//////////查看个人资料结束//////////

//////////查看聊天记录开始//////////
var msgCode = 0, pageSize = 10;

function chatRecord(uid, mCode, pageSize) {
    var params = `{
                    "module":"getHistoryMsg",
                    "action":"ServiceRecordService",
                    "paras": {
                        "msgCode":"${mCode}",
                        "sendUid":"${uid}",
                        "pageSize": ${pageSize},
                        "userId":${userId},
                        "token": "${token}"
                      }
                    }`;
    console.log(params);
    $.ajax({
        url: jsonpUrl,
        type: "get",
        dataType: "jsonp",
        jsonp: "callback", //服务端用于接收callback调用的function名的参数
        jsonpCallback: "record_jsonCallback", //callback的function名称,服务端会把名称和data一起传递回来
        data: {params: params},
        success: function (data) {
            console.log(data);
            $('.recordState').remove();
            if (data.value.result.length == 0) {
                $(".mes" + chatObjIndex).prepend(`<p class="recordState">聊天记录搜索已经尽头</p>`);
            } else {
                $.each(data.value.result, function (i, list) {
                    showMsg(list, -1);
                })
                $(".mes" + chatObjIndex).prepend(`<p class="recordState">开放查看聊天记录功能 <span class="record_more" style="color: blue;cursor: pointer;">加载更多</span></p>`);
                msgCode = data.value.result[data.value.result.length - 1].msgCode;
            }

        }
    });
}

$('.chat02_title_t').click(function () {
    isChatRecord = 1;
    msgCode = 0;
    console.log(333)


    $(".mes" + chatObjIndex).prepend(`<p class="recordState">开放查看聊天记录功能 <span class="record_more" style="color: blue;cursor: pointer;">加载更多</span></p>`);

    $(".chat01_content").scrollTop(0);

    if ($(".mes" + chatObjIndex).find('.message').html()) {
        console.log(444)
        msgCode = $('.mes' + chatObjIndex).find('.wrap-text').eq(0).attr('data-msg');

    } else {
        msgCode = 0;

    }
});

function record_jsonCallback() {

}

$('.chat01_content').on("mousewheel DOMMouseScroll", function (e) {

    if (isChatRecord == 1) {

        var sTop = $(this).scrollTop();

        if (sTop == 0) {
            var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) ||  // chrome & ie
                (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1));              // firefox
            if (delta > 0) { /////// 向上滚///////
                $('.recordState').html('正在努力加载...');

                chatRecord(chatObjIndex, msgCode, pageSize);
            }
        }

    }
});

$('body').on('click', '.record_more', function () {
    console.log(222);
    chatRecord(chatObjIndex, msgCode, pageSize);
})
//////////查看聊天记录结束//////////

////////// 增加聊天用户开始 //////////
$('#add-box button').click(function () {
    var addUid = parseInt($('#add-box input').val());
    showNewMsg(addUid);
})
////////// 增加聊天用户结束 //////////

////////////// base64解码开始/////////////////
function decode(input) {
    var _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    var output = "";
    var chr1, chr2, chr3;
    var enc1, enc2, enc3, enc4;
    var i = 0;
    input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
    while (i < input.length) {
        enc1 = _keyStr.indexOf(input.charAt(i++));
        enc2 = _keyStr.indexOf(input.charAt(i++));
        enc3 = _keyStr.indexOf(input.charAt(i++));
        enc4 = _keyStr.indexOf(input.charAt(i++));
        chr1 = (enc1 << 2) | (enc2 >> 4);
        chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
        chr3 = ((enc3 & 3) << 6) | enc4;
        output = output + String.fromCharCode(chr1);
        if (enc3 != 64) {
            output = output + String.fromCharCode(chr2);
        }
        if (enc4 != 64) {
            output = output + String.fromCharCode(chr3);
        }
    }
    output = _utf8_decode(output);
    return output;
}

var _utf8_decode = function (utftext) {
    var string = "";
    var i = 0;
    var c = c1 = c2 = 0;
    while (i < utftext.length) {
        c = utftext.charCodeAt(i);
        if (c < 128) {
            string += String.fromCharCode(c);
            i++;
        } else if ((c > 191) && (c < 224)) {
            c2 = utftext.charCodeAt(i + 1);
            string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
            i += 2;
        } else {
            c2 = utftext.charCodeAt(i + 1);
            c3 = utftext.charCodeAt(i + 2);
            string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
            i += 3;
        }
    }
    return string;
};
////////////// base64解码结束/////////////////





