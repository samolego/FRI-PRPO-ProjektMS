package si.uni_lj.fri.prpo.skupina05.api.v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.uni_lj.fri.prpo.skupina05.entitete.Zanr;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.ZanrZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.upravljanje.UpravljanjeZanrovZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.ZanrDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("zanri")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ZanrVir {

    @Context
    private UriInfo uriInfo;

    @Inject
    private ZanrZrno zanrZrno;

    @Inject
    private UpravljanjeZanrovZrno upravljanjeZanrovZrno;

    @GET
    public Response getZanri() {
        final var query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Zanr> zanri = zanrZrno.getZanri(query);

        return Response.ok(zanri)
                .header("X-Total-Conut", zanri.size())
                .build();
    }

    @GET
    @Path("{id}")
    public Response getZanrById(@PathParam("id") int id) {
        var zanr = zanrZrno.getZanrById(id);

        return zanr.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response createZanr(ZanrDTO zanrData) {
        var success = upravljanjeZanrovZrno.dodajZanr(zanrData);

        if(success) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteZanr(@PathParam("id") int id) {
        var success = upravljanjeZanrovZrno.izbrisiZanr(id);

        if(!success) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response updateZanr(@PathParam("id") int id, ZanrDTO zanrData) {
        var success = upravljanjeZanrovZrno.posodobiZanr(id, zanrData);

        if(!success) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().build();
        /*var zanr = zanrData.toZanr();

        if(zanr.isPresent()) {
            var success = upravljanjeZanrovZrno.posodobiZanr(id, zanrData);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();*/
    }

}
