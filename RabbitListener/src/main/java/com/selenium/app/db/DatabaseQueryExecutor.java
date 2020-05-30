package com.selenium.app.db;

import com.mongodb.*;
import com.selenium.app.models.Article;

public class DatabaseQueryExecutor {

    private DB database;
    private DBCollection articles;

    public DatabaseQueryExecutor() {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDB("tech_crunch_data");
        articles = database.getCollection("articles");
    }

    public void save(Article article) {
        DBObject articleToSave = new BasicDBObject("title", article.getTitle())
                .append("author", article.getAuthor())
                .append("description", article.getDescription())
                .append("_id", article.getLink())
                .append("date", article.getDate());

        articles.save(articleToSave);
    }

}
