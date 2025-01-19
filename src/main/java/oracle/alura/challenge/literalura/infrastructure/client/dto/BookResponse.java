package oracle.alura.challenge.literalura.infrastructure.client.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignorar propiedades no mapeadas
public record BookResponse(
        int id,
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<AuthorResponse> authors,
        @JsonAlias("subjects") List<String> subjects,
        @JsonAlias("bookshelves") List<String> bookshelves,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("copyright") boolean copyright,
        @JsonAlias("download_count") int downloadCount
) {}