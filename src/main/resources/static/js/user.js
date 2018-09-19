var basicUrl = 'http://localhost:8080';
$(".btnDelete").click(function() {
        console.log("Delete " +this.name);
        $.ajax({
                        'type': 'DELETE',
                        'url': basicUrl + "/user/delete?id="+this.name,
                        'contentType': 'application/json',
                        'success': function(data, status){redirect(basicUrl+"/user/all");},
                        'error': function (xhr, status) {
                            alert(xhr.responseJSON.message);
                        }
                 });
});

$(".btnAdd").click(function() {
        console.log("Add new");

        $('#idNumber').val('');
        $('#email').val('');
        $('#lName').val('');
        $('#fName').val('');
        $('#password').val('');
        $('#confirm_password').val('');
});

$('#modalRegister').on('shown.bs.modal', function (e) {

      $('#idNumber').val('');
      $('#email').val('');
      $('#lName').val('');
      $('#fName').val('');
      $('#password').val('');
      $('#confirm_password').val('');
      resetValidation();
})
$('#modalLogIn').on('shown.bs.modal', function (e) {

  $('#passwordLogIn').val('');
  $('#login').val('');
  resetValidation();
})

$(".btnEdit").click(function() {
        console.log("Edit " +this.name);
        $(".modal-title").text("Edit user");
        $.ajax({
                        'type': 'GET',
                        'url': basicUrl + "/user/email",
                        'data': {
                                email: this.name
                            },
                        'success': function(user, status){
                                $('#idNumber').val(user.id);
                                $('#email').val(user.email);
                                $('#lName').val(user.lastName);
                                $('#fName').val(user.firstName);
                                $('#password').val(user.password);
                                $('#confirm_password').val(user.password);
                            }}
                        );
});

function saveOrEditUser() {
        $.ajax({
                'type': 'POST',
                'url': basicUrl + "/user/save",
                'contentType': 'application/json',
                'data': JSON.stringify({
                    id: $('#idNumber').val(),
                    password : $('#password').val(),
                    email: $('#email').val(),
                    lastName: $('#lName').val(),
                    firstName: $('#fName').val()}),
                'dataType': 'json',
                'success': function(data, status){
                    redirect(basicUrl+"/user/all");
                },
                'error': function (xhr, status) {
                    alert(xhr.responseJSON.message);
                }
         });
}

function signIn() {
        $.ajax({
                'type': 'POST',
                'url': basicUrl,
                'data': {
                    j_password : $('#passwordLogIn').val(),
                    j_username : $('#login').val()
                    },
                'dataType': 'json',
                'success': function(data, status){
                    if(data.success){
                        redirect(basicUrl+"/user/car");
                    }
                },
                'error': function (xhr, status, error) {
                   console.log(xhr.responseText);
                   var errorData = JSON.parse(xhr.responseText);
                   console.log("xhr: " + xhr.responseText + " errorData: " + errorData);
                   $("#usernamepassword-error").text(errorData.exception);
                   $("#usernamepassword-error").show();

                }
         });
}

function redirect(url){
            $(document).ready( function() {$( location ).attr("href", url);});
}