/* 
 * ProgressBar.js - Progress bar에 대한 정의
 * 호환성 : IE7, IE8, 파이어폭스3.0.4, 크롬 에서 정상동작
 *          Opera 브라우저에서는 onbeforeunload 이벤트 미지원으로 동작하지 않음(오류 발생은 없음)
 */ 
var progressBar = {
    image: "/wcom/images/common/progress_bar.gif", // 사용할 이미지 파일
    enable: function() {
        // 크롬과 사파리에서 beforeunload 이벤트가 실행되는 동안
        // 동적으로 생성된 img 엘리먼트가가 정상적으로 로딩되지 않아 미리 img 엘리먼트를 생성한다.
    $("body").append('<img id ="imgProgressbar" src="' + progressBar.image + '" alt="progressbar" />');
        $("#imgProgressbar").css("display", "none");
        // IE에서 애니메이션 gif가 멈춰있는 현상으로 인하여 setTimeout을 이용하여 Progressbar function 실행
        $(window).bind("beforeunload", function() { setTimeout("Progressbar()", 0); });
    }

};

/* Progress Bar 함수 */
function Progressbar() {
    $("#imgProgressbar").modal({
            overlayCss: { "background-color": "#000", "cursor": "wait" },
            //containerCss: { "background-color": "#fff", "border": "0px solid #ccc" },
            close: false,
            closeHTML: ''
     });
}
/////////////////////////////////내가 한 부분  S
var circle = '';

jQuery.fn.CircleProgress = function CircleProgress(){

	circle = new ProgressBar.Circle('#progress', {
	    color: '#FFBB00',
	    trailColor: '#eee',
	    trailWidth: 9,
	    strokeWidth: 9,
	    duration: 600000,
	    easing: 'easeInOut'
	});
	

	circle.set(0.15);
	
	/*circle.animate(1, function() {
	    circle.animate(0);
	    circle.animate(1);
	    circle.animate(0);
	});
	setTimeout(function() {
	    circle.animate(0.3);
	}, 1000);*/

	/*setTimeout(function() {
	    circle.animate(0.4);
	}, 3500);

	setTimeout(function() {
	    circle.animate(0.8);
	}, 5500);

	setTimeout(function() {
	    circle.animate(1);
	}, 8000);*/
};


