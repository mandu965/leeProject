var CLICK_CONTEXT = '/lee';

// 특정 객체의 속성에 포함된 값을 파싱
jQuery.fn.getAttrValue = function getAttrValue($obj, attribute, delimiter, index, prefix) {
	var value = $obj.attr(attribute).toString();
	
	if( !value )
		return '';
	
	if( prefix )
		value = value.substr( value.indexOf(prefix, 0) + prefix.length ); 
	
	var values = value.split(delimiter);
	if( !values || values.length < index + 1 )
		return '';

	return values[index];
};

// A 태그의 링크 속성으로 전달한 값(# 이후의 문자열 파싱)
jQuery.fn.getAnchorValue = function getAnchorValue($obj, index) {
	return jQuery.fn.getAttrValue($obj, 'href', ';', index, '#');
};