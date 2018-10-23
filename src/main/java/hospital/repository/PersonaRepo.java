package hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.model.Persona;

public interface PersonaRepo extends JpaRepository<Persona, Integer> {

}
