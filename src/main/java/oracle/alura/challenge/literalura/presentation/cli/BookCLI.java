package oracle.alura.challenge.literalura.presentation.cli;

import oracle.alura.challenge.literalura.application.service.BookService;
import oracle.alura.challenge.literalura.infrastructure.client.BookProcessor;
import oracle.alura.challenge.literalura.infrastructure.client.dto.BookResponse;
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
                case "2" -> getBookDetails(scanner);
                case "3" -> saveBookToFavorites(scanner);
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
        System.out.println("1. Buscar libros");
        System.out.println("2. Ver detalles de un libro");
        System.out.println("3. Guardar libro en favoritos");
        System.out.println("0. Salir");
        System.out.println("==============================");
    }

    private void searchBooks(Scanner scanner) {
        System.out.print("Ingrese un término de búsqueda: ");
        String             query = scanner.nextLine();
        List<BookResponse> books = bookService.searchBooks(query);
        if (books.isEmpty()) {
            System.out.println("No se encontraron libros para el término: " + query);
            return;
        }
        System.out.println("Resultados de la búsqueda:");
        books.forEach(book -> System.out.println(" - " + book.title()));
    }
    private void getBookDetails(Scanner scanner) {
        System.out.print("Ingrese el ID del libro: ");
        try {
            int          bookId = Integer.parseInt(scanner.nextLine());
            BookResponse book   = bookService.getBookDetails(bookId);
            if (book != null) {
                System.out.println("Detalles del libro:");
                bookProcessor.displayBook(book);
            } else {
                System.out.println("No se encontró el libro con ID: " + bookId);
            }
        } catch (NumberFormatException e) {
            System.out.println("El ID debe ser un número válido.");
        }
    }

    private void saveBookToFavorites(Scanner scanner) {
        System.out.print("Ingrese el ID del libro que desea guardar: ");
        try {
            int     bookId  = Integer.parseInt(scanner.nextLine());
            boolean success = bookService.saveBookToFavorites(bookId);
            if (success) {
                System.out.println("El libro fue guardado en favoritos.");
            } else {
                System.out.println("No se pudo guardar el libro en favoritos. Verifique el ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("El ID debe ser un número válido.");
        }
    }
}