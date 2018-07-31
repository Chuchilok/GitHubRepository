////////////////////上传图片////////////////////
$("#file_Img").change(function(){
    var imgName = $("#file_Img")[0].files[0].name;
    console.log($("#file_Img")[0].files[0])
    $('#textarea').val(imgName);
})
function ajaxImgFileUpload() {
    var formData = new FormData();

    formData.append('file', $("#file_Img")[0].files[0]);    //将文件转成二进制形式
    formData.append("userId", userId);
    formData.append("token", token);
    $.ajax({
        type: "post",
        url: uploadImgUrl,
        async: false,
        contentType: false,    //这个一定要写
        processData: false, //这个也一定要写，不然会报错
        data: formData,
        dataType: 'text',    //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
        success: function (data) {
            var json = JSON.parse(data).result;
            console.log(json);
            var imgContent = `{'image':'${json.image}','subimage':'${json.subimage}'}`;
            sendMsg(chatObjIndex, imgContent ,4);
            var content = `<img class="image" src="${host}${json.subimage}" data-img="${host}${json.image}">`;
            sendFileShow(content);

        },
        error: function (XMLHttpRequest, textStatus, errorThrown, data) {
            console.log(errorThrown);
        }
    });
}
$("#sendImg").click(function () {
    var imgName = $("#file_Img").val();
    if(imgName){
        ajaxImgFileUpload();
        $('#textarea').val('');
        $("#file_Img").val('');
    }
});


////////////////////上传视频////////////////////
$("#file_Video").change(function(){
    var imgName = $("#file_Video")[0].files[0].name;
    $('#textarea').val(imgName);
})
function ajaxVideoFileUpload() {
    var formData = new FormData();
    formData.append('file', $("#file_Video")[0].files[0]);    //将文件转成二进制形式
    formData.append("userId", userId);
    formData.append("token", token);

    var img = "https://img.alicdn.com/bao/uploaded/TB1qimQIpXXXXXbXFXXSutbFXXX.jpg";
    var image = new Image();
    image.crossOrigin = '';
    image.src = img;

    formData.append("subimage", image);
    $.ajax({
        type: "post",
        url: uploadVideoUrl,
        async: false,
        contentType: false,    //这个一定要写
        processData: false, //这个也一定要写，不然会报错
        data: formData,
        dataType: 'text',    //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
        success: function (data) {
            var json = JSON.parse(data).result;
            console.log(json);
            var videoContent = `{'videoUrl':'${json.videoUrl}','imageUrl':'${videoImg}'}`;
            sendMsg(chatObjIndex, videoContent ,5);
            var content = `<img class="vedioImg" src="${host}${videoImg}" data-vedio="${host}${json.videoUrl}">`;
            sendFileShow(content);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown, data) {
            console.log(errorThrown);
        }
    });
}
$("#sendVideo").click(function () {
    var imgName = $("#file_Video").val();
    if(imgName) {
        ajaxVideoFileUpload();
        $('#textarea').val('');
        $("#file_Video").val('');
    }
});


////////////////////上传语音////////////////////
$("#file_Voice").change(function(){
    var imgName = $("#file_Voice")[0].files[0].name;
    $('#textarea').val(imgName);
})
function ajaxVoiceFileUpload() {
    var formData = new FormData();
    formData.append('file', $("#file_Voice")[0].files[0]);    //将文件转成二进制形式
    formData.append("userId", userId);
    formData.append("token", token);
    $.ajax({
        type: "post",
        url: uploadVoiceUrl,
        async: false,
        contentType: false,    //这个一定要写
        processData: false, //这个也一定要写，不然会报错
        data: formData,
        dataType: 'text',    //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
        success: function (data) {
            var json = JSON.parse(data).result;
            console.log(json);
            var voiceContent = `{'voiceUrl':'${json.voiceUrl}'}`;
            sendMsg(chatObjIndex, voiceContent ,7);
            var content = `<p><i class="voice" data-src="${host}${json.voiceUrl}"></i></p>`;
            sendFileShow(content);
        },

        error: function (XMLHttpRequest, textStatus, errorThrown, data) {
            console.log(errorThrown);
        }
    });
}
$("#sendVoice").click(function () {
    var imgName = $("#file_Voice").val();
    if(imgName) {
        ajaxVoiceFileUpload();
        $('#textarea').val('');
        $("#file_Voice").val('');
    }
});
