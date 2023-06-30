window.addEventListener('load', function () {
    const url = 'http://localhost:8080/pacientes'; // URL de la API REST para obtener la lista de pacientes
  
    const pacientesList = document.querySelector('#pacientes-list');
    console.log(pacientesList);
    const settings = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      }
    };
    console.log(settings);
    console.log(url);
    fetch(url, settings)
      .then(response => response.json())
      .then(data => {
        console.log(data);
        data.forEach(paciente => {
        const rows = `<tr>
            <td>${paciente.id}</td>
            <td>${paciente.nombre}</td>
            <td>${paciente.apellido}</td>
            <td>${paciente.dni}</td>
            <td>${paciente.fechaIngreso}</td>
            <td>${paciente.domicilio?.calle}</td>
            <td>${paciente.domicilio?.numero}</td>
            <td>${paciente.domicilio?.localidad}</td>
            <td>${paciente.domicilio?.provincia}</td>
          </tr>`;
          pacientesList.innerHTML += rows;
        });
      })
      .catch(error => {
        console.error('Error al obtener la lista de pacientes:', error);
      });
  });
  
