
window.addEventListener('load', function () {
  
    const alert = document.querySelector('#response')
    const formulario = document.querySelector('#add_new_odontologo');
    console.log(formulario);
   
    formulario.addEventListener('submit', function (event) {
      event.preventDefault();
  
   
      const formData = {
        matricula: document.querySelector('#matricula').value,
        nombre: document.querySelector('#nombre').value,
        apellido: document.querySelector('#apellido').value,
      };
      
      console.log(formData)

      const url = 'http://localhost:8080/odontologos/registrar';
      const settings = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      };

      fetch(url, settings)
        .then(response => {
          console.log(response.status);
          console.log(response.statusText)
          return response.json()
        })
        .then(data => {
          console.log(data)
          data.statusText;
          // Si no hay ningún error, mostramos un mensaje indicando que el odontólogo fue agregado
          let successAlert =
          `<div class="alert alert-success alert-dismissible"> 
            <button type="button" class="close"  
            data-dismiss="alert">&times;</button> 
            <strong> Odontólogo agregado </strong>
            </div>`;
          
          
          alert.innerHTML += successAlert;
          alert.style.display = 'block'
          formulario.reset();
        }).catch(error => {

          let errorAlert =
          `<div class="alert alert-danger alert-dismissible">' +
            '<button type="button" class="close" ' +
            'data-dismiss="alert">&times;</button>' +
            '<strong>Error, intente nuevamente</strong> </div>`;
  
          alert.innerHTML += errorAlert;
          alert.style.display = 'block';
          formulario.reset();
        });
    });
  
    
  });
