var basicUrl = 'http://localhost:8080/user/ticket';

$('#editOrCreate').on('shown.bs.modal', function (e) {
    resetValidation();
})

function saveOrEditCar() {
    $.ajax({
        'type': 'POST',
        'url': basicUrl + "/save",
        'contentType': 'application/json',
        'data': JSON.stringify({
            id: $('#idNumber').val(),
            number: $('#carNumber').val(),
            carType: $('#carType').val(),
            model:$('#carName').val(),
            producer:$('#carProducer').val(),
            description:$('#carDescription').val()
            }),
        'dataType': 'json',
        'success': function(data, status){
            redirect(basicUrl);
        },
        'error': function (xhr, status) {
            alert(xhr.responseJSON.message);
        }
     });
}

function redirect(url){
    $(document).ready( function() {$( location ).attr("href", url);});
}