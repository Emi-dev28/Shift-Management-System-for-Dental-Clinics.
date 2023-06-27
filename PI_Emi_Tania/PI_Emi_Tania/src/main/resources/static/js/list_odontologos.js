/*window.addEventListener('load', function () {
    const url = 'http://localhost:8080/odontologos'; // URL de la API REST para obtener la lista de odontólogos

    fetch(url)
       .then(response => response.json())
       .then(data => {
          const odontologosList = document.getElementById('odontologos-list');

          data.forEach(odontologo => {
             const row = document.createElement('tr');
             row.innerHTML = `
                <td>${odontologo.matricula}</td> // definir una celda de datos dentro de una tabla
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
             `;
             odontologosList.appendChild(row);
          });
       })
       .catch(error => {
          console.error('Error al obtener la lista de odontólogos:', error);
       });
 });*/

 window.addEventListener('load', function () {
     const url = 'http://localhost:8080/odontologos'; // URL de la API REST para obtener la lista de odontólogos

    const settings = {
        method:'GET',
        headers: {
            'Content-Type':'application/json'
        }
    }

     fetch(`http://localhost:8080/odontologos`,settings)
         .then(response => response.json())
         .then(data => {
             const odontologosList = document.getElementById('odontologos-list');
             let rows = '';

             data.forEach(odontologo => {
                 rows += `
                     <tr>
                         <td>${odontologo.matricula}</td>
                         <td>${odontologo.nombre}</td>
                         <td>${odontologo.apellido}</td>
                     </tr>
                 `;
             });

             odontologosList.innerHTML = rows;
         })
         .catch(error => {
             console.error('Error al obtener la lista de odontólogos:', error);
         });
 });
