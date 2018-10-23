package hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.model.HistorialPaciente;

public interface HistorialPacienteRepo extends JpaRepository<HistorialPaciente, Integer> {

}
