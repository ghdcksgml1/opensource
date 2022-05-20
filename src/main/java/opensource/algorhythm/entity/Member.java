package opensource.algorhythm.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import opensource.algorhythm.constant.Role;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
@ToString
public class Member {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}