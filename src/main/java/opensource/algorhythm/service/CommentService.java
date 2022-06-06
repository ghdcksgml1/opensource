package opensource.algorhythm.service;

import lombok.RequiredArgsConstructor;
import opensource.algorhythm.dto.CommentDto;
import opensource.algorhythm.entity.Comment;
import opensource.algorhythm.entity.Post;
import opensource.algorhythm.repository.CommentRepository;
import opensource.algorhythm.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    //댓글 생성
    public Comment createComment(CommentDto commentDto){
        Comment comment = Comment.createComment(commentDto);

        Post post = postRepository.findById(commentDto.getPostId()).get();
        post.getCommentList().add(comment);
        postRepository.save(post);

        return commentRepository.save(comment);
    }
}
