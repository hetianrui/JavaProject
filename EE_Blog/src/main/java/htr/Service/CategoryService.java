package htr.Service;

import htr.mapper.CategoryMapper;
import htr.model.Article;
import htr.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;


    public List<Category> queryByUserId(Long id) {
        return categoryMapper.queryByUserId(id);
    }

    public int add(Category category) {
        return categoryMapper.insert(category);
    }

    public Category queryById(long acid) {
        return categoryMapper.selectByPrimaryKey(acid);
    }
}
