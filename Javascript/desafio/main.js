function checaIdade(idade){
   return new Promise(function(resolve, reject){
       
        setTimeout( function conditional(){
            if(idade > 18){
                resolve('Maior que 18');
            }
            else{
                reject('Menor de 18');
            }
        }, 2000);
    });
}

checaIdade(20)
    .then(function(response){
        console.log(response);
    })
    .catch(function(error){
        console.warn(error);
    });