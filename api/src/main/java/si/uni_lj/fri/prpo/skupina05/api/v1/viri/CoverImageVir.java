package si.uni_lj.fri.prpo.skupina05.api.v1.viri;

import si.uni_lj.fri.prpo.skupina05.api.imdb.ImdbResponse;
import si.uni_lj.fri.prpo.skupina05.storitve.beans.FilmZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.logging.Logger;

@ApplicationScoped
@Path("cover-image")
// Serve image type
@Produces("image/*")
public class CoverImageVir {

    private static final String RAPID_API = "https://imdb188.p.rapidapi.com/api/v1/searchIMDB?query=";

    private static final String API_KEY;

    static {
        // Read API key from environment variable
        API_KEY = System.getenv("RAPID_API_KEY");
    }

    private final Logger log = Logger.getLogger(CoverImageVir.class.getName());
    @Inject
    private FilmZrno filmiZrno;

    private final HashMap<Integer, String> imageCache = new HashMap<>();

    @GET
    @Path("{id}")
    public Response getCoverImage(@PathParam("id") int id) {
        if (API_KEY == null) {
            log.severe("RAPID API key is not set! Please set it in the environment variable RAPID_API_KEY.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        var film = filmiZrno.getFilmById(id);

        final String image;
        if (film.isPresent()) {
            final var flm = film.get();
            final var client = ClientBuilder.newClient();

            // Check cache
            if (imageCache.containsKey(flm.getId())) {
                image = imageCache.get(flm.getId());
            } else {
                // Request to api using JAX-RS
                var response = client.target(RAPID_API + flm.getIme())
                        .request()
                        .header("X-RapidAPI-Key", API_KEY)
                        .header("X-RapidAPI-Host", "imdb188.p.rapidapi.com")
                        .get();

                // Parse to json
                if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                    log.warning("Failed to get cover image from IMDB API! Response: " + response.readEntity(String.class));
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                }
                var imdbResponse = response.readEntity(ImdbResponse.class);
                image = imdbResponse.data.get(0).image;

                // Cache image
                imageCache.put(flm.getId(), image);
            }

            // Download image and serve it
            return client.target(image)
                    .request()
                    .get();
        }

        return film.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }
}
