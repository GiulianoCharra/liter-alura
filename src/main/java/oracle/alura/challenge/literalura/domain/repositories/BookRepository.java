package oracle.alura.challenge.literalura.domain.repositories;

import oracle.alura.challenge.literalura.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório de libros
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByLanguage(String language);

    Optional<Book> findByTitleContains(String query);
}
