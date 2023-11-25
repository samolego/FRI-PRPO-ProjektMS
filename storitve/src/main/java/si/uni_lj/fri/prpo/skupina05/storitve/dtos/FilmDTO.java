package si.uni_lj.fri.prpo.skupina05.storitve.dtos;

import si.uni_lj.fri.prpo.skupina05.entitete.Film;
import si.uni_lj.fri.prpo.skupina05.entitete.Kinoteka;
import si.uni_lj.fri.prpo.skupina05.entitete.Zanr;

import java.util.Date;
import java.util.Optional;

public record FilmDTO(String name, String opis, Date datumIzida, Zanr zanr) {

    public Optional<Film> toFilm() {
        if (name == null ||
                opis == null ||
                datumIzida == null ||
                zanr == null ||
                name.isBlank() ||
                opis.isBlank()
        ) {
            return Optional.empty();
        }

        var film = new Film();
        film.setIme(name);
        film.setOpis(opis);
        film.setDatumIzida(datumIzida);
        film.setZanr(zanr);

        return Optional.of(film);
    }

    public Film toFilmClass() {
        Film f = new Film();
        f.setIme(name);
        f.setOpis(opis);
        f.setDatumIzida(datumIzida);
        f.setZanr(zanr);
        return f;
    }
}
