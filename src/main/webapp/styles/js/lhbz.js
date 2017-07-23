function doOnKeyDown() { if(event.keyCode == 13 && window.event.srcElement.tagName.toLowerCase() != "textarea" ) { event.keyCode = 9; } } //去空格 
function trim(str) { return str.replace(/(^\s*)|(\s*$)/g, ""); } 
function addOneRow(tbname) { 
	var oRow;
	var oCell;
	var j;
	var t = document.all(tbname); 
	if(t.rows.length <= 0) return; 
	oRow = t.insertRow(-1);
	var cns = t.rows(0).cells.length;
	var rns = t.rows.length;
	oCell = oRow.insertCell(-1);
	oCell.innerHTML="<input type=\"text\" value=\"" + (t.rows.length - 1) + "\" size=\"2\" disabled>";
	oCell.className=t.rows(1).cells(0).className;
	for(j = 1; j < cns; j++)
	{ 
		oCell = oRow.insertCell(-1);
		oCell.innerHTML = t.rows(1).cells(j).innerHTML;
		oCell.className=t.rows(1).cells(j).className;
	}
	for(j = 1; j < rns - 1; j++) {
		t.rows(j).cells(cns - 1).innerHTML="<input type=\"button\" value=\"-\" onClick=\"delOneRow('" + tbname + "'," + j + ")\">";
	}
	t.rows(rns - 1).cells(cns - 1).innerHTML="<input type=\"button\" value=\"+\" onClick=\"addOneRow('" + tbname + "')\">";
}
function delOneRow(tbname, row) {
	var t = document.all(tbname);
	t.deleteRow(row);
	var cns = t.rows(0).cells.length;
	var rns = t.rows.length;
	var idx;
	for(idx = 1; idx < rns; idx++) {
		t.rows(idx).cells(0).innerHTML="<input type=\"text\" value=\"" + idx + "\" size=\"2\" disabled>";
		if(idx != rns -1) {
			t.rows(idx).cells(cns - 1).innerHTML="<input type=\"button\" value=\"-\" onClick=\"delOneRow('" + tbname + "'," + idx + ")\">";
		}
	}
	t.rows(rns - 1).cells(cns - 1).innerHTML="<input type=\"button\" value=\"+\" onClick=\"addOneRow('" + tbname + "')\">";
}
function disableDb(tb_id, isDisabled) { 
	var t = document.all(tb_id); 
	if(t != null) { 
		for(j = 1; j < t.rows(0).cells.length - 1; j++){ 
			t.rows(0).cells(j).children(0).readOnly = isDisabled; 
		} 
	} 
} //清除总机构的值 
function clearZjg() { 
	document.all("zjg_nsrsbh").value = ""; 
	document.all("zjg_mc").value = ""; 
	document.all("zjg_zcdz").value = ""; 
	document.all("zjg_yzbm").value = ""; 
	document.all("zjg_fddbrmc").value = ""; 
	document.all("zjg_dhhm").value = ""; 
	document.all("zjg_jyfw").value = ""; 
} //设置总机构各项为只读/可用 
function disableZjg(isDisabled) { 
	document.all("zjg_nsrsbh").readOnly = isDisabled; 
	document.all("zjg_mc").readOnly = isDisabled; 
	document.all("zjg_zcdz").readOnly = isDisabled; 
	document.all("zjg_yzbm").readOnly = isDisabled;
	document.all("zjg_fddbrmc").readOnly = isDisabled; 
	document.all("zjg_dhhm").readOnly = isDisabled; 
	document.all("zjg_jyfw").readOnly = isDisabled; 
} 

function reloadVCode(divId, ctx) {
	$("#" + divId).attr("src", ctx + "/validate?t="+Math.random());
}