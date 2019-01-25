var startDateBooking;
var endDateBooking;

function getBookingObject(startDateBooking, endDateBooking, title) {
    var booking = {
        "startDateBooking": startDateBooking,
        "endDateBooking": endDateBooking,
        "title": title
    }
    return booking;
}
$(document).ready(function() {
    $('#calendar').fullCalendar({
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
            var booking = getBookingObject(start, end, title);
            if (title) {
                eventData = {
                    title: title,
                    start: start,
                    end: end
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