package lab4.tp4.Repositories;

import lab4.tp4.Entities.Categoria;
import lab4.tp4.Enums.Denominacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
    @Query(value = "SELECT * FROM Categoria n WHERE n.denominacion = :denominacion", nativeQuery = true)
    Categoria findByDenominacion(@Param("denominacion") Denominacion denominacion);
}
