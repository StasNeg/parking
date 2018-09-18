var carValidator;
$(document).ready( function () {
     console.log(carsValue);
    var table = $('#carTable').DataTable(
     {
           data:carsValue,
            "columns": [
                        { "data": "id",
                          "visible": false
                         },

                        { title: titNumber,
                        "data": "number" },

                        {title: titProduser,
                        "data": "producer" },
                        {title: titType,
                        "data": "carType" },

                        {title: titModel,
                        "data": "model" },

                        {title: titDescription,
                         "data": "description" },

                        {title: titEdit,
                             "targets": -1,
                             "data": null,
                             "defaultContent": '<button type="button" name = "btnEdit" class="btn btn-primary btn-xs" data-title="Edit" '+
                                                'data-toggle="modal" data-target="#editOrCreate"> ' +
                                                '<span class="glyphicon glyphicon-pencil"></span> ' +
                                                '</button>'
                        },
                        {title: titDelete,
                             "targets": -1,
                             "data": null,
                             "defaultContent": '<button type="button" name = "btnDelete" class="btn btn-danger btn-xs btnDelete" data-title="Delete"> ' +
                                                                            '<span class="glyphicon glyphicon-trash"></span></button>'
                        }

                    ],
                    "language": {
                                    "sEmptyTable":     "No data available in table",
                                        "sInfo":           "Showing _START_ to _END_ of _TOTAL_ entries",
                                        "sInfoEmpty":      "Showing 0 to 0 of 0 entries",
                                        "sInfoFiltered":   "(filtered from _MAX_ total entries)",
                                        "sInfoPostFix":    "",
                                        "sInfoThousands":  ",",
                                        "sLengthMenu":     "Show _MENU_ entries",
                                        "sLoadingRecords": "Loading...",
                                        "sProcessing":     "Processing...",
                                        "sSearch":         "Search:",
                                        "sZeroRecords":    "No matching records found",
                                        "oPaginate": {
                                            "sFirst":    "First",
                                            "sLast":     "Last",
                                            "sNext":     "Next",
                                            "sPrevious": "Previous"
                                        },
                                        "oAria": {
                                            "sSortAscending":  ": activate to sort column ascending",
                                            "sSortDescending": ": activate to sort column descending"
                                        }
                                }

         }
     );
     $('#carTable tbody').on( 'click', '[name= "btnEdit"]', function () {
         var data = table.row( $(this).parents('tr') ).data();
         edit(data.id);
         } );
     $(".btnAdd").on('click',function() {
         console.log("Add new");
         $("#editOrCreate select").each(function(){
             $(this).val("0").change();
         });
         $('#idNumber').val("");
         $('#carNumber').val("");
         $('#carDescription').val("");
     });
     carValidator = $( '#validateFormSaveOrEdit' ).validate( {
        rules: {
            carNumber: {
                required: true,
                remote: {
                    url: basicUrl + "/uniqueNumber",
                    dataType:'json',
                    data:{
                        id: function(){return $("#idNumber").val()}
                    },
                    contentType: 'application/json'
                }
            },
            carType: {selectTypeNotEmpty:"0"},
            carProducer: {selectTypeNotEmpty:"0"},
            carName: {selectTypeNotEmpty:"0"}
        },
        messages: {
            carNumber:{

              required: "Requared number",
              remote: "notUnique car Number"
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
            saveOrEditCar();
         }
    });
});

$.validator.addMethod("selectTypeNotEmpty", function(value, element,arg){
  return arg !== value;
 }, "Value must not be empty arg.");

