package client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.*;
import java.util.List;
import model.Age;
import util.Config;

public class AgeClient {
    public int getAges(int idEtudiant) throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(Config.NOTES_URL + idEtudiant)).build();
        HttpResponse<String> rep = HttpClient.newHttpClient()
                .send(req, HttpResponse.BodyHandlers.ofString());
        return new Gson().fromJson(rep.body(), Age.class).getAge();
    }
}