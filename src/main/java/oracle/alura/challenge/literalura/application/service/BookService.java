package oracle.alura.challenge.literalura.application.service;

import oracle.alura.challenge.literalura.domain.entities.Author;
import oracle.alura.challenge.literalura.domain.entities.Book;

import java.util.List;

/**
 * Servicio para la gestión de libros y autores.
 */
public interface BookService {

    /**
     * Busca un libro en la API externa.
     *
     * @param query Título o autor del libro.
     * @return El libro encontrado o null si no existe.
     */
    Book searchBook(String query);

    /**
     * Mostrar todos los libros.
     *
     * @return Lista de libros.
     */
    List<Book> getAllBooks();

    /**
     * Mostrar todos los autores.
     *
     * @return Lista de autores.
     */
    List<Author> getAllAuthors();

    /**
     * Mostrar autores vivos en un año determinado.
     *
     * @param year El año de consulta.
     * @return Lista de autores vivos en ese año.
     */
    List<Author> getAuthorsAliveInYear(int year);

    /**
     * Mostrar todos los libros en un idioma determinado.
     *
     * @param language El idioma de consulta.
     * @return Lista de libros en ese idioma.
     */
    List<Book> getAllBooksByLanguage(String language);
}
