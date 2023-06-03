const navbar = document.getElementById("navbar");
const topLogo = document.getElementById("top-logo-navbar");

var navbarOffsetTop = navbar.offsetTop;

function fixNavbar() {
    if (window.scrollY >= navbarOffsetTop) {
        topLogo.src = "./media/esn_padova_logo_small_color_2.png";
        topLogo.classList.remove("top-logo-navbar");
        topLogo.classList.add("top-logo-navbar-small");
    } else {
        topLogo.src = "./media/esn_padova_logo_full_color_2.png";
        topLogo.classList.remove("top-logo-navbar-small");
        topLogo.classList.add("top-logo-navbar");
    }
}

window.addEventListener("scroll", fixNavbar);