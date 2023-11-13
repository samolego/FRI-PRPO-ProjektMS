package si.uni_lj.fri.prpo.skupina05.storitve.dtos;

import si.uni_lj.fri.prpo.skupina05.entitete.Zanr;

import java.util.Optional;

public record ZanrDTO(String name) {

    public Optional<Zanr> toZanr() {
        if (name == null || name.isBlank()) {
            return Optional.empty();
        }

        var zanr = new Zanr();
        zanr.setIme(name);

        return Optional.of(zanr);
    }
}
