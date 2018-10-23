package hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.model.Profesional;

public interface ProfesionalRepo extends JpaRepository<Profesional, Integer> {

}
