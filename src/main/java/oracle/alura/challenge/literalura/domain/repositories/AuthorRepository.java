package oracle.alura.challenge.literalura.domain.repositories;

import oracle.alura.challenge.literalura.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Busca un autor por su nombre.
     *
     * @param name El nombre del autor.
     * @return El autor encontrado o null si no existe.
     */
    Optional<Author> findByName(String name);

    /**
     * Busca autores que estén vivos en un año determinado.
     *
     * @param year El año de consulta.
     * @return Lista de autores vivos en ese año.
     */
    @Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND (a.deathYear > :year OR a.deathYear IS NULL)")
    List<Author> findLivingAuthorsInYear(int year);
}