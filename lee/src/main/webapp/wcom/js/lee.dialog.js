// dialogs variable
var _lee_dialogs = new Array();

//init dialog
function initDialog(id, css, width, height, title, titleDesc) {
	_lee_dialogs[id] = $('#' + id).dialog({
		autoOpen:false, //false면 open()메소드가 호출될때까지 출력되지 않는다.
		dialogClass: css, // 다이알로그에 지정한 임의의 class명을 추가합니다
		width: width,
		height: height,
		modal:true, //다일러로그 밖, 바탕화면 블라인드 처리
		resizable:false, //창 크기조절 가능여부
		draggable : true, //드래그 가능여부
		title: (title ? title : '') + '<span>' + (titleDesc ? titleDesc : '') + '</span>'
		//position : [100 , 200] [좌우(left), 상하(top)]
		
	});
	
	if( !$('#kwork_contents.dialogOutter').hasClass('_lee_dialogs_' + id) ) {
		_lee_dialogs[id].html('<div id="kwork_contents" class="dialogOutter _lee_dialogs_' + id + '">' + _lee_dialogs[id].html() + '</div>');
	}
}

//open dialog 
function openDialog(id, position) {
	if( position )
		_lee_dialogs[id].dialog({position: position});
	_lee_dialogs[id].dialog('open');
}

// close dialog
function closeDialog(id) {
	_lee_dialogs[id].dialog('close');
}

//buttons
function dialogButtons(id, buttons) {
	_lee_dialogs[id].dialog({buttons:buttons});
}
