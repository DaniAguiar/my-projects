const usuarios = [{
  nome: 'Diego',
  idade: 23,
  empresa: 'Rocketseat'
}, {
  nome: 'Gabriel',
  idade: 15,
  empresa: 'Rocketseat'
}, {
  nome: 'Lucas',
  idade: 30,
  empresa: 'Facebook'
}]; // Utilizando map()

const idades = usuarios.map(usuarios => usuarios.idade);
console.log(idades); // Utilizando filter()

const empregados = usuarios.filter(usuarios => usuarios.idade >= 18 && usuarios.empresa === 'Rocketseat');
console.log(empregados); // Utilizando find()

const verificador = usuarios.find(usuarios => usuarios.empresa === 'Facebook');
console.log(verificador); // Unindo Operações...

const newUserAges = [{
  nome: 'Diego',
  idade: 23 * 2,
  empresa: 'Rocketseat'
}, {
  nome: 'Gabriel',
  idade: 15 * 2,
  empresa: 'Rocketseat'
}, {
  nome: 'Lucas',
  idade: 30 * 2,
  empresa: 'Facebook'
}];
console.log(newUserAges.filter(usuarios => usuarios.idade <= 50));
