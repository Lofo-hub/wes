package client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.*;
import java.util.List;
import model.Note;
import util.Config;

public class NotesClient {
    public List<Note> getNotes(int idEtudiant) throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(Config.NOTES_URL + idEtudiant)).build();
        HttpResponse<String> rep = HttpClient.newHttpClient()
                .send(req, HttpResponse.BodyHandlers.ofString());
        Type type = new TypeToken<List<Note>>(){}.getType();
        return new Gson().fromJson(rep.body(), type);
    }
}