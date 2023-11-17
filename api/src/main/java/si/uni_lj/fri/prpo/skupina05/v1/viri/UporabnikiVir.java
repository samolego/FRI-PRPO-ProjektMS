package si.uni_lj.fri.prpo.skupina05.v1.viri;

import si.uni_lj.fri.prpo.skupina05.storitve.dtos.UporabnikDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikiVir {

    @POST
    public Response dodaj(UporabnikDTO uporabnikDTO) {
        return Response.status(Response.Status.OK).build();
    }
}
