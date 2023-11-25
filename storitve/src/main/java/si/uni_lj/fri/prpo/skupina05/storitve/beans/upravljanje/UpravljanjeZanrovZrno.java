package si.uni_lj.fri.prpo.skupina05.storitve.beans.upravljanje;

import si.uni_lj.fri.prpo.skupina05.entitete.Zanr;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.ZanrZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.ZanrDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeZanrovZrno {

    private final Logger LOG = Logger.getLogger(UpravljanjeZanrovZrno.class.getName());
    @Inject
    private ZanrZrno zanrZrno;

    @PostConstruct
    public void init() {
        LOG.info("Inicializacija zrna " + UpravljanjeZanrovZrno.class.getSimpleName() + ".");
    }

    @PreDestroy
    public void destroy() {
        LOG.info("Deinicializacija zrna " + UpravljanjeZanrovZrno.class.getSimpleName() + ".");
    }

    public Optional<Zanr> toZanr(ZanrDTO zanrDTO) {
        String name = zanrDTO.getIme();
        if (name == null || name.isBlank()) {
            return Optional.empty();
        }

        var zanr = new Zanr();
        zanr.setIme(name);

        return Optional.of(zanr);
    }

    @Transactional
    public boolean dodajZanr(ZanrDTO zanrDTO) {
        var film = toZanr(zanrDTO);
        film.ifPresent(zanrZrno::insertEntity);
        return film.isPresent();
    }

    @Transactional
    public void odstraniZanr(ZanrDTO zanrDTO) {
        var znrOpt = toZanr(zanrDTO);

        znrOpt.flatMap(z -> zanrZrno.getZanrByName(z.getIme()))
                .ifPresent(zanr -> zanrZrno.deleteZanrById(zanr.getId()));
    }

    @Transactional
    public boolean izbrisiZanr(int id) {
        var zanr = zanrZrno.getZanrById(id);

        if(!zanr.isPresent()) {
            LOG.info("Ne najdem žanra.");
            return false;
        }

        zanrZrno.deleteZanrById(id);
        return true;
    }

    @Transactional
    public boolean posodobiZanr(int id, ZanrDTO zanrData) {
        Optional<Zanr> zanr = toZanr(zanrData);

        if(!zanr.isPresent()) {
            LOG.info("Ne najdem žanra.");
            return false;
        }
        zanr.flatMap(z -> zanrZrno.updateEntity(id, z));
        return true;
    }

}

