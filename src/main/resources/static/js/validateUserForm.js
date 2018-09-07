
$(document).ready( function () {
     $( '#validateFormRegister' ).validate( {
        rules: {
            fName: "required",
            lName: "required",
            password: {
                required: true,
                minlength: 5
            },
            confirm_password: {
                required: true,
                minlength: 5,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true,
                remote: {url: basicUrl + "/register/uniqueEmail",
                            dataType:'json',
                            data:{
                                id: function(){return $("#idNumber").val()}
                            },
                            contentType: 'application/json'
                        }                }
        },
        messages: {
            // fName: "Please enter your firstname",
            fName: form_register_error_firstname_requared,
            // lName: "Please enter your lastname",
            lName: form_register_error_lastname_requared,
            password: {
                required: form_register_error_password,
                minlength: form_register_error_password_length
            },
            confirm_password: {
                required: form_register_error_password_confirm_requared,
                minlength: form_register_error_password_confirm_length,
                equalTo: form_register_error_password_confirm_equals
            },
            email:{
              email: form_register_error_email_correct,
              required: form_register_error_email,
              remote: form_register_error_email_dublicate
            }
        },
        errorElement: "em",
        errorPlacement: function ( error, element ) {
            // Add the `help-block` class to the error element
            error.addClass( "help-block" );

            // Add `has-feedback` class to the parent div.form-group
            // in order to add icons to inputs
            element.parents( ".form-group" ).addClass( "has-feedback" );
            error.insertAfter( element );

            // Add the span element, if doesn't exists, and apply the icon classes to it.
            if ( !element.next( "span" )[ 0 ] ) {
                $( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element );
            }
        },
        success: function ( label, element ) {
            // Add the span element, if doesn't exists, and apply the icon classes to it.
            if ( !$( element ).next( "span" )[ 0 ] ) {
                $( "<span class='glyphicon glyphicon-ok form-control-feedback'></span>" ).insertAfter( $( element ) );
            }
        },
        highlight: function ( element, errorClass, validClass ) {
                            $( element ).parents( ".form-group" ).addClass( "has-error" ).removeClass( "has-success" );
                            $( element ).next( "span" ).addClass( "glyphicon-remove" ).removeClass( "glyphicon-ok" );
                        },
        unhighlight: function ( element, errorClass, validClass ) {
                            $( element ).parents( ".form-group" ).addClass( "has-success" ).removeClass( "has-error" );
                            $( element ).next( "span" ).addClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" );
                        },
        submitHandler: function () {
            saveOrEditUser();
         }
    });

    $( '#validateFormLogIn' ).validate( {
            rules: {
                passwordLogIn: {
                    required: true
                },
                login: {
                    required: true
                }
            },
            messages: {
                password: {
                    required: "Please provide a password"
                }
            },
            errorElement: "em",
            errorPlacement: function ( error, element ) {
                // Add the `help-block` class to the error element
                error.addClass( "help-block" );

                // Add `has-feedback` class to the parent div.form-group
                // in order to add icons to inputs
                element.parents( ".form-group" ).addClass( "has-feedback" );
                error.insertAfter( element );

                // Add the span element, if doesn't exists, and apply the icon classes to it.
                if ( !element.next( "span" )[ 0 ] ) {
                    $( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element );
                }
            },
            success: function ( label, element ) {
                // Add the span element, if doesn't exists, and apply the icon classes to it.
                if ( !$( element ).next( "span" )[ 0 ] ) {
                    $( "<span class='glyphicon glyphicon-ok form-control-feedback'></span>" ).insertAfter( $( element ) );
                }
            },
            highlight: function ( element, errorClass, validClass ) {
                    $( element ).parents( ".form-group" ).addClass( "has-error" ).removeClass( "has-success" );
                    $( element ).next( "span" ).addClass( "glyphicon-remove" ).removeClass( "glyphicon-ok" );
            },
            unhighlight: function ( element, errorClass, validClass ) {
                    $( element ).parents( ".form-group" ).addClass( "has-success" ).removeClass( "has-error" );
                    $( element ).next( "span" ).addClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" );
            },
            submitHandler: function () {
                   signIn();
            }
        });
});

