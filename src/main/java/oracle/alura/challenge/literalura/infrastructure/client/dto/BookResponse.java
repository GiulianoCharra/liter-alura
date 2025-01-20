package oracle.alura.challenge.literalura.infrastructure.client.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignorar propiedades no mapeadas
public record BookResponse(
        int id,
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<AuthorResponse> authors,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") int downloadCount
) {
}