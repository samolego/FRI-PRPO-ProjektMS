package si.uni_lj.fri.prpo.skupina05.api.v1;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("v1")
@OpenAPIDefinition(info = @Info(title = "PriporocilniSeznamApi", version = "v1.0.0"))
public class PriporocilniSeznamApplication extends Application {
}
