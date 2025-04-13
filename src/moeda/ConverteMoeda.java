package moeda;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConverteMoeda {
  public MoedaRecord buscaMoeda(String moeda){
      URI pegaMoeda = URI.create("https://v6.exchangerate-api.com/v6/44bc5839cb20957e3dec4744/latest/" + moeda);
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder().uri(pegaMoeda).build();
      HttpResponse<String> response = null;

      try {
          response = HttpClient.newHttpClient().send(request,HttpResponse.BodyHandlers.ofString());
      } catch (IOException | InterruptedException e) {
          throw new RuntimeException("Não foi possivel fazer a consulta da moeda!");
      }
      return new Gson().fromJson(response.body(), MoedaRecord.class);
  }
}
