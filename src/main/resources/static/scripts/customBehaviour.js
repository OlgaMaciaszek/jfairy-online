function copyContent() {
    var text = $('#copyContent')[0];
    var selection = window.getSelection();
    var range = document.createRange();
    range.selectNodeContents(text);
    selection.removeAllRanges();
    selection.addRange(range);
    document.execCommand('copy');
}
$(document).ready(function () {
    $('td').click(function () {
        $('#copyContent').text($.trim($(this).text()));
        copyContent();
    });

    $('td').dblclick(function () {
        $('#copyContent').text($.trim($(this).parent().text()));
        copyContent();
    });
});