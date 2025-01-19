package oracle.alura.challenge.literalura.infrastructure.client;

import oracle.alura.challenge.literalura.infrastructure.client.dto.BookResponse;

import java.util.List;

public interface GutendexClient {
    /**
     * Obtiene una lista de libros según los parámetros de consulta.
     *
     * @param query Parámetros de consulta para filtrar los libros.
     * @return Lista de libros obtenida de la API.
     */
    List<BookResponse> fetchBooks(String query);
}
