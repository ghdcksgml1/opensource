package opensource.algorhythm.dto;

import lombok.Getter;
import lombok.Setter;
import opensource.algorhythm.entity.Member;
import opensource.algorhythm.entity.Post;

@Getter
@Setter
public class CommentDto {

    private String content;

    private Post post;

    private Member member;
}
