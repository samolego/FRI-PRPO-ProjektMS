package si.uni_lj.fri.prpo.skupina05.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.uni_lj.fri.prpo.skupina05.entitete.Film;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.FilmZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.upravljanje.UpravljanjeFilmovZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.FilmDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("filmi")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, HEAD, DELETE, OPTIONS")
public class FilmVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private FilmZrno filmiZrno;

    @Inject
    private UpravljanjeFilmovZrno upravljanjeFilmovZrno;

    @GET
    @Operation(summary = "Get all films", description = "Returns all films.")
    @APIResponses({
            @APIResponse(description = "OK with all films", responseCode = "200", content = @Content(schema = @Schema(
                    implementation = Film.class
            )))
    })
    public Response getFilmi() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Film> filmi = filmiZrno.getFilmi(query);
        long count = filmiZrno.getFilmiCount(query);

        return Response.ok(filmi)
                .header("X-Total-Count", count)
                .build();
    }


    @GET
    @Operation(summary = "Get film by ID", description = "Returns a single film by its ID.")
    @APIResponses({
            @APIResponse(description = "OK with the film", responseCode = "200", content = @Content(schema = @Schema(
                    implementation = Film.class
            ))),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    @Path("{id}")
    public Response getFilmById(@PathParam("id") int id) {
        var film = filmiZrno.getFilmById(id);

        return film.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }


    @POST
    @Operation(summary = "Add new film", description = "Adds new film using entered parameters.")
    @APIResponses({
            @APIResponse(description = "Created", responseCode = "201"),
            @APIResponse(description = "Bad Request", responseCode = "400"),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    public Response createFilm(FilmDTO filmData) {
        var status = upravljanjeFilmovZrno.dodajFilm(filmData);

        if (status) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


    @DELETE
    @Operation(summary = "Delete film", description = "Deletes a film using ID.")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200"),
            @APIResponse(description = "Not found", responseCode = "404")
    })
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
    @Operation(summary = "Change film properties", description = "Change film properties using ID.")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200"),
            @APIResponse(description = "Not found", responseCode = "404"),
            @APIResponse(description = "Bad Request", responseCode = "400")
    })
    @Path("{id}")
    public Response updateFilm(@PathParam("id") int id, FilmDTO filmData) {
        var film = upravljanjeFilmovZrno.toFilm(filmData);

        if (film.isPresent()) {
            var success = upravljanjeFilmovZrno.posodobiFilm(id, filmData);
            return Response.ok().build();
            //return Response.ok(success).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
