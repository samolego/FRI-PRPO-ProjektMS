package si.uni_lj.fri.prpo.skupina05.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
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
@CrossOrigin(supportedMethods = "GET, POST, HEAD, DELETE, OPTIONS")
public class ZanrVir {

    @Context
    private UriInfo uriInfo;

    @Inject
    private ZanrZrno zanrZrno;

    @Inject
    private UpravljanjeZanrovZrno upravljanjeZanrovZrno;

    @GET
    @Operation(summary = "Get all genres", description = "Returns all genres.")
    @APIResponses({
            @APIResponse(description = "OK with all genres", responseCode = "200", content = @Content(schema = @Schema(
                    implementation = Zanr.class
            )))
    })
    public Response getZanri() {
        final var query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Zanr> zanri = zanrZrno.getZanri(query);

        return Response.ok(zanri)
                .header("X-Total-Conut", zanrZrno.getZanriCount(query))
                .build();
    }

    @GET
    @Operation(summary = "Get genre by ID", description = "Returns a single genre by its ID.")
    @APIResponses({
            @APIResponse(description = "OK with the genre", responseCode = "200", content = @Content(schema = @Schema(
                    implementation = Zanr.class
            ))),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    @Path("{id}")
    public Response getZanrById(@PathParam("id") int id) {
        var zanr = zanrZrno.getZanrById(id);

        return zanr.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Operation(summary = "Add new genre", description = "Adds new genre using entered parameters.")
    @APIResponses({
            @APIResponse(description = "Created", responseCode = "201"),
            @APIResponse(description = "Bad Request", responseCode = "400")
    })
    public Response createZanr(ZanrDTO zanrData) {
        var success = upravljanjeZanrovZrno.dodajZanr(zanrData);

        if(success) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Operation(summary = "Delete genre", description = "Deletes a genre using ID.")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200"),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    @Path("{id}")
    public Response deleteZanr(@PathParam("id") int id) {
        var success = upravljanjeZanrovZrno.izbrisiZanr(id);

        if(!success) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().build();
    }

    @PUT
    @Operation(summary = "Change genre properties", description = "Change genre properties using ID.")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200"),
            @APIResponse(description = "Not found", responseCode = "404"),
            @APIResponse(description = "Bad Request", responseCode = "400")
    })
    @Path("{id}")
    public Response updateZanr(@PathParam("id") int id, ZanrDTO zanrData) {
        var success = upravljanjeZanrovZrno.posodobiZanr(id, zanrData);

        if(!success) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().build();

    }

}
