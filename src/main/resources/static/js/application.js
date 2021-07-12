$(document).ready(function(){
    $('#employee-info-profile-picture-browse').change(function(){
        showImageThumbnail(this);
    });
});

function showImageThumbnail(fileInput){
    file = fileInput.files[0];
    reader = new FileReader();
    reader.onload = function(e) {
        $('#employee-info-profile-picture').attr('src', e.target.result)
    }
    reader.readAsDataURL(file);
}