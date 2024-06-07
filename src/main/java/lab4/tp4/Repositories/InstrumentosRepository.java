package lab4.tp4.Repositories;

import lab4.tp4.Entities.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstrumentosRepository extends JpaRepository<Instrumento, Integer> {
    @Query(value = "SELECT * FROM Instrumento i WHERE i.categoria_id = :denominacion", nativeQuery = true)
    List<Instrumento> findInstrumentosByDenominacion(@Param("denominacion") Integer denominacion);
}
