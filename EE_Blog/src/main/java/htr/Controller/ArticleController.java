package htr.Controller;

import htr.Service.ArticleService;
import htr.Service.CategoryService;
import htr.Service.CommentService;
import htr.model.Article;
import htr.model.Category;
import htr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;
    @RequestMapping("/")
    public String query(Model model){
        List<Article> list = articleService.query();
        model.addAttribute("articleList",list);
        return "index";
    }
    @RequestMapping("/a/{id}")
    public String details(@PathVariable("id") Long id,Model model){
        Article article=articleService.queryId(id);
        article.setCommentList(commentService.queryById(id));
        model.addAttribute("article",article);
        return "/info";
    }

    @RequestMapping("/writer")
    public String write(HttpSession session,Model model){
        User user=(User)session.getAttribute("user");
        List<Article> list=articleService.queryByUserId(user.getId());
        List<Category> list1=new ArrayList<>(categoryService.queryByUserId(user.getId()));
        model.addAttribute("categoryList",list1);
        model.addAttribute("articleList",list);
        model.addAttribute("activeCid",list1.get(0).getId());
        return "writer";
    }
    /*
    新建，修改都跳转到编辑页面
    新建id=1，修改id=2
     */
    @RequestMapping("/writer/forward/{id}/{TypeId}/editor")
    public String editor1(@PathVariable("id") int id,
                          @PathVariable("TypeId") long acid,
                          Model model){
        if(id==1){
            Category categories = categoryService.queryById(acid);
            model.addAttribute("type",id);
        //model.addAttribute("activeCid",categories.getId());
            model.addAttribute("category",categories);
        }else {
            Article article=articleService.queryId(acid);
            Category categories = categoryService.queryById(article.getCategoryId());
            model.addAttribute("type",id);
            model.addAttribute("category",categories);
            model.addAttribute("article",article);
        }
        return "editor";
    }
    //发布功能
    @RequestMapping("/writer/article/{id}/{TypeId}")
    public String publish(@PathVariable("id") long id,
                          @PathVariable("TypeId") int temp,Article article,HttpSession session){
            User user=(User) session.getAttribute("user");
        if (id==2){
            article.setId(new Long(temp));
            article.setUpdatedAt(new Date());
            articleService.UpdateByKey(article);
        }else {
            article.setUpdatedAt(new Date());
            article.setCommentList(null);
            article.setUserId(user.getId());
            article.setCategoryId(temp);
            article.setCommentCount(0);
            article.setCreatedAt(new Date());
            article.setViewCount(new Long(0));
            article.setCoverImage("https://picsum.photos/id/1/400/300");
            article.setStatus(Byte.valueOf(String.valueOf(0)));
            articleService.insert(article);
        }
        return "redirect:/writer";
    }

}
