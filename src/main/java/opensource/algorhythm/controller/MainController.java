package opensource.algorhythm.controller;

import lombok.extern.slf4j.Slf4j;
import opensource.algorhythm.config.auth.PrincipalDetail;
import opensource.algorhythm.entity.Post;
import opensource.algorhythm.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MainController {

    @Autowired
    PostService postService;

    @GetMapping(value = "/")
    public String main(Model model, @AuthenticationPrincipal PrincipalDetail principal){
        model.addAttribute("principal",principal);
        model.addAttribute("boj_username",principal.getBojUsername());
        model.addAttribute("github_username",principal.getGithubUsername());
        model.addAttribute("id",principal.getId());

        List<Post> postList = postService.findAllPost();
        log.info("allPostSize={}", postList.size());
        model.addAttribute("postList", postList);
        return "index";
    }

    //게시물 검색
    @RequestMapping(value = "/search")
    public String searchPost(@RequestParam String keyword, @RequestParam int problemNum, Model model){
        List<Post> searchPostList = new ArrayList<>();

        List<Post> postListByTag = postService.searchPostByTag(keyword);
        List<Post> postListByProblemNum = postService.searchPostByProblemNum(problemNum);
        log.info("search result: {}", postListByTag.get(0).getTitle());
        if (postListByTag != null){
            for (Post post : postListByTag) {
                searchPostList.add(post);
            }
        }
        if (postListByProblemNum != null){
            for (Post post : postListByProblemNum) {
                searchPostList.add(post);
            }
        }

        model.addAttribute("postList", searchPostList);
        return "index";
    }

}
