var listElement = document.querySelector('#app ul');
var inputElement = document.querySelector('#app input');
var buttonElement = document.querySelector('#app button');

var toDos = JSON.parse(localStorage.getItem('list_toDos')) || [];

function renderToDo() {
    listElement.innerHTML = '';

    for (toDo of toDos) {
        var toDoElement = document.createElement('li');
        var toDoText = document.createTextNode(toDo);

        var linkElement = document.createElement('a');

        linkElement.setAttribute('href', '#');

        var pos = toDos.indexOf(toDo);

        linkElement.setAttribute('onclick', 'removeToDo( ' + pos + ')');

        var linkText = document.createTextNode('Excluir');

        linkElement.appendChild(linkText);

        toDoElement.appendChild(toDoText);
        toDoElement.appendChild(linkElement);

        listElement.appendChild(toDoElement);
    }
}

renderToDo();

function addToDo() {
    var toDoText = inputElement.value;

    toDos.push(toDoText);
    inputElement.value = '';
    renderToDo();
    saveToStorage();
}

function removeToDo(position) {
    toDos.splice(position, 1);
    renderToDo();
    saveToStorage();
}

buttonElement.onclick = addToDo;

function saveToStorage() {
    localStorage.setItem('list_toDos', JSON.stringify(toDos));
}