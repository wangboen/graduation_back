package nju.edu.graduation.entity;

public class Patent {
    private int id;
    private String name;
    private int owner;
    private int inventor;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getInventor() {
        return inventor;
    }

    public void setInventor(int inventor) {
        this.inventor = inventor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}