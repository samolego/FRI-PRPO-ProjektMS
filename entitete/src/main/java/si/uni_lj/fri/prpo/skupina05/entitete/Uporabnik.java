package si.uni_lj.fri.prpo.skupina05.entitete;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM uporabnik u"),
                @NamedQuery(name = "Uporabnik.getUsernameFromId", query = "SELECT u.uporabniskoIme FROM uporabnik u WHERE u.id = :id"),

                @NamedQuery(name = "Uporabnik.getIme", query = "SELECT u.ime FROM uporabnik u WHERE u.id = :id"),
                @NamedQuery(name = "Uporabnik.getPriimek", query = "SELECT u.priimek FROM uporabnik u WHERE u.id = :id"),
                @NamedQuery(name = "Uporabnik.getAllEmails", query = "SELECT u.email FROM uporabnik u"),
        }
)
public class Uporabnik implements IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;

    private String priimek;

    @Column(name = "uporabnisko_ime")
    private String uporabniskoIme;

    private String email;

    private String geslo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "film_id")
    private List<Film> filmiPogledano;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "film_id")
    private List<Film> filmiVsec;


    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getIme() { return ime; }

    public void setIme(String ime) { this.ime = ime; }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getUporabniskoIme() {
        return uporabniskoIme;
    }

    public void setUporabniskoIme(String uporabniskoIme) {
        this.uporabniskoIme = uporabniskoIme;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<Film> getFilmiPogledano() {
        if (filmiPogledano == null)
            this.filmiPogledano = new ArrayList<>();
        return filmiPogledano;
    }

    public void setFilmiPogledano(List<Film> filmiPogledano) {
        this.filmiPogledano = filmiPogledano;
    }

    public List<Film> getFilmiVsec() {
        if (filmiVsec == null)
            this.filmiVsec = new ArrayList<>();
        return filmiVsec;
    }

    public void setFilmiVsec(List<Film> filmiVsec) {
        this.filmiVsec = filmiVsec;
    }

    public String getGeslo() {
        return geslo;
    }

    public void setGeslo(String geslo) {
        this.geslo = geslo;
    }
}