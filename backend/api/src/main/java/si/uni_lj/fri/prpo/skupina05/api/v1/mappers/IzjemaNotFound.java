package si.uni_lj.fri.prpo.skupina05.api.v1.mappers;

import si.uni_lj.fri.prpo.skupina05.storitve.dtos.izjeme.IzjemaNotFoundDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IzjemaNotFound implements ExceptionMapper<IzjemaNotFoundDTO> {

    @Override
    public Response toResponse(IzjemaNotFoundDTO izjema) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity("{\n\"message\": \"" + izjema.getMessage() + "\"}")
                .build();
    }
}
