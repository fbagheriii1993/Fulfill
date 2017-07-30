package com.example.fbagheri.fulfill;

public class Task {


    private String title;
    private String desc;
    private int id;
    private int imageTagId;
    private int difficulty;
    private String dueDate;
    private  int done ;
    private int score;

    public Task(){

    }

    public Task(String title, String desc,int imageTagId) {
        this(title,desc,imageTagId,0);
    }

    public Task(String title, String desc,int imageTagId, int difficulty  ) {
        this(title, desc, imageTagId, 0, difficulty);
    }
    public Task(String title, String desc,int imageTagId, int id ,int difficulty ) {
        this(title, desc, imageTagId, id, difficulty,"",0,0);
    }
    public Task(String title, String desc, int imageTagId, int difficulty , String dueDate) {
        this(title, desc, imageTagId, 0, difficulty, dueDate ,0,0);
    }

    public Task(String title, String desc, int imageTagId, int difficulty , String dueDate , int done ,int score ) {
        this(title, desc, imageTagId, 0, difficulty, dueDate , done , score);
    }


    public Task(String title, String desc,int imageTagId, int id ,int difficulty , String dueDate , int done ,int score ) {
        this.title = title;
        this.desc = desc;
        this.id = id;
        this.imageTagId = imageTagId;
        this.difficulty = difficulty;
        this.dueDate = dueDate;
        this.done = done;
        this.score = score;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }


    public int getId() {
        return id;
    }

    public void setId(int title) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc= desc;
    }

    public int getImageTagId() {

        return imageTagId;
    }

    public void setImageTagId(int imageTagId) {
        this.imageTagId = imageTagId;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

