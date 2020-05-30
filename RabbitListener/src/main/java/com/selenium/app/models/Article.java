package com.selenium.app.models;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private String title;
    private String author;
    private String description;
    private String link;
    private String date;

    @Override
    public String toString() {
        return "{" +
                "\"title\": \"" + title + "\"," +
                "\"author\": \"" + author + "\"," +
                "\"description\": \"" + description + "\"," +
                "\"link\": \"" + link + "\"," +
                "\"date\": \"" + date + "\"" +
                '}';
    }
}

