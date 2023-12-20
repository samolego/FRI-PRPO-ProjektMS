package si.uni_lj.fri.prpo.skupina05.storitve.dtos.izjeme;

public class IzjemaBadRequestDTO extends RuntimeException {

    public IzjemaBadRequestDTO(String msg) { super(msg); }
}
