var carValidator;
$(document).ready( function () {
     carValidator = $( '#validateFormSaveOrEdit' ).validate( {
        rules: {
            carNumber: "required",
            carType: {selectTypeNotEmpty:"0"},
            carProducer: {selectTypeNotEmpty:"0"},
            carName: {selectTypeNotEmpty:"0"}
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
            saveOrEditCar();
         }
    });
});

$.validator.addMethod("selectTypeNotEmpty", function(value, element,arg){
  return arg !== value;
 }, "Value must not be empty arg.");

