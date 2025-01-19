package oracle.alura.challenge.literalura.infrastructure.client.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorResponse(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") Integer birthYear,
        @JsonAlias("death_year") Integer deathYear
) {}