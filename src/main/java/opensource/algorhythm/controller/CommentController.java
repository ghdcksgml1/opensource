package opensource.algorhythm.controller;

import lombok.RequiredArgsConstructor;
import opensource.algorhythm.config.auth.PrincipalDetail;
import opensource.algorhythm.dto.CommentDto;
import opensource.algorhythm.entity.Comment;
import opensource.algorhythm.entity.Post;
import opensource.algorhythm.repository.MemberRepository;
import opensource.algorhythm.repository.PostRepository;
import opensource.algorhythm.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentService commentService;

    //댓글 생성
    @PostMapping("/posts/{id}/comment/new")
    public String createComment(@PathVariable Long id,
                                @RequestBody CommentDto commentDto,
                                @AuthenticationPrincipal PrincipalDetail principal,
                                Model model){
        commentDto.setPost(postRepository.findById(id).get());
        commentDto.setMember(memberRepository.findByUsername(principal.getUsername()));
        commentService.createComment(commentDto);

        List<Comment> comments = commentService.selectComment(id);
        Post post = postRepository.findById(id).get();
        model.addAttribute("principal",principal);
        model.addAttribute("boj_username",principal.getBojUsername());
        model.addAttribute("github_username",principal.getGithubUsername());
        model.addAttribute("id",principal.getId());
        model.addAttribute("comments", comments);
        model.addAttribute("post", post);
        return "contentView";
    }

    //댓글 삭제
    @PostMapping(value = "/posts/{id}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable Long id,
                                @PathVariable Long commentId,
                                @AuthenticationPrincipal PrincipalDetail principal,
                                Model model){
        commentService.deleteComment(commentId);
        Post post = postRepository.findById(id).get();
        List<Comment> commentList = post.getCommentList();

        model.addAttribute("principal",principal);
        model.addAttribute("boj_username",principal.getBojUsername());
        model.addAttribute("github_username",principal.getGithubUsername());
        model.addAttribute("id",principal.getId());
        model.addAttribute("comments", commentList);
        model.addAttribute("post", post);

        return "contentView";
    }
}
