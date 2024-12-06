package DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.repository;

import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Inventory i WHERE i.film.filmId = :filmId")
    void deleteByFilmId(@Param("filmId") int filmId);
}
