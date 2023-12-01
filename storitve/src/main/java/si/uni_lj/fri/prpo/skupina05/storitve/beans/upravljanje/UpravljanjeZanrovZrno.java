package si.uni_lj.fri.prpo.skupina05.storitve.beans.upravljanje;

import si.uni_lj.fri.prpo.skupina05.entitete.Zanr;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.ZanrZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.ZanrDTO;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.izjeme.IzjemaBadRequestDTO;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.izjeme.IzjemaNotFoundDTO;

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

    public Optional<Zanr> toZanr(ZanrDTO zanrDTO) throws IzjemaBadRequestDTO {
        String name = zanrDTO.getIme();
        if (name == null || name.isBlank()) {
            throw new IzjemaBadRequestDTO("Prosimo, izpolnite vsa zahtevana polja.");
            //return Optional.empty();
        }

        var zanr = new Zanr();
        zanr.setIme(name);

        return Optional.of(zanr);
    }

    @Transactional
    public boolean dodajZanr(ZanrDTO zanrDTO) throws IzjemaBadRequestDTO {
        var zanr = toZanr(zanrDTO);
        if(!zanr.isPresent()) {
            throw new IzjemaBadRequestDTO("Ne najdem zanra.");
        }
        zanr.ifPresent(zanrZrno::insertEntity);
        return zanr.isPresent();
    }

    @Transactional
    public void odstraniZanr(ZanrDTO zanrDTO) {
        var znrOpt = toZanr(zanrDTO);

        znrOpt.flatMap(z -> zanrZrno.getZanrByName(z.getIme()))
                .ifPresent(zanr -> zanrZrno.deleteZanrById(zanr.getId()));
    }

    @Transactional
    public boolean izbrisiZanr(int id) throws IzjemaNotFoundDTO {
        var zanr = zanrZrno.getZanrById(id);

        if(!zanr.isPresent()) {
            LOG.info("Ne najdem žanra.");
            throw new IzjemaNotFoundDTO("Ne najdem žanra.");
            //return false;
        }

        zanrZrno.deleteZanrById(id);
        return true;
    }

    @Transactional
    public boolean posodobiZanr(int id, ZanrDTO zanrData) {
        Optional<Zanr> zanr = toZanr(zanrData);

        if(!zanr.isPresent()) {
            LOG.info("Ne najdem žanra.");
            throw new IzjemaBadRequestDTO("Ne najdem žanra.");
            //return false;
        }

        if(!zanrZrno.getZanrById(id).isPresent()) {
            throw new IzjemaNotFoundDTO("Ne najdem žanra.");
        }

        zanr.flatMap(z -> zanrZrno.updateEntity(id, z));
        return true;
    }

}

