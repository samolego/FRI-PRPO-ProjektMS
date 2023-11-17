package si.uni_lj.fri.prpo.skupina05.v1.viri;

import si.uni_lj.fri.prpo.skupina05.storitve.dtos.KinotekaDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("kinoteke")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class KinotekeVir {
    @POST
    public Response dodaj(KinotekaDTO kinotekaDTO) {
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("{id}")
    public Response spremeniIme(KinotekaDTO kinotekaDTO) {
        return Response.status(Response.Status.OK).build();
    }
}
