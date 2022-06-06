package opensource.algorhythm.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import opensource.algorhythm.dto.PostFormDto;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
@ToString
@RequiredArgsConstructor
public class Post {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private int problemNum;

    private String problemName;

    private String code;

    private Long memberId;

    public static Post createPost(PostFormDto postFormDto){
        Post post = new Post();
        post.setTitle(postFormDto.getTitle());
        post.setProblemNum(postFormDto.getProblemNum());
        post.setProblemName(postFormDto.getProblemName());
        post.setProblemName(postFormDto.getCode());
        post.setMemberId(postFormDto.getMemberId());
        return post;
    }

}
