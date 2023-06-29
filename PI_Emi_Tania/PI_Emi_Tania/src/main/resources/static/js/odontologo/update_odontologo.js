window.addEventListener('load', function () {
    const updateAlert = document.querySelector('#update_response');
    const updateForm = document.querySelector('#update_odontologo');
    
    updateForm.addEventListener('submit', function (event) {
      event.preventDefault();
  
      const formData = {
        id:document.querySelector('#update_id').value,
        matricula: document.querySelector('#update_matricula').value,
        nombre: document.querySelector('#update_nombre').value,
        apellido: document.querySelector('#update_apellido').value,
      };
  
      const url = 'http://localhost:8080/odontologos/actualizar';
      const settings = {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      };
  
      fetch(url, settings)
        .then(response => {
          console.log(response.status);
          console.log(response.statusText);
          return response.json();
        })
        .then(data => {
          console.log(data);
          data.statusText;
          // Si no hay ningún error, mostramos un mensaje indicando que el odontólogo fue actualizado
          let successAlert =
            `<div class="alert alert-success alert-dismissible"> 
              <button type="button" class="close" data-dismiss="alert">&times;</button> 
              <strong> Odontólogo actualizado </strong>
            </div>`;
  
          updateAlert.innerHTML += successAlert;
          updateAlert.style.display = 'block';
          updateForm.reset();
        })
        .catch(error => {
          let errorAlert =
            `<div class="alert alert-danger alert-dismissible">' +
              '<button type="button" class="close" ' +
              'data-dismiss="alert">&times;</button>' +
              '<strong>Error, intente nuevamente</strong> </div>`;
  
          updateAlert.innerHTML += errorAlert;
          updateAlert.style.display = 'block';
          updateForm.reset();
        });
    });
  });
  