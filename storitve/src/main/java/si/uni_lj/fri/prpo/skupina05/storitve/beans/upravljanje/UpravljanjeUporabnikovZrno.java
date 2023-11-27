package si.uni_lj.fri.prpo.skupina05.storitve.beans.upravljanje;

import si.uni_lj.fri.prpo.skupina05.entitete.Film;
import si.uni_lj.fri.prpo.skupina05.entitete.Uporabnik;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.FilmZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.UporabnikZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.FilmDTO;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.UporabnikDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class UpravljanjeUporabnikovZrno {

    private final Logger LOG = Logger.getLogger(UpravljanjeUporabnikovZrno.class.getName());

    @Inject
    private UporabnikZrno uporabnikZrno;


    @PostConstruct
    public void init() { LOG.info("Inicializacija zrna " + UpravljanjeUporabnikovZrno.class.getSimpleName() + ".");}

    @PreDestroy
    public void destroy() { LOG.info("Deinicializacija zrna " + UpravljanjeUporabnikovZrno.class.getSimpleName() + "."); }


    public Optional<Uporabnik> toUporabnik(UporabnikDTO uporabnikDTO) {

        String ime = uporabnikDTO.getIme();
        String priimek = uporabnikDTO.getPriimek();
        String uporabniskoIme = uporabnikDTO.getUporabniskoIme();
        String email = uporabnikDTO.getEmail();
        String geslo = uporabnikDTO.getGeslo();

        if(ime == null || ime.isBlank() ||
                priimek == null || priimek.isBlank() ||
                uporabniskoIme == null || uporabniskoIme.isBlank() ||
                email == null || email.isBlank() ||
                geslo == null || geslo.isBlank()
        ) {
            return Optional.empty();
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,5}$");
        Matcher matcher = pattern.matcher(email);
        boolean matchFoundEmail = matcher.find();
        if(!matchFoundEmail) {
            LOG.info("Neustrezna oblika elektronskega naslova.");
            return Optional.empty();
        }

        Pattern pattern1 = Pattern.compile("^[a-zA-Z0-9]{7,100}$");
        Matcher matcher1 = pattern1.matcher(geslo);
        boolean matchFoundPassword = matcher1.find();
        if(!matchFoundPassword) {
            LOG.info("Neustrezna oblika gesla.");
            return Optional.empty();
        }

        Uporabnik uporabnik = new Uporabnik();

        uporabnik.setIme(ime);
        uporabnik.setPriimek(priimek);
        uporabnik.setUporabniskoIme(uporabniskoIme);
        uporabnik.setEmail(email);
        uporabnik.setGeslo(geslo);

        return Optional.of(uporabnik);
    }

    @Transactional
    public boolean dodajUporabnika(UporabnikDTO uporabnikDTO) {
        Optional<Uporabnik> uporabnikOptional = toUporabnik(uporabnikDTO);

        if(!uporabnikOptional.isPresent()) {
            LOG.info("Ne najdem uporabnika.");
            return false;
        }

        Uporabnik uporabnik = uporabnikOptional.get();
        uporabnikZrno.dodajUporabnika(uporabnik);
        return true;
    }

    @Transactional
    public boolean izbrisiUporabnika(int id) {
        var uporabnik = uporabnikZrno.getUporabnikById(id);

        if(!uporabnik.isPresent()) {
            LOG.info("Ne najdem uporabnika.");
            return false;
        }

        uporabnikZrno.deleteUporabnikById(id);
        return true;
    }

    @Transactional
    public boolean posodobiUporabnika(int id, UporabnikDTO uporabnikDTO) {
        Optional<Uporabnik> uporabnik = toUporabnik(uporabnikDTO);

        if(!uporabnik.isPresent()) {
            LOG.info("Ne najdem uporabnika.");
            return false;
        }

        uporabnik.flatMap(u -> uporabnikZrno.updateEntity(id, u));
        return true;
    }

    /*public boolean addFilmPogledano (int id, int idFilm) {
        Film film = filmZrno.getFilmById(idFilm).get();

        List<Film> filmi = uporabnikZrno.getUporabnikById(id).get().getFilmiPogledano();

        boolean success = filmi.add(film);

        return success;
    }

    public boolean addFilmVsec (int id, int idFilm) {
        Film film = filmZrno.getFilmById(idFilm).get();

        List<Film> filmi = uporabnikZrno.getUporabnikById(id).get().getFilmiVsec();

        boolean success = filmi.add(film);

        return success;
    }*/
}
