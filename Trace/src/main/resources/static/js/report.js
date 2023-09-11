function openWindowPop(url, name, listNo) {
    var options = 'top=10, left=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no';
    
    var urlWithParam = url + '?list_no=' + encodeURIComponent(listNo);
    window.open(urlWithParam, name, options);
}

function submitFormAndClosePopup() {

    window.close();

    return false;
}