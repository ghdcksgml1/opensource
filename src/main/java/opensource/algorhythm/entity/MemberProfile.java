package opensource.algorhythm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
public class MemberProfile {

    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String memo;
    private String favAlgorithm;

}
