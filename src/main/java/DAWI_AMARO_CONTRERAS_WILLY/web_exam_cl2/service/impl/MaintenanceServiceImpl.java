package DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.service.impl;
import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.dto.FilmCreateDto;
import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.dto.FilmDetailDto;
import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.dto.FilmDto;
import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.entity.Film;
import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.entity.Language;
import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.repository.*;
import DAWI_AMARO_CONTRERAS_WILLY.web_exam_cl2.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    @Autowired
    FilmRepository filmRepository;

    @Autowired
    private FilmActorRepository filmActorRepository;

    @Autowired
    private FilmCategoryRepository filmCategoryRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<FilmDto> findAllFilms() {

        List<FilmDto> films = new ArrayList<FilmDto>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            FilmDto filmDto = new FilmDto(film.getFilmId(),
                    film.getTitle(),
                    film.getLanguage().getName(),
                    film.getRentalDuration(),
                    film.getRentalRate());
            films.add(filmDto);
        });
        return films;

    }

    @Override
    public FilmDetailDto findFilmById(int id) {

        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(film -> new FilmDetailDto(film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguage().getLanguageId(),
                film.getLanguage().getName(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating(),
                film.getSpecialFeatures(),
                film.getLastUpdate())
        ).orElse(null);

    }

    @Override
    public Boolean updateFilm(FilmDetailDto filmDetailDto) {
        Optional<Film> optional = filmRepository.findById(filmDetailDto.filmId());
        return optional.map(
                film -> {
                    film.setTitle(filmDetailDto.title());
                    film.setDescription(filmDetailDto.description());
                    film.setReleaseYear(filmDetailDto.releaseYear());
                    film.setRentalDuration(filmDetailDto.rentalDuration());
                    film.setRentalRate(filmDetailDto.rentalRate());
                    film.setLength(filmDetailDto.length());
                    film.setReplacementCost(filmDetailDto.replacementCost());
                    film.setRating(filmDetailDto.rating());
                    film.setSpecialFeatures(filmDetailDto.specialFeatures());
                    film.setLastUpdate(new Date());
                    filmRepository.save(film);
                    return true;
                }
        ).orElse(false);
    }

    @Override
    public Boolean deleteFilm(int filmId) {
        try {
            filmActorRepository.deleteByFilmId(filmId);

            filmCategoryRepository.deleteByFilmId(filmId);

            rentalRepository.deleteByFilmId(filmId);

            inventoryRepository.deleteByFilmId(filmId);

            filmRepository.deleteByFilmId(filmId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean createFilm(FilmCreateDto filmCreateDto) {
        try {
            Film film = new Film();
            film.setTitle(filmCreateDto.title());
            film.setDescription(filmCreateDto.description());
            film.setReleaseYear(filmCreateDto.releaseYear());

            Optional<Language> language = languageRepository.findById(filmCreateDto.languageId());
            if (language.isPresent()) {
                film.setLanguage(language.get());
            } else {
                throw new RuntimeException("Language not found with id " + filmCreateDto.languageId());
            }

            film.setRentalDuration(filmCreateDto.rentalDuration());
            film.setRentalRate(filmCreateDto.rentalRate());
            film.setLength(filmCreateDto.length());
            film.setReplacementCost(filmCreateDto.replacementCost());
            film.setRating(filmCreateDto.rating());
            film.setSpecialFeatures(filmCreateDto.specialFeatures());
            film.setLastUpdate(new Date());

            filmRepository.save(film);
            return true;
        } catch (Exception e) {return false;
        }
    }
}
