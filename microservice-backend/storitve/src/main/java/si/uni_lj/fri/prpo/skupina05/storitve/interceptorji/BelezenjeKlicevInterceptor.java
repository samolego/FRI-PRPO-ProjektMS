package si.uni_lj.fri.prpo.skupina05.storitve.interceptorji;

import si.uni_lj.fri.prpo.skupina05.storitve.anotacije.BeleziKlice;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Interceptor
@BeleziKlice
public class BelezenjeKlicevInterceptor {

    private final Logger LOG = Logger.getLogger(BelezenjeKlicevInterceptor.class.getName());

    private final Map<String, Integer> counters = new HashMap<>();

    @AroundInvoke
    public Object beleziKlice(InvocationContext context) throws Exception {
        final var method = context.getMethod().getName();
        final var counter = counters.getOrDefault(method, 0) + 1;
        counters.put(method, counter);

        LOG.info("Klic metode " + method + " (Klic je bil sedaj izveden " + counter + "-krat.)");

        return context.proceed();
    }
}
