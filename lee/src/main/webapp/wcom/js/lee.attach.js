// 파라미터에 해당하는 파일 확장자를 반환
jQuery.fn.uploadifyFileExt = function uploadifyFileExt(extension){
	var imgExt = ['jpg','jpeg','gif','png','bmp'];
	var excelExt = ['xls','xlsx'];
	var pdfExt = ['pdf'];
	var pptExt = ['ppt','pptx'];
	var hwpExt = ['hwp','txt'];
	var docExt = ['doc'];
	var zipExt = ['zip','egg','rar','alz'];
	var musicExt = ['mp3','wav','mpg','mpeg','wma','asf','asx'];
	var movieExt = ['avi','mpg','mpeg','asf','wma','wmv','wav','smi','ac3','mp4','mkv'];
	
	if(extension=='all' || extension==null || extension=='') extension = 'img,excel,pdf,ppt,hwp,doc,zip,music,movie';
	else if(extension=='document') extension = 'excel,pdf,ppt,hwp,doc,zip';
	
	var ext = '';
	var extArr = extension.split(',');
	for(var i=0;i<extArr.length;i++){
		switch(extArr[i].split(' ').join('')){
		case 'img':
			for(var j=0;j<imgExt.length;j++){
				ext += '*.'+imgExt[j]+';';
			}
			break;
		case 'excel':
			for(var j=0;j<excelExt.length;j++){
				ext += '*.'+excelExt[j]+';';
			}
			break;
		case 'pdf':
			for(var j=0;j<pdfExt.length;j++){
				ext += '*.'+pdfExt[j]+';';
			}
			break;
		case 'ppt':
			for(var j=0;j<pptExt.length;j++){
				ext += '*.'+pptExt[j]+';';
			}
			break;
		case 'hwp':
			for(var j=0;j<hwpExt.length;j++){
				ext += '*.'+hwpExt[j]+';';
			}
			break;
		case 'doc':
			for(var j=0;j<docExt.length;j++){
				ext += '*.'+docExt[j]+';';
			}
			break;
		case 'zip':
			for(var j=0;j<zipExt.length;j++){
				ext += '*.'+zipExt[j]+';';
			}
			break;
		case 'music':
			for(var j=0;j<musicExt.length;j++){
				ext += '*.'+musicExt[j]+';';
			}
			break;
		case 'movie':
			for(var j=0;j<movieExt.length;j++){
				ext += '*.'+movieExt[j]+';';
			}
			break;
		}
	}
	return ext;
};

// 업로드 모듈 초기화
jQuery.fn.uploadifyStart = function uploadifyStart(id, option, callback){
	$('#'+id).uploadify({
		'uploader'					: option.uploader ? option.uploader : CLICK_CONTEXT+'/wcom/jquery/css/uploadify/uploadify.swf',
		'script'						: option.script ? option.script : CLICK_CONTEXT+'/json/comm/attach/uploadifyUpload.json',
		'buttonImg'				: option.buttonImg ? option.buttonImg : option.buttonText ? '' : CLICK_CONTEXT+'/wcom/images/btn/btn_upload.gif',
		'buttonText'				: option.buttonText ? option.buttonText : 'UPLOAD FILES',
		'width'						: option.width ? option.width : option.buttonText ? 120 : 74,
		'height'						: option.height ? option.height : option.buttonText ? 30 : 20,
		'folder'						: option.folder ? option.folder : 'default', 
		'cancelImg'					: option.cancelImg ? option.cancelImg : CLICK_CONTEXT+'/wcom/images/icon/ico_menu_del.gif',
		'auto'							: option.auto ? option.auto : option.auto==undefined ? true : false,
		'method'						: option.method ? option.method : 'POST',
		'queueID'					: option.queueID ? option.queueID : 'custom-queue',
		'multi'							: option.multi ? option.multi : true,
		'fileExt'						: option.fileExt ? option.fileExt : $().uploadifyFileExt('all'),
		'sizeLimit'					: option.sizeLimit && option.sizeLimit > 0 ? option.sizeLimit : 100*1024*1024,	// 최대 업로드 크기 default : 100M
		'removeCompleted'	: option.removeCompleted ? option.removeCompleted : option.removeCompleted==undefined ? true : false,
		'onError'						: function(event, queueID, fileObj, errorObj){//alert(queueID + '--에러에러--' + fileObj.name);
			//nothing
		},
		'onComplete'				: callback && $.isFunction(callback) ? callback : function(event, queueID, fileObj, response, data){alert(1);
			//override
		}
	});	
};

// 기본 파일 업로드 초기화 - 첨부파일 확장자 체크하는 로직 추가 - 2013.06.07. 김진오.
jQuery.fn.lee_defaultAttachInit = function lee_defaultAttachInit(id, queueID, folder, maxSize){
	$().uploadifyStart(id, {
		folder: folder ? folder : 'default',
		queueID: queueID ? queueID : 'default-queue',
		sizeLimit: maxSize ? maxSize : 0,
		fileExt: $().uploadifyFileExt('all'),
		removeCompleted: false
	}, function(event, queueID, fileObj, response, data){
		var res = $.parseJSON(response);
		var addHtml = '<input type="hidden" name="kpn_lctn_cntn" value="'+res.kpn_lctn_cntn+'"/>';
		addHtml += '<input type="hidden" name="kpn_url" value="'+res.kpn_url+'"/>';
		addHtml += '<input type="hidden" name="file_dsp_nm" value="'+fileObj.name+'"/>';
		addHtml += '<input type="hidden" name="file_kpn_nm" value="'+res.file_kpn_nm+'"/>';
		addHtml += '<input type="hidden" name="file_estn_nm" value="'+res.file_estn_nm+'"/>';
		addHtml += '<input type="hidden" name="file_tp_nm" value="'+res.file_tp_nm+'"/>';
		addHtml += '<input type="hidden" name="file_sz_byte" value="'+res.file_sz_byte+'"/>';
		addHtml += '<input type="hidden" name="rsrc_cmnt_cntn" value=""/>';
		$('#'+id+queueID).append(addHtml);
		
		if(res.ext_check < 0){
			$().ck_alert('첨부할 수 없는 파일입니다.<br>※ 확장자가 '+res.ext_check_db+'인 파일만 첨부 가능합니다.');
			$('#attach').uploadifyCancel(queueID);
		}
	});
};

// 일반 게시판 파일 업로드 초기화 - 첨부파일 확장자 체크하는 로직 추가 - 2013.06.07. 김진오.
jQuery.fn.click_boardAttachInit = function click_boardAttachInit(folder, bbs_sno, bbs_cls_no){
	if($('#attach').next('object').html()!=null){
		$('#attach').uploadifyClearQueue();
	}else{
		$.ajax({
			cache: false,
			url: '/nams/json/comm/attach/attachBbsMaxSize.json',
			data: 'bbs_sno='+bbs_sno+'&bbs_cls_no='+bbs_cls_no,
			type: 'POST',
			dataType: 'json',
			error: function(){
				alert('error');
			},
			success: function(json){
				if(json.result=='success'){
					$().uploadifyStart('attach', {
						queueID: 'attach-queue',
						sizeLimit: Number(json.maxSize),
						folder: folder ? folder : 'default',
								fileExt: $().uploadifyFileExt('document'),
								removeCompleted: false
					}, function(event, queueID, fileObj, response, data){
						var res = $.parseJSON(response);
						var addHtml = '<input type="hidden" name="kpn_lctn_cntn" value="'+res.kpn_lctn_cntn+'"/>';
						addHtml += '<input type="hidden" name="kpn_url" value="'+res.kpn_url+'"/>';
						addHtml += '<input type="hidden" name="file_dsp_nm" value="'+fileObj.name+'"/>';
						addHtml += '<input type="hidden" name="file_kpn_nm" value="'+res.file_kpn_nm+'"/>';
						addHtml += '<input type="hidden" name="file_estn_nm" value="'+res.file_estn_nm+'"/>';
						addHtml += '<input type="hidden" name="file_tp_nm" value="'+res.file_tp_nm+'"/>';
						addHtml += '<input type="hidden" name="file_sz_byte" value="'+res.file_sz_byte+'"/>';
						addHtml += '<input type="hidden" name="rsrc_cmnt_cntn" value=""/>';
						$('#attach'+queueID).append(addHtml);
						
						if(res.ext_check < 0){
							$().ck_alert('첨부할 수 없는 파일입니다.<br>※ 확장자가 '+res.ext_check_db+'인 파일만 첨부 가능합니다.');
							$('#attach').uploadifyCancel(queueID);
						}
					});
				}
			}
		});
	}
};