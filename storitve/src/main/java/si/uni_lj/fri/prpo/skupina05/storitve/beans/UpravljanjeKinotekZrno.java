package si.uni_lj.fri.prpo.skupina05.storitve.beans;

import si.uni_lj.fri.prpo.skupina05.entitete.Kinoteka;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.KinotekaDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class UpravljanjeKinotekZrno {
    //private UUID idZrna = UUID.randomUUID();
    private final Logger LOG = Logger.getLogger(UpravljanjeKinotekZrno.class.getName());

    @Inject
    private KinotekaZrno kinotekaZrno;

    @PostConstruct
    public void init() { LOG.info("Inicializacija zrna " + UpravljanjeKinotekZrno.class.getSimpleName() + "."); }

    @PreDestroy
    public void destroy() { LOG.info("Deinicializacija zrna " + UpravljanjeKinotekZrno.class.getSimpleName() + "."); }

    @RequestScoped
    public void dodajKinoteko(KinotekaDTO kinotekaDTO) {
        UUID idMetode = UUID.randomUUID();
        var kinoteka = kinotekaDTO.toKinoteka();

        Pattern pattern = Pattern.compile("www.\\w+.\\w+");
        Matcher matcher = pattern.matcher(kinotekaDTO.spletnaStran());
        boolean matchFoundSpletnaStran = matcher.find();
        boolean kinotekaExists = false;
        if (kinotekaZrno.getKinotekeWithSpletnaStran(kinotekaDTO.spletnaStran()) != null ) kinotekaExists = true;
        if(!matchFoundSpletnaStran && !kinotekaExists) {
            LOG.info("Neustrezna oblika spletne strani.");
        } else {
            if(!kinotekaExists) {
                kinoteka.ifPresent(kinotekaZrno::insertEntity);
            } else {
                LOG.info("Kinoteka že obstaja.");
            }
        }
        LOG.info(String.valueOf(idMetode));
    }

    @RequestScoped
    public void spremeniIme(KinotekaDTO kinotekaDTO) {
        UUID idMetode = UUID.randomUUID();
        Optional<Kinoteka> kinoteka = kinotekaDTO.toKinoteka();
        Kinoteka kinoteka2 = kinotekaDTO.toKinotekaClass();
        if(kinoteka.isPresent()) {
            kinotekaZrno.updateKinoteka(kinotekaZrno.getKinotekaBySpletnaStran(kinotekaDTO.spletnaStran()).getId(), kinoteka2);
        }
        LOG.info(String.valueOf(idMetode));
    }
}
