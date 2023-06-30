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
            console.error("API 요청에 실패했습니다. 상태 코드: " + xhr.status);
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

function dashControl(type,id,status){
    // $.ajax({
    //     url : "http://172.16.153.85:5001/api/managementSystem/viewerControl_JSON",
    //     type : "PUT",
    //     data : {
    //         "pc_idx" : 1,
    //         "open" : true,
    //         "pc_status" : "waiting"
    //     },
    //     success : function (){
    //         if(type == 'onoff'){
    //             if(status == true){
    //                 $('#xrayline' + id).addClass('nonActive');
    //             } else{
    //                 $('#xrayline' + id).removeClass('nonActive');
    //             }
    //         }
    //     } ,
    //     error : function (request, status, error){
    //         console.log(status)
    //         if(status == true){
    //             $('#xrayline' + id).addClass('nonActive');
    //         } else{
    //             $('#xrayline' + id).removeClass('nonActive');
    //         }
    //     }
    // });
}

function healthCheck(){
    $('.healthCheck').toggleClass("bg-primary");
    $('.healthCheck').toggleClass("bg-dark");
    $('.healthCheck').children().toggleClass("fa-cog fa-spin");
    $('.healthCheck').children().toggleClass("fa-pause");
}

function search_valid(){
    if($('#searchCategory').val()=="no" && $('#date_start').val()=="" && $('#date_end').val()==""){
        alert("검색 종류를 선택해주세요.");
        $('#searchCategory').focus();
        return false;
    }
    if($('#searchText').val()=="" && $('#searchCategory').val()!="Y"){
        alert("검색어를 입력해주세요.");
        $('#searchText').focus();
        return false;
    }
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

setInterval(function() {
    var currentTime = moment().format('YYYY년 MM월 DD일 HH시 mm분 ss초');
    $('.title_time').children().html(currentTime);
}, 1000);



