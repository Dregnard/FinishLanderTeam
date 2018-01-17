/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = function () {


    $("#login").click(function () {
        loginUser();
    });
    $("#regis").click(function () {

        registerUser();
    });
    $("#butReg").click(function () {
        $("#modalRegistro").modal({backdrop: "static", keyboard: "false"});
    });
    $("#btnCancel").click(function () {
        $("#modalRegistro").modal('hide');
        $("#regName").val("");
        $("#regUser").val("");
        $("#regPwd").val("");
        $("#regPwd2").val("");
    });

};

function loginUser() {
    if (loginValid()) {
        var username = $("#userName").val();
        var password = $("#pass").val();
        var url = "Login";
        var emess = "Error 98907";
        $.ajax({
            method: "POST",
            url: url,
            data: {username: username, password: password},
            success: function (u) {


                if (u["mess"] === "La contraseña introducida es erronea") {
                    $("#pass").focus();
                    alert(u["mess"]);
                    return;
                }
                if (u["mess"] === "El usuario introducido no existe") {
                    $("#userName").focus();
                    alert(u["mess"]);
                    return;
                }
                //location.reload();
                guardarLS(username, password);
                document.open();
                document.write(u);
                document.close();

            },
            error: function (e) {
                if (e["responseJSON"] === undefined)
                    alert(emess);
                else
                    alert(e["responseJSON"]["error"]);
            }
        });
    }
}
function loginValid() {
    if ($("#userName").val() === "") {
        $("#userName").focus();
        alert("Por favor, introduce tu nombre de usuario");
        return false;
    }
    if ($("#pass").val() === "") {
        $("#pass").focus();
        alert("Por favor, introduce tu contraseña");
        return false;
    }
    return true;
}
function registerUser() {
    if (registerValid()) {
        var nombre = $("#user").val();
        var username = $("#RuserName").val();
        var password = $("#Rpass").val();
        var url = "Register";
        var emess = "Error desconocido";
        $.ajax({
            method: "POST",
            url: url,
            data: {nombre: nombre, username: username, password: password},
            success: function (u) {
                if (u["mess"] === "El nombre de usuario ya está en uso") {
                    $("#user").focus();
                } else {
                    $("#user").val("");
                    $("#userName").val("");
                    $("#pass").val("");
                    cerrarModal();
                }
                alert(u["mess"]);
            },
            error: function (e) {
                if (e["responseJSON"] === undefined)
                    alert(emess);
                else
                    alert(e["responseJSON"]["error"]);
            }
        });
    }
}

function registerValid() {
    if ($("#user").val() === "") {
        $("#user").focus();
        alert("Por favor, introduce tu nombre");
        return false;
    }
    if ($("#RuserName").val() === "") {
        $("#RuserName").focus();
        alert("Por favor, introduce tu nombre de usuario");
        return false;
    }
    if ($("#Rpass").val() === "") {
        $("#Rpass").focus();
        alert("Por favor, introduce una contraseña");
        return false;
    }

    return true;
}

function cerrarModal() {
    $("#modalRegistro").modal('hide');
}

function guardarLS(username, password) {
    sessionStorage.removeItem("_userN");
    sessionStorage.removeItem("_pass");

    sessionStorage.setItem("_userN", username);
    sessionStorage.setItem("_pass", password);
}


