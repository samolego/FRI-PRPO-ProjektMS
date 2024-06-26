package si.uni_lj.fri.prpo.skupina05.entitete;


import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "kinoteka")
@NamedQueries(value =
        {
                @NamedQuery(name = "Kinoteka.getAll", query = "SELECT k FROM kinoteka k"),
                @NamedQuery(name = "Kinoteka.getName", query = "SELECT k.ime FROM kinoteka k WHERE k.id = :id"),

                @NamedQuery(name = "Kinoteka.getSpletnaStran", query = "SELECT k.spletnaStran FROM kinoteka k WHERE k.id = :id"),
                @NamedQuery(name = "Kinoteka.getFilmi", query = "SELECT k.filmi FROM kinoteka k WHERE k.id = :id"),
                @NamedQuery(name = "Kinoteka.getByIme", query = "SELECT k FROM kinoteka k WHERE k.ime = :ime"),
                @NamedQuery(name = "Kinoteka.getBySpletnaStran", query = "SELECT k FROM kinoteka k WHERE k.spletnaStran = :spletnaStran"),
        }
)
public class Kinoteka implements IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;

    @Column(name = "spletna_stran")
    private String spletnaStran;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "film_id")
    @JsonbTransient
    private List<Film> filmi;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getIme() { return ime; }

    public void setIme(String ime) { this.ime = ime; }

    public String getSpletnaStran() { return spletnaStran; }

    public void setSpletnaStran(String spletnaStran) {
        this.spletnaStran = spletnaStran;
    }

    public List<Film> getFilmi() {
        if (filmi == null)
            this.filmi = new ArrayList<>();
        return filmi;
    }

    public void setFilmi(List<Film> filmi) {
        this.filmi = filmi;
    }
}