package opensource.algorhythm.repository;

import opensource.algorhythm.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByPostId(Long id);
}