
$(function () {
    var Height = $(window).height();
    $('.loadtip').height(Height * 0.065);
    if (window.screen.width <= 320) {
        $('.comment-list li img').css('marginTop', '0.08rem');
        $('.comment-list li span').css({marginTop: '0.025rem', marginBottom: '0.025rem'});
    }
});
////////////////// 跨域请求//////////////////
var jsonpUrl = 'http://192.168.1.200:8081/Common-IMServer-Webapi/UsercenterApi/Apiv1JPShare.do';
var ipportUrl = 'http://192.168.1.200:8081'
var userId = 0;
var pageNO = 0;
var pageSize = 5;
var friendCirId = GetRequest().friendCirId;

function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
};
$(function () {

    var paramsPerson = "{'action': 'AllianceService','module': 'allianceDetail'," +
        "'paras': {'userId':'" + userId + "','friendCirId':'" + friendCirId + "'}}";
    console.log(jsonpUrl);
    console.log(paramsPerson);
    $.ajax({
        url: jsonpUrl,
        type: "get",
        dataType: "jsonp",
        jsonp: "callback",
        jsonpCallback: "jsonpCallback1",
        data: {params: paramsPerson},
        success: function (json) {
            console.log(json)
            var data = json.value.result;
            $('.detail-txt img').attr('src', ipportUrl+data.headPicUrl);
            $('.detail-txt .right span').html(data.nickname);
            $('.detail-txt .right p').html(data.content);
            $('.detail-img .right span').html(data.publishTime);
            if (data.isHelp == 1) {
                $('.detail-img .help').show();
            }
            // var html = `<img src="${data.media[0].subUrl}">`;
            var html = '';
            $.each(data.media, function (i, list) {
                html += `
                    <img src="${ipportUrl}${list.subUrl}">
                `;
            });
            $('.detail-img .img').html(html);
            if (data.media.length == 0) {
                $('.detail-img .img>img').css({width: '1.5rem', height: '1.5rem'});
            }
        },
        error: function () {
            alert('Error');
        }
    });
    var paramsPraise = "{'action': 'PraiseService','module': 'friendCirclePraise'," +
        "'paras': {'userId':'" + userId + "','friendCirId':'" + friendCirId + "'}}";
    $.ajax({
        url: jsonpUrl,
        type: "get",
        dataType: "jsonp",
        jsonp: "callback",
        jsonpCallback: "jsonpCallback2",
        data: {params: paramsPraise},
        success: function (json) {
            var data = json.value.result;
            var html = '';
            $.each(data, function (i, list) {
                html += `
                    <img src="${ipportUrl}${list.praiseHeadPicUrl}">
                `;
            });
            $('.friend-6').html(html);

        },
        error: function () {
            alert('Error');
        }
    });
    var paramsPraise = "{'action': 'DisussService','module': 'friendCircleDisuss'," +
        "'paras': {'userId':'" + userId + "','friendCirId':'" + friendCirId + "'," +
        "'pageNO':'" + pageNO + "','pageSize':'" + pageSize + "'}}";
    $.ajax({
        url: jsonpUrl,
        type: "get",
        dataType: "jsonp",
        jsonp: "callback",
        jsonpCallback: "jsonpCallback3",
        data: {params: paramsPraise},
        success: function (json) {
            console.log(json)
            var html = '';
            $.each(json.value.result, function (i, list) {
                html += `
                    <li class="msg-box clearfix">
                        <img src="${ipportUrl}${list.discussHeadPicUrl}">
                        <div class="msg-right">
                            <span>${list.nickname}<b>${list.discussTime}</b></span>
                            <p>${list.content}</p>
                        </div>
                    </li>
                `;
            });
            $('.list-group').append(html);
            pageNO += 1;
            mySwiper.update();
        },
        error: function () {
            alert('Error');
        }
    });
});
var loadFlag = true;
var oi = 0;
var mySwiper = new Swiper('.swiper-container', {
    direction: 'vertical',
    scrollbar: '.swiper-scrollbar',
    slidesPerView: 'auto',
    mousewheelControl: true,
    freeMode: true,
    onTouchEnd: function (swiper) {
        var _viewHeight = document.getElementsByClassName('swiper-wrapper')[0].offsetHeight;
        var _contentHeight = document.getElementsByClassName('swiper-slide')[0].offsetHeight;
        var html = '';
        if (mySwiper.translate <= _viewHeight - _contentHeight - 50 && mySwiper.translate < 0) {
            if (loadFlag) {
                $(".loadtip span").html('正在加载...');
            } else {
                $(".loadtip span").html('没有更多啦！');
            }
            setTimeout(function () {
                var paramsPraise = "{'action': 'DisussService','module': 'friendCircleDisuss'," +
                    "'paras': {'userId':'" + userId + "','friendCirId':'" + friendCirId + "'," +
                    "'pageNO':'" + pageNO + "','pageSize':'" + pageSize + "'}}";
                $.ajax({
                    url: jsonpUrl,
                    type: "get",
                    dataType: "jsonp",
                    jsonp: "callback",
                    jsonpCallback: "jsonpCallback3",
                    data: {params: paramsPraise},
                    success: function (json) {
                        var html = '';
                        if (json.value.result.length == 0) {
                            $(".loadtip span").html('没有更多啦！');
                        } else {
                            $.each(json.value.result, function (i, list) {
                                html += `
                                <li class="msg-box clearfix">
                                    <img src="${list.discussHeadPicUrl}">
                                    <div class="msg-right">
                                        <span>${list.nickname}<b>${list.discussTime}</b></span>
                                        <p>${list.content}</p>
                                    </div>
                                </li>
                            `;
                            });
                            $('.list-group').append(html);
                            pageNO++;
                            $(".loadtip span").html('上拉加载更多...');
                            mySwiper.update();
                        }
                    },
                    error: function () {
                        alert('Error');
                    }
                });
            }, 800);
        }
        return false;
    }
});
