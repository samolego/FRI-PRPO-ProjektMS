package si.uni_lj.fri.prpo.skupina05.entitete;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "zanr")
@NamedQueries(value = {
        @NamedQuery(name = "Zanr.getAll", query = "SELECT z FROM zanr z"),
        @NamedQuery(name = "Zanr.getIme", query = "SELECT z.ime FROM zanr z WHERE z.id = :id"),
        @NamedQuery(name = "Zanr.getFilmi", query = "SELECT z.filmi FROM zanr z WHERE z.id = :id"),
}
)
public class Zanr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Film> filmi;

    // getter in setter metode

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
