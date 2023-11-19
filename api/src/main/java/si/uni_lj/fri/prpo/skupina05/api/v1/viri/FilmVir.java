package si.uni_lj.fri.prpo.skupina05.api.v1.viri;

import si.uni_lj.fri.prpo.skupina05.entitete.Film;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.FilmZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.upravljanje.UpravljanjeFilmovZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.FilmDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("filmi")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FilmVir {

    @Inject
    private FilmZrno filmiZrno;

    @Inject
    private UpravljanjeFilmovZrno upravljanjeFilmovZrno;

    @GET
    public Response getFilmi() {
        List<Film> filmi = filmiZrno.getFilmi();

        return Response.ok(filmi)
                .header("X-Total-Count", filmi.size())
                .build();
    }


    @GET
    @Path("{id}")
    public Response getFilmById(@PathParam("id") int id) {
        var film = filmiZrno.getFilmById(id);

        return film.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response createFilm(FilmDTO filmData) {
        var status = upravljanjeFilmovZrno.dodajFilm(filmData);

        if (status) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


    @DELETE
    @Path("{id}")
    public Response deleteFilm(@PathParam("id") int id) {
        var film = filmiZrno.getFilmById(id);

        if (film.isPresent()) {
            filmiZrno.deleteFilmById(id);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("{id}")
    public Response updateFilm(@PathParam("id") int id, FilmDTO filmData) {
        var film = filmData.toFilm();

        if (film.isPresent()) {
            var success = upravljanjeFilmovZrno.posodobiFilm(id, filmData);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
