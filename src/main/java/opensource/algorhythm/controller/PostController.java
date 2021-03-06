package opensource.algorhythm.controller;

import lombok.RequiredArgsConstructor;
import opensource.algorhythm.config.auth.PrincipalDetail;
import lombok.extern.slf4j.Slf4j;
import opensource.algorhythm.dto.CommentDto;
import opensource.algorhythm.dto.PostEditDto;
import opensource.algorhythm.dto.PostFormDto;
import opensource.algorhythm.entity.Comment;
import opensource.algorhythm.entity.Member;
import opensource.algorhythm.entity.Post;
import opensource.algorhythm.repository.MemberRepository;
import opensource.algorhythm.repository.PostRepository;
import opensource.algorhythm.service.CommentService;
import opensource.algorhythm.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentService commentService;

    //게시물 폼 생성
    @GetMapping(value = "/new")
    public String postForm(Model model, @AuthenticationPrincipal PrincipalDetail principal){
        model.addAttribute("postFormDto", new PostFormDto());
        model.addAttribute("principal",principal);
        model.addAttribute("boj_username",principal.getBojUsername());
        model.addAttribute("github_username",principal.getGithubUsername());
        model.addAttribute("id",principal.getId());

        return "contentForm";
    }

    //게시물 생성
    @ResponseBody
    @PostMapping("/new")
    public String newPost(@RequestBody PostFormDto postFormDto, Model model,@AuthenticationPrincipal PrincipalDetail principal){
        try {

            //Long member_id = memberRepository.findByUsername(principal.getUsername()).getId();
            //postFormDto.setMemberId(member_id);

            Member member = memberRepository.findByUsername(principal.getUsername());
            Post post = postService.createPost(postFormDto);

            post.setMember(member);
            member.getPost().add(post);

            postRepository.save(post);
            memberRepository.save(member);

            log.info("member={}, title={}, num={}, name={}, code={}", post.getMember(), post.getTitle(), post.getProblemNum(), post.getProblemName(), post.getCode());
            log.info("postList={}", member.getPost());
            log.info("member post size={}", member.getPost().size());
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }

        return "ok";
    }

    //특정 게시물 조회
    @GetMapping(value = "/{postId}")
    public String seePost(@PathVariable Long postId, Model model,@AuthenticationPrincipal PrincipalDetail principal){
        model.addAttribute("principal",principal);
        model.addAttribute("boj_username",principal.getBojUsername());
        model.addAttribute("github_username",principal.getGithubUsername());
        model.addAttribute("id",principal.getId());

        Post post = postRepository.findById(postId).get();
        List<Comment> comments = commentService.selectComment(postId);
        model.addAttribute("comments",comments);
        model.addAttribute("post", post);
        return "contentView";
    }

    /*
    @PostMapping(value = "/{id}")
    public String seePost(@PathVariable Long id,
                          @RequestBody CommentDto commentDto,
                          @AuthenticationPrincipal PrincipalDetail principal,
                          Model model){
        commentDto.setPost(postRepository.findById(id).get());
        commentDto.setMember(memberRepository.findByUsername(principal.getUsername()));
        Comment comment = commentService.createComment(commentDto);
        Post post = postRepository.findById(id).get();
        model.addAttribute("principal",principal);
        model.addAttribute("boj_username",principal.getBojUsername());
        model.addAttribute("github_username",principal.getGithubUsername());
        model.addAttribute("id",principal.getId());
        model.addAttribute("comments", comment);
        model.addAttribute("post", post);
        return "contentView";
    }*/

    //게시물 수정 폼 get
    @GetMapping(value = "/{postId}/edit")
    public String editPostGet(@PathVariable Long postId,
                              @AuthenticationPrincipal PrincipalDetail principal,
                              Model model){
        model.addAttribute("principal",principal);
        model.addAttribute("boj_username",principal.getBojUsername());
        model.addAttribute("github_username",principal.getGithubUsername());
        model.addAttribute("id",principal.getId());

        Post post = postRepository.findById(postId).get();
        model.addAttribute("post", post);
        return "";
    }

    //게시물 수정 post
    @ResponseBody
    @PostMapping(value = "/{postId}/edit")
    public String editPost(@PathVariable Long postId,
                           @RequestBody PostEditDto postEditDto,
                           @AuthenticationPrincipal PrincipalDetail principal,
                           Model model){
        Post post = postService.editPost(postId, postEditDto);
        model.addAttribute("post", post);
        return "contentView";
    }

    //게시물 삭제
    @PostMapping(value = "/{postId}/delete")
    public String deletePost(@PathVariable Long postId,
                             @AuthenticationPrincipal PrincipalDetail principal,
                             Model model){
        postService.deletePost(postId);
        return "redirect:/";
    }

}
