package nju.edu.graduation.entity;

public class ChatResp {
    private String msg;
    private String from;
    private String to;

    public ChatResp() {
    }

    public ChatResp(String msg,String from,String to) {
        this.msg = msg;
        this.from = from;
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
