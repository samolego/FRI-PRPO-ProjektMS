package si.uni_lj.fri.prpo.skupina05.entitete;


import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "film")
@NamedQueries(value =
        {
                @NamedQuery(name = "Film.getAll", query = "SELECT f FROM film f"),
                @NamedQuery(name = "Film.getAllRatedAs", query = "SELECT f FROM film f WHERE f.rating = :rating"),
                @NamedQuery(name = "Film.getAllWithGenre", query = "SELECT f.ime, g.ime FROM film f INNER JOIN zanr g ON f.zanr=g WHERE g.ime= :zanr"),
                @NamedQuery(name = "Film.getGenre", query = "SELECT f.ime, g.ime FROM film f INNER JOIN zanr g ON f.zanr=g where f.ime = :ime"),

                @NamedQuery(name = "Film.getAllMinRating", query = "SELECT f FROM film f WHERE f.rating >= :rating"),
                @NamedQuery(name = "Film.getAllMaxRating", query = "SELECT f FROM film f WHERE f.rating <= :rating"),
                @NamedQuery(name = "Film.getDate", query = "SELECT f FROM film f WHERE f.datumIzida = :datumIzida"),
                @NamedQuery(name = "Film.getByIme", query = "SELECT f FROM film f WHERE f.ime = :ime"),
        })
public class Film implements IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;

    private String opis;

    private Integer rating;

    @Column(name = "datum_izida")
    private Date datumIzida;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "zanr_id")
    //@JsonbTransient
    private Zanr zanr;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "uporabnik_id")
    //@JsonbTransient
    private List<Uporabnik> uporabnikiPogledano;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "uporabnik_id")
    //@JsonbTransient
    private List<Uporabnik> uporabnikiVsec;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "kinoteka_id")
    private List<Kinoteka> kinoteke;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getIme() { return ime; }

    public void setIme(String ime) { this.ime = ime; }

    public String getOpis() { return opis; }

    public void setOpis(String opis) { this.opis = opis; }

    public Integer getRating() { return rating; }

    public void setRating(Integer rating) { this.rating = rating; }

    public Date getDatumIzida() {
        return datumIzida;
    }

    public void setDatumIzida(Date datumIzida) {
        this.datumIzida = datumIzida;
    }

    public Zanr getZanr() { return zanr; }

    public void setZanr(Zanr zanr) { this.zanr = zanr; }

    public List<Uporabnik> getUporabnikiPogledano() {
        if (uporabnikiPogledano == null)
            this.uporabnikiPogledano = new ArrayList<>();
        return uporabnikiPogledano;
    }

    public void setUporabnikiPogledano(List<Uporabnik> uporabnikiPogledano) {
        this.uporabnikiPogledano = uporabnikiPogledano;
    }

    public List<Uporabnik> getUporabnikiVsec() {
        if (uporabnikiVsec == null)
            this.uporabnikiVsec = new ArrayList<>();
        return uporabnikiVsec;
    }

    public void setUporabnikiVsec(List<Uporabnik> uporabnikiVsec) {
        this.uporabnikiVsec = uporabnikiVsec;
    }

    public List<Kinoteka> getKinoteke() {
        if (kinoteke == null)
            this.kinoteke = new ArrayList<>();
        return kinoteke;
    }

    public void setKinoteke(List<Kinoteka> kinoteke) {
        this.kinoteke = kinoteke;
    }
}
