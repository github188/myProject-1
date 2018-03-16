var obj = { date: new Date(), year: -1, month: -1, timeArr: [],flag:-1 ,start:new Date()};
var htmlObj = { header: "", left: "", right: "" };
var elemId = null;
function getAbsoluteLeft(objectId) {
   var o = document.getElementById(objectId)
   var oLeft = o.offsetLeft;
    while (o.offsetParent != null) {
        oParent =o.offsetParent
        oLeft += oParent.offsetLeft
        o = oParent
    }
    return oLeft
}
//获取控件上绝对位置
function getAbsoluteTop(objectId) {
   var o = document.getElementById(objectId);
   var oTop = o.offsetTop + o.offsetHeight + 10;
    while (o.offsetParent != null) {
        oParent = o.offsetParent
        oTop += oParent.offsetTop
        o = oParent
    }
    return oTop
}
//获取控件宽度
function getElementWidth(objectId) {
    x = document.getElementById(objectId);
    return x.clientHeight;
}
var pickerEvent = {
    Init: function (elemid,queryStartObj) {  	
    	obj.date = queryStartObj;
    	obj.start = queryStartObj;

    	if(!queryStartObj){
    		obj.date = new Date();
    	}

    	if(obj.flag == -1){
             dateUtil.getCurrent();
        }
        
        
        for (var item in pickerHtml) {
            pickerHtml[item]();
           
        }
        var p = document.getElementById("calendar_choose");
        if (p != null) {
            //document.body.removeChild(p);
            document.getElementById("queryDateBody").removeChild(p);
        }

        var html = '<div id="calendar_choose" class="calendar" style="display: block; position: absolute;border-radius:6px;">'
        html += htmlObj.header;
        html += '<div class="basefix" id="bigCalendar" style="display: block;">';
        html += htmlObj.left;
        html += htmlObj.right;
        html += '<div style="clear: both;"></div>';
        html +='</div></div>';

        elemId=elemid;
        obj.flag = -1;
        var elemObj = document.getElementById(elemid); 

        $("#queryDateBody").append(html);
        //$(document.body).append(html);

        document.getElementById("picker_last").onclick = pickerEvent.getLast;
        document.getElementById("picker_next").onclick = pickerEvent.getNext;
		document.getElementById("picker_today").onclick = pickerEvent.getToday;
        document.getElementById("calendar_choose").style.left = getAbsoluteLeft(elemid)+"px";
        document.getElementById("calendar_choose").style.top  = getAbsoluteTop(elemid)+"px";
        document.getElementById("calendar_choose").style.zIndex = 1000;
        var tds = document.getElementById("calendar_tab").getElementsByTagName("td");
        for (var i = 0; i < tds.length; i++) {
            if (tds[i].getAttribute("date") != null && tds[i].getAttribute("date") != "" && tds[i].getAttribute("price") != "-1") {
                tds[i].onclick = function () {
                    commonUtil.chooseClick(this)
                };
            }
        }
       
        // return html;
        //return elemObj;
    },
    getLast: function () {
        dateUtil.getLastDate();
        pickerEvent.Init(elemId);
    },
    getNext: function () {
        dateUtil.getNexDate();
        pickerEvent.Init(elemId);
    },
	getToday:function(){
		dateUtil.getCurrent();
		pickerEvent.Init(elemId);
	},
    setTimeArr: function (arr) {
        obj.timeArr = arr;
    },
    remove: function () {
        var p = document.getElementById("calendar_choose");
        if (p != null) {
           // document.body.removeChild(p);
            document.getElementById("queryDateBody").removeChild(p);
        }
    },
    isShow: function () {
        var p = document.getElementById("calendar_choose");
        if (p != null) {
            return true;
        }
        else {
            return false;
        }
    },
}

var pickerHtml = {
    getHead: function () {
        var head = '<ul class="calendar_num basefix"><li class="bold">日</li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li class="bold">六</li><li class="picker_today bold"  ><a id="picker_today">回到今天</a><a data-dismiss="modal" class="closes" style="color:#fff">关闭</a></li></ul>';
        htmlObj.header = head;
    },
    getLeft: function () {
        var left = '<div class="calendar_left pkg_double_month" style="float:right"><p class="date_text">' + obj.year + '年<br>' + obj.month + '月</p><a href="javascript:void()" title="上一月" id="picker_last" class="pkg_circle_top"></a><a href="javascript:void()" title="下一月" id="picker_next" class="pkg_circle_bottom "></a></div>';
        htmlObj.left = left;
    },
    getRight: function () {
        var days = dateUtil.getLastDay();
        var week = dateUtil.getWeek();
        var html = '<table id="calendar_tab" class="calendar_right"><tbody>';
        var index = 0;
        for (var i = 1; i <= 35; i++) {
            if (index == 0) {
                html += "<tr>";
            }
            var c = week > 0 ? week : 0;
            if ((i - 1) >= week && (i - c) <= days) {
                var price = commonUtil.getPrice((i - c));
                var priceStr = "";
                var classStyle = "";
                if (price != -1) {
                    priceStr =  "<dfn>"+price+"</dfn>";
                    classStyle = "class='on'";
                    
                }
				if (price != -1&&obj.year==new Date().getFullYear()&&obj.month==new Date().getMonth()+1&&i-c==new Date().getDate()) {
                    classStyle = "class='on today'";
                }
				//判断今天
				if(obj.year==new Date().getFullYear()&&obj.month==new Date().getMonth()+1&&i-c==new Date().getDate()){
					html += '<td  ' + classStyle + ' date="' + obj.year + "-" + obj.month + "-" + (i - c) + '" price="' + price + '"><a><span class="date basefix" style="color:red">今天</span><span class="team basefix" style="display: none;">&nbsp;</span><span class="calendar_price01">' + priceStr + '</span></a></td>';
				}
				else{
                	html += '<td  ' + classStyle + ' date="' + obj.year + "-" + obj.month + "-" + (i - c) + '" price="' + price + '"><a><span class="date basefix">' + (i - c) + '</span><span class="team basefix" style="display: none;">&nbsp;</span><span class="calendar_price01">' + priceStr + '</span></a></td>';
				}
                if (index == 6) {

                    html += '</tr>';
                    index = -1;
                }
            }
            else {
                html += "<td></td>";
                if (index == 6) {
                    html += "</tr>";
                    index = -1;
                }
            }
            index++;
        }
        html += "</tbody></table>";
        htmlObj.right = html;

    }
}
var dateUtil = {
    //根据日期得到星期
    getWeek: function () {
        var d = new Date(obj.year, obj.month - 1, 1);
        return d.getDay();
    },
    //得到一个月的天数
    getLastDay: function () {
        var new_year = obj.year;//取当前的年份        
        var new_month = obj.month;//取下一个月的第一天，方便计算（最后一不固定）        
        var new_date = new Date(new_year, new_month, 1);                //取当年当月中的第一天        
        return (new Date(new_date.getTime() - 1000 * 60 * 60 * 24)).getDate();//获取当月最后一天日期        
    },
    getCurrent: function () {
        var dt = obj.date;
        obj.year = dt.getFullYear();
        obj.month = dt.getMonth() + 1;
		obj.day = dt.getDate();
    },

    getLastDate: function () {
    	var sDate = $("#sDate").val();
    	var sY = sDate.split("-")[0];
    	var sM = sDate.split("-")[1];

    	var nowY = $(".date_text").text().slice(0,4);  //日历当前年
    	var nowM = $(".date_text").text().slice(5,$(".date_text").text().length-1);  //日历当前月份
    	
        if (obj.year == -1) {
            var dt = new Date(obj.date);
            obj.year = dt.getFullYear();
            obj.month = dt.getMonth() + 1;
        }else {  
        	var newMonth = obj.month - 1;
            if (newMonth <= 0) {
                obj.year -= 1;
                obj.month = 12;
            }else {
            	if(sDate){
            		if(parseInt(nowY) == parseInt(sY)){     //当前年份等于查询开始年份的情况
            			if(parseInt(nowM) > parseInt(sM)){  //月份小于查询开始月份
                   	 		obj.month -= 1;
            			}else{            				
            				alertMsg('没有空闲月份了');
            			}
                	}else if(parseInt(nowY) > parseInt(sY)){  //当前年份小于查询开始年份的情况
                		obj.month -= 1;
                	}else{
                		alertMsg('没有空闲月份了');
                	}            		
            	}else{
            		obj.month -= 1;
            	}
            	         		
            }
        }
        obj.flag = 1;
    },
    getNexDate: function () {
    	
    	var eDate = $("#eDate").val();
    	var eY = eDate.split("-")[0];
    	var eM = eDate.split("-")[1];

    	var nowY = $(".date_text").text().slice(0,4);  //日历当前年
    	var nowM = $(".date_text").text().slice(5,$(".date_text").text().length-1);  //日历当前月份
    	
        if (obj.year == -1) {
            var dt = new Date(obj.date);
            obj.year = dt.getFullYear();
            obj.month = dt.getMonth() + 1;
        }
        else {
            var newMonth = obj.month + 1;
            if (newMonth > 12) {
                obj.year += 1;
                obj.month = 1;
            }
            else {
            	if(eDate){   //如果有查询截止时间 的情况      		
            		if(parseInt(nowY) == parseInt(eY)){     //当前年份等于查询截止年份的情况
            			if(parseInt(nowM) < parseInt(eM)){  //月份小于查询截止月份
                   	 		obj.month += 1;
            			}else{
            				
            				alertMsg('没有空闲月份了');
            				$("#queryDateWin").modal('hide');	
 
            			}
                	}else if(parseInt(nowY) < parseInt(eY)){  //当前年份小于查询截止年份的情况
                		obj.month += 1;
                	}else{
                		
                		alertMsg('没有空闲月份了');
                		$("#queryDateWin").modal('hide');	
                
                	}
            	}else{   //没有输入查询截止时间的情况
            		obj.month += 1;
            	}
            }
        }
        obj.flag = 2;
    }
}
var commonUtil = {
    getPrice: function (day) {
        var dt = obj.year + "-";
        if (obj.month < 10)
        {
            dt += "0"+obj.month;
        }
        else
        {
            dt+=obj.month;
        }
        if (day < 10) {
            dt += "-0" + day;
        }
        else {
            dt += "-" + day;
        }
        for (var i = 0; i < obj.timeArr.length; i++) {
            if (obj.timeArr[i].date == dt) {
                return obj.timeArr[i].freetime.split('.')[0];
            }
        }
        return -1;
    },
    chooseClick: function (sender) {
        var date = sender.getAttribute("date");
        var price = sender.getAttribute("price");
        var el = document.getElementById(elemId);
        if (el != null) {
            el.value = date;
			//alert("日期是："+date);
			//alert("价格是：￥"+price);
            pickerEvent.remove();
        }
    }
}


/*$(document).bind("click", function (event) {
    var e = event || window.event;
    var elem = e.srcElement || e.target;
    while (elem) {
        if (elem.id == "calendar_choose") {
            return;
        }
        elem = elem.parentNode;
    }
    pickerEvent.remove();
});*/

