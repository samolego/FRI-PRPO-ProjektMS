package si.uni_lj.fri.prpo.skupina05.api.v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.uni_lj.fri.prpo.skupina05.entitete.Uporabnik;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.UporabnikZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.upravljanje.UpravljanjeUporabnikovZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.UporabnikDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikVir {

    @Context
    private UriInfo uriInfo;

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Inject
    private UpravljanjeUporabnikovZrno upravljanjeUporabnikovZrno;

    @GET
    public Response getUporabniki() {
        final var query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = uporabnikZrno.getUporabniki(query);

        return Response.ok(uporabniki)
                .header("X-Total-Count", uporabnikZrno.getUporabnikiCount(query))
                .build();
    }

    @GET
    @Path("{id}")
    public Response getUporabnikById(@PathParam("id") int id) {
        var uporabnik = uporabnikZrno.getUporabnikById(id);

        return uporabnik.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response addUporabnik(UporabnikDTO uporabnikData) {
        var success = upravljanjeUporabnikovZrno.dodajUporabnika(uporabnikData);

        if(success) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUporabnik(@PathParam("id") int id) {

        var success = upravljanjeUporabnikovZrno.izbrisiUporabnika(id);
        if(!success) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();

    }

    @PUT
    @Path("{id}")
    public Response updateUporabnik(@PathParam("id") int id, UporabnikDTO uporabnikData) {

        var success = upravljanjeUporabnikovZrno.posodobiUporabnika(id, uporabnikData);
        if(!success) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();

    }

}
