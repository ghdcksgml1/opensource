package opensource.algorhythm.controller;

import lombok.RequiredArgsConstructor;
import opensource.algorhythm.dto.PostFormDto;
import opensource.algorhythm.entity.Post;
import opensource.algorhythm.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시물 폼 생성
    @GetMapping(value = "/new")
    public String postForm(Model model){
        model.addAttribute("postFormDto", new PostFormDto());
        return "contentForm";
    }

    //게시물 생성
    @ResponseBody
    @PostMapping("/new")
    public String newPost(@RequestBody PostFormDto postFormDto, Model model){
        try {
            postService.createPost(postFormDto);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }
        return "redirect:/";
    }

    //게시물 조회
    @GetMapping(value = "/{id}")
    public String seePost(){
        return "";
    }

    //게시물 수정
    @PostMapping(value = "/{id}/edit")
    public String editPost(){
        return "";
    }

    //게시물 삭제
    @PostMapping(value = "/{id}/delete")
    public String deletePost(){
        return "";
    }
}
