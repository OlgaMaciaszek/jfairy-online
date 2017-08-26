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
    var cells = $('td');
    cells.click(function () {
        $('#copyContent').text($.trim($(this).text()));
        copyContent();
    });
    cells.dblclick(function () {
        $('#copyContent').text($.trim($(this).parent().text()));
        copyContent();
    });
    $('.panel,.panel-body').click(function () {
        $('#copyContent').text($.trim($(this).text()));
        copyContent();
    });
    $('#reload').click(function () {
        window.location.reload(true);
    })
});