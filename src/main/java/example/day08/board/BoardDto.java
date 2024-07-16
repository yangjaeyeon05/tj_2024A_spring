package example.day08.board;

public class BoardDto {

    private int bno;
    private String btitle;
    private String bcontent;
    private String bdate;
    private int bview;
    private String bpwd;

    public BoardDto (){}

    public BoardDto(int bno, String btitle, String bcontent, String bdate, int bview, String bpwd) {
        this.bno = bno;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bdate = bdate;
        this.bview = bview;
        this.bpwd = bpwd;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public int getBview() {
        return bview;
    }

    public void setBview(int bview) {
        this.bview = bview;
    }

    public String getBpwd() {
        return bpwd;
    }

    public void setBpwd(String bpwd) {
        this.bpwd = bpwd;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", btitle='" + btitle + '\'' +
                ", bcontent='" + bcontent + '\'' +
                ", bdate='" + bdate + '\'' +
                ", bview=" + bview +
                ", bpwd='" + bpwd + '\'' +
                '}';
    }
}   // class end
