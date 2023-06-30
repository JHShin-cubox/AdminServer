var modal = document.getElementById("delete-Modal01");

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
function changeModify(val){
    const parentDiv = $(val).parent().parent().children();
    $('#userId').val($(val).text());
    $('#userId').attr("readonly",true)
    $('#name').val(parentDiv.eq(1).text());
    $('#name').attr("readonly",true)
    $('#department').val(parentDiv.eq(2).text());
    $('#rank').val(parentDiv.eq(3).text());
    $('#phone').val(parentDiv.eq(4).text());
    $('#email').val(parentDiv.eq(5).text());
    $('.modal_title').text('사용자 수정');
    $('.btn_save').text('수정');
    $('.btn_save').attr('onclick',"insertActionLog('운영관리','사용자 관리','수정');validCheck()");
    $('#idCheckDiv').attr("class","");
    $('#idCheck').hide();
    $('.txt_st1').hide();
    $('.valid_error').hide();
    $('#modalForm').attr("action","/operation/userModify");
}
function modalReset(){
    $('#modalForm').find('input').val('');
    $('#userId').attr("readonly",false)
    $('#name').attr("readonly",false)
    $('.modal_title').text('사용자 등록');
    $('.btn_save').text('등록');
    $('.btn_save').attr('onclick',"insertActionLog('운영관리','사용자 관리','등록');validCheck()");
    $('#idCheckDiv').attr("class","overlap_input");
    $('#idCheck').show();
    $('.txt_st1').show();
    $('.valid_error').hide();
    $('#modalForm').attr("action","/operation/userSignUp");
}
function duplicateCheck(){
    let userId = $('#userId').val()
    if($('#userId').val()==""){
        alert("아이디를 입력해주세요.")
    } else{
        $.ajax({
            url: '/operation/duplicateCheck',
            type: 'post',
            data:{'userId' : userId},
            success: function (data){
                if(data == 'Y') {
                    alert("아이디 사용이 가능합니다");
                    $('#valid_id').hide();
                    $('#valCheckYn').val('Y');
                }
                else alert("아이디가 존재합니다");
            },
            error: function (err){
                console.log(err)
            }
        });
    }
}
function validCheck(){
    let valCheck = 'N';
    if($('#userId').val()=="" && $('#valCheckYn') != 'Y'){
        $('#valid_no_both').show();
        valCheck = 'Y';
        $('#userId').focus();
        $('#valid_no_dup').hide();
        $('#valid_no_id').hide();
    }
    else if($('#valCheckYn').val() == 'N'){
        $('#valid_no_dup').show();
        valCheck = 'Y';
        $('#userId').focus();
        $('#valid_no_id').hide();
        $('#valid_no_both').hide();
    }
    else if($('#userId').val() == ""){
        $('#valid_no_id').show();
        valCheck = 'Y';
        $('#userId').focus();
        $('#valid_no_dup').hide();
        $('#valid_no_both').hide();
    } else{
        valCheck = 'N';
        $('#valid_no_id').hide();
        $('#valid_no_dup').hide();
        $('#valid_no_both').hide();
    }
    if($('#password').val()==""){
        $('#valid_password').show();
        valCheck = 'Y';
    } else{valCheck = 'N'; $('#valid_password').hide();}

    if($('#password_check').val()==""){
        $('#valid_password_check_no').show();
        $('#valid_password_check_dep').hide();
        valCheck = 'Y';
    }
    else if($('#password_check').val() != $('#password').val()){
        $('#valid_password_check_dep').show();
        $('#valid_password_check_no').hide();
        valCheck = 'Y';
    } else{
        valCheck = 'N';
        $('#valid_password_check_dep').hide();
        $('#valid_password_check_no').hide();
    }

    if($('#name').val()==""){
        $('#valid_name').show();
        valCheck = 'Y';
    } else{valCheck = 'N'; $('#valid_name').hide();}
    let e_RegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    if($('#email').val()!="" && !e_RegExp.test($('#email').val())){
        $('#valid_email').show();
        valCheck = 'Y';
    }

    if(valCheck == 'Y') return false;
    $('#modalForm').submit()
}
function duplicateReset(){
    $('#valCheckYn').val('N');
}
function passwordCheckReset(){
    if($('#password_check').val() != $('#password').val()){
        $('#valid_password_check_dep').show();
        $('#valid_password_check_no').hide();
    }else{$('#valid_password_check_dep').hide();$('#valid_password_check_no').hide();}
}
function passwordReset(){ $('#valid_password').hide();}
function nameReset(){ $('#valid_name').hide();}
function emailReset(){
    let e_RegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    if(!e_RegExp.test($('#email').val())){
        $('#valid_email').show();
    } else $('#valid_email').hide();
}
function deleteUser(val){
    if(confirm("정말 삭제하시겠습니까?\n\n복구가 불가능 합니다.")){
        $.ajax({
            url:"/record/action",
            method:"POST",
            data:{
                'main':"운영관리",
                'sub':"사용자 관리",
                'type':"삭제"
            },
        });
        $('#del_userId').val(val)
        $('#del_form').submit();
    }
}