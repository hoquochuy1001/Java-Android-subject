package com.hoquochuy911.sqllitedemo;

public class Book {
    private int id;
    private String title;
    private Author id_author;

    public Book() {

        this.id = 0;
        this.title = null;
        this.id_author = null;
    }

    public Book(int id, String title, Author id_author) {
        this.id = id;
        this.title = title;
        this.id_author = id_author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getId_author() {
        return id_author;
    }

    public void setId_author(Author id_author) {
        this.id_author = id_author;
    }
}
