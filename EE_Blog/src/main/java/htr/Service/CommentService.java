package htr.Service;

import htr.mapper.CommentMapper;
import htr.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    public List<Comment> queryById(Long id) {
        return commentMapper.queryById(id);
    }

    public int insert(Comment comment) {
        return commentMapper.insert(comment);
    }
}
