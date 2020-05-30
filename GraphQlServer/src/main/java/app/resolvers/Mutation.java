package app.resolvers;

import app.models.UpdateArticleInput;
import app.models.entities.Article;
import app.services.ArticleService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private ArticleService articleService;

    public Article updateArticle(UpdateArticleInput updateArticleInput) throws Exception {
        return articleService.updateArticle(updateArticleInput);
    }

}