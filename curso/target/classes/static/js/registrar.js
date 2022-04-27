// Call the dataTables jQuery plugin
$(document).ready(function() {
//on ready
});


async function registrarUsuarios(){
    let datos = {    };

    datos.nombre = document.getElementById('txtNombre').value;
    console.log(datos.nombre);
    datos.apellido = document.getElementById('txtApellido').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;


    let repetirPassword =  document.getElementById('txtRepetirPassword').value;

     if(repetirPassword != datos.password){

      alert('Contraseñas diferentes');
      return;

     }
    const request = await fetch('api/usuarios', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
    });

alert('La cuenta se ha creado correctamente');
 window.location.href = 'login.html';

}


