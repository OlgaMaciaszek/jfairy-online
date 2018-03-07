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
    });
    // Select all
    $("A[href='#select_all']").click(function () {
        $("#" + $(this).attr('rel') + " INPUT[type='checkbox']").attr('checked', true);
        return false;
    });
    // Select none
    $("A[href='#select_none']").click(function () {
        $("#" + $(this).attr('rel') + " INPUT[type='checkbox']").attr('checked', false);
        return false;
    });
    $("A[href='#show_advanced']").click(function () {
        var reference = $(this).attr('rel');
        $("#" + reference).show();
        $("#" + reference + "Switch").hide();
    });
    $("A[href='#hide_advanced']").click(function () {
        var reference = $(this).attr('rel');
        $("#" + reference).hide();
        $("#" + reference + "Switch").show();
    });
});