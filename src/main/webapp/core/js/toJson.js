/**
 * 转换json数组为String对象
 * @param {type} jsonArray 
 * @returns {JsonArrayToStringCfz.JsonArrayString|String}
 */
function JsonArrayToStringCfz(jsonArray) {
    if (jsonArray) {
        var JsonArrayString = "[";
        for (var i = 0; i < jsonArray.length; i++) {
            JsonArrayString = JsonArrayString + JsonToStringCfz(jsonArray[i]) + ",";
        }
        JsonArrayString = JsonArrayString.substring(0, JsonArrayString.length - 1) + "]";
        return JsonArrayString;
    } else {
        return "";
    }
}
/**
 * 转换json对象为String
 * @param {type} o
 * @returns {String}
 */
function JsonToStringCfz(o) {
    var arr = [];
    var fmt = function(s) {
        if (typeof s == 'object' && s != null)
            return json2str(s);
        return /^(string|number)$/.test(typeof s) ? "\"" + s + "\"" : s;
    }
    for (var i in o)
        arr.push("\"" + i + "\":" + fmt(o[i]));
    return '{' + arr.join(',') + '}';
}

/** 
* json对象转字符串形式 
*/ 
function json2str(o) {
	var arr = [];
	var fmt = function(s) {
		if (typeof s == 'object' && s != null)
			return json2str(s);
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
	}
	for (var i in o)
		arr.push("'" + i + "':" + fmt(o[i]));
	return '{' + arr.join(',') + '}';
}