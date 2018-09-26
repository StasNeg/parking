$('#editOrCreate').on('shown.bs.modal', function (e) {
    resetValidation();
})

function saveOrEditTicket() {

}

function redirect(url) {
    $(document).ready(function () {
        $(location).attr("href", url);
    });
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
        var bubble = new H.ui.InfoBubble(evt.target.getPosition(), {
            // read custom data
            content: evt.target.getData()
        });
        removeBubbles();
        // show info bubble
        ui.addBubble(bubble);
    }, false);

    $.each(coordinates, function (key, value) {
        addMarkerToGroup(group, {lat: value.lat, lng: value.lng}, html[key]);
    });
}

$('#googleMap').on('shown.bs.modal', function (e) {
    group.getObjects().forEach( function (object) {
        group.removeObject(object);
    });
    removeBubbles();
    $.ajax({
        'type': 'get',
        'url': basicUrl + "/placeByCity",
        'contentType': 'application/json',
        'data': {
            city: $("#city").val()
        },
        'success': function (data, status) {
            showMap(data);
        },
        'error': function (xhr, status) {
            alert(xhr.responseJSON.message);
        }
    });
})

function showMap(places) {
    var coord = [];
    var text = [];
    $.each(places, function (key, value) {
        coord.push({lat: value.lat, lng: value.lng});
        text.push('<button type="button" class="btn btn-default btnMapSubmit" id="' + value.id + '">'
            + value.name + ', ' + value.streetNumber +
            '</button>');
    });
    var currentCity = cities.filter(function(item){
        return item.id===parseInt($('#city').val());
     });
    map.setCenter({lat:currentCity[0].lat, lng:currentCity[0].lng});
    map.setZoom(12);
    map.getViewPort().resize();
    addInfoBubble(map, coord, text);
}

$(document).on('click','.btnMapSubmit', function (e) {
//    console.log(e.target.innerText);
    $("#googleMap").modal('hide');
    $("#address").val(e.target.innerText);
})

function removeBubbles(){
    ui.getBubbles().forEach(function (bubble) {
        ui.removeBubble(bubble);
    });
}