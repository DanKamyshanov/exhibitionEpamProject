function changeLanguage(){
    let language = document.getElementById("language").value;
    let request = new XMLHttpRequest();
    request.open("GET", "${requestScope}?language=" + language, true);

    request.onreadystatechange = function (){
        location.reload();
    }
    request.send();
}