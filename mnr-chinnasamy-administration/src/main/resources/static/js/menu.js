/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function toggleMenu() {
    if(document.getElementById("menu").style.display == "block"){
        document.getElementById("menu").style.display = "none";
    } else {
        document.getElementById("menu").style.display = "block";
    }
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
    if (!event.target.matches('.app-launcher-image')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if(openDropdown.style.display == "block"){
                openDropdown.style.display = "none";
            }
        }
    }
}