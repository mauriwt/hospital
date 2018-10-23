package hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hospital.model.Catalogo;

@Repository
public interface CatalogoRepo extends JpaRepository<Catalogo, Integer>{

  @Query("SELECT c FROM Catalogo c WHERE c.tipo = :tipo")
  public List<Catalogo> getCatalogoByTipo(@Param("tipo") String tipo);
}
