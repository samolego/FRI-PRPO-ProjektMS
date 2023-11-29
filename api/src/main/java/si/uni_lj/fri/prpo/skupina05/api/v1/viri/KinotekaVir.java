package si.uni_lj.fri.prpo.skupina05.api.v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.uni_lj.fri.prpo.skupina05.entitete.Kinoteka;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.KinotekaZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.upravljanje.UpravljanjeKinotekZrno;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.KinotekaDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("kinoteke")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class KinotekaVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private KinotekaZrno kinotekaZrno;

    @Inject
    private UpravljanjeKinotekZrno upravljanjeKinotekZrno;

    @GET
    public Response getKinoteke() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Kinoteka> kinoteke = kinotekaZrno.getKinoteke(query);

        return Response.ok(kinoteke)
                .header("X-Total-Count", kinoteke.size())
                .build();
    }

    /*@GET
    @Path("{ime}")
    public Response getKinotekaByIme(@PathParam("ime") String ime) {
        var kinoteka = kinotekaZrno.getKinotekaByIme(ime);

        return kinoteka.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }*/

    @GET
    @Path("{id}")
    public Response getKinotekaById(@PathParam("id") int id) {
        var kinoteka = kinotekaZrno.getKinotekaById(id);

        return kinoteka.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response createKinoteka(KinotekaDTO kinotekaData) {
        var status = upravljanjeKinotekZrno.dodajKinoteko(kinotekaData);

        if (status) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /*@DELETE
    @Path("{ime}")
    public Response deleteKinoteka(@PathParam("ime") String ime) {
        var kinoteka = kinotekaZrno.getKinotekaByIme(ime);

        if(kinoteka.isPresent()) {
            kinotekaZrno.deleteKinotekaById(kinoteka.get().getId());
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }*/
    @DELETE
    @Path("{id}")
    public Response deleteKinoteka(@PathParam("id") int id) {
        var success = upravljanjeKinotekZrno.izbrisiKinoteko(id);

        if (!success) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().build();
    }

    @PUT
    public Response updateNameKinoteka(KinotekaDTO kinotekaData) {

        var success = upravljanjeKinotekZrno.spremeniIme(kinotekaData);
        if (!success) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }
}
