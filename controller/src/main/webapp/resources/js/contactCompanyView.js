function contactWhenChange() {
    document.getElementById('groupContactCalendar').hidden =
        document.getElementById('contactWhen').selectedIndex !== 6;
}

function contactWhenModeClick(mode) {
    if (mode) {
        document.getElementById('contactWhenChangeBtn').setAttribute('class', 'btn btn-primary active');
        document.getElementById('contactWhenCreateBtn').setAttribute('class', 'btn btn-default');
    } else {
        document.getElementById('contactWhenCreateBtn').setAttribute('class', 'btn btn-primary active');
        document.getElementById('contactWhenChangeBtn').setAttribute('class', 'btn btn-default');
    }
}