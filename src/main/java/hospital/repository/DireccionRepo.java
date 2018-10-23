package hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.model.Direccion;

public interface DireccionRepo extends JpaRepository<Direccion, Integer> {

}
