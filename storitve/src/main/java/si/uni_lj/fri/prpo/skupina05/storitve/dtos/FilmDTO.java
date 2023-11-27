package si.uni_lj.fri.prpo.skupina05.storitve.dtos;

import java.util.Date;


public class FilmDTO {
    private String name;
    private String opis;
    private Date datumIzida;
    private String zanrId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDatumIzida() {
        return datumIzida;
    }

    public void setDatumIzida(Date datumIzida) {
        this.datumIzida = datumIzida;
    }

    public String getZanrId() {
        return zanrId;
    }

    public void setZanrId(String zanrId) {
        this.zanrId = zanrId;
    }
}