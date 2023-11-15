package si.uni_lj.fri.prpo.skupina05.storitve.dtos;

import si.uni_lj.fri.prpo.skupina05.entitete.Uporabnik;

import java.util.Optional;

public record UporabnikDTO(String ime, String priimek, String uporabniskoIme, String email, String geslo) {

    public Optional<Uporabnik> toUporabnik() {
        if(ime == null ||
                priimek == null ||
                uporabniskoIme == null ||
                email == null ||
                geslo == null
        ) {
            return Optional.empty();
        }

        var uporabnik = new Uporabnik();
        uporabnik.setIme(ime);
        uporabnik.setPriimek(priimek);
        uporabnik.setUporabniskoIme(uporabniskoIme);
        uporabnik.setEmail(email);
        uporabnik.setGeslo(geslo);

        return Optional.of(uporabnik);
    }

    @Override
    public String email() {
        return email;
    }
}
