var listElement = document.querySelector('#app ul');
var inputElement = document.querySelector('#app input');
var buttonElement = document.querySelector('#app button');

var repositories = [];

function myPromise(userRepository) {
    return new Promise(function (resolve, reject) {
        var xhr = new XMLHttpRequest();

        xhr.open('GET', 'https://api.github.com/users/' + userRepository + '/repos');
        xhr.send(null);

        xhr.onreadystatechange = function () {
         if(xhr.readyState === 4) {
                if (xhr.status === 200) {
                    resolve(JSON.parse(xhr.responseText));
                    repositories = JSON.parse(xhr.responseText);

                    renderRepo();

                } else {
                    if (xhr.status === 404) {
                        reject('Usuário inexistente!');
                    }
                    else {
                        reject('Erro na requisição');
                    }

                    userNotFound();
                }
            }
        }
    });
}

myPromise()
    .then(function () {
    })
    .catch(function (error) {
        console.warn(error);
    });

function renderRepo() {

    for (repo of repositories) {
        if(repo.name === 'undefined.github.io'){
            searchRepository();
        }
        else{
            var repoElement = document.createElement('li');
            var repoText = document.createTextNode(repo.name);
            repoElement.appendChild(repoText);
            listElement.appendChild(repoElement);
        }
    }
}

function searchRepository() {
    var repoText = inputElement.value;
    listElement.innerHTML = '';
    myPromise(repoText);
}

function userNotFound() {
    var notFoundElement = document.createElement('h2');
    var notFoundText = document.createTextNode('Informe um usuário válido!');
    notFoundElement.appendChild(notFoundText);
    listElement.appendChild(notFoundElement);
}

buttonElement.onclick = searchRepository;