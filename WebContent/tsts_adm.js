/**
 * Created by sosnov on 24.05.14.
 */
function del_tst_but_listenner() {
    var id = this.id.replace(/^\D+/g, '');
    $.ajax({
        type: 'GET',
        dataType: 'json',
        cache: false,
        data: {
            id: id
        },
        url: 'DeleteTstsSrvlt',
        success: function (data) {
            if (!data.res) {
                alert('Error: uncorrect value!');
            } else {
                if (data.res == "ok") {
                    alert("Тест успешно удален");
                    updateData();
                } else {
                    alert("При удалении теста произошла ошибка: " + data.txt);
                    updateData();
                }
            }
        }
    });
}

function del_qgroup_prop_but_listenner(el) {

    while (el.parentNode && el.tagName.toLowerCase() != 'tr') {
        el = el.parentNode;
    }
    if (el.parentNode && el.parentNode.rows.length > 1) {
        el.parentNode.removeChild(el);
    }

    var table = document.getElementById("qgrps_4_crt_tst");
    table.rows[1].cells[0].innerHTML = 1;
    table.rows[1].cells[4].innerHTML = "<button id=\"qgrpprop_" + table.rows[1].cells[0].value + "\" onclick=\"del_qgroup_prop_but_listenner(this);\" class=\"btn btn-default\">Удалить</button>";
    ;
    for (var i = 2; i < table.rows.length; i++) {
        table.rows[i].cells[0].innerHTML = (1 + parseInt(table.rows[i - 1].cells[0].innerHTML));
        table.rows[i].cells[4].innerHTML = "<button id=\"qgrpprop_" + (table.rows[i - 1].cells[0].value + 1) + "\" onclick=\"del_qgroup_prop_but_listenner(this);\" class=\"btn btn-default\">Удалить</button>";
        ;
    }
}


/*
 Обработчик создания нового теста
 */

function set_new_tst_lisnr() {
    var grps_prop = [];
    var table = document.getElementById("qgrps_4_crt_tst");
    for (var i = 1; i < table.rows.length; i++) {
        var val = {
            g_name: table.rows[i].cells[1].innerHTML,
            g_size: table.rows[i].cells[2].innerHTML,
            g_num: table.rows[i].cells[3].innerHTML
        }
        grps_prop.push(val);
    }

    var prop = {
        dur: document.getElementById("tsts_dur").value
    }
    var json = {
        name: document.getElementById("tst_name").value,
        descript: document.getElementById("tst_descript").value,
        prop: JSON.stringify(prop),
        groups: grps_prop
    };
   // alert(JSON.stringify(json))
    $.ajax({
        type: 'GET',
        dataType: 'json',
        cache: false,
        data: {
            tst_crt_json: JSON.stringify(json)
        },
        url: 'CrtTestSrvlt',
        success: function (data) {
            if (!data.res) {
                alert('Error: uncorrect value!');
            } else {
                if (data.res == "ok") {
                    alert("Тест успешно добавлен");
                } else {
                    alert("При добавлении теста произошла ошибка: " + data.txt);
                }
            }
            $("#add_new_tst").find("button").prop('disabled', false);
            ;
            updateData();

        }
    });
}

/*
 Добавляет в  таблицу с тестами
 */
function updt_tsts_list(tsts) {
    var table = document.getElementById("all_tsts");
    for (var i = table.rows.length - 1; i > 0; i--) {
        table.deleteRow(i);
    }
    for (var i = 0; i < tsts.length; i++) {
        var tbody = document.getElementById("all_tsts").getElementsByTagName("tbody")[0];
        // create row
        var row = document.createElement("tr");
        // create table cell 1
        var td = document.createElement("th")
        var strHtml1 = /*quests[i].id;*/i + 1;
        td.innerHTML = strHtml1;
        // append data to row
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = tsts[i].name;
        td.innerHTML = strHtml1;
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = tsts[i].size;
        td.innerHTML = strHtml1;
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = tsts[i].dur;
        td.innerHTML = strHtml1;
        row.appendChild(td);
        td = document.createElement("th")
        /*strHtml1 = " ";
         if(tsts[i].status=="1")strHtml1 += "Активный";
         if(tsts[i].status=="0")strHtml1 += "Неактивный";
         td.innerHTML = strHtml1;
         row.appendChild(td);
         td = document.createElement("th")*/
        strHtml1 = tsts[i].status;
        /*if(tsts[i].status=="1")strHtml1 += " <button id=\"tstdeactivbut_" + tsts[i].id + "\" onclick=\"this.disabled=\'disabled\'\" class=\"btn btn-default\">Удалить</button>";
         if(tsts[i].status=="0")strHtml1 += " <button id=\"tstactivbut_" + tsts[i].id + "\" onclick=\"this.disabled=\'disabled\'\" class=\"btn btn-default\">Удалить</button>";
         strHtml1 += "<button id=\"tstdelbut_" + tsts[i].id + "\" onclick=\"this.disabled=\'disabled\'\" class=\"btn btn-default\">Удалить</button>";*/
        //alert(strHtml1);
        td.innerHTML = strHtml1;
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = "<button id=\"tstdelbut_" + tsts[i].id + "\" onclick=\"this.disabled=\'disabled\'\" class=\"btn btn-default\">Удалить</button>";
        ;
        td.innerHTML = strHtml1;
        row.appendChild(td);
        tbody.appendChild(row);
        /*$("#all_tsts").getById("tstdeactivbut_" + tsts[i].id).click(deactv_tst_but_listenner);
         $("#all_tsts").getById("tstactivbut_" + tsts[i].id).click(actv_tst_but_listenner);
         $("#all_tsts").getById("tstdelbut_" + tsts[i].id).click(del_tst_but_listenner);*/
    }
    $("#all_tsts").find("button").click(del_tst_but_listenner);

}

function getTests() {
    var x = document.getElementById("all_tsts");
    while (x.length > 0) {
        x.remove(x.length - 1);
    }

    $.getJSON("LessTstsSrvlt", function (result) {
        updt_tsts_list(result.tsts);
    });
}
function showNewTstGroupsProp() {
    $('#qgrp_crttst_size').val("");
    $('#qgrp_crttst_num').val("");
    $('#qgrp_prop').modal({show: true})
}

function save_qgrp_prop() {
    $('#qgrp_prop').modal('hide');
    var table = document.getElementById("qgrps_4_crt_tst");
    for (var i = 0; i < table.rows.length; i++) {
        if (table.rows[i].cells[1].innerHTML == $('#tstscrt_qgrp_select').val()) {
            table.rows[i].cells[2].innerHTML = $('#qgrp_crttst_size').val();
            table.rows[i].cells[3].innerHTML = $('#qgrp_crttst_num').val();
            $("#tstscrt_qgrp_select").val("");
            return;
        }
    }
    var row = table.insertRow(table.rows.length);
    var cell1 = row.insertCell(0);
    cell1.innerHTML = table.rows.length - 1;
    var cell1 = row.insertCell(1);
    cell1.innerHTML = $('#tstscrt_qgrp_select').val();
    cell1 = row.insertCell(2);
    cell1.innerHTML = $('#qgrp_crttst_size').val();
    cell1 = row.insertCell(3);
    cell1.innerHTML = $('#qgrp_crttst_num').val();
    cell1 = row.insertCell(4);
    var strHtml1 = "<button id=\"qgrpprop_" + (table.rows.length - 1) + "\"  onclick=\"del_qgroup_prop_but_listenner(this);\" class=\"btn btn-default\">Удалить</button>";
    ;
    cell1.innerHTML = strHtml1;
    $("#tstscrt_qgrp_select").val("");
    // $("#qgrps_4_crt_tst").find("button").click(del_qgroup_prop_but_listenner);
}

$(document).ready(function () {
    $("#add_new_tst").find("button").click(set_new_tst_lisnr);
    $("#qgrp_prop_ok").click(save_qgrp_prop);

});
$(document).ready(function () {
    $('#tstscrt_qgrp_select').change(showNewTstGroupsProp);


});
