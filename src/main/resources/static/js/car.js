var basicUrl = 'http://localhost:8080/user/car';
$(".btnDelete").click(function() {
        console.log("Delete " +this.name);
        $.ajax({
                        'type': 'DELETE',
                        'url': basicUrl + "/delete?id="+this.name,
                        'contentType': 'application/json',
                        'success': function(data, status){redirect(basicUrl);},
                        'error': function (xhr, status) {
                            alert(xhr.responseJSON.message);
                        }
                 });
});

$(".btnAdd").click(function() {
        console.log("Add new");
        console.log(carsValue);
});

$('#editOrCreate').on('shown.bs.modal', function (e) {
      resetValidation();
})


var resetValidation = function(){
        $("select").each(function(){
            $(this).val("0").change();
        });
        $( "span" ).each(function(){
            $(this).removeClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" );
            $(this).parents(".form-group" ).removeClass( "has-success" ).removeClass( "has-error" );
        });
        $("em").each(function(){$(this).hide()});
//        $(".error").removeClass("error");
    }

$(".btnCancel").click(function() {
})
$(".btnEdit").click(function() {
        console.log("Edit " +this.name);
        $(".modal-title").text("Edit Current Car");
        $.ajax({
                        'type': 'GET',
                        'url': basicUrl ,
                        'data': {
                                id: this.name
                            },
                        'success': function(car, status){
                                $('#idNumber').val(car.id);
                                $('#carNumber').val(car.number);
                                $('#description').val(car.description);
                            }}
                        );
});

function saveOrEditUser() {
        $.ajax({
                'type': 'POST',
                'url': basicUrl + "/save",
                'contentType': 'application/json',
                'data': JSON.stringify({
                    id: $('#idNumber').val(),
                    number: $('#carNumber').val(),
                    carType: $('#carType').val()}),
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