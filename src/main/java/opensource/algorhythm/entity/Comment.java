package opensource.algorhythm.entity;

import lombok.Getter;
import lombok.Setter;
import opensource.algorhythm.dto.CommentDto;
import opensource.algorhythm.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
public class Comment {

  @Id
  @Column(name = "comment_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  private Post post;
  
  private Long memberId;
  
  private String content;

}
