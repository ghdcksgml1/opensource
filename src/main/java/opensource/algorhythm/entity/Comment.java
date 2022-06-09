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

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

  @OneToOne
  @JoinColumn(name = "member_id")
  private Member member;
  
  private String content;

}
// select * from Comment C JOIN Member M ON (C.memberId = M.id) where C.post_id := 1