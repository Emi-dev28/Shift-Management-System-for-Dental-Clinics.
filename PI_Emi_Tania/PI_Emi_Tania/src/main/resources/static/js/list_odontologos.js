window.addEventListener('load', function () {
    const url = 'http://localhost:8080/odontologos'; // URL de la API REST para obtener la lista de odontólogos

    const odontologosList = document.querySelector('#odontologos-list');
    console.log(odontologosList);
    const settings = {
      method: 'GET',
      headers: {
          'Content-Type': 'application/json',
      }
    };
   console.log(settings)
   console.log(url)
    fetch(url, settings)
       .then(response => {
         return response.json()})
       .then(data => {
         console.log(data)
          data.forEach(odontologo => {
            const rows = `<tr>
            <td> ${odontologo.matricula} </td>
            <td> ${odontologo.nombre} </td>
            <td> ${odontologo.apellido} </td>
            </tr>`
             odontologosList.innerHTML += rows;
          });
       })
       .catch(error => {
          console.error('Error al obtener la lista de odontólogos:', error);
       });
 });