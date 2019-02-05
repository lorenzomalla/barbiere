var startDateBooking;
var endDateBooking;
var href = $(location).attr('href');
var stringa = "Il tuo costo sarà di €10";
// Ottieni l'oggetto per chiamare il backend
function getBookingObject(startDateBooking, endDateBooking, title, value) {
    var booking = {
        "startDateBooking": startDateBooking,
        "endDateBooking": endDateBooking,
        "title": title + " " + value,
    }
    return booking;
}

function getURLParameter(url, name) {
    return (RegExp(name + '=' + '(.+?)(&|$)').exec(url) || [, null])[1];
}

$(document).ready(function() {
    $(".closeon").on('click', function() {
        $(this).attr("data-id");
    });
    // inizializza il calendario
    $('#calendar').fullCalendar({
        themeSystem: 'bootstrap3',
        timeFormat: 'H:mm',
        contentHeight: 600, // Adatta automaticamente il calendario a seconda del dispositivo
        defaultView: 'agendaDay',
        events: function(start, end, timezone, callback) {
            $.ajax({
                url: '/rest/booking/getBookingOfWeek',
                type: 'GET',
                contentType: 'application/json',
                cache: true,
                success: function(data) {
                    var title = getURLParameter(href, 'user');
                    var events = [];
                    $.each(data, function(index, data) {
                        events.push({
                            id: data['id'],
                            title: data['titoloPrenotazione'],
                            start: moment.utc(data['startDateBooking']).zone(new Date().getTimezoneOffset()).format(), // incrementa
                            end: moment.utc(data['endDateBooking']).zone(new Date().getTimezoneOffset()).format(),
                            //                            color: data['titoloPrenotazione'] == title ? 'red' : '#1f8c22', //Cambia il colore per la prenotazione dell'utente che è in sessionse
                        });
                    });
                    callback(events); // populate events
                    //                    addDeleteButton();
                },
                error: function(data) {
                    console.log(data);
                }
            });
        },
        navLinks: true, // can click day/week names to navigate views
        selectable: true, // can click on select
        selectHelper: true,
        select: function(start, end) {
            //        	var poputConferma = prompt('C');
            var title = getURLParameter(href, 'user');
            var eventData;
            //            var startDate = moment.utc(start).zone(new Date().getTimezoneOffset()).format();
            //            var endDate = moment.utc(end).zone(new Date().getTimezoneOffset()).format()
            $.confirm({
                title: 'Conferma',
                content: 'Con chi vuoi prenotare?',
                buttons: {
                    Castrese: function() {
                        var value = "Castrese";
                        var booking = getBookingObject(start, end, title, value);
                        eventData = {
                            title: title + " " + value,
                            start: start,
                            end: end
                        };
                        confirmBookingUser(booking, eventData);
                    },
                    Nicola: function() {
                        var value = "Nicola";
                        var booking = getBookingObject(start, end, title, value);
                        eventData = {
                            title: title + " " + value,
                            start: start,
                            end: end
                        };
                        confirmBookingUser(booking, eventData);
                    },
                    Annulla: function() {

                    },
                }
            });
        },
        editable: false,
        eventLimit: true,
    });
});

function addDeleteButton(id) {
    var fc = $('.fc-bg');
    for (i = 2; i < fc.length; i++) {
        $(fc[i]).append("<button class='btn btn-default btn-sm closeon' type='submit' data-id='" + id + "'>" +
            "<span class='glyphicon glyphicon-trash'></span>" +
            "</button>");
        $('.closeon').css('float', 'right');
        $('.closeon').css('margin', 'auto');
        $('.closeon').css('margin-right', 'auto');
        return;
    }
}

function confirmBookingUser(booking, eventData) {
    $.ajax({
        url: '/rest/booking/saveUserBookingInfo',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(booking),
        cache: 'false',
        success: function(data) {
            console.log("Data: " + data);
            $('#calendar').fullCalendar('renderEvent', eventData, true);
            addDeleteButton(data.id);
        },
        error: function(data) {
            if (data) {
                console.log(data.responseJSON.reason);
            }
        }
    });
    $.alert('Confermato!');
}