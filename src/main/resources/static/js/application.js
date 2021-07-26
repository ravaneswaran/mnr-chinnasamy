$(document).ready(function(){
    $('#user-info-profile-picture-browse').change(function(){
        showImageThumbnail(this);
    });
});

function showImageThumbnail(fileInput){
    file = fileInput.files[0];
    reader = new FileReader();
    reader.onload = function(e) {
        $('#user-info-profile-picture').attr('src', e.target.result)
    }
    reader.readAsDataURL(file);
}

function toggleCanAccessAdministrationApp(elementId){
    try {
        var isElementChecked = $("#"+elementId).is(':checked');
        var uri = "/ajax/administration-app/access/for-user-role/" + elementId +"?canAccessAdministrationApp=" + isElementChecked;
        $.ajax({
            type: "GET",
            contentType: "text/html",
            url: uri,
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log("SUCCESS : ", data);
                //var obj = jQuery.parseJSON('{"response":"1", "status":"success"}');
                var obj = jQuery.parseJSON(data);
                alert(obj.response);
            },
            error: function (e) {
                console.log("FAILURE : ", data);
                //var obj = jQuery.parseJSON("{'response':'1', 'status':'success'}");
                var obj = jQuery.parseJSON(data);
                alert(obj.response);
            }
        });
    } catch(err) {
        //console.log(err);
    }
}