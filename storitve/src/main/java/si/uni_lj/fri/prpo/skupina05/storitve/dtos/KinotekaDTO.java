package si.uni_lj.fri.prpo.skupina05.storitve.dtos;

import si.uni_lj.fri.prpo.skupina05.entitete.Kinoteka;

import java.util.Optional;

public class KinotekaDTO {
    private String ime;
    private String spletnaStran;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getSpletnaStran() {
        return spletnaStran;
    }

    public void setSpletnaStran(String spletnaStran) {
        this.spletnaStran = spletnaStran;
    }
}
/*public record KinotekaDTO(String ime, String spletnaStran) {

    public Optional<Kinoteka> toKinoteka() {
        if(ime == null || spletnaStran == null) {
            return Optional.empty();
        }

        var kinoteka = new Kinoteka();
        kinoteka.setIme(ime);
        kinoteka.setSpletnaStran(spletnaStran);

        return Optional.of(kinoteka);
    }

    public Kinoteka toKinotekaClass() {
        Kinoteka k = new Kinoteka();
        k.setIme(ime);
        k.setSpletnaStran(spletnaStran);
        return k;
    }

    @Override
    public String ime() {
        return ime;
    }

    @Override
    public String spletnaStran() {
        return spletnaStran;
    }
}*/
