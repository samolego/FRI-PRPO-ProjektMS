package si.uni_lj.fri.prpo.skupina05.entitete;


import org.eclipse.persistence.internal.expressions.SQLUpdateAllStatementForOracleAnonymousBlock;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "film")
@NamedQueries(value =
        {
                @NamedQuery(name = "Film.getAll", query = "SELECT f FROM film f"),
                @NamedQuery(name = "Film.getAllRatedAs", query = "SELECT f FROM film f WHERE f.rating = :rating"),
                @NamedQuery(name = "Film.getAllWithGenre", query = "SELECT f.ime, g.ime FROM film f INNER JOIN zanr g ON f.zanr=g WHERE g.ime= :zanr"),
                @NamedQuery(name = "Film.getGenre", query = "SELECT f.ime, g.ime FROM film f INNER JOIN zanr g ON f.zanr=g where f.ime = :ime")

        })

public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;

    private String opis;

    private Integer rating;

    private Date datimIzida;

    @ManyToOne
    @JoinColumn(name = "zanr_id")
    private Zanr zanr;

    @ManyToMany
    @JoinColumn(name = "uporabnik_id")
    private List<Uporabnik> uporabnikiPogledano;

    @ManyToMany
    @JoinColumn(name = "uporabnik_id")
    private List<Uporabnik> uporabnikiVsec;

    @ManyToMany
    @JoinColumn(name = "kinoteka_id")
    private List<Kinoteka> kinoteke;

    // getter in setter metode

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getIme() { return ime; }

    public void setIme(String ime) { this.ime = ime; }

    public String getOpis() { return opis; }

    public void setOpis(String opis) { this.opis = opis; }

    public Integer getRating() { return rating; }

    public void setRating(Integer rating) { this.rating = rating; }

    public Date getDatimIzida() { return datimIzida; }

    public void setDatimIzida(Date datimIzida) {
        this.datimIzida = datimIzida;
    }

    public Zanr getZanr() { return zanr; }

    public void setZanr(Zanr zanr) { this.zanr = zanr; }

    public List<Uporabnik> getUporabnikiPogledano() {
        return uporabnikiPogledano;
    }

    public void setUporabnikiPogledano(List<Uporabnik> uporabnikiPogledano) {
        this.uporabnikiPogledano = uporabnikiPogledano;
    }

    public List<Uporabnik> getUporabnikiVsec() {
        return uporabnikiVsec;
    }

    public void setUporabnikiVsec(List<Uporabnik> uporabnikiVsec) {
        this.uporabnikiVsec = uporabnikiVsec;
    }

    public List<Kinoteka> getKinoteke() {
        return kinoteke;
    }

    public void setKinoteke(List<Kinoteka> kinoteke) {
        this.kinoteke = kinoteke;
    }
}
