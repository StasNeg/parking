var basicUrl = 'http://localhost:8080/user/ticket';
var map;
var ui;
var behavior;
var group;
$(document).ready( function () {
    var table = $('#ticketTable').DataTable(
     {
           "ajax": {
                       "url": basicUrl+'/all',
                       "dataSrc": ""
                   },
            "columns": [
                        {"data": "id",
                         "visible":false },
                        {title: "Car Model",
                        "data": null,
                        render: function ( data, type, row ) {
                            return data.car.producer + ' ' + data.car.model + ' ' + data.car.number;
                            }
                         },
                        {title: "Parking Place",
                        "data": null,
                        "render": function ( data, type, row ) {
                                return data.city +', '+ data.street +' '+ data.streetNumber;
                            }
                        },

                        {title: "Data Time Start",
                        "data": "dateTimeStart" },

                        {title: "Data Time Start",
                         "data": "dateTimeEnd" }
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




    var platform = new H.service.Platform({
       'app_id': 'IMKvUNuOHPm9m6STg3ax',
       'app_code': 'nFMkNnKiTHe_BkWaKjI_JA'
     });
    var maptypes = platform.createDefaultLayers();
    map = new H.Map(
      document.getElementById('map'),
      maptypes.normal.map,
      {
        zoom: 13,
        center: { lng:  35.018446, lat:48.474591 }
      });
    ui = H.ui.UI.createDefault(map, maptypes);
    behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));
    group = new H.map.Group();
    map.addObject(group);
     $( '#validateFormSaveOrEdit' ).validate( {
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

