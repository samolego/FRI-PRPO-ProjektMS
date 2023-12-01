package si.uni_lj.fri.prpo.skupina05.storitve.beans.upravljanje;

import si.uni_lj.fri.prpo.skupina05.entitete.Film;
import si.uni_lj.fri.prpo.skupina05.entitete.Zanr;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.FilmZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.ZanrZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.FilmDTO;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.izjeme.IzjemaBadRequestDTO;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.izjeme.IzjemaNotFoundDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeFilmovZrno {

    private final Logger LOG = Logger.getLogger(UpravljanjeFilmovZrno.class.getName());
    @Inject
    private FilmZrno filmZrno;

    @Inject
    private ZanrZrno zanrZrno;

    @PostConstruct
    public void init() {
        LOG.info("Inicializacija zrna " + UpravljanjeFilmovZrno.class.getSimpleName() + ".");
    }

    @PreDestroy
    public void destroy() {
        LOG.info("Deinicializacija zrna " + UpravljanjeFilmovZrno.class.getSimpleName() + ".");
    }

    public Optional<Film> toFilm(FilmDTO filmDTO) throws IzjemaBadRequestDTO, IzjemaNotFoundDTO {
        String name = filmDTO.getName();
        String opis = filmDTO.getOpis();
        Date datumIzida = filmDTO.getDatumIzida();
        String zanrId = filmDTO.getZanrId();

        if (name == null ||
                opis == null ||
                datumIzida == null ||
                zanrId == null ||
                name.isBlank() ||
                opis.isBlank()
        ) {
            throw new IzjemaBadRequestDTO("Prosimo, izpolnite vsa zahtevana polja.");
            //return Optional.empty();
        }

        if(Integer.valueOf(zanrId) == null) {
            LOG.info("Neustrezna oblika identifikatorja žanra.");
            throw new IzjemaBadRequestDTO("Neustrezna oblika identifikatorja žanra.");
            //return Optional.empty();
        }
        int zanrIdInt = Integer.parseInt(zanrId);

        if(!zanrZrno.getZanrById(zanrIdInt).isPresent()) {
            throw new IzjemaNotFoundDTO("Ne najdem žanra s podanim identifikatorjem.");
            //return Optional.empty();
        }

        Zanr zanr = new Zanr();
        zanr = zanrZrno.getZanrById(zanrIdInt).get();

        Film film = new Film();
        film.setIme(name);
        film.setOpis(opis);
        film.setDatumIzida(datumIzida);
        film.setZanr(zanr);

        return Optional.of(film);
    }

    @Transactional
    public boolean dodajFilm(FilmDTO filmDTO) throws IzjemaBadRequestDTO {
        var film = toFilm(filmDTO);
        if(film.isPresent()) {
            film.ifPresent(filmZrno::insertEntity);
        } else {
            throw new IzjemaBadRequestDTO("Ne najdem filma.");
        }

        return film.isPresent();
    }

    @Transactional
    public boolean odstraniFilm(FilmDTO filmDTO) {
        var filmData = toFilm(filmDTO);
        var film = filmData.flatMap(f -> filmZrno.getFilmByIme(f.getIme()));

        return film.map(flm -> {
                    filmZrno.deleteFilmById(flm.getId());
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public boolean posodobiFilm(int id, FilmDTO filmData) throws IzjemaNotFoundDTO {
        var film = toFilm(filmData);

        if(!film.isPresent()) {
            throw new IzjemaBadRequestDTO("Ne najdem filma.");
        }

        if(!filmZrno.getFilmById(id).isPresent()) {
            throw new IzjemaNotFoundDTO("Ne najdem filma.");
        }

        var success = film.flatMap(f -> filmZrno.updateEntity(id, f));

        return success.isPresent();
    }
}
