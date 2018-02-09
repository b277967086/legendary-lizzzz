//notation: js file can only use this kind of comments
//since comments will cause error when use in webview.loadurl,
//comments will be remove by java use regexp
(function() {
    if (window.JavascriptBridge) {
        return;
    }

    function showMachine(){

        if(isAndroid){
            alert("true");
        }     else {
               alert("false");
        }
    }

    function isAndroid() {
        var ua = navigator.userAgent.toLowerCase();
        var isA = ua.indexOf("android") > -1;
        if (isA) {
            return true;
        }
        return false;
    }


})();