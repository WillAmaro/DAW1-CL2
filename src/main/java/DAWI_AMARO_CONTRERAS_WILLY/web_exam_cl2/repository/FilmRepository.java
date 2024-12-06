package DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.repository;

import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.entity.Film;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Film f WHERE f.id = :filmId")
    void deleteByFilmId(Integer filmId);



    @CacheEvict(value ="films", allEntries = true)
    Film save(Film film);
}
