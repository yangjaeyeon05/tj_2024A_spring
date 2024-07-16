package example.day09.todo;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ToDoDto {
    private  int tno;
    private String content;
    private int state;
}



