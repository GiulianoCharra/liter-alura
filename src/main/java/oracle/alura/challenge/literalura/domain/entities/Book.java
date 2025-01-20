package oracle.alura.challenge.literalura.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @Column(
            nullable = false
    )
    private Integer id;

    @Column(
            nullable = false,
            length = 200
    )
    private String title;

    @Column(
            nullable = false,
            length = 200
    )
    private String language;

    @Column(
            name = "download_count",
            nullable = false
    )
    private int downloadCount;

    @ManyToOne()
    @JoinColumn(
            name = "author_id",
            referencedColumnName = "id"
    )
    private Author author;
    
    public Book(
            int id,
            String title,
            Author author,
            String language
               ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.language = language;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer()
                                    .getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer()
                                       .getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Book book = (Book) o;
        return getId() != null && Objects.equals(getId(),
                                                 book.getId()
                                                );
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer()
                                       .getPersistentClass()
                                       .hashCode() : getClass().hashCode();
    }
}
