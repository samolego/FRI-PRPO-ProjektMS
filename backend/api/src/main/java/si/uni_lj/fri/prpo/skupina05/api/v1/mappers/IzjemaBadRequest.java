package si.uni_lj.fri.prpo.skupina05.api.v1.mappers;

import si.uni_lj.fri.prpo.skupina05.storitve.dtos.izjeme.IzjemaBadRequestDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IzjemaBadRequest implements ExceptionMapper<IzjemaBadRequestDTO> {

    @Override
    public Response toResponse(IzjemaBadRequestDTO izjema) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\n\"message\": \"" + izjema.getMessage() + "\"}")
                .build();
    }
}
