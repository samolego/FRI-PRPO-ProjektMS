package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.storitve.dtos.UporabnikDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    public void dodajUporabnika(UporabnikDTO uporabnikDTO) {
        var uporabnik = uporabnikDTO.toUporabnik();

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,5}$");
        Matcher matcher = pattern.matcher(uporabnikDTO.email());
        boolean matchFoundEmail = matcher.find();
        if(matchFoundEmail) {
            uporabnik.ifPresent(uporabnikZrno::insertEntity);
        } else {
            LOG.info("Neustrezna oblika elektronskega naslova.");
        }
    }
}
