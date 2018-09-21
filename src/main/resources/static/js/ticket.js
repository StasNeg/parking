
$('#editOrCreate').on('shown.bs.modal', function (e) {
    resetValidation();
    group = new H.map.Group();
    $.ajax({
                            'type': 'get',
                            'url': basicUrl + "/countryAndCity",
                            'contentType': 'application/json',
                            'success': function(data, status){
                                $.each(data.countryAndCity.Ukraine, function(iter, city){
                                    $('#city').append($("<option></option>").attr("value",city)
                                            .text(city));
                                    });
                                $.each(data.cars, function(iter, car){
                                    $('#car').append($("<option></option>").attr("value",car.id)
                                        .text(car.number + " " + car.producer + ", " + car.model));
                                      });
                            },
                            'error': function (xhr, status) {
                                alert(xhr.responseJSON.message);
                            }
                     });


})

function saveOrEditTicket() {

}

function redirect(url){
    $(document).ready( function() {$( location ).attr("href", url);});
}

function addMarkerToGroup(group, coordinate, html) {
  var marker = new H.map.Marker(coordinate);
  // add custom data to the marker
  marker.setData(html);
  group.addObject(marker);
}

/**
 * Add two markers showing the position of Liverpool and Manchester City football clubs.
 * Clicking on a marker opens an infobubble which holds HTML content related to the marker.
 * @param  {H.Map} map      A HERE Map instance within the application
 */
function addInfoBubble(map, coordinates, html) {

  // add 'tap' event listener, that opens info bubble, to the group
  group.addEventListener('tap', function (evt) {
    // event target is the marker itself, group is a parent event target
    // for all objects that it contains
    var bubble =  new H.ui.InfoBubble(evt.target.getPosition(), {
      // read custom data
      content: evt.target.getData()
    });
    // show info bubble
    ui.addBubble(bubble);
  }, false);

  $.each(coordinates, function(key, value){
      addMarkerToGroup(group,{lat:value.lat, lng:value.lng}, html[key]);
    });
}

$('#googleMap').on('shown.bs.modal', function (e) {
    $.ajax({
            'type': 'get',
            'url': basicUrl + "/placeByCity",
            'contentType': 'application/json',
            'data':{
                city: $("#city").val()
            },
            'success': function(data, status){
                showMap(data);
            },
            'error': function (xhr, status) {
                alert(xhr.responseJSON.message);
            }
     });
})

function showMap(places){
    var coord = [];
    var text = [];

    $.each(places, function(key, value){
        coord.push({lat:value.lat, lng:value.lng});
        text.push('<button type="submit" class="btn btn-default btnMapSubmit" data-dismiss="modal" id="' + value.id+'">'
        + value.street+ ', ' + value.streetNumber +
        '</button>');
    });
    console.log(coord, map);
    addInfoBubble(map,coord,text);
    map.getViewPort().resize();
}
$('.btnMapSubmit').on('click', function (e){
    console.log(this.text);
    $("#address").val(this.text);
})