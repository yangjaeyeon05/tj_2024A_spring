package example.day07.todo;

public class ToDoDto {
    private  int tno;
    private String content;
    private int state;

    public ToDoDto(){}

    public ToDoDto(int tno, String content, int state) {
        this.tno = tno;
        this.content = content;
        this.state = state;
    }

    public int getTno() {
        return tno;
    }

    public void setTno(int tno) {
        this.tno = tno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ToDoDto{" +
                "tno=" + tno +
                ", content='" + content + '\'' +
                ", state=" + state +
                '}';
    }
}



