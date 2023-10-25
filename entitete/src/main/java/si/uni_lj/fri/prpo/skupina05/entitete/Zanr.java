package si.uni_lj.fri.prpo.skupina05.entitete;

@Entity(name = "zanr")
public class Zanr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;

    @OneToMany
    @JoinColumn(name = "")
    private Zanr zanr;

}
