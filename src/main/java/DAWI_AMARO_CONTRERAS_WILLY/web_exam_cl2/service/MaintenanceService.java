package DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.service;

import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.dto.FilmCreateDto;
import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.dto.FilmDetailDto;
import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.dto.FilmDto;

import java.util.List;

public interface MaintenanceService {

    List<FilmDto> findAllFilms();

    FilmDetailDto findFilmById(int id);

    Boolean updateFilm(FilmDetailDto filmDetailDto);

    Boolean deleteFilm(int filmId);

    Boolean createFilm(FilmCreateDto filmCreateDto);


}