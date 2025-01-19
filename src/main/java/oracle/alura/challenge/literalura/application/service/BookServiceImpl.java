package oracle.alura.challenge.literalura.application.service;

import oracle.alura.challenge.literalura.infrastructure.client.GutendexClient;
import oracle.alura.challenge.literalura.infrastructure.client.dto.BookResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final GutendexClient gutendexClient;

    public BookServiceImpl(GutendexClient gutendexClient) {
        this.gutendexClient = gutendexClient;
    }

    @Override
    public List<BookResponse> searchBooks(String query) {
        return gutendexClient.fetchBooks("search=" + query);
    }

    @Override
    public BookResponse getBookDetails(int bookId) {
        List<BookResponse> books = gutendexClient.fetchBooks("ids=" + bookId);
        return books.isEmpty() ? null : books.getFirst();
    }

    @Override
    public boolean saveBookToFavorites(int bookId) {
        // Implementar lógica para guardar en la base de datos.
        // Retornar true si se guarda correctamente, false en caso contrario.
        return false;
    }
}