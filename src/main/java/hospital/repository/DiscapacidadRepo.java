package hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.model.Discapacidad;

public interface DiscapacidadRepo extends JpaRepository<Discapacidad, Integer> {

}
