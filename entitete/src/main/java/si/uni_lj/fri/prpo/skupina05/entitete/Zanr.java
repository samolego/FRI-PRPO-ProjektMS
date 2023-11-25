package si.uni_lj.fri.prpo.skupina05.entitete;


import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "zanr")
@NamedQueries(value = {
        @NamedQuery(name = "Zanr.getAll", query = "SELECT z FROM zanr z"),
        @NamedQuery(name = "Zanr.getIme", query = "SELECT z.ime FROM zanr z WHERE z.id = :id"),
        @NamedQuery(name = "Zanr.getFilmi", query = "SELECT z.filmi FROM zanr z WHERE z.id = :id"),
        @NamedQuery(name = "Zanr.getByIme", query = "SELECT z FROM zanr z WHERE z.ime = :ime")
}
)
public class Zanr implements IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;

    // Ko izbrišemo žanr, se izbrišejo tudi filmi, ki imajo ta žanr
    @OneToMany(mappedBy = "zanr", cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @JsonbTransient
    private List<Film> filmi;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getIme() { return ime; }

    public void setIme(String ime) { this.ime = ime; }

    public List<Film> getFilmi() {
        if (filmi == null)
            this.filmi = new ArrayList<>();
        return filmi;
    }

    public void setFilmi(List<Film> filmi) {
        this.filmi = filmi;
    }
}
