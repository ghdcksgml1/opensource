package opensource.algorhythm.repository;

import lombok.RequiredArgsConstructor;
import opensource.algorhythm.entity.Comment;
import opensource.algorhythm.entity.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentJPQLRepository {

    private final EntityManager em;

    public List<Comment> commentJoinPost(Post post){
        List<Comment> resultList = em.createQuery("select c from Comment c join fetch c.post", Comment.class)
                .getResultList();
        return resultList;
    }
}
