package client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.*;
import java.util.List;
import model.Province;
import util.Config;

public class ProvinceClient {
    public List<Province> getProvinces() throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(Config.PROVINCES_URL)).build();
        HttpResponse<String> rep = HttpClient.newHttpClient()
                .send(req, HttpResponse.BodyHandlers.ofString());
        Type type = new TypeToken<List<Province>>(){}.getType();
        List <Province> provinces = new Gson().fromJson(rep.body(), type);

        return provinces;
    }
}