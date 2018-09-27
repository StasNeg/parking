var basicUrl = 'http://localhost:8080/user/ticket';
var map;
var ui;
var behavior;
var group;
var cities;
var cars;

$(document).ready(function () {
    console.log(dataTableI18n.locale, locale);
    var table = $('#ticketTable').DataTable(
        {
            "ajax": {
                "url": basicUrl + '/all',
                "dataSrc": ""
            },
            "columns": [
                {
                    "data": "id",
                    "visible": false
                },
                {
                    title: "Car Model",
                    "data": null,
                    render: function (data, type, row) {
                        return data.car.producer + ' ' + data.car.model + ' ' + data.car.number;
                    }
                },
                {
                    title: "Parking Place",
                    "data": null,
                    "render": function (data, type, row) {
                        return data.city + ', ' + data.street + ' ' + data.streetNumber;
                    }
                },

                {
                    title: "Data Time Start",
                    "data": "dateTimeStart"
                },

                {
                    title: "Data Time Start",
                    "data": "dateTimeEnd"
                }
            ],
            "language": {
                        "url": dataTableI18n[locale]
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
            maptypes.normal.map
        );
    ui = H.ui.UI.createDefault(map, maptypes);
    behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));
    group = new H.map.Group();
    map.addObject(group);
    $('#validateFormSaveOrEdit').validate({
        rules: {
            carNumber: {
                required: true,
                remote: {
                    url: basicUrl + "/uniqueNumber",
                    dataType: 'json',
                    data: {
                        id: function () {
                            return $("#idNumber").val()
                        }
                    },
                    contentType: 'application/json'
                }
            },
            carType: {selectTypeNotEmpty: "0"},
            carProducer: {selectTypeNotEmpty: "0"},
            carName: {selectTypeNotEmpty: "0"}
        },
        messages: {
            carNumber: {

                required: "Requared number",
                remote: "notUnique car Number"
            }
        },
        errorElement: "em",
        errorPlacement: function (error, element) {
            // Add the `help-block` class to the error element
            error.addClass("help-block");

            // Add `has-feedback` class to the parent div.form-group
            // in order to add icons to inputs
            element.parents(".form-group").addClass("has-feedback");
            error.insertAfter(element);

            // Add the span element, if doesn't exists, and apply the icon classes to it.
            if (!element.next("span")[0]) {
                $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(element);
            }
        },
        success: function (label, element) {
            // Add the span element, if doesn't exists, and apply the icon classes to it.
            if (!$(element).next("span")[0]) {
                $("<span class='glyphicon glyphicon-ok form-control-feedback'></span>").insertAfter($(element));
            }
        },
        highlight: function (element, errorClass, validClass) {
            $(element).parents(".form-group").addClass("has-error").removeClass("has-success");
            $(element).next("span").addClass("glyphicon-remove").removeClass("glyphicon-ok");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parents(".form-group").addClass("has-success").removeClass("has-error");
            $(element).next("span").addClass("glyphicon-ok").removeClass("glyphicon-remove");
        },
        submitHandler: function () {
            saveOrEditCar();
        }
    });

    $.ajax({
            'type': 'get',
            'url': basicUrl + "/cities",
            'contentType': 'application/json',
            'success': function (data, status) {
                cities = data.city;
                cars = data.cars;
                $.each(data.city, function (iter, city) {
                    $('#city').append($("<option></option>").attr("value", city.id)
                        .text(city.name));
                });
                $.each(data.cars, function (iter, car) {
                    $('#car').append($("<option></option>").attr("value", car.id)
                        .text(car.number + " " + car.producer + ", " + car.model));
                });
            },
            'error': function (xhr, status) {
                alert(xhr.responseJSON.message);
            }
        });

});





$.validator.addMethod("selectTypeNotEmpty", function (value, element, arg) {
    return arg !== value;
}, "Value must not be empty arg.");
