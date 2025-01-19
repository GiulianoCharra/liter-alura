package oracle.alura.challenge.literalura.application.service;

import oracle.alura.challenge.literalura.infrastructure.client.dto.BookResponse;

import java.util.List;

public interface BookService {
    List<BookResponse> searchBooks(String query);

    BookResponse getBookDetails(int bookId);

    boolean saveBookToFavorites(int bookId);
}