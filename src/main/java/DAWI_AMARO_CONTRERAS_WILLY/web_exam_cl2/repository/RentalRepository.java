package DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.repository;

import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Rental r WHERE r.inventory.id IN (SELECT i.id FROM Inventory i WHERE i.film.id = :filmId)")
    void deleteByFilmId(Integer filmId);
}
