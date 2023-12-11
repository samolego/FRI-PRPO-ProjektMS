package si.uni_lj.fri.prpo.skupina05.api.imdb;

import java.util.List;

public class ImdbResponse {
    public boolean status;
    public String message;
    public long timestamp;
    public List<ImdbData> data;

    public static class ImdbData {
        public String id;
        public String qid;
        public String title;
        public short year;
        public String stars;
        public String image;
    }
}
