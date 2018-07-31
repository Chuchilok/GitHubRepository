var liWidth;
console.log($(window).width());
$('iframe').height($(window).height()*0.6);
$(function(){
    $(`.item span[data-box='user-box']`).click();
    liWidth = $('.nav-box li').width();
})
$('.item .san').click(function(){
    var state = $(this).siblings('div').css('display');
    if(state == 'none'){
        $(this).siblings('div').slideDown();
    }else{
        $(this).siblings('div').slideUp();
    }
});
///////////////选择用户////////////////
$('.table-box').on('click','.sel',function(){
    if($(this).hasClass('active')){
        $(this).removeClass('active')
    }else{
        $(this).addClass('active');
    }
});

///////////////导航栏关闭按钮点击////////////////

$('.nav-box').on('click','.close',function(){
    var box = $(this).attr('data-box');
    $('.'+box).removeClass('active');
    var obj = $(this).parent();
    if(obj.hasClass('active')){
        obj.removeClass('active');
        var index = obj.index();
        if(index == $('.nav-box>li').length-1){
            index --;
        }
    }
    obj.remove();
    var nextLi = $($('.nav-box>li')[index]);
    nextLi.find('span').click();

    var sum = $('.nav-box li').length;
    var allwidth = $('.top').width()-$('.ic_left').outerWidth();
    if(allwidth-liWidth*sum>0){
        $('.nav-box li').width(liWidth);
    }
});
///////////////菜单栏点击////////////////
$('.item span').click(function(){

    var name = $(this).html();
    var box = $(this).attr('data-box');

    var d1 = $("#login-area").outerHeight(true);
    var d2 = $("#manage .top").outerHeight(true);

    if($('.'+box).find('iframe').length==0){
        var iframe = document.createElement('iframe');

        iframe.height= document.body.scrollHeight-d1-d2;
        iframe.width= "100%";
        iframe.src = 'iframe/'+box+'.html';

    }

    var sum = $('.nav-box li').length+1;
    var allwidth = $('.top').width()-$('.ic_left').outerWidth();
    var usewidth = allwidth-$('.nav-box li').outerWidth()*sum;
    var turnwidth = 0;

    if($(`.nav-box li i[data-box=${box}]`).length==0){
        if($('.nav-box li').width() > usewidth){
            $('.nav-box li').width(allwidth/sum-5);
            turnwidth = 1;
            console.log('太长了')
        }

        var html = '';
        html = `
            <li>
                <span>${name}</span>
                <i class="close" data-box="${box}"></i>
            </li>
        `;
        $('.nav-box').append(html);
        if(turnwidth==1){
            $('.nav-box li').width(allwidth/sum-6);
        }
    }

    var DIV = $('.item-box>div');
    DIV.removeClass('active');
    $('.'+box).addClass('active').html(iframe);

    var Li = $('.nav-box>li');
    Li.removeClass('active');
    $(".nav-box i[data-box='"+box+"']").parent().addClass('active');



});
///////////////导航栏点击////////////////
$('.nav-box').on('click','span',function(){
    var box = $(this).siblings().attr('data-box');
    var Span = $(".item span[data-box='"+box+"']");
    Span.click();
});
//////////////////////返回按钮//////////////////////
$('.top .back').click(function(){
    window.location.href = 'login.html';
});
/////////////////////// 注销和切换账号////////////////////
$("#login-area .logout").click(function(){
    $("#dialog-login").dialog("open");
    $.messager.confirm('Confirm','您确认要注销吗?',function(r){
        if (r){
            window.location.href = 'login.html';
        }
    });
});

$("#login-area .toggle").click(function(){
    $("#dialog-login").dialog("open");
    $.messager.confirm('Confirm','您确认要切换账号吗?',function(r){
        if (r){
            window.location.href = 'login.html';
        }
    });
});