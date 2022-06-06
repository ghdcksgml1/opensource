package opensource.algorhythm.entity;

import lombok.Getter;
import lombok.Setter;
import opensource.algorhythm.dto.CommentDto;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
public class Comment {

  @Id
  @Column(name = "comment_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long postId;
  
  private Long memberId;
  
  private String content;

  public static Comment createComment(CommentDto commentDto){
    Comment comment = new Comment();
    comment.setContent(commentDto.getContent());
    comment.setPostId(commentDto.getPostId());
    comment.setMemberId(commentDto.getMemberId());
    return comment;
  }

}
