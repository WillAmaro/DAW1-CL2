package DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.repository;

import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
