package hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.model.Turno;

public interface TurnoRepo extends JpaRepository<Turno, Integer> {

}
