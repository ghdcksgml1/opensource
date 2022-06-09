package opensource.algorhythm.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import opensource.algorhythm.dto.PostFormDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter @Setter
@RequiredArgsConstructor
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String level;

    private int problemNum;

    private String problemName;

    private String problemIdea;

    private String timeComplexity;

    @Lob
    private String code;

    //private Long memberId;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String tags;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    public static Post createPost(PostFormDto postFormDto){
        Post post = new Post();
        post.setTitle(postFormDto.getTitle());
        post.setLevel(postFormDto.getLevel());
        post.setProblemNum(postFormDto.getProblemNum());
        post.setProblemName(postFormDto.getProblemName());
        post.setProblemIdea(postFormDto.getProblemIdea());
        post.setTimeComplexity(postFormDto.getTimeComplexity());
        post.setCode(postFormDto.getCode());
        //post.setMemberId(postFormDto.getMemberId());
        post.setTags(postFormDto.getTags());
        return post;
    }

}
