package example.day09.board;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BoardDto {

    private int bno;
    private String btitle;
    private String bcontent;
    private String bdate;
    private int bview;
    private String bpwd;

}   // class end
