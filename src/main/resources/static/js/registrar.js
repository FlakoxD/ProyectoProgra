// Call the dataTables jQuery plugin
$(document).ready(function() {
});

async function registrarUsuarios(){
  let datos = {};
    datos.nombre= document.getElementById('txtNombre').value;
    datos.apellido= document.getElementById('txtApellido').value;
    datos.email= document.getElementById('txtEmail').value;
    datos.password= document.getElementById('txtContra').value;

    let repetirPassword = document.getElementById('txtRepetirContra').value;

    if(repetirPassword != datos.password){
      alert('Las contrasenas no coinciden');
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

  alert("La cuenta fue registrada con exito");
  window.location.href = 'login.html'

}

