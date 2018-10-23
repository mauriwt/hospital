package hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hospital.model.Consulta;

@Repository
public interface ConsultaRepo extends JpaRepository<Consulta, Integer>{

}
