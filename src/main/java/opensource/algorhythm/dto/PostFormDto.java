package opensource.algorhythm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostFormDto {

    private String title;

    private String level;

    private int problemNum;

    private String problemName;

    private String problemIdea;

    private String timeComplexity;

    private String code;

    //private Long memberId;

    private String tags;

}
