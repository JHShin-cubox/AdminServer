<!--==================================================================
프로젝트명 : 통합관리시스템
작성지 : 신정호
작성일 : 2023년 11월 22일
용도 : 통합 함수 목록
==================================================================-->

function toggleMenu(){
    $(".menuContainer").toggleClass("hidden");
}
function enterKey(){
    if(window.event.keyCode==13){
        apiLogin();
    }
}
function apiLogin() {

    if($('#loginId').val()==''){
        $("#userCheck").hide();
        $('#loginId').next().show();
        $('#loginId').focus();
        return false;
    }
    if($('#loginPwd').val()==''){
        $("#userCheck").hide();
        $('#loginPwd').next().show();
        $('#loginPwd').focus();
        return false;
    }

    let xhr = new XMLHttpRequest();
    let url = "/api/auth/signin";
    let data = {
        "userId": $('#loginId').val(),
        "userPassword": $('#loginPwd').val()
    };
    let json = JSON.stringify(data);
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.onload = function() {
        if (xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            console.log("API 요청에 성공했습니다.");
            location.href="/"
        }
        else{
            alert(JSON.parse(xhr.responseText).message)
            // console.error("API 요청에 실패했습니다. 상태 코드: " + xhr.status + xhr.responseText);
            $('#loginId').next().hide();
            $('#loginPwd').next().hide();
            $("#userCheck").show();
        }
    }
    xhr.send(json);
}

function apiLogout() {

    let xhr = new XMLHttpRequest();
    let url = "/api/auth/signout";
    xhr.open("GET", url, true);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.onload = function() {
        if (xhr.status == 200) {
            console.log("API 요청에 성공했습니다.");
            location.href="/login"
        }
        else{
            console.error("API 요청에 실패했습니다. 상태 코드: " + xhr.status);
        }
    }
    xhr.send();
}

function moveRecord(deviceId){
    var test = deviceId.split(",");
    if(deviceId != ''){
        $('#searchDateType').val(test[1]);
        location.href="/record/"+test[0]+"?type="+test[1];
    } else {
        location.href="/record";
    }
}

function leftToggle(e){
    $(e).next().slideToggle();
}

function healthCheck(){
    $('.healthCheck').toggleClass("bg-primary");
    $('.healthCheck').toggleClass("bg-dark");
    $('.healthCheck').children().toggleClass("fa-cog fa-spin");
    $('.healthCheck').children().toggleClass("fa-pause");
}

function search_valid(){
    if($('#searchCategory').val() =="no" && $('#date_start').val()=="" && $('#date_end').val()==""){
        alert("검색 종류를 선택해주세요.");
        $('#searchCategory').focus();
        return false;
    }
    if($('#searchCategory').val()!="no" && $('#searchText').val()=="" && $('#searchCategory').val()!="Y"){
        alert("검색어를 입력해주세요.");
        $('#searchText').focus();
        return false;
    }
    $('#search_form').attr('action',$('#srh_action').val());
    $('#search_form').attr('method','GET');
    $('#search_form').submit();
}

function excelDown(){
    $('#search_form').attr('action',$('#excel_action').val());
    $('#search_form').attr('method','POST');
    $('#search_form').submit();
}

function apiEquipModify() {
    let xhr = new XMLHttpRequest();
    let url = "http://172.16.150.21:8090/api/managementSystem/equipment_management";
    let data = {
        "name": $('#modelName').val(),
        "location": $('#location').val(),
        "type": $('#deviceType').val(),
        "id": $('#deId').val()
    };
    let json = JSON.stringify(data);
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.onload = function() {
        if (xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            console.log("API 요청에 성공했습니다.");
            location.reload();
        }
        else{
            console.error("API 요청에 실패했습니다. 상태 코드: " + xhr.status);
        }
    }
    xhr.send(json);
}

function insertActionLog(main,sub,type){
    $.ajax({
        url:"/record/action",
        method:"POST",
        data:{
            'main':main,
            'sub':sub,
            'type':type
        },
        success: function(data) {
            console.log(data);
        },
        error: function(err) {
            console.log(err);
        }

    })
}
function sideMenu(val){
    var checkClass = 'N';
    if($(val).hasClass('on')==true){checkClass = 'Y'}
    $('.nav_wrap').each(function(){
        $(this).removeClass("on");
        $(this).children().eq(1).css('rotate','0deg');
    })
    if(checkClass =='Y'){
        $(val).removeClass('on');
        $(val).children().eq(1).css('rotate','0deg');
    }
    else{
        $(val).addClass('on');
        $(val).children().eq(1).css('rotate','180deg');
    }
}
function homePage(){
    $('.open').each(function(){$(this).children().eq(0).click()});
    location.href='/';
}




setInterval(function() {
    var currentTime = moment().format('YYYY년 MM월 DD일 HH시 mm분 ss초');
    $('.title_time').children().html(currentTime);
}, 1000);



