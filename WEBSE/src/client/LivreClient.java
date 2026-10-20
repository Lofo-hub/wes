package client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.*;
import java.util.List;
import model.Livre;
import util.Config;

public class LivreClient {
    public List<Livre> getLivres() throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(Config.LIVRES_URL)).build();
        HttpResponse<String> rep = HttpClient.newHttpClient()
                .send(req, HttpResponse.BodyHandlers.ofString());
        Type type = new TypeToken<List<Livre>>(){}.getType();
        List <Livre> livres = new Gson().fromJson(rep.body(), type);
        return livres;
    }
}
