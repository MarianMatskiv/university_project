package app.services;

import app.models.UpdateArticleInput;
import app.models.entities.Article;
import app.repositories.ArticleRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article findOneById(String id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        return articleOptional.orElse(null);
    }

    public List<Article> findAll() {
        return (List<Article>) articleRepository.findAll();
    }

    @Transactional
    public Article updateArticle(UpdateArticleInput input) throws Exception {
        System.out.println("ID: " + input.getId());
        System.out.println("LIKED: " + input.getLiked());

        Article article = articleRepository.findById(input.getId()).orElseThrow(Exception::new);
        article.setLiked(input.getLiked());
        article = articleRepository.save(article);
        return article;
    }
}
