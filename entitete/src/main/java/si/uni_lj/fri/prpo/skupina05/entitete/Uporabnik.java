package si.uni_lj.fri.prpo.skupina05.entitete;


import org.eclipse.persistence.internal.expressions.SQLUpdateAllStatementForOracleAnonymousBlock;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM uporabnik u"),
                @NamedQuery(name = "Uporabnik.getUsernameFromId", query = "SELECT u.uporabniskoIme FROM uporabnik u WHERE u.id = :id")
        })
public class Uporabnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;

    private String priiimek;

    private String uporabniskoIme;

    private String email;

    @ManyToMany
    @JoinColumn(name = "film_id")
    private List<Film> filmiPogledano;

    @ManyToMany
    @JoinColumn(name = "film_id")
    private List<Film> filmiVsec;

    // getter in setter metode

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getIme() { return ime; }

    public void setIme(String ime) { this.ime = ime; }

    public String getPriiimek() { return priiimek; }

    public void setPriiimek(String priiimek) { this.priiimek = priiimek; }

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
}