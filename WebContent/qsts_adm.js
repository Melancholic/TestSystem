/**
 * Created by sosnov on 22.05.14.
 */


/*
 обработчик удаления вопросов
 */
function del_qst_but_listenner() {
    var id = this.id.replace(/^\D+/g, '');
    $.ajax({
        type: 'GET',
        dataType: 'json',
        cache: false,
        data: {
            id: id
        },
        url: 'DeleteQuestSrvlt',
        success: function (data) {
            if (!data.res) {
                alert('Error: uncorrect value!');
            } else {
                if (data.res == "ok") {
                    alert("Вопрос успешно удален");
                    getQsts();

                } else {
                    alert("При удалении вопроса произошла ошибка: " + data.txt);
                    getQsts();
                }
            }
            $("#add_question").find("button").prop('disabled', false);
            ;

        }
    });

}
/*
 обработчик удаления групп
 */
function del_qgrp_but_listenner() {
    var id = this.id.replace(/^\D+/g, '');
    //  alert(id);
    $.ajax({
        type: 'GET',
        dataType: 'json',
        cache: false,
        data: {
            id: id
        },
        url: 'DeleteQGrpsSrvlt',
        success: function (data) {
            if (!data.res) {
                alert('Error: uncorrect value!');
            } else {
                if (data.res == "ok") {
                    alert("Группа успешно удалена");
                    updateData();
                } else {
                    alert("При удалении группы произошла ошибка: " + data.txt);
                    updateData();
                }
            }
            $("#add_qgrp").find("button").prop('disabled', false);
            ;

        }
    });

}

/*
 Заполняет списки и таблицы с группами
 */
function getQGroup() {
    var x = document.getElementById("qst_grp");
    while (x.length > 0) {
        x.remove(x.length - 1);
    }
    x = document.getElementById("qgrp_select");
    while (x.length > 1) {
        x.remove(x.length - 1);
    }
    x = document.getElementById("tstscrt_qgrp_select");
    while (x.length >= 1) {
        x.remove(x.length - 1);
    }


    $.getJSON("LessQGrpsSrvlt", function (result) {
        q = result;
        updt_qgrps_list(result.groups);
        for (var i = 0; i < result.groups.length; i++) {

            var tbody = document.getElementById("qgrp_select");
            var opt = document.createElement("option");
            // create table cell 1
            strHtml1 = result.groups[i].name;
            opt.innerHTML = strHtml1;
            tbody.appendChild(opt);

            var tbody = document.getElementById("qst_grp");
            var opt = document.createElement("option");
            // create table cell 1
            strHtml1 = result.groups[i].name;
            opt.innerHTML = strHtml1;
            tbody.appendChild(opt);

            var tbody = document.getElementById("tstscrt_qgrp_select");
            var opt = document.createElement("option");
            // create table cell 1
            strHtml1 = result.groups[i].name;
            opt.innerHTML = strHtml1;
            tbody.appendChild(opt)

        }
        $("#tstscrt_qgrp_select").val("");

    });
}

/*
 Обработчик создания нового вопроса
 */

function set_new_qsts_lisnr() {
    var json = {
        ID: 0,
        group: document.getElementById("qst_grp").options[document.getElementById("qst_grp").selectedIndex].text,
        question: document.getElementById("qst_txt").value,
        answers:document.getElementById("qst_answ").value,
        type: "text"
    };

    $.ajax({
        type: 'GET',
        dataType: 'json',
        cache: false,
        data: {
            qst_crt_json: JSON.stringify(json)
        },
        url: 'CrtQuestSrvlt',
        success: function (data) {
            if (!data.res) {
                alert('Error: uncorrect value!');
            } else {
                if (data.res == "ok") {
                    alert("Вопрос успешно добавлен");
                } else {
                    alert("При добавлении вопроса произошла ошибка: " + data.txt);
                }
            }
            $("#add_question").find("button").prop('disabled', false);
            ;
            updateData();

        }
    });
}

/*
 Обработчик создания новой группы
 */

function set_new_qgrp_lisnr() {
    var json = {
        name:document.getElementById("qgrp_name").value,
        descript: document.getElementById("qgrp_descript").value
    };

    $.ajax({
        type: 'GET',
        dataType: 'json',
        cache: false,
        data: {
            qgrp_crt_json: JSON.stringify(json)
        },
        url: 'CrtQGroupSrvlt',
        success: function (data) {
            if (!data.res) {
                alert('Error: uncorrect value!');
            } else {
                if (data.res == "ok") {
                    alert("Группа успешно добавлена");
                } else {
                    alert("При добавлении группы произошла ошибка: " + data.txt);
                }
            }
            $("#add_new_qgrpt").find("button").prop('disabled', false);
            ;
            updateData();

        }
    });
}

/*
 MAIN
 */
$(document).ready(function () {
    $("#questions_list").find("button").click(del_qst_but_listenner);
    $("#add_question").find("button").click(set_new_qsts_lisnr);

    $("#add_new_qgrpt").find("button").click(set_new_qgrp_lisnr);
    $("#qgrps_lst").find("button").click(del_qgrp_but_listenner);


});


/*
 обновляет таблицу с вопросами
 */
function updt_qsts_list(quests) {
    for (var i = 0; i < quests.length; i++) {
        var tbody = document.getElementById("all_quests").getElementsByTagName("tbody")[0];
        // create row
        var row = document.createElement("tr");
        // create table cell 1
        var td = document.createElement("th")
        var strHtml1 = /*quests[i].id;*/i + 1;
        td.innerHTML = strHtml1;
        // append data to row
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = JSON.parse(quests[i].txt).question;
        td.innerHTML = strHtml1;
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = JSON.parse(quests[i].answ).Answer;
        td.innerHTML = strHtml1;
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = "<button id=\"qdelbut_" + quests[i].id + "\" onclick=\"this.disabled=\'disabled\'\" class=\"btn btn-default\">Удалить</button>";
        td.innerHTML = strHtml1;
        row.appendChild(td);
        tbody.appendChild(row);

    }
    $("#questions_list").find("button").click(del_qst_but_listenner);
}
/*
 Добавляет в  таблицу с группами
 */
function updt_qgrps_list(qgrps) {
    var table = document.getElementById("all_qgrps");
    for (var i = table.rows.length - 1; i > 0; i--) {
        table.deleteRow(i);
    }

    for (var i = 0; i < qgrps.length; i++) {
        var tbody = document.getElementById("all_qgrps").getElementsByTagName("tbody")[0];
        // create row
        var row = document.createElement("tr");
        // create table cell 1
        var td = document.createElement("th")
        var strHtml1 = /*quests[i].id;*/i + 1;
        td.innerHTML = strHtml1;
        // append data to row
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = qgrps[i].name;
        td.innerHTML = strHtml1;
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = qgrps[i].size;
        td.innerHTML = strHtml1;
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = "<button id=\"qgdelbut_" + qgrps[i].id + "\" onclick=\"this.disabled=\'disabled\'\" class=\"btn btn-default\">Удалить</button>";
        //alert(strHtml1);
        td.innerHTML = strHtml1;
        row.appendChild(td);
        tbody.appendChild(row);

    }
    $("#qgrps_lst").find("button").click(del_qgrp_but_listenner);
}


/*
 Получает список вопросов
 */
function getQsts() {
    $.ajax({
        type: 'GET',
        dataType: 'json',
        cache: false,
        data: {
            group: $('#qgrp_select').val()
        },
        url: 'LessQuestSrvlt',
        success: function (data) {
            if (!data.quests) {
                alert('Error: uncorrect value!');
            } else {
                var table = document.getElementById("all_quests");
                for (var i = table.rows.length - 1; i > 0; i--) {
                    table.deleteRow(i);
                }
                updt_qsts_list(data.quests);
            }
        }
    });
}

/*
 MAIN
 */
$(document).ready(function () {
    $('#qgrp_select').change(getQsts);
    var q = 0;
    ;
    updateData();
});

    
    
