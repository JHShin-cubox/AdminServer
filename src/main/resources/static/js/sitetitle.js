$(function () {

  $("#inc_cont000000").load("../main/index.html");
  /*단말기 관리*/
  $("#inc_cont010101").load("../Device/sub01.html");
  $("#inc_cont010201").load("../Device/sub02.html");
  $("#inc_cont010301").load("../Device/sub03.html");
  $("#inc_cont010401").load("../Device/sub04.html");
 
   /*인증관리*/
  $("#inc_cont020101").load("../Certified/sub01.html");
  $("#inc_cont020201").load("../Certified/sub02.html");
  $("#inc_cont020301").load("../Certified/sub03.html");
  $("#inc_cont020401").load("../Certified/sub04.html");
  $("#inc_cont020501").load("../Certified/sub05.html");
  $("#inc_cont020601").load("../Certified/sub06.html");
    /*통계관리*/
  $("#inc_cont030101").load("../Stats/sub01.html");
  $("#inc_cont030201").load("../Stats/sub02.html");
  $("#inc_cont030301").load("../Stats/sub03.html");
  $("#inc_cont030401").load("../Stats/sub04.html");
  $("#inc_cont030501").load("../Stats/sub05.html");
  $("#inc_cont030601").load("../Stats/sub06.html");
 
 /*임계치 관리*/
  $("#inc_cont040101").load("../Critical/sub01.html");
  $("#inc_cont040201").load("../Critical/sub02.html");
  $("#inc_cont040301").load("../Critical/sub03.html");
	
/*운영관리*/
  $("#inc_cont050101").load("../Operation/sub01.html");
  $("#inc_cont050201").load("../Operation/sub02.html");
  $("#inc_cont050301").load("../Operation/sub03.html");
  $("#inc_cont050401").load("../Operation/sub04.html");
  $("#inc_cont050501").load("../Operation/sub05.html");
	
$("#inc_cont050601").load("../Operation/sub06.html");
	

  
  /*회원정보*/
  $("#inc_cont080101").load("../member/sub01.html"); 
  $("#inc_cont080201").load("../member/sub02.html"); 
  

});


var Btitle01 = "대시보드";
var Btitle02 = "이력 관리";
var Btitle03 = "통계 관리";
var Btitle04 = "임계치 관리 ";
var Btitle05 = "운영 관리";
var Btitle08 = "사용자 관리";



var Mtitle0101 = "Viewer";
var Mtitle0102 = "X-Ray";
var Mtitle0103 = "문형 금속 검색기";
var Mtitle0104 = "신발 탐색기";


var Mtitle0201 = "X-Ray 이미지 이력";
var Mtitle0202 = "X-Ray 전원 이력";
var Mtitle0203 = "Viewer 전원 이력";
var Mtitle0204 = "로그인 이력";
var Mtitle0205 = "활동 이력";


var Mtitle0301 = "X-Ray 자동판독 통계";
var Mtitle0302 = "일별 얼굴인증 통계";
var Mtitle0303 = "일별 과금 통계";
var Mtitle0304 = "일별 오류 통계";

var Mtitle0401 = "임계치 설정";
var Mtitle0402 = "임계치 변경 이력";


var Mtitle0501 = "사용자 관리";
var Mtitle0502 = "권한 관리";
var Mtitle0503 = "메뉴 관리";
var Mtitle0504 = "권한별 메뉴 관리";
var Mtitle0505 = "코드 관리";




var Mtitle0801 = "업체 등록";
var Mtitle0802 = "비밀번호 변경";


var Btitle01_link = "../layout/layout.html?pageum=010101";
var Btitle02_link = "../layout/layout.html?pageum=020101";
var Btitle03_link = "../layout/layout.html?pageum=030101";
var Btitle04_link = "../layout/layout.html?pageum=040101";
var Btitle05_link = "../layout/layout.html?pageum=050101";
var Btitle06_link = "../layout/layout.html?pageum=060101";
var Btitle07_link = "../layout/layout.html?pageum=070101";

var Pageum000000_link = "/?pageum=000000";
var Pageum010101_link = "/equip/viewer?pageum=010101";
var Pageum010201_link = "/equip/xray?pageum=010201";
var Pageum010301_link = "../layout/layout.html?pageum=010301";
var Pageum010401_link = "../layout/layout.html?pageum=010401";

var Pageum020101_link = "/record/xrayImage?pageum=020101";
var Pageum020201_link = "/record/xrayPower?pageum=020201";
var Pageum020301_link = "/record/viewerPower?pageum=020301";
var Pageum020401_link = "/record/login?pageum=020401";
var Pageum020501_link = "/record/action?pageum=020501";
var Pageum020601_link = "../layout/layout.html?pageum=020601";

var Pageum030101_link = "/statistics/xray?pageum=030101";
var Pageum030201_link = "../layout/layout.html?pageum=030201";
var Pageum030301_link = "../layout/layout.html?pageum=030301";
var Pageum030401_link = "../layout/layout.html?pageum=030401";

var Pageum040101_link = "../layout/layout.html?pageum=040101";
var Pageum040201_link = "../layout/layout.html?pageum=040201";
var Pageum040301_link = "../layout/layout.html?pageum=040301";

var Pageum050101_link = "/operation/userList?pageum=050101";
var Pageum050201_link = "/operation/login?pageum=050201";
var Pageum050301_link = "../layout/layout.html?pageum=050301";
var Pageum050401_link = "../layout/layout.html?pageum=050401";
var Pageum050501_link = "../layout/layout.html?pageum=050501";
var Pageum050601_link = "../layout/layout.html?pageum=050601";



var Pageum060101_link = "../layout/layout.html?pageum=060101";
var Pageum060201_link = "../layout/layout.html?pageum=060201";
var Pageum060301_link = "../layout/layout.html?pageum=060301";
var Pageum060401_link = "../layout/layout.html?pageum=060401";
var Pageum060501_link = "../layout/layout.html?pageum=060501";


var Pageum070101_link = "../layout/layout.html?pageum=070101";
var Pageum070201_link = "../layout/layout.html?pageum=070201";
var Pageum070301_link = "../layout/layout.html?pageum=070301";


var Pageum080101_link = "../layout/com.html?pageum=080101";
var Pageum080201_link = "../layout/com.html?pageum=080201";





if ($pageum == '000000') {
  var Bum = "00";
  var Mum = "00";
  var Sum = "00";
  var Btitle = Btitle01;
  var Mtitle = Mtitle0101;
 var pageum = "000000";
};





if ($pageum == '010101') {
  var Bum = "01";
  var Mum = "01";
  var Sum = "01";
  var Btitle = Btitle01;
  var Mtitle = Mtitle0101;
 var pageum = "010101";
};




if ($pageum == '010201') {
  var Bum = "01";
  var Mum = "02";
  var Sum = "01";
  var Btitle = Btitle01;
  var Mtitle = Mtitle0102;
 var pageum = "010201";
};



if ($pageum == '010301') {
  var Bum = "01";
  var Mum = "03";
  var Sum = "01";
  var Btitle = Btitle01;
  var Mtitle = Mtitle0103;
  var pageum = "010301";
};


if ($pageum == '010401') {
  var Bum = "01";
  var Mum = "04";
  var Sum = "01";
  var Btitle = Btitle01;
  var Mtitle = Mtitle0104;
	 var pageum = "010401";
  
};




if ($pageum == '020101') {
  var Bum = "02";
  var Mum = "01";
  var Sum = "01";
  var Btitle = Btitle02;
  var Mtitle = Mtitle0201;
  var pageum = "020101";

};


if ($pageum == '020201') {
  var Bum = "02";
  var Mum = "02";
  var Sum = "01";
  var Btitle = Btitle02;
  var Mtitle = Mtitle0202;
 	 var pageum = "020201";
};

if ($pageum == '020301') {
  var Bum = "02";
  var Mum = "03";
  var Sum = "01";
  var Btitle = Btitle02;
  var Mtitle = Mtitle0203;
var pageum = "020301";
};


if ($pageum == '020401') {
  var Bum = "02";
  var Mum = "04";
  var Sum = "01";
  var Btitle = Btitle02;
  var Mtitle = Mtitle0204;
var pageum = "020401";
};


if ($pageum == '020501') {
  var Bum = "02";
  var Mum = "05";
  var Sum = "01";
  var Btitle = Btitle02;
  var Mtitle = Mtitle0205;
  var pageum = "020501";
};


if ($pageum == '020601') {
  var Bum = "02";
  var Mum = "06";
  var Sum = "01";
  var Btitle = Btitle02;
  var Mtitle = Mtitle0206;
 
var pageum = "020601";
};



/* */

if ($pageum == '030101') {
  var Bum = "03";
  var Mum = "01";
  var Sum = "01";
  var Btitle = Btitle03;
  var Mtitle = Mtitle0301;
 var pageum = "030101";
};

if ($pageum == '030201') {
  var Bum = "03";
  var Mum = "02";
  var Sum = "01";
  var Btitle = Btitle03;
  var Mtitle = Mtitle0302;
   var pageum = "030201";
};

if ($pageum == '030301') {
  var Bum = "03";
  var Mum = "03";
  var Sum = "01";
  var Btitle = Btitle03;
  var Mtitle = Mtitle0303;
   var pageum = "030301";
};

if ($pageum == '030401') {
  var Bum = "03";
  var Mum = "04";
  var Sum = "01";
  var Btitle = Btitle03;
  var Mtitle = Mtitle0304;
  var pageum = "030401";
};


/**/
if ($pageum == '040101') {
  var Bum = "04";
  var Mum = "01";
  var Sum = "01";
  var Btitle = Btitle04;
  var Mtitle = Mtitle0401;
  var pageum = "040101";
  
};

if ($pageum == '040201') {
  var Bum = "04";
  var Mum = "02";
  var Sum = "01";
  var Btitle = Btitle04;
  var Mtitle = Mtitle0402;
 var pageum = "040201";
};

if ($pageum == '040301') {
  var Bum = "04";
  var Mum = "03";
  var Sum = "01";
  var Btitle = Btitle04;
  var Mtitle = Mtitle0403;
 var pageum = "040301";
};



/**/

if ($pageum == '050101') {
  var Bum = "05";
  var Mum = "01";
  var Sum = "01";
  var Btitle = Btitle05;
  var Mtitle = Mtitle0501;
 var pageum = "050101";
};

if ($pageum == '050201') {
  var Bum = "05";
  var Mum = "02";
  var Sum = "01";
  var Btitle = Btitle05;
  var Mtitle = Mtitle0502;
 var pageum = "050201";
};


if ($pageum == '050301') {
  var Bum = "05";
  var Mum = "03";
  var Sum = "01";
  var Btitle = Btitle05;
  var Mtitle = Mtitle0503;
 var pageum = "050301";
};

if ($pageum == '050401') {
  var Bum = "05";
  var Mum = "04";
  var Sum = "01";
  var Btitle = Btitle05;
  var Mtitle = Mtitle0504;
 var pageum = "050401";
};

if ($pageum == '050501') {
  var Bum = "05";
  var Mum = "05";
  var Sum = "01";
  var Btitle = Btitle05;
  var Mtitle = Mtitle0505;
 var pageum = "050501";
};

if ($pageum == '050601') {
  var Bum = "05";
  var Mum = "06";
  var Sum = "01";
  var Btitle = Btitle05;
  var Mtitle = Mtitle0506;
 var pageum = "050601";
};
/**/

if ($pageum == '060101') {
  var Bum = "06";
  var Mum = "01";
  var Sum = "01";
  var Btitle = Btitle06;
  var Mtitle = Mtitle0601;
 var pageum = "060101";
};


if ($pageum == '060201') {
  var Bum = "06";
  var Mum = "02";
  var Sum = "01";
  var Btitle = Btitle06;
  var Mtitle = Mtitle0602;
var pageum = "060201";
};


if ($pageum == '060301') {
  var Bum = "06";
  var Mum = "03";
  var Sum = "01";
  var Btitle = Btitle06;
  var Mtitle = Mtitle0603;
var pageum = "060301";
};


if ($pageum == '060401') {
  var Bum = "06";
  var Mum = "04";
  var Sum = "01";
  var Btitle = Btitle06;
  var Mtitle = Mtitle0604;
var pageum = "060401";
};


if ($pageum == '060501') {
  var Bum = "06";
  var Mum = "05";
  var Sum = "01";
  var Btitle = Btitle06;
  var Mtitle = Mtitle0604;
var pageum = "060501";
};





/**/

if ($pageum == '080101') {
  var Bum = "08";
  var Mum = "01";
  var Sum = "01";
  var Btitle = Btitle08;
  var Mtitle = Mtitle0801;
 var pageum = "080101";
};

if ($pageum == '080201') {
  var Bum = "08";
  var Mum = "02";
  var Sum = "01";
  var Btitle = Btitle08;
  var Mtitle = Mtitle0802;
 var pageum = "080201";
};


/**/