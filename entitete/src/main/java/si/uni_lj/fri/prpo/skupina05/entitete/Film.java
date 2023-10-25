package si.uni_lj.fri.prpo.skupina05.entitete;

@Entity(name = "film")
@NamedQueries(value =
        {
                @NamedQuery(name = "Film.getAll", query = "SELECT f FROM film f")
        })

public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;

    private String opis;
    private Integer rating;
    @ManyToOne
    @JoinColumn(name = "zanr_id")
    private Zanr zanr;

    // getter in setter metode
}
