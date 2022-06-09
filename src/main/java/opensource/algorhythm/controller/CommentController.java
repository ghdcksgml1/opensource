package opensource.algorhythm.controller;

import lombok.RequiredArgsConstructor;
import opensource.algorhythm.config.auth.PrincipalDetail;
import opensource.algorhythm.dto.CommentDto;
import opensource.algorhythm.entity.Comment;
import opensource.algorhythm.entity.Member;
import opensource.algorhythm.repository.MemberRepository;
import opensource.algorhythm.repository.PostRepository;
import opensource.algorhythm.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/posts/{id}/new")
    public String createComment(@PathVariable Long id, @RequestBody CommentDto commentDto, Model model, @AuthenticationPrincipal PrincipalDetail principal){
        commentDto.setPost(postRepository.findById(id).get());
        commentDto.setMember(memberRepository.findByUsername(principal.getUsername()));
        commentService.createComment(commentDto);

        return "";
    }
}
