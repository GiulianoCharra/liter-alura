package oracle.alura.challenge.literalura.infrastructure.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import oracle.alura.challenge.literalura.infrastructure.client.dto.BookResponse;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

@Service
public class GutendexClientImpl implements GutendexClient {

    private static final String API_BASE_URL = "https://gutendex.com/books";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GutendexClientImpl(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<BookResponse> fetchBooks(String query) {
        try {
            // Construcción de la URL
            String url = API_BASE_URL + (query != null && !query.isEmpty() ? "?" + query : "");

            // Construcción de la solicitud HTTP
            HttpRequest request = HttpRequest.newBuilder()
                                             .uri(new URI(url))
                                             .GET()
                                             .header("Accept", "application/json")
                                             .build();

            // Envío de la solicitud y manejo de la respuesta
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Parsear el cuerpo de la respuesta a una lista de libros
                String body = response.body();
                var result = objectMapper.readTree(body).get("results");
                return objectMapper.readValue(result.toString(),
                                              objectMapper.getTypeFactory().constructCollectionType(List.class, BookResponse.class));
            } else {
                System.err.println("Error al obtener libros: " + response.statusCode());
                return Collections.emptyList();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}