package hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.model.SignosPaciente;

public interface SignosPacienteRepo extends JpaRepository<SignosPaciente, Integer> {

}
