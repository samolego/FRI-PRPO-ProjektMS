package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.storitve.dtos.ZanrDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    public void dodajZanr(ZanrDTO zanrDTO) {
        var film = zanrDTO.toZanr();
        film.ifPresent(zanrZrno::insertEntity);
    }

    public void odstraniZanr(ZanrDTO zanrDTO) {
        var znrOpt = zanrDTO.toZanr();

        znrOpt.flatMap(z -> zanrZrno.getZanrByName(z.getIme()))
                .ifPresent(zanr -> zanrZrno.deleteZanrById(zanr.getId()));
    }
}
