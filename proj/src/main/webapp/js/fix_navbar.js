const navbar = document.getElementById("navbar");
const topLogo = document.getElementById("top-logo-navbar");
const navReplacement = document.getElementById("navbar-space-replacement");

var navbarOffsetTop = navbar.offsetTop;

function fixNavbar() {
    if (window.scrollY >= navbarOffsetTop) {
        navbar.classList.add("fix-position");
        navReplacement.classList.add("navbar-space-replacement");
        topLogo.src = "./media/esn_padova_logo_small_color_2.png";
        topLogo.classList.remove("top-logo-navbar");
        topLogo.classList.add("top-logo-navbar-small");
    } else {
        navReplacement.classList.remove("navbar-space-replacement");
        navbar.classList.remove("fix-position");
        topLogo.src = "./media/esn_padova_logo_full_color_2.png";
        topLogo.classList.remove("top-logo-navbar-small");
        topLogo.classList.add("top-logo-navbar");
    }
}

window.addEventListener("scroll", fixNavbar);