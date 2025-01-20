package oracle.alura.challenge.literalura.presentation.cli;

import oracle.alura.challenge.literalura.application.service.BookService;
import oracle.alura.challenge.literalura.domain.entities.Author;
import oracle.alura.challenge.literalura.domain.entities.Book;
import oracle.alura.challenge.literalura.infrastructure.client.BookProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class BookCLI implements CommandLineRunner {

    private final BookService bookService;

    private final BookProcessor bookProcessor;

    @Autowired
    public BookCLI(
            BookService bookService,
            BookProcessor bookProcessor
                  ) {
        this.bookService = bookService;
        this.bookProcessor = bookProcessor;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit    = false;

        while (!exit) {
            showMenu();
            System.out.print("Seleccione una opción: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> searchBooks(scanner);
                case "2" -> listAllBooks();
                case "3" -> listAllAuthors();
                case "4" -> listAuthorsAliveInYear(scanner);
                case "5" -> listAllBooksByLanguage(scanner);
                case "0" -> {
                    System.out.println("Saliendo del programa...");
                    exit = true;
                }
                default -> System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
    private void showMenu() {
        System.out.println("\n===== Catálogo de Libros =====");
        System.out.println("1. Buscar libro por titulo o autor");
        System.out.println("3. Guardar libro en favoritos");
        System.out.println("4. Listar todos los libros");
        System.out.println("5. Listar todos los libros por idioma");
        System.out.println("5. Listar todos los autores");
        System.out.println("6. Listar autores vivos en un año");
        System.out.println("0. Salir");
        System.out.println("==============================");
    }

    private void searchBooks(Scanner scanner) {
        System.out.print("Ingrese un término de búsqueda: ");
        String query = scanner.nextLine();
        Book   book  = bookService.searchBook(query);
        if (book == null) {
            System.out.println("No se encontro ningún libro con el término de búsqueda: " + query);
            return;
        }
        System.out.println("Resultados de la búsqueda:");
        System.out.println(" - " + book.getTitle());
    }

    private void listAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No hay libros disponibles.");
        } else {
            System.out.println("Lista de libros:");
            books.forEach(book -> System.out.println(" - " + book.getTitle()));
        }
    }

    private void listAllAuthors() {
        List<Author> people = bookService.getAllAuthors();
        if (people.isEmpty()) {
            System.out.println("No hay autores disponibles.");
        } else {
            people.forEach(author ->
                                   System.out.println("Nombre: " + author.getName() +
                                                              ", Año de Nacimiento: " + author.getBirthYear() +
                                                              ", Año de Fallecimiento: " + (author.getDeathYear() != null ? author.getDeathYear() : "Vivo"))
                          );
        }
    }

    private void listAuthorsAliveInYear(Scanner scanner) {
        System.out.print("Ingrese un año para buscar autores vivos: ");
        try {
            int          year   = Integer.parseInt(scanner.nextLine());
            List<Author> people = bookService.getAuthorsAliveInYear(year);
            if (people.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + year + ".");
            } else {
                people.forEach(author ->
                                       System.out.println("Nombre: " + author.getName() + ", Año de Nacimiento: " + author.getBirthYear())
                              );
            }
        } catch (NumberFormatException e) {
            System.out.println("El año debe ser un número válido.");
        }
    }

    private void listAllBooksByLanguage(Scanner scanner) {
        System.out.print("Ingrese un idioma para buscar libros: ");
        String     language = scanner.nextLine();
        List<Book> books    = bookService.getAllBooksByLanguage(language);
        if (books.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma " + language + ".");
        } else {
            books.forEach(book -> System.out.println(" - " + book.getTitle()));
        }
    }
}