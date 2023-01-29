function invalidLogin(){
    const login = document.getElementsByName('login');
    const error = document.getElementById('invalidLogin');
    if(login.length >= 0){
        error.style.display = 'none';
    }
}

function emailError(){
    const email = document.getElementsByName('email');
    const error = document.getElementById('emailError');
    if(email.length >= 0){
        error.style.display = 'none';
    }
}

function phoneError(){
    const phone = document.getElementsByName('phone_number');
    const error = document.getElementById('phoneError');
    if(phone.length >= 0){
        error.style.display = 'none';
    }
}

function verifyPassword(){
    const password = document.getElementById('password').value;
    const passwordConfirm = document.getElementById('passwordConfirm').value;
    const error = document.getElementById('invalidPassword');
    const button = document.getElementById('button');
    if(password === passwordConfirm || passwordConfirm === ''){
        error.style.display = 'none';
        button.disabled = false;
    } else{
        error.style.display = 'block';
        button.disabled = true;
    }
}


