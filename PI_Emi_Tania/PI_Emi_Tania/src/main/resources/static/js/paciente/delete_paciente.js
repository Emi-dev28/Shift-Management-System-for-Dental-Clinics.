window.addEventListener('load', function () {
    const form = document.querySelector('#delete_paciente');
    const responseDiv = document.querySelector('#delete_response');
  
    form.addEventListener('submit', function (event) {
      event.preventDefault();
  
      const pacienteId = document.querySelector('#delete_id').value;
      deletePaciente(pacienteId);
    });
  
    function deletePaciente(pacienteId) {
      const url = `http://localhost:8080/pacientes/${pacienteId}`;
  
      const settings = {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
      };
  
      fetch(url, settings)
        .then(response => response.json())
        .then(data => {
          responseDiv.textContent = 'Paciente eliminado correctamente.';
          responseDiv.style.display = 'block';
          console.log('Paciente eliminado:', data);
        })
        .catch(error => {
          responseDiv.textContent = 'Error al eliminar el paciente.';
          responseDiv.style.display = 'block';
          console.error('Error al eliminar el paciente:', error);
        });
    }
  });
  