// 상단메뉴 고정
$(document).on("scroll", function () {
  if ($(document).scrollTop() > 50) {
    $("header").removeClass("large").addClass("small");
  } else {
    $("header").removeClass("small").addClass("large");
  }
});


// 퀵메뉴 오픈
$(document).ready(function () {
  $(".back_icon_open").click(function () {
    $("#quick").toggleClass("quick_open");
    $(".inner").addClass("inner_w");
    $(".quick_top").toggleClass("open");
    $("#body_area_in").toggleClass("body_big_view");
$("#body_area_in2").toggleClass("body_big_view");
  });
});


// 퀵메뉴 오픈
$(document).ready(function () {
  $("#quick_btn_mobile").click(function () {
	 $("#quick_btn_mobile").hide();
    $("#quick_btn_mobile2").show();
	$("#quick").addClass("quick_open");  
});
});

// 퀵메뉴 오픈
$(document).ready(function () {
  $("#quick_btn_mobile2").click(function () {
    $("#quick_btn_mobile").show();
    $("#quick_btn_mobile2").hide();
	$("#quick").removeClass("quick_open");
	  
});
});
// 퀵메뉴 오픈
$(document).ready(function () {

  $(".quick_vicon1").click(function () {
    $("#quick").removeClass("quick_open");
    $("#body_area_in").addClass("body_big_view");
	 $("#body_area_in2").addClass("body_big_view");

    $(".quick_vicon1").hide();
    $(".quick_vicon2").show();

    $(".nav li.open .menu2").slideToggle();
  });

  $(".quick_vicon2").click(function () {
    $("#quick").addClass("quick_open");
    $("#body_area_in").removeClass("body_big_view");
	$("#body_area_in2").addClass("body_small_view");
	$("#body_area_in2").removeClass("body_big_view");
    $(".quick_vicon1").show();
    $(".quick_vicon2").hide();
    $(".nav li.open .menu2").slideToggle();

  });
  $(".nav_icon").click(function () {
    $("#quick").addClass("quick_open");
    $(".quick_top").removeClass("open");
    $("#body_area_in").removeClass("body_big_view");
	$("#body_area_in2").addClass("body_small_view");
    $(".quick_vicon1").show();
    $(".quick_vicon2").hide();

    $(".nav li.open").toggleClass("open");
  });


	
	  $(".quick_close_btn").click(function () {
    $("#body_area_in2").addClass("body_big_view");
	$("#body_area_in2").removeClass("body_small_view");


  });

	
});


// 페이지 아이콘 표시
$(document).ready(function () {
  $(".icon_id1").click(function () {
    $(".b_icon .b_icon1").show();
    $(".b_icon .b_icon2").hide();
    $(".b_icon .b_icon3").hide();
    $(".b_icon .b_icon4").hide();
    $(".b_icon .b_icon5").hide();
  });
  $(".icon_id2").click(function () {
    $(".b_icon .b_icon1").hide();
    $(".b_icon .b_icon2").show();
    $(".b_icon .b_icon3").hide();
    $(".b_icon .b_icon4").hide();
    $(".b_icon .b_icon5").hide();
  });
  $(".icon_id3").click(function () {
    $(".b_icon i.b_icon1").hide();
    $(".b_icon i.b_icon2").hide();
    $(".b_icon i.b_icon3").show();
    $(".b_icon i.b_icon4").hide();
    $(".b_icon i.b_icon5").hide();
  });
  $(".icon_id4").click(function () {
    $(".b_icon .b_icon1").hide();
    $(".b_icon .b_icon2").hide();
    $(".b_icon .b_icon3").hide();
    $(".b_icon .b_icon4").show();
    $(".b_icon .b_icon5").hide();
  });
  $(".icon_id5").click(function () {
    $(".b_icon .b_icon1").hide();
    $(".b_icon .b_icon2").hide();
    $(".b_icon .b_icon3").hide();
    $(".b_icon .b_icon4").hide();
    $(".b_icon .b_icon5").show();
  });
});



// 퀵메뉴 오픈
$(document).ready(function () {
  $(".user_ar_btn").click(function () {
    $("#user").toggleClass("user_b");
    $(".user_ar_btn").toggleClass("open");
	$(".memberinfobox").toggleClass("open");
  });
});



// j쿼리 js 파일 인크루드 

function include(file) {
  var script = document.createElement('script');
  script.src = file;
  script.type = 'text/javascript';
  script.defer = true;

  document.getElementsByTagName('head').item(0).appendChild(script);
}


function searchParam(key) {
  return new URLSearchParams(location.search).get(key);
};
$pageum = searchParam('pageum');




$(function(){

	var Gmotion  = $('.gmotion');
	  Gmotion.addClass('animate');

});

