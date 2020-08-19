package htr.Controller;

import htr.Service.CategoryService;
import htr.model.Category;
import htr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = "/c/add", method=RequestMethod.POST)
    public String add(HttpSession session,String name){
        User user=(User) session.getAttribute("user");
        Category category1=new Category();
        category1.setUserId(user.getId());
        category1.setName(name);
        categoryService.add(category1);
        return "redirect:/writer";
    }
}
