package oracle.alura.challenge.literalura.infrastructure.client;

import oracle.alura.challenge.literalura.infrastructure.client.dto.BookResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Procesador de libros.
 */
@Component
public class BookProcessor {

    /**
     * Formatea una lista de libros para su impresión.
     *
     * @param books Lista de libros a formatear.
     */
    public void displayBooks(List<BookResponse> books) {
        if (books.isEmpty()) {
            System.out.println("No hay libros disponibles.");
        } else {
            books.forEach(this::displayBook);
        }
    }

    /**
     * Muestra la información detallada de un libro.
     *
     * @param book Libro a mostrar.
     */
    public void displayBook(BookResponse book) {
        System.out.println("Título: " + book.title());
        System.out.println("Autores:");
        book.authors()
            .forEach(author ->
                             System.out.println(" - " + author.name() +
                                                        (author.birthYear() != null ? " (Nacido en " + author.birthYear() + ")" : ""))
                    );
        System.out.println("Idiomas: " + String.join(", ",
                                                     book.languages()
                                                    ));
        System.out.println("Descargas: " + book.downloadCount());
        System.out.println("----");
    }
}