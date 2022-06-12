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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @GetMapping(value = "/search")
    public String searchPost(@RequestParam String keyword,
                             @RequestParam(required = false, defaultValue = "-1") int problemNum,
                             Model model,
                             @AuthenticationPrincipal PrincipalDetail principal){
        Set<Post> searchPostSet = new HashSet<>();
        log.info("keyword={}, num={}", keyword, problemNum);
        List<Post> postListByTag = postService.searchPostByTag(keyword);
        List<Post> postListByProblemNum = postService.searchPostByProblemNum(problemNum);
        log.info("search result: {}", postListByTag.get(0).getTitle());
        if (postListByTag != null){
            for (Post post : postListByTag) {
                searchPostSet.add(post);
            }
        }
        if (postListByProblemNum != null){
            for (Post post : postListByProblemNum) {
                searchPostSet.add(post);
            }
        }

        model.addAttribute("principal",principal);
        model.addAttribute("boj_username",principal.getBojUsername());
        model.addAttribute("github_username",principal.getGithubUsername());
        model.addAttribute("id",principal.getId());
        model.addAttribute("postList", searchPostSet);
        return "index";
    }

}
