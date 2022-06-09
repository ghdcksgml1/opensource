package opensource.algorhythm.repository;

import opensource.algorhythm.entity.Comment;
import opensource.algorhythm.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByPost(Post post);
}
