const toggleRPassword = document.getElementById('toggleRPassword');
const rpassword = document.getElementById('rpassword');

toggleRPassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type = rpassword.getAttribute('type') === 'password' ? 'text' : 'password';
    rpassword.setAttribute('type', type);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye');
    this.classList.toggle('fa-eye-slash');
});