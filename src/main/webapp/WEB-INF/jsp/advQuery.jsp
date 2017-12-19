<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>高级查询</title>
<style type="text/css">
button{
	border:0;
	width:100%;
	height:100%;
}
.csr{
	width: 100px; 
	cursor: pointer;
}
</style>
<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>

</head>
<body>

	<table border="1" bordercolor="#a0c6e5"
		style="border-collapse: collapse;">
		<tr align="center">
			<td class="csr">
				<button disabled name="oper" attr="and">与</button>
			</td>
			<td class="csr">
				<button disabled name="oper" attr="or">或</button>
			</td>
			<td class="csr">
				<button name="brackets" attr="(">添加(</button>
			</td>
			<td class="csr">
				<button name="brackets" attr=")">添加)</button>
			</td>
			<td class="csr" onclick="javascript:delAll()">
				<button>清空</button>
			</td>
		</tr>
	</table>
	<table border="1" bordercolor="#a0c6e5"
		style="border-collapse: collapse;">
		<tr align="center">
			<td>
			<select id="column">
					<option value="">请选择</option>
					<c:forEach items="${entityField}" varStatus="i" var="item">
						<option value="${item.col}" attr="${item.typeCode}">${item.colName}</option>
					</c:forEach>
			</select>
			</td>
			<td><select id="exp">
					<option value="=">等于</option>
					<option value="<>">不等于</option>
					<option value=">">大于</option>
					<option value=">=">大于等于</option>
					<option value="<">小于</option>
					<option value="<=">小于等于</option>
					<option value="in">包含</option>
			</select></td>
			<td><input id="val" type="text"></td>
			<td style="cursor: pointer">
				<button type="button" onclick="javascript:addRow()">添加</button>
			</td>
		</tr>
		<tr>
			<td colspan="4"><textarea
					style="width: 486px; height: 330px; border: 0;" id="query"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<button type="button" onclick="advQuery()">查询</button>
			</td>
		</tr>
	</table>

	<input type="hidden" id="symbol">

</body>
</html>
<script type="text/javascript">
var basePath  = '<%=basePath%>';
var num = 0;
(function($){
    $.fn.extend({
        insertAtCaret: function(myValue){
            var $t=$(this)[0];
            //IE
            if (document.selection) {
                this.focus();
                sel = document.selection.createRange();
                sel.text = myValue;
                this.focus();
            }
            //FF
            else{
                if ($t.selectionStart || $t.selectionStart == '0') {
                    var startPos = $t.selectionStart;
                    var endPos = $t.selectionEnd;
                    var scrollTop = $t.scrollTop;
                    $t.value = $t.value.substring(0, startPos) + myValue + $t.value.substring(endPos, $t.value.length);
                    this.focus();
                    $t.selectionStart = startPos + myValue.length;
                    $t.selectionEnd = startPos + myValue.length;
                    $t.scrollTop = scrollTop;
                }
                else {
                    this.value += myValue;
                    this.focus();
                }
            }
        }
    }) 
})(jQuery);


$(function (){
	$('button[name=oper]').each(function(){
		$(this).bind('click', function(){
			$('button[name=oper]').attr('disabled', true);
			$('#symbol').val($(this).attr('attr'));
		});
	});
	
	$('button[name=brackets]').each(function(){
		$(this).bind('click', function(){
			$('#query').insertAtCaret($(this).attr('attr'));
		});
	});
});

function addRow() {
	var c = $('#column').val();
	if(c == ''){
		alert('请选择属性');
		return;
	}
	
	var v = $('#val').val();
	var e = $('#exp').val();
	if(v == ''){
		alert('请输入值');
		return;
	}
	var colType = $('#column option:selected').attr('attr');
	if(colType == '10140004' || colType == '10140005' || colType == 'java.lang.String' || colType == 'java.util.Date'){		
		if(e == 'in'){
			var val = '';
			var vs = v.split(',');
			for(var i=0;i<vs.length;i++){
				val += "'" + vs[i] + "',";
			}
			v = val.substring(0, val.length - 1);
			v = "(" + v + ")";
		}
		else{
			v = "'" + v + "'";
		}
	}
	else{
		if(e == 'in'){
			v = "(" + v + ")";
		}
	}

	var s = $('#symbol').val();
	if(num > 0 && s == ''){
		alert('请选择与或运算符');
		return;
	}

	//查询语句
	var query = $('#query').val();
	query = query == '' ? query : query + " ";
	$('#query').val(query + s + " " + c  + " " + e + " " + v);
	
	num++;
	
	$('button[name=oper]').attr('disabled', false);
	$('#symbol').val('');
}

function delAll() {
	$('#query').val('');
	num = 0;
}


function advQuery(){
	var frames = window.parent.window.document.getElementsByTagName("iframe")[1];
	var condition = $('#query').val();
	if(condition != ''){
		var param = $('#queryForm').serialize();
		// 提交
		$.ajax({
	        url: basePath + "rest/" + frames.contentWindow.routeName + "Action/advQuery",
	        type:"post",
			data:"condition="+condition,
	        success: function(json){
	        	frames.contentWindow.grid.set({data:json});
	        	parent.$(".l-dialog,.l-window-mask").remove();
	        },
	        error: function(json) {
	        	parent.$(".l-dialog,.l-window-mask").remove();
	        }
	    });
	}
}
</script>