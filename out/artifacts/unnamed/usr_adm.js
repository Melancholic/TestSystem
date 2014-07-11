/**
 * Created by sosnov on 25.05.14.
 */

function  del_usr_but_listenner(){
    var id = this.id.replace(/^\D+/g, '');
    $.ajax({
        type: 'GET',
        dataType: 'json',
        cache: false,
        data: {
            id: id
        },
        url: 'DeleteUsrSrvlt',
        success: function (data) {
            if (!data.res) {
                alert('Error: uncorrect value!');
            } else {
                if (data.res == "ok") {
                    alert("Пользователь успешно удален");
                    getUsers();

                } else {
                    alert("При удалении пользователя произошла ошибка: " + data.txt);
                    getUsers();
                }
            }

        }
    });
}
function del_ugrp_but_listenner(){;
    var id = this.id.replace(/^\D+/g, '');
    $.ajax({
        type: 'GET',
        dataType: 'json',
        cache: false,
        data: {
            id: id
        },
        url: 'DeleteUGrpsSrvlt',
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
        }
    });
}

function set_new_ugrp_lisnr() {
    var json = {
        name: document.getElementById("ugrp_name").value,
        descript: document.getElementById("ugrp_descript").value
    };

    $.ajax({
        type: 'GET',
        dataType: 'json',
        cache: false,
        data: {
            ugrp_crt_json: JSON.stringify(json)
        },
        url: 'CrtUGroupSrvlt',
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
            $("#add_new_ugrpt").find("button").prop('disabled', false);
            ;
            updateData();

        }
    });
}

function set_new_user_lisnr() {
    if(document.getElementById("upswd1").value.length<3){
        alert("Длина пароля должна быть более 3 символов!");
        $("#add_user").find("button").prop('disabled', false);
        return false;
    }
    if(document.getElementById("upswd1").value!=document.getElementById("upswd2").value ){
        alert("Пароли не совпадают!");
        $("#add_user").find("button").prop('disabled', false);
        return false;
    }
    var json = {
        group :   document.getElementById("u_grp").value,
        name: document.getElementById("uname").value,
        pswd: document.getElementById("upswd1").value,
        admin :  document.getElementById("isAdmin").checked
    };
    $.ajax({
        type: 'GET',
        dataType: 'json',
        cache: false,
        data: {
            usr_crt_json: JSON.stringify(json)
        },
        url: 'CrtUsrSrvlt',
        success: function (data) {
            if (!data.res) {
                alert('Error: uncorrect value!');
            } else {
                if (data.res == "ok") {
                    alert("Пользователь успешно добавлен");
                } else {
                    alert("При добавлении пользователя произошла ошибка: " + data.txt);
                }
            }
            $("#add_user").find("button").prop('disabled', false);
            ;
            updateData();

        }
    });
}

function updt_usrs_list(users) {
    for (var i = 0; i < users.length; i++) {
        var tbody = document.getElementById("all_users").getElementsByTagName("tbody")[0];
        // create row
        var row = document.createElement("tr");
        if (users[i].isAdmin == "1") {
            row.className = "danger";
        }
        // create table cell 1
        var td = document.createElement("th")
        var strHtml1 = /*quests[i].id;*/i + 1;
        td.innerHTML = strHtml1;
        // append data to row
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = users[i].name;
        td.innerHTML = strHtml1;
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = users[i].group;
        td.innerHTML = strHtml1;
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = "<button id=\"udelbut_" + users[i].id + "\" onclick=\"this.disabled=\'disabled\'\" class=\"btn btn-default\">Удалить</button>";
        td.innerHTML = strHtml1;
        row.appendChild(td);
        tbody.appendChild(row);

    }
    $("#users_list").find("button").click(del_usr_but_listenner);
}
function updt_ugrps_list(ugrps) {
    var table = document.getElementById("all_ugrps");
    for (var i = table.rows.length - 1; i > 0; i--) {
        table.deleteRow(i);
    }

    for (var i = 0; i < ugrps.length; i++) {
        var tbody = document.getElementById("all_ugrps").getElementsByTagName("tbody")[0];
        // create row
        var row = document.createElement("tr");
        // create table cell 1
        var td = document.createElement("th")
        var strHtml1 = /*quests[i].id;*/i + 1;
        td.innerHTML = strHtml1;
        // append data to row
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = ugrps[i].name;
        td.innerHTML = strHtml1;
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = ugrps[i].size;
        td.innerHTML = strHtml1;
        row.appendChild(td);
        td = document.createElement("th")
        strHtml1 = "<button id=\"ugdelbut_" + ugrps[i].id + "\" onclick=\"this.disabled=\'disabled\'\" class=\"btn btn-default\">Удалить</button>";
        //alert(strHtml1);
        td.innerHTML = strHtml1;
        row.appendChild(td);
        tbody.appendChild(row);

    }
    $("#ugrps_lst").find("button").click(del_ugrp_but_listenner);
}

function getUsers() {
    $.ajax({
        type: 'GET',
        dataType: 'json',
        cache: false,
        data: {
            group: $('#ugrp_select').val()
        },
        url: 'LessUsrsSrvlt',
        success: function (data) {
            if (!data.users) {
                alert('Error: uncorrect value!');
            } else {
                var table = document.getElementById("all_users");
                for (var i = table.rows.length - 1; i > 0; i--) {
                    table.deleteRow(i);
                }
                updt_usrs_list(data.users);
            }
        }
    });
}

function getUGroup() {
    var x = document.getElementById("u_grp");
    while (x.length > 0) {
        x.remove(x.length - 1);
    }
    x = document.getElementById("ugrp_select");
    while (x.length > 1) {
        x.remove(x.length - 1);
    }

    $.getJSON("LessUGrpsSrvlt", function (result) {
        q = result;
        updt_ugrps_list(result.groups);
        for (var i = 0; i < result.groups.length; i++) {

            var tbody = document.getElementById("ugrp_select");
            var opt = document.createElement("option");
            // create table cell 1
            strHtml1 = result.groups[i].name;
            opt.innerHTML = strHtml1;
            tbody.appendChild(opt);

            var tbody = document.getElementById("u_grp");
            var opt = document.createElement("option");
            // create table cell 1
            strHtml1 = result.groups[i].name;
            opt.innerHTML = strHtml1;
            tbody.appendChild(opt);

        }
    });
}

$(document).ready(function () {
    $("#users_list").find("button").click(del_usr_but_listenner);
    $("#add_user").find("button").click(set_new_user_lisnr);

    $("#add_new_ugrpt").find("button").click(set_new_ugrp_lisnr);
    //  $("#qgrps_lst").find("button").click(del_qgrp_but_listenner);
    $('#ugrp_select').change(getUsers);

});
