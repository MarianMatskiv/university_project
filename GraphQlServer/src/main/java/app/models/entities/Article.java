package app.models.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "articles")
public class Article {

    @Id
    @Column(name = "_id")
    private String id;
    private String title;
    private String author;
    private String description;
    private String date;
    private Boolean liked;

    @Override
    public String toString() {
        return "{" +
                "\"title\": \"" + title + "\"," +
                "\"author\": \"" + author + "\"," +
                "\"description\": \"" + description + "\"," +
                "\"link\": \"" + id + "\"," +
                "\"date\": \"" + date + "\"" +
                '}';
    }

}
