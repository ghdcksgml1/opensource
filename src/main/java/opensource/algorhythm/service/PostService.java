package opensource.algorhythm.service;

import lombok.RequiredArgsConstructor;
import opensource.algorhythm.dto.PostEditDto;
import opensource.algorhythm.dto.PostFormDto;
import opensource.algorhythm.entity.Post;
import opensource.algorhythm.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //post 생성
    public Post createPost(PostFormDto postFormDto){

        Post post = Post.createPost(postFormDto);
        return postRepository.save(post);
    }

    //post 조회
    public Post findPost(Long id){
        Post post = postRepository.findById(id).get();
        return post;
    }

    //모든 post 조회
    public List<Post> findAllPost(){
        return postRepository.findAll();
    }

    //post 수정
    public Post editPost(Long postId, PostEditDto postEditDto){ //수정한 dto
        Post post = postRepository.findById(postId).get();

        post.setTitle(postEditDto.getTitle());
        post.setProblemIdea(postEditDto.getProblemIdea());
        post.setCode(postEditDto.getCode());
        post.setTimeComplexity(postEditDto.getTimeComplexity());

        return postRepository.save(post);
    }

    //post 삭제
    public void deletePost(Long id){
        postRepository.deleteById(id);
    }

    //post 검색 - 문제 번호 or 태그로 검색
    public List<Post> searchPost(String keyword){
        List<Post> postList = new ArrayList<>();

        try {
            List<Post> postListByProblemNum = postRepository.findByProblemNum(Integer.parseInt(keyword.trim()));
            if (postListByProblemNum != null){
                for (Post post : postListByProblemNum) {
                    postList.add(post);
                }
            }
        }catch (NumberFormatException e){

        }

        List<Post> postListByTag = postRepository.findByTagsContainingIgnoreCase(keyword);
        if (postListByTag != null){
            for (Post post : postListByTag) {
                postList.add(post);
            }
        }
        return postList;
    }
}
