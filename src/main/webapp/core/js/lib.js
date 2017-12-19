//表单取值、赋值
(function($) {
	$.fn.setValues = function(obj, onSuccess, bxpepei) {
		if (!obj) {
			$("body").unmask();
			return;
		};
		bxpepei = bxpepei || false;
		return this.each(function() {
			var type = typeof obj;
			var $this = $(this);
			var status = $this.attr("pageStatus") || $("head").attr("pageStatus");
			var suc = function(d) {};
			var url = $this.attr("getAction");
			if (type == "string") {
				url = obj;
			} else if (type == "object") {
				if (!obj) return;
				if (status == "detail") $.fn.setDivValues(obj, $this);
				else $.fn.setValues.setWithData(obj, $this, bxpepei);
				return;
			} else if (type == "function") {
				suc = obj;
			}
			if (typeof onSuccess == "function") {
				suc = onSuccess;
			}
			if (url) {
				$.ajax({
					url: url,
					$this: $this,
					dataType: 'json',
					success: function(data) {
						if (data.success) {
							if (status == "detail") $.fn.setDivValues(data.data, this.$this);
							else $.fn.setValues.setWithData(data.data, this.$this);
						} else {
							var msg = data.msg || data.data;
							$.ligerDialog.warn(msg);
						}
						$("body").unmask();
						suc(data);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert([XMLHttpRequest, textStatus, errorThrown]);
						$("body").unmask();
						$.ligerDialog.error('加载数据失败！' + textStatus, "error");
					}
				});
			} else { //13-5-2 下午9:18 lybide
				$("body").unmask();
			}

		});
	};
	$.fn.getValues = function() {
		var data = {};
		$(":input", this).not(":submit, :reset, :image,:button, [disabled],[groupChild=true]").each(function() {
			var ele = $(this);
			var manager;
			var ltype = ele.attr("ltype");
			var name = ele.attr("name");
			if (!name) return;
			if ((ele.is(":hidden") || ele.is("textarea")) && !ele.attr("ligeruiid")) {
				if (data[name] === undefined) {
					data[name] = ele.val();
				} else {
					if (!$.isArray(data[name])) {
						data[name] = [data[name]];
					}
					data[name].push(ele.val());
				}
				return;
			} else if (ele.is("textarea")) {
				data[name] = ele.val();
			} else if (ele.is(":text")) {
				switch (ltype) {
				case "select":
					manager = ele.ligerComboBox();
					break;
				case "spinner":
					manager = ele.ligerSpinner();
					break;
				case "date":
					//                        manager = ele.ligerDateEditor();
					data[name] = ele.val();
					break;
				default:
					manager = ele.ligerTextBox();
					break;
				}
			} else if (ele.is(":password")) {
				data[name] = ele.val();
			} else if (ele.is(":radio")) {
				if (ltype == "radioGroup") manager = ele.ligerRadioGroup();
				else manager = ele.ligerRadio();
			} else if (ele.is(":checkbox")) {
				if (ltype == "checkboxGroup") {
					manager = ele.ligerCheckBoxGroup();
				} else manager = ele.ligerCheckBox();
			}
			if (manager) {
				if (data[name] === undefined) {
					data[name] = manager.getValue();
				} else {
					if (!$.isArray(data[name])) {
						data[name] = [data[name]];
					}
					data[name].push(manager.getValue());
				}
			}
		});
		return data;
	};
	function parseName(name, data) {
		if (!data) {
			return undefined;
		}
		if (data[name] !== undefined) return data[name];
		var names = name.split(".");
		var value = data;
		for (var i = 0; i < names.length; i++) {
			if (value[names[i]] != undefined) value = value[names[i]];
			else return undefined;
		}
		return value;
	}

	function parseValues(value, ligerData) {
		if (value == undefined) return "&nbsp;";
		var str = [];
		var values = value.toString().split(KH__valueSplit); //alert(JSON.stringify(ligerData));
		var data = ligerData.data;
		for (var i = 0; i < values.length; i++) {
			if (ligerData.tree) {
				var valueField = "id";
				if (ligerData.valueField) valueField = ligerData.valueField;
				str.push(getTextById(valueField, values[i], data));
			} else {
				$.each(data,
				function(index, item) {
					if (values[i] == item["id"]) {
						if (ligerData && ligerData.lineWrap === false) str += ("<li class='l-lineWrap1-li'><div class='l-lineWrap1-li-div'>" + item["text"] + "</div></li>");
						else str.push(item["text"]);
					}
				});
			}
		}
		if (ligerData && ligerData.lineWrap === false) str = "<ul class='l-lineWrap1'>" + str + "</ul>";
		else str = str.join(";");
		return str == "" ? "&nbsp;": str;
	}

	function getTextById(valueField, id, data) {
		for (var i in data) {
			var temp = null;
			if (id == data[i][valueField]) {
				return data[i]["text"];
			}
			if (data[i]["children"]) {
				temp = getTextById(valueField, id, data[i]["children"]);
			}
			if (temp != null) return temp;
		}
		return null;
	}

	$.fn.setValues.setWithData = function(data, t, bxpepei) {
		if (data === undefined) return;
		function parseName(name, data) {
			if (!data) {
				return undefined;
			}
			if (data[name] !== undefined) return data[name];
			var names = name.split(".");
			var value = data;
			for (var i = 0; i < names.length; i++) {
				if (value[names[i]] != undefined) {
					value = value[names[i]];
				} else {
					return undefined;
				}
			}
			return value;
		}

		$(":input", t).not(":submit, :reset, :image,:button, [disabled]").each(function() {

			var ele = $(this);

			var manager;
			var ltype = ele.attr("ltype");
			var name = ele.attr("name");
			if (ltype == "select") {
				if (ele.attr("id")) name = ele.attr("id").replace(/-txt$/, "");
			}
			if (!name) return;
			var v = parseName(name, data);
			if (v === null) v = "";
			if (v == undefined && bxpepei) { //下拉自选，无值时退出 13-4-15 下午11:11 lybide
				return;
			}
			if ((ele.is(":hidden")) && !ele.attr("ligeruiid")) {
				if (v !== undefined) ele.val(v);
				return;
			} else if (ele.is("textarea")) { //2013-5-27 上午10:20 lybide
				manager = ele.ligerTextBox();
			} else if (ele.is(":text")) {
				switch (ltype) {
				case "select":
					manager = ele.ligerComboBox();
					break;
				case "radioGroup":
					manager = ele.ligerRadioGroup();
					break;
				case "checkboxGroup":
					manager = ele.ligerCheckBoxGroup();
					break;
				case "spinner":
					manager = ele.ligerSpinner();
					break;
				case "date":
					manager = ele.ligerDateEditor();
					break;
				case "text":
					manager = ele.ligerTextBox();
					break;
				}
			} else if (ele.is(":radio")) {
				if (ltype == "radioGroup") {
					if (ele.attr("groupChild") == "true") {
						return;
					}
					manager = ele.ligerRadioGroup();
				} else {
					manager = ele.ligerRadio();
				}
			} else if (ele.is(":checkbox")) {
				if (ltype == "checkboxGroup") {
					if (ele.attr("groupChild") == "true") {
						return;
					}
					manager = ele.ligerCheckBoxGroup();
				} else {
					manager = ele.ligerCheckBox();
				}
			} else if (ele.is("select") && !ltype) { //2013-9-11 上午9:29 lybide 原生下拉框
				if (v !== undefined) {
					ele.val(v);
				}
				return;
			}
			if (manager) {
				if (v !== undefined) manager.setValue(v);
			} else {
				ele.val(v);
			}
		});
	};
	$.fn.setDivValues = function(data, t) {
		if (data == undefined) return;

		$("div.input", t).each(function() {
			var ele = $(this);
			var name = ele.attr("name");
			if (!name) return;
			var v = parseName(name, data);
			if (v === undefined) return;
			$(this).setDivValue(v);
		});

	};
	$.fn.setDivValue = function(v) {
		var ele = $(this);
		var xtype = ele.attr("xtype");
		//        var name = ele.attr("name");
		//        if (!name)return;
		//        var v = parseName(name, data);
		//        if (v === undefined) return;
		if (v === null) {
			ele.html("&nbsp;");
			return;
		}
		if (xtype == "combobox" || xtype == "select" || xtype == "checkboxGroup" || xtype == "radioGroup") {
			var ligerData = ele.data("ligerui");
			if (ligerData.tree) {
				ligerData.data = ligerData.tree.data || ligerData.data;
			}
			var bindData = ligerData.data;
			if (bindData) {
				v = parseValues(v, ligerData);
			}
		} else if (xtype == "date") {
			if (isNaN(v)) {
				var flength = ele.data("format").length;
				if (v.length > flength) {
					v = v.substring(0, flength);
				}
			} else {
				v = $.kh.getFormatDate(new Date(v), ele.data("format"));
			}
		} else if (xtype == "spinner") { //2013-9-12 下午4:21 lybide
			var ligerData = ele.data("ligerui");
			///todo modify by jyl
			if(v!=''&&v!=null&&v!=undefined){
				if (ligerData.type == "int") {
					v = parseInt(v);
				} else if (ligerData.type == "float") {
					v = new Number(v).toFixed(ligerData.decimalplace || 2);
				}
			}
		}
		if (isNaN(v)) {
			ele.html(v == "" ? "&nbsp;": v.replace(/\n/g, "<br>"));
		} else {
			ele.html(v + "&nbsp;");
		}

	};

	$.transValues = function(datas) {
		var dataArray = [];
		$.each(datas,
		function(index, d) {
			if ($.isArray(d)) {
				$.each(d,
				function(i, dd) {
					dataArray.push({
						name: index,
						value: dd
					});
				});
			} else {
				dataArray.push({
					name: index,
					value: d
				});
			}
		});
		return dataArray;
	}

	$.fn.activeCurrentTab = function() { 
		var currentObject = $(this);
		var divTabObj = currentObject.parents("div .navtab");
		if (divTabObj.length > 0) { //当前元素是否在一个Tab里面
			var ligerTab = divTabObj.ligerTab(); //实例化Tab；
			var selectedTabItemId = ligerTab.getSelectedTabItemID(); //当前选中的Tab的id
			if (currentObject.parents("div[tabid='" + selectedTabItemId + "']").get(0) === undefined) {
				//如果当前元素不在当前选中的Tab里面，则激活当前元素所在的Tab
				var thisTabItemId = currentObject.parents("div[tabid]").attr("tabid");
				ligerTab.selectTabItem(thisTabItemId);
			}
		}
	}
	$.fn.unmask = function() {
		var id = $(this).attr("mask_id");
		$("#" + id).hide();
		$("#" + id + "_loading").hide();
	}
	$.fn.mask = function(title) {
		var $this = this;
		title = title || "正在加载数据，请稍候……";
		this.id = "_global_mask_div";
		$(this).attr("mask_id", this.id);
		if ($("#" + this.id).size() > 0) {
			$("#" + this.id).show();
			$("#" + this.id + "_loading").html(title).show();
			//$("#"+this.id+"_text").html(title);
			return;
		}
		this.maskDiv = function() {
			var wnd = $(window),
			doc = $(document);
			if (wnd.height() > doc.height()) {
				wHeight = wnd.height();
			} else {
				wHeight = doc.height();
			}
			//创建遮罩背景
			$("body").append("<div id='" + this.id + "' style='width:100%;'></div>");
			//$("#" + this.id).height(wHeight).css({position:"absolute", top:"0px", left:"0px", background:"#fff", filter:"Alpha(opacity=90);", opacity:"0.3", zIndex:"10000", display:"block"});
			$("#" + this.id).height(wHeight).addClass("l-mask-bg");
		}
		this.sPosition = function(obj) {
			var w, h;
			var mw = obj.outerWidth(true);
			var mh = obj.outerHeight(true);
			var ww = $(window).width();
			var wh = $(window).height();
			var body = $("body");
			var bw = body.width();
			var bh = body.height();
			var st = body.scrollTop();
			var sl = body.scrollLeft();
			w = bw > ww ? bw: ww;
			h = bh > wh ? bh: wh;
			var top = 0;
			var left = 0;
			left = sl > ww ? sl: 0;
			top = st > wh ? st: 0;

			var mTop = parseInt(top + ((h - mh) / 2)); //计算上边距
			var mLeft = parseInt(left + ((w - mw) / 2)); //计算左边距
			return {
				w: w,
				h: h,
				top: mTop,
				left: mLeft
			}; //13-5-2 下午3:07 lybide
			var MyDiv_w = parseInt(obj.width());
			var MyDiv_h = parseInt(obj.height());
			var width = parseInt($(document).width());
			var height = parseInt($(window).height());
			var left = parseInt($(document).scrollLeft());
			var top = parseInt($(document).scrollTop());

			var Div_topposition = top + (height / 2) - (MyDiv_h / 2); //计算上边距
			var Div_leftposition = left + (width / 2) - (MyDiv_w / 2); //计算左边距
			return [Div_topposition, Div_leftposition];
		}
		//this.maskDiv();
		var obj = $("<div class='l-mask-text' id='" + this.id + "_loading'>" + title + "</div>");
		$("body").append(obj);
		obj.show();
		var pos = this.sPosition(obj);
		obj.css({
			top: pos.top + "px",
			left: pos.left + "px"
		});

		var mask = $("<div id='" + this.id + "' class='l-mask-bg'></div>");
		$("body").append(mask);
		mask.width(pos.w);
		mask.height(pos.h);
		var fsdw = 0;
		$(window).resize(function() { //13-5-2 下午3:50 lybide
			if ($("#" + $this.id).is(":visible") == true) {
				var pos = $this.sPosition(obj);
				obj.css({
					top: pos.top + "px",
					left: pos.left + "px"
				});
				mask.width(pos.w);
				mask.height(pos.h);

				fsdw++;
			}
		});
		return this;
	}

})(jQuery);

//检测函数
var check = function(r) {
    return r.test(navigator.userAgent.toLowerCase());
};
var statics = {
    /**
    * 是否为webkit内核的浏览器
    */
    isWebkit : function() {
        return check(/webkit/);
    },
    /**
    * 是否为火狐浏览器
    */
    isFirefox : function() {
        return check(/firefox/);
    },
    /**
    * 是否为谷歌浏览器
    */
    isChrome : function() {
        return !statics.isOpera() && check(/chrome/);
    },
    /**
    * 是否为Opera浏览器
    */
    isOpera : function() {
        return check(/opr/);
    },
    /**
    * 检测是否为Safari浏览器
    */
    isSafari : function() {
    // google chrome浏览器中也包含了safari
        return !statics.isChrome() && !statics.isOpera() && check(/safari/)
    },
    isIe : function() {
		if (window.attachEvent) {
			return true;
		} else {
			return false;
		}
	}
};



