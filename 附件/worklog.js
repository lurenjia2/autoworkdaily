var Worklog = {
		plateCode : 'DOMAIN_WORKLOAD_WORKLOG_PLATE',
		maxDayWorkHour : 15,
		doAddWorklog : function(formID){
			var jvForm = $("#"+formID);
			if (!jvForm.formvalidate()) {
				return false;
			}
			dataJson = Worklog.getParamsData(formID);
			if(dataJson.isit==true&&(dataJson.workTypeSub==null||dataJson.workTypeSub=="")){
				alert("请选择工作子类别。");
				return false;
			}
			
			
			dataJson.oper = "add";
			var fn = function(res){
				if(res.success){
					JIRA.msg("保存成功!");
					
					Worklog.doReset(formID);
					
					$("#form_create_workload").find("input[name=logdateStr]").val(dataJson.logdateStr);
					
					Worklog.showHasFill();
				}
			};
			
			JIRA.post(ctxPath+"/worklog/fill/action.do",dataJson,fn);
			/*JIRA.confirm("确定要保存吗?",function(){
				
			});*/
		},
		getParamsData : function(formID){
			var jvForm = $("#"+formID);
			var json = JIRA.formToJson(jvForm);
			if(json.stimeStr!=null&&json.stimeStr!=''){
				json.stimeStr = json.logdateStr +" "+json.stimeStr;
			}else{
				json.stimeStr = json.logdateStr +" 00:00:00";
			}
			
			if(json.etimeStr!=null&&json.etimeStr!=''){
				json.etimeStr = json.logdateStr +" "+json.etimeStr;
			}else{
				json.etimeStr = json.logdateStr +" 23:59:59";
			}
			return json;
		},

		removeWorklog : function(id){
			var fn = function(res){
				if(res.success){
					Worklog.showHasFill();
				}
			};
			JIRA.confirm("确定要删除吗?",function(){
				var dataJson = {
						oper : "del",
						id : id
				};
				JIRA.post(ctxPath+"/worklog/fill/action.do",dataJson,fn);
			});
		},
		showHasFill : function(){
			var fn = function(){
				Worklog.initLoadData();
			}
			var logdate = $("#form_create_workload").find("input[name=logdateStr]").val();
			$("#hasfill-content").loadPage(ctxPath+"/worklog/fill/getHasfillPage.do",{logdate : logdate},fn);
			
			Worklog.getContinuedTime("form_create_workload");
		},
		doReset : function(formID){
			var form = $("#"+formID);
			form[0].reset();
			Worklog.reloadSumConsumtime(0);
			
			var search_select_pname = form.find("select[name=workTypeSub]");
			search_select_pname.find('option:selected').removeAttr('selected');
			search_select_pname.trigger("chosen:updated");
		},
		addWorklog : function(){
			var clone = $("#add-content-first");
		},
		editWorklog : function(rownum,id){
			showLoading();
			var _width = 800;
			var _addurl = ctxPath+"/worklog/fill/load.do";
			var _editurl = "/worklog/edit.jsp";
			var add_content = $("#add-content");
			if(add_content.length==0){
				add_content = $('<div id="add-content" class="hide"></div>');
				$("body").append(add_content);
			}
			var fn = function(){
				hideLoading();
				add_content.removeClass('hide').dialog({
					modal: true,
					width : _width,
					title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-edit'></i>编辑日报</h4></div>",
					title_html: true,
					buttons: [ 
						{
							html: "<i class='icon-remove bigger-110'></i>&nbsp;关闭",
							"class" : "btn btn-xs",
							click: function() {
								add_content.find("form").find("input[name=consumeTimes]").val(0);
								$( this ).dialog( "close" ); 
							} 
						},
						{
							html: "<i class='icon-save bigger-110'></i>&nbsp;保存",
							"class" : "btn btn-primary btn-xs",
							click: function() {
								var formID = add_content.find("form").attr("id");
								var jvForm = $("#"+formID);
								if (!jvForm.formvalidate()) {
									return false;
								}
								var dataJson = Worklog.getParamsData(formID);
								dataJson.oper = "edit";
								 
								var fn = function(res){
									if(res.success){
										add_content.find("form").find("input[name=consumeTimes]").val(0);
										add_content.dialog( "close" );
										Worklog.showHasFill();
									}else{
										JIRA.msg(res.msg,"error");
									}
								};
								JIRA.post(ctxPath+"/worklog/fill/action.do",dataJson,fn);
							} 
						}
					]
				});
			}
			add_content.loadPage(_addurl,{id : id,forward : _editurl},fn);
		},
		saveWorklog : function(id){
			var _tr = $("#edit-"+id);
			var plate = _tr.find("select[name=plate]").val();
			var subplate = _tr.find("select[name=subplate]").val();
			var workType = _tr.find("select[name=workType]").val();
			var workTypeSub = _tr.find("select[name=workTypeSub]").val();
			var workCycle = _tr.find("select[name=workCycle]").val();
			var duty = _tr.find("select[name=duty]").val();
			var oadept = _tr.find("input[name=oadept]").val();
			var consumeTime = _tr.find("input[name=consumeTimes]").val();
			var workContent = _tr.find("textarea[name=workContent]").val();
			var workResult = _tr.find("textarea[name=workResult]").val();
			
			var json = {
					oper : 'edit',
					id : id,
					plate : plate,
					subplate : subplate,
					workType : workType,
					workTypeSub : workTypeSub,
					workCycle : workCycle,
					duty : duty,
					oadept : oadept,
					consumeTime : consumeTime,
					workContent : workContent,
					workResult : workResult
			};
			var fn = function(res){
				if(res.success){
					Worklog.showHasFill();
				}
			};
			JIRA.post(ctxPath+"/worklog/fill/action.do",json,fn);
		},
		changeWorkType : function(workType,num){
			var val = workType;
			var getnewobj = function(){
				var parent = $("#select_workType_sub_"+num).parent();
				var obj = $('<select class="form-control" name="workTypeSub" id="select_workType_sub_'+num+'" required="true"></select>');
				parent.html(obj);
				return obj;
			}
			if("项目建设"==val){
				$("#hd_div_source").hide();
				$("#select_workType_"+num).select({url:ctxPath+"/system/directory/findByDomainOfkv.do?dcode=DOMAIN_PORTAL_WORKLOAD_JOB_TYPE&fl=vv",data :val,showAllText : "-请选择-" });
				getnewobj().JiraChosen({url:ctxPath+"/jira/project/getxmhzprojects.do?fl=vv&dir=value",showAll : true,blankText : "请选择项目",data : $("#hd_workTypeSub_"+num).val()/*,callback : changeJobTypeValue(num)*/});
			}else if("其他"==val){
				$("#hd_div_source").hide();
				$("#select_workType_"+num).select({url:ctxPath+"/system/directory/findByDomainOfkv.do?dcode=DOMAIN_PORTAL_WORKLOAD_JOB_TYPE&fl=vv",data :val,showAllText : "-请选择-" });
				getnewobj().append('<option value="其他">其他</option>');
			}else if("日常工作"==val){
				$("#select_workType_"+num).select({url:ctxPath+"/system/directory/findByDomainOfkv.do?dcode=DOMAIN_PORTAL_WORKLOAD_JOB_TYPE&fl=vv",data :val,showAllText : "-请选择-" });
				getnewobj().JiraChosen({url:ctxPath+"/system/directory/findByDomainOfkv.do?dcode=DOMAIN_WORKLOAD_ONLINE_PROJECT&fl=kv&dir=value",showdefault : true,showAll : true,blankText : "请选择在线系统",data : $("#hd_workTypeSub_"+num).val()});
				
				$("#hd_div_source").show("fast",function(){
					$("#select_task_source_"+num).select({url:ctxPath+"/system/directory/findByDomainOfkv.do?dcode=DOMAIN_PORTAL_WORKLOAD_SOURCE&fl=vv", data : $("#hd_task_source_"+num).val(),showAllText : "-请选择-" });
				});
			}else{
				$("#hd_div_source").hide();
				$("#select_workType_"+num).select({url:ctxPath+"/system/directory/findByDomainOfkv.do?dcode=DOMAIN_PORTAL_WORKLOAD_JOB_TYPE&fl=vv",data :val,showAllText : "-请选择-" });
				getnewobj().select({url:ctxPath+"/system/directory/findByDomainOfkv.do?dcode=DOMAIN_PORTAL_WORKLOAD_JOB_TYPE_LSRW&fl=kv&dir=value",data : $("#hd_workTypeSub_"+num).val()});
			}
		},
		resetWorklog : function(id){
			var subform = $("#form_submit_workload");
			var oldtr = subform.find("table tbody tr[id="+id+"]");
			oldtr.show().next().remove();
		},
		cloneWorklog : function(id){
			var fn = function(res){
				hideLoading();
				if(res.success){
					Worklog.showHasFill();
				}else{
					JIRA.msg(res.msg,"error");
				}
			};
			JIRA.confirm("确定要克隆该条工作量吗?",function(){
				showLoading();
				var dataJson = {
						oper : "clone",
						id : id 
				};
				JIRA.post(ctxPath+"/worklog/fill/action.do",dataJson,fn);
			});
		},
		doSubmitWorklog : function(){
			var logdateStr = $("#form_create_workload").find("input[name=logdateStr]").val();
			var fn = function(res){
				if(res.success){
					Worklog.showHasFill();
				}else{
					JIRA.msg(res.msg,"error");
				}
			};
			JIRA.confirm("确定要保存当日工作量吗?保存后7天内还可以编辑或删除。",function(){
				if(Worklog.getSumConsumtime()>Worklog.maxDayWorkHour){
					JIRA.alert('当日消耗工时不能超过'+Worklog.maxDayWorkHour+',当日已填报消耗总工时为：'+Worklog.getSumConsumtime()+'小时。');
					return;
				}
				
				var dataJson = {
						logdateStr : logdateStr 
				};
				JIRA.post(ctxPath+"/worklog/fill/submitbatch.do",dataJson,fn);
			});
		},
		getSumConsumtime : function(){
			var inputs = $("#form_submit_workload").find("input[name=consumeTimes]:hidden");
			var sum = 0.0;
			$.each(inputs,function(i,v){
				sum += ($(v).val()!=null&&$(v).val()!="")?parseFloat($(v).val()):0.0;
			});
			return sum;
		},
		getContinuedTime : function(formID){
			var logdateStr = $("#"+formID).find("input[name=logdateStr]").val();
			var t1 = logdateStr+" "+$("#"+formID).find("input[name=stime]").val();
			var t2 = logdateStr+" "+$("#"+formID).find("input[name=etime]").val();
			var total = ($.fullCalendar.parseDate(t2)-$.fullCalendar.parseDate(t1))/1000;
			var day = parseInt(total / (24*60*60));
			var afterDay = total - day*24*60*60;
			var hour = parseInt(afterDay/(60*60));
			
			var afterHour = total - day*24*60*60 - hour*60*60;//取得算出小时数后剩余的秒数
			var min = parseInt(afterHour/60);//计算整数分
			var afterMin = total - day*24*60*60 - hour*60*60 - min*60;//取得算出分后剩余的秒数
			
			//$("input[name=continuedTime]").val(hour+"."+min);
			return hour;
		},
		initLoadData : function(){
			var temp = "";
			var ip_time = $("#form_create_workload").find("input[name=consumeTimes]");
			ip_time.bind('input propertychange', function(e) {
				//Worklog.reloadSumConsumtime(parseFloat((this.value==null||this.value=="")?0:this.value));
			});
			var num = Worklog.maxDayWorkHour-Worklog.getSumConsumtime();
			
			if(num<0){
				temp = "当日总消耗工时已经大于允许最大值，不允许再填报。";
			}else{ 
				temp = "您还可以填报"+num.toFixed(1)+"小时"; 
			}
			
			//ip_time.attr("max",num);
			//ip_time.attr("placeholder",temp);
			
			Worklog.reloadSumConsumtime();
			//$("#maxConsumTime").html(Worklog.maxDayWorkHour);
		},
		reloadSumConsumtime : function(addnum){
			if(addnum==null||addnum==""){
				addnum = 0;
			}
			var sum = Worklog.getSumConsumtime();
			$("#sumConsumeTime").html(addnum+sum);
		},
		loadAjaxData : function(num){
			if(num==null||num==""){
				num = 0;
			}
			//$("#select_classification_"+num).select({url : ctxPath+ "/system/directory/findByDomainOfkv.do?dcode=DOMAIN_PORTAL_WORKLOAD_CLASSIFI&fl=vv&dir=sort",data : $("#hd_classification_"+num).val()});
			
			//计划完成时间
			$("#select_workCycle_"+num).select({url:ctxPath+"/system/directory/findByDomainOfkv.do?dcode=DOMAIN_WORKLOAD_WORKLOG_COMPLETE_TIME",data : $("#hd_workCycle_"+num).val()});
			$("#select_duty_"+num).select({url:ctxPath+"/system/directory/findByDomainOfkv.do?dcode=DOMAIN_WORKLOAD_WORKLOG_CDZR",data : $("#hd_duty_"+num).val()});
			//$("#select_oadept_"+num).initTreeSelect({url:ctxPath+"/gfportal/dept/jstree.do",title : "所属群组/部门"});
			
			$("#logdateStr_"+num).val(getDateToday());
			//Worklog.getHashFillWorklogSum(getDateToday());
		},
		getHashFillWorklogSum : function(logdate,userid){
			$.ajax({
				  type: 'POST',
				  url: ctxPath+"/worklog/fill/getHashFillWorklogSum.do",
				  data: {logdate : logdate,loginid : userid},
				  dataType: "json",
				  beforeSend : function(){
					   
				  },
				  success: function(json){
					  $("#ft_hash_fill").html('<font color="green">当日已填报消耗工时：<strong>'+json.totaltime+'</strong>小时</font>');
				  },
				  error : function(res){
					  
				  },
				  complete : function(){
					  
				  }
			});
		},
		loadPlateDate : function(num){
			$("#select_plate_"+num).select({url:ctxPath+"/worklog/parameter/findByParentCodeByKeyValue.do?domainCode="+Worklog.plateCode,params : {dir : 's.domain_sort'},data : $("#hd_plate_"+num).val(),showAll : true,showAllText : "-请选择-",callback : function(){
				$("#select_subplate_"+num).JiraChosen({url:ctxPath+"/worklog/parameter/findByParentIDByKeyValue.do?domainCode="+Worklog.plateCode+"&parentID="+($("#select_plate_"+num+" option:selected").val()==null||$("#select_plate_"+num+" option:selected").val()==""?"getall&andparent=true":$("#select_plate_"+num+" option:selected").val()),data : $("#hd_subplate_"+num).val(),showAll : true,showAllText : "-请选择工作板块-"});
			}});
			$("#select_plate_"+num).on("change",function(){
				$("#select_subplate_"+num).JiraChosen({url:ctxPath+"/worklog/parameter/findByParentIDByKeyValue.do?domainCode="+Worklog.plateCode+"&parentID="+($(this).val()==null||$(this).val()==""?"getall&andparent=true":$(this).val()),showAll : true,showAllText : "-请选择-"});
			});
			if($("#select_workType_"+num).length>0){
				$("#select_workType_"+num).select({url:ctxPath+"/system/directory/findByDomainOfkv.do?dcode=DOMAIN_PORTAL_WORKLOAD_JOB_TYPE&fl=vv",data : $("#hd_workType_"+num).val(),showAllText : "-请选择-",callback : function(){
					Worklog.changeWorkType($("#select_workType_"+num).val(),num);
				}});
			}
		},
		platesubChange : function(num,subobj){
			var subid = subobj.value;
			var text = $(subobj).find("option:selected").text();
			
			
			if(subid==null||subid==''){
				return;
			}
			
			//$("#hd_subplate_"+num).val(text);
			
			var success = function(res){
				//$(subobj).find("option:selected").text(res.domainName);
				//$("#select_plate_"+num).select({url:ctxPath+"/worklog/parameter/findByParentCodeByKeyValue.do?domainCode="+Worklog.plateCode,params : {dir : 's.domain_sort'},data : $("#hd_plate_"+num).val(),showAll : true,showAllText : "-请选择-"});
 				$("#select_plate_"+num).val(res.domainParentID);
			};
			JIRA.post(ctxPath+"/system/domain/load2json.do",{id : subid},success);
		},
		plateChange : function(num,obj){
			var val = obj.value;
			var text = $(obj).find("option:selected").text();
			
			//$("#hd_plate_"+num).val(text); 
		}
};


function getDateToday(){
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth()+1;
	var day = today.getDate();
	
	if(parseInt(month)<10){
		month = "0"+month;
	}
	return year+"-"+month+"-"+day;
}


/*$(function(){
	document.onkeydown=function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        	if(e && e.keyCode==13){ // enter 键
        		Worklog.doAddWorklog("form_create_workload");
        	}
    }; 
});*/