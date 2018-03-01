function invalidInput() {
    function extracted() {
        return ($('#contributorName').val() || $('#contributorSurname').val() || $('#contributorEmail').val())
            && !$('#dataAgreement').is(':checked');
    }

    if (extracted()) {
        $('#submit').prop('disabled', true);
    } else {
        $('#submit').prop('disabled', false);
    }
}

$(document).ready(function () {
    $('#contributorName').on("change paste keyup", function () {
        invalidInput();
    });
    $('#contributorSurname').on("change paste keyup", function () {
        invalidInput();
    });
    $('#contributorEmail').on("change paste keyup", function () {
        invalidInput();
    });
    $('#dataAgreement').on("change", function () {
        invalidInput();
    });
});