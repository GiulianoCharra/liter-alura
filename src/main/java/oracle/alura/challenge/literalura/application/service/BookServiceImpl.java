package oracle.alura.challenge.literalura.application.service;

import lombok.RequiredArgsConstructor;
import oracle.alura.challenge.literalura.domain.entities.Author;
import oracle.alura.challenge.literalura.domain.entities.Book;
import oracle.alura.challenge.literalura.domain.repositories.AuthorRepository;
import oracle.alura.challenge.literalura.domain.repositories.BookRepository;
import oracle.alura.challenge.literalura.infrastructure.client.GutendexClient;
import oracle.alura.challenge.literalura.infrastructure.client.dto.AuthorResponse;
import oracle.alura.challenge.literalura.infrastructure.client.dto.BookResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final GutendexClient gutendexClient;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public Book searchBook(String query) {
        // Busca libros en la base de datos antes de consultar la API externa
        Optional<Book> existingBook = bookRepository.findByTitleContains(query);
        if (existingBook.isPresent()) {
            return existingBook.get();
        }

        // Busca en la API externa
        Optional<BookResponse> firstBook = gutendexClient.fetchBooks("search=" + query)
                                                         .stream()
                                                         .findFirst();

        if (firstBook.isEmpty()) {
            return null;
        }

        BookResponse bookResponse = firstBook.get();
        AuthorResponse authorResponse = bookResponse.authors()
                                                    .getFirst(); // Primer autor

        // Guarda el autor si no existe
        Author author = authorRepository.findByName(authorResponse.name())
                                        .orElseGet(() -> authorRepository.save(
                                                new Author(authorResponse.name(),
                                                           authorResponse.birthYear(),
                                                           authorResponse.deathYear()
                                                )
                                                                              ));

        // Guarda y retorna el libro
        Book book = new Book(
                bookResponse.id(),
                bookResponse.title(),
                author,
                bookResponse.languages()
                            .getFirst()
        );

        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    @Override
    public List<Author> getAuthorsAliveInYear(int year) {
        return authorRepository.findLivingAuthorsInYear(year);
    }
    @Override
    public List<Book> getAllBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }
}
