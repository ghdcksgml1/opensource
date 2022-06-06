package opensource.algorhythm.service;

import lombok.RequiredArgsConstructor;
import opensource.algorhythm.dto.PostFormDto;
import opensource.algorhythm.entity.Post;
import opensource.algorhythm.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //post 수정
    public String editPost(Long id, PostFormDto postFormDto){ //수정한 dto
        Post post = postRepository.getById(id);
        post.setTitle(postFormDto.getTitle());
        post.setCode(postFormDto.getCode());
        post.setProblemName(postFormDto.getProblemName());
        post.setProblemNum(postFormDto.getProblemNum());

        postRepository.save(post);

        return "";
    }

    //post 삭제
    public String deletePost(Long id){
        postRepository.deleteById(id);
        return "";
    }
}
