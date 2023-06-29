window.addEventListener('load', function () {
    const deleteAlert = document.querySelector('#delete_response');
    const deleteForm = document.querySelector('#delete_odontologo');
  
    deleteForm.addEventListener('submit', function (event) {
      event.preventDefault();
  
      const formData = {
        id: document.querySelector('#delete_id').value,
      };
  
      const url = 'http://localhost:8080/odontologos/eliminar/';
      const settings = {
        method: 'DELETE',
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
          // Si no hay ningún error, mostramos un mensaje indicando que el odontólogo fue eliminado
          let successAlert =
            `<div class="alert alert-success alert-dismissible"> 
              <button type="button" class="close" data-dismiss="alert">&times;</button> 
              <strong> Odontólogo eliminado </strong>
            </div>`;
  
          deleteAlert.innerHTML += successAlert;
          deleteAlert.style.display = 'block';
          deleteForm.reset();
        })
        .catch(error => {
          let errorAlert =
            `<div class="alert alert-danger alert-dismissible">' +
              '<button type="button" class="close" ' +
              'data-dismiss="alert">&times;</button>' +
              '<strong>Error, intente nuevamente</strong> </div>`;
  
          deleteAlert.innerHTML += errorAlert;
          deleteAlert.style.display = 'block';
          deleteForm.reset();
        });
    });
  });
  