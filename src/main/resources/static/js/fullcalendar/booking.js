var startDateBooking;
var endDateBooking;
// Ottieni l'oggetto per chiamare il backend
function getBookingObject(startDateBooking, endDateBooking, title) {
    var booking = {
        "startDateBooking": startDateBooking,
        "endDateBooking": endDateBooking,
        "title": title
    }
    return booking;
}
$(document).ready(function() {
//	inizializza il calendario
    $('#calendar').fullCalendar({
    	themeSystem: 'bootstrap3',
    	timeFormat: 'H:mm',
    	contentHeight: 600, //Adatta automaticamente il calendario a seconda del dispositivo
        defaultView: 'agendaDay',
        events: function(start, end, timezone, callback) {
            $.ajax({
                url: '/rest/booking/getBookingOfWeek',
                type: 'GET',
                contentType: 'application/json',
                cache: 'false',
                success: function(data) {
                    var events = [];
                    $.each(data, function(index, data) {
                        events.push({
                            title: data['titoloPrenotazione'],
                            start: moment.utc(data['startDateBooking']).zone(new Date().getTimezoneOffset()).format(), //incrementa il timezone locale
                            end: moment.utc(data['endDateBooking']).zone(new Date().getTimezoneOffset()).format(),
                        });
                    });
                    callback(events); // populate events
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
            var title = prompt('Nome della prenotazione');
            var eventData;
            var startDate = moment.utc(start).zone(new Date().getTimezoneOffset()).format();
            var endDate = moment.utc(end).zone(new Date().getTimezoneOffset()).format()
            var booking = getBookingObject(startDate, endDate, title);
            if (title) {
                eventData = {
                    title: title,
                    start: startDate,
                    end: endDate
                };
                $.ajax({
                    url: '/rest/booking/saveUserBookingInfo',
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify(booking),
                    cache: 'false',
                    success: function(data) {
                        console.log("Data: " + data);
                        if (data != null) {
                            check = true;
                            $('.form').submit();
                        }
                    },
                    error: function(data) {
                        if (data) {
                            console.log(data.responseJSON.reason);
                        }
                    }
                });
                $('#calendar').fullCalendar('renderEvent', eventData, true);
//                $('#calendar').fullCalendar('refetchEvents'); // stick?
            }
            $('#calendar').fullCalendar('unselect');
        },
        editable: true,
        eventLimit: true, // allow "more" link when too many events
    });
});