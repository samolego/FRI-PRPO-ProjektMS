package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class BelezenjeKlicevZrno {

    private final Logger LOG = Logger.getLogger(BelezenjeKlicevZrno.class.getName());
    private int count;

    @PostConstruct
    public void init() {
        LOG.info("Inicializacija zrna " + BelezenjeKlicevZrno.class.getSimpleName() + ".");
    }

    @PreDestroy
    public void destroy() {
        LOG.info("Deinicializacija zrna " + BelezenjeKlicevZrno.class.getSimpleName() + ".");
    }

    public void beleziKlice() {
        ++this.count;
        LOG.info("Beležim klic #" + this.count + ".");
    }

}
