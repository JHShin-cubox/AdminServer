
	// 모달
$(function(){
  var target;

  $('.btn.primary').on("click", function(){
    target = $(this).data('popup-show');

    if (target === "#mediaPop"){
      $('html').addClass('popup_opened');
      $(target).addClass('opened');
    } else {
      $(target).show();
      $('body').css({
        'overflow-y': 'hidden'
      });
    }
  });

  $('.modal_content .btn_close, .bgWrap').on("click", function(){
    $(target).hide();
    $('body').removeAttr('style');
  });

  $('.popup_cls').on("click", function(){
    $('html').removeClass('popup_opened');
    $(target).removeClass('opened');

    return false;
  });
})
                
