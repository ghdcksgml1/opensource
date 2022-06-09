package opensource.algorhythm.repository;

import opensource.algorhythm.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByProblemNum(int problemNum);
    List<Post> findByProblemNameContaining(String name);

    List<Post> findByTagsContaining(String keyword);
}
