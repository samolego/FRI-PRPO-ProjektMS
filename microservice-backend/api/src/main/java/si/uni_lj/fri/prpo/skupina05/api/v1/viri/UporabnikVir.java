package si.uni_lj.fri.prpo.skupina05.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
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
@CrossOrigin(supportedMethods = "GET, POST, HEAD, DELETE, OPTIONS")
@ApplicationScoped
public class UporabnikVir {

    @Context
    private UriInfo uriInfo;

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Inject
    private UpravljanjeUporabnikovZrno upravljanjeUporabnikovZrno;

    @GET
    @Operation(summary = "Get all users", description = "Returns all users.")
    @APIResponses({
            @APIResponse(description = "OK with all users", responseCode = "200", content = @Content(schema = @Schema(
                    implementation = Uporabnik.class
            )))
    })
    public Response getUporabniki() {
        final var query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = uporabnikZrno.getUporabniki(query);

        return Response.ok(uporabniki)
                .header("X-Total-Count", uporabnikZrno.getUporabnikiCount(query))
                .build();
    }

    @GET
    @Operation(summary = "Get user by ID", description = "Returns a single user by its ID.")
    @APIResponses({
            @APIResponse(description = "OK with the user", responseCode = "200", content = @Content(schema = @Schema(
                    implementation = Uporabnik.class
            ))),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    @Path("{id}")
    public Response getUporabnikById(@PathParam("id") int id) {
        var uporabnik = uporabnikZrno.getUporabnikById(id);

        return uporabnik.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Operation(summary = "Add new user", description = "Adds new user using entered parameters.")
    @APIResponses({
            @APIResponse(description = "Created", responseCode = "201"),
            @APIResponse(description = "Bad Request", responseCode = "400")
    })
    public Response addUporabnik(UporabnikDTO uporabnikData) {
        var success = upravljanjeUporabnikovZrno.dodajUporabnika(uporabnikData);

        if(success) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Operation(summary = "Delete user", description = "Deletes a user using ID.")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200"),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    @Path("{id}")
    public Response deleteUporabnik(@PathParam("id") int id) {

        var success = upravljanjeUporabnikovZrno.izbrisiUporabnika(id);
        if(!success) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();

    }

    @PUT
    @Operation(summary = "Change user properties", description = "Change user properties using ID.")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200"),
            @APIResponse(description = "Not found", responseCode = "404"),
            @APIResponse(description = "Bad Request", responseCode = "400")
    })
    @Path("{id}")
    public Response updateUporabnik(@PathParam("id") int id, UporabnikDTO uporabnikData) {

        var success = upravljanjeUporabnikovZrno.posodobiUporabnika(id, uporabnikData);
        if(!success) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();

    }

}
