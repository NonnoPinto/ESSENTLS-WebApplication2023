window.onload = function(){
    document.getElementById("loginForm").action = "javascript:;";
    document.getElementById("loginForm").method = "";
    document.getElementById("loginForm").onsubmit="loginRequest();";
    document.getElementById("js_enabled").value = "true";
}