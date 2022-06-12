package opensource.algorhythm.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProfileDto {
    private String memo;
    private String favAlgorithm;
}
