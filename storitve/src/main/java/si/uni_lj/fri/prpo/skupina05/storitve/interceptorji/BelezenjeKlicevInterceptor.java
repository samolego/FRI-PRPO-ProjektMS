package si.uni_lj.fri.prpo.skupina05.storitve.interceptorji;

import si.uni_lj.fri.prpo.skupina05.storitve.anotacije.BeleziKlice;
import si.uni_lj.fri.prpo.skupina05.storitve.dtos.FilmDTO;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@BeleziKlice
public class BelezenjeKlicevInterceptor {

    private final Logger LOG = Logger.getLogger(BelezenjeKlicevInterceptor.class.getName());

    @AroundInvoke
    public Object beleziKlice(InvocationContext context) throws Exception {
        if (context.getParameters().length == 1 && context.getParameters()[0] instanceof FilmDTO filmDTO && filmDTO.toFilm().isEmpty()) {
            final var msg = "Podan film ne vsebuje vseh obveznih podatkov!";
            LOG.severe(msg);

            throw new IllegalArgumentException(msg);
        }

        return context.proceed();
    }
}
