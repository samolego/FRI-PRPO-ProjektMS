package si.uni_lj.fri.prpo.skupina05.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
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
@CrossOrigin(supportedMethods = "GET, POST, HEAD, DELETE, OPTIONS")
@ApplicationScoped
public class KinotekaVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private KinotekaZrno kinotekaZrno;

    @Inject
    private UpravljanjeKinotekZrno upravljanjeKinotekZrno;

    @GET
    @Operation(summary = "Get all cinemas", description = "Returns all cinemas.")
    @APIResponses({
            @APIResponse(description = "OK with all cinemas", responseCode = "200", content = @Content(schema = @Schema(
                    implementation = Kinoteka.class
            )))
    })
    public Response getKinoteke() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Kinoteka> kinoteke = kinotekaZrno.getKinoteke(query);

        return Response.ok(kinoteke)
                .header("X-Total-Count", kinotekaZrno.getKinotekeCount(query))
                .build();
    }

    @GET
    @Operation(summary = "Get cinema by ID", description = "Returns a single cinema by its ID.")
    @APIResponses({
            @APIResponse(description = "OK with the cinema", responseCode = "200", content = @Content(schema = @Schema(
                    implementation = Kinoteka.class
            ))),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    @Path("{id}")
    public Response getKinotekaById(@PathParam("id") int id) {
        var kinoteka = kinotekaZrno.getKinotekaById(id);

        return kinoteka.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Operation(summary = "Add new cinema", description = "Adds new cinema using entered parameters.")
    @APIResponses({
            @APIResponse(description = "Created", responseCode = "201"),
            @APIResponse(description = "Bad Request", responseCode = "400")
    })
    public Response createKinoteka(KinotekaDTO kinotekaData) {
        var status = upravljanjeKinotekZrno.dodajKinoteko(kinotekaData);

        if (status) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Operation(summary = "Delete cinema", description = "Deletes a cinema using ID.")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200"),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    @Path("{id}")
    public Response deleteKinoteka(@PathParam("id") int id) {
        var success = upravljanjeKinotekZrno.izbrisiKinoteko(id);

        if (!success) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().build();
    }

    @PUT
    @Operation(summary = "Change cinema properties", description = "Change cinema properties using ID.")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200"),
            @APIResponse(description = "Not found", responseCode = "404"),
            @APIResponse(description = "Bad Request", responseCode = "400")
    })
    public Response updateNameKinoteka(KinotekaDTO kinotekaData) {

        var success = upravljanjeKinotekZrno.spremeniIme(kinotekaData);
        if (!success) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }
}
