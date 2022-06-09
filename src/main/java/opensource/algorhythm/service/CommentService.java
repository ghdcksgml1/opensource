package opensource.algorhythm.service;

import lombok.RequiredArgsConstructor;
import opensource.algorhythm.dto.CommentDto;
import opensource.algorhythm.entity.Comment;
import opensource.algorhythm.entity.Post;
import opensource.algorhythm.repository.CommentJPQLRepository;
import opensource.algorhythm.repository.CommentRepository;
import opensource.algorhythm.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentJPQLRepository commentJPQLRepository;
    private final PostRepository postRepository;

    //댓글 생성
    public Comment createComment(CommentDto commentDto){

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());

        Post post = postRepository.findById(commentDto.getPost().getId()).get();

        comment.setPost(post);
        comment.setMember(commentDto.getMember());

        commentRepository.save(comment);

        Post findPost = postRepository.findById(commentDto.getPost().getId()).get();
        findPost.getCommentList().add(comment);
        postRepository.save(findPost);

        return comment;
    }

    // 댓글 조회
    public List<Comment> selectComment(Long post_id){
        Post post = postRepository.findById(post_id).get();
        List<Comment> comments = commentJPQLRepository.commentJoinPost(post);
        return comments;
    }

}
