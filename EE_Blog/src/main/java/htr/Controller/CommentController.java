package htr.Controller;

import htr.Service.CommentService;
import htr.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/a/{id}/comments")
    public String insert(@PathVariable("id") long id,String content){
        Comment comment=new Comment();
        comment.setContent(content);
        comment.setCreatedAt(new Date());
        comment.setArticleId(id);
        commentService.insert(comment);
        return "redirect:/a/"+id;
    }

}
