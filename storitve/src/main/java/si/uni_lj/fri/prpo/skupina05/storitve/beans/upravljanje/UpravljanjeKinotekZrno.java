package si.uni_lj.fri.prpo.skupina05.storitve.beans.upravljanje;

import si.uni_lj.fri.prpo.skupina05.entitete.Kinoteka;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.KinotekaZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.KinotekaDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class UpravljanjeKinotekZrno {
    private final Logger LOG = Logger.getLogger(UpravljanjeKinotekZrno.class.getName());

    @Inject
    private KinotekaZrno kinotekaZrno;

    @PostConstruct
    public void init() { LOG.info("Inicializacija zrna " + UpravljanjeKinotekZrno.class.getSimpleName() + "."); }

    @PreDestroy
    public void destroy() { LOG.info("Deinicializacija zrna " + UpravljanjeKinotekZrno.class.getSimpleName() + "."); }

    public Optional<Kinoteka> toKinoteka(KinotekaDTO kinotekaDTO) {

        String ime = kinotekaDTO.getIme();
        String spletnaStran = kinotekaDTO.getSpletnaStran();

        if(ime == null || ime.isBlank() ||
                spletnaStran == null || spletnaStran.isBlank()
        ) {
            return Optional.empty();
        }

        Pattern pattern = Pattern.compile("\\w+\\.[a-zA-Z]{2,5}$");
        Matcher matcher = pattern.matcher(spletnaStran);
        boolean matchFound = matcher.find();
        if(!matchFound) {
            LOG.info("Neustrezna oblika spletne strani.");
            return Optional.empty();
        }

        Kinoteka kinoteka = new Kinoteka();
        kinoteka.setIme(ime);
        kinoteka.setSpletnaStran(spletnaStran);

        return Optional.of(kinoteka);
    }

    @RequestScoped
    @Transactional
    public boolean dodajKinoteko(KinotekaDTO kinotekaDTO) {
        UUID idMetode = UUID.randomUUID();
        var kinoteka = toKinoteka(kinotekaDTO);

        if(!kinoteka.isPresent()) {
            LOG.info("Ne najdem kinoteke.");
            return false;
        }

        kinotekaZrno.insertEntity(kinoteka.get());
        return true;
    }

    @Transactional
    public boolean izbrisiKinoteko(int id) {
        var kinoteka = kinotekaZrno.getKinotekaById(id);

        if(!kinoteka.isPresent()) {
            LOG.info("Ne najdem kinoteke.");
            return false;
        }

        kinotekaZrno.deleteKinotekaById(id);
        return true;
    }

    @RequestScoped
    @Transactional
    public boolean spremeniIme(KinotekaDTO kinotekaDTO) {
        UUID idMetode = UUID.randomUUID();
        Optional<Kinoteka> kinoteka = toKinoteka(kinotekaDTO);
        //Kinoteka kinoteka2 = toKinoteka(kinotekaDTO).get();
        if(kinoteka.isPresent() && kinotekaZrno.getKinotekaBySpletnaStran(kinotekaDTO.getSpletnaStran()).isPresent()) {
            kinotekaZrno.updateKinoteka(kinotekaZrno.getKinotekaBySpletnaStran(kinotekaDTO.getSpletnaStran()).get().getId(), kinoteka.get());
        }
        LOG.info(String.valueOf(idMetode));
        return kinoteka.isPresent();
    }
}
