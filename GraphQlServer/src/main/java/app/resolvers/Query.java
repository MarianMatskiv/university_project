package app.resolvers;

import app.models.entities.Article;
import app.services.ArticleService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private ArticleService articleService;

    public List<Article> getAllArticles() {
        return articleService.findAll();
    }

    public Article getArticle(String id) {
        return articleService.findOneById(id);
    }
}
