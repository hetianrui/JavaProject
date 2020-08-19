package htr.Service;

import htr.mapper.ArticleMapper;
import htr.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    public List<Article> query(){
    return articleMapper.selectAll();
    }

    public Article queryId(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    public List<Article> queryByUserId(Long id) {
        return articleMapper.queryByUserId(id);
    }

    public int UpdateByKey(Article article) {
        return articleMapper.updateByKey(article);
    }

    public int  insert(Article article) {
        return articleMapper.insert(article);
    }
}
