function submitHospitalName(form) {
    form.method = "get";
    form.action = "/hospitals/search/hospitalname";
    form.submit();
    return true;
}

function submitRoadNameAddress(form){
    form.method = "get";
    form.action = "/hospitals/search/roadnameaddress";
    form.submit();
    return true;
}