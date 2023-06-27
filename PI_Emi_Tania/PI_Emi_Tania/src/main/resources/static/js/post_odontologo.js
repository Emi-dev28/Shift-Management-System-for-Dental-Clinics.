
/*Este código se encarga de capturar los datos ingresados 
en el formulario de agregar un nuevo odontólogo, enviarlos al servidor a través de una solicitud POST 
y mostrar mensajes de éxito o error dependiendo de la respuesta recibida.*/

window.addEventListener('load', function () {
    // Al cargar la página, buscamos y obtenemos el formulario donde estarán
    // los datos que el usuario cargará del nuevo odontólogo
    const formulario = document.querySelector('#add_new_odontologo');
  
    // Ante un submit del formulario, se ejecutará la siguiente función
    formulario.addEventListener('submit', function (event) {
      event.preventDefault();
  
      // Creamos un objeto que contendrá los datos del nuevo odontólogo
      const formData = {
        matricula: document.querySelector('#matricula').value,
        nombre: document.querySelector('#nombre').value,
        apellido: document.querySelector('#apellido').value,
      };
  
      // Invocamos la API "odontologos" utilizando la función fetch de JavaScript
      // con el método POST para guardar el odontólogo que enviaremos en formato JSON

      const url = 'http://localhost:8080/odontologos/registrar';
      const settings = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      };
  
      fetch(`http://localhost:8080/odontologos/registrar`, settings)
        .then(response => response.json())
        .then(data => {
          // Si no hay ningún error, mostramos un mensaje indicando que el odontólogo fue agregado
          let successAlert =
            '<div class="alert alert-success alert-dismissible">' +
            '<button type="button" class="close" ' +
            'data-dismiss="alert">&times;</button>' +
            '<strong></strong> Odontólogo agregado </div>';
  
          document.querySelector('#response').innerHTML += successAlert;
          document.querySelector('#response').style.display = 'block';
          resetUploadForm();
        })
        .catch(error => {
          // Si hay algún error, mostramos un mensaje indicando que el odontólogo no se pudo guardar 
          //y se debe intentar nuevamente
          let errorAlert =
            '<div class="alert alert-danger alert-dismissible">' +
            '<button type="button" class="close" ' +
            'data-dismiss="alert">&times;</button>' +
            '<strong>Error, intente nuevamente</strong> </div>';
  
          document.querySelector('#response').innerHTML = errorAlert;
          document.querySelector('#response').style.display = 'block';
          resetUploadForm();
        });
    });
  
    function resetUploadForm() {
      document.querySelector('#matricula').value = '';
      document.querySelector('#nombre').value = '';
      document.querySelector('#apellido').value = '';
    }
  });
  

   
