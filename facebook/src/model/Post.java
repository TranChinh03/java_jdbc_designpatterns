/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class Post {
    private int id;
    private String title;
    private String content;
    private String created;
    private int postcategoryId;
    private int userId;

    private Post(PostBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.content = builder.content;
        this.created = builder.created;
        this.postcategoryId = builder.postcategoryId;
        this.userId = builder.userId;
    }

    public static class PostBuilder {
        private int id;
        private String title;
        private String content;
        private String created;
        private int postcategoryId;
        private int userId;

        public PostBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public PostBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public PostBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public PostBuilder setCreated(String created) {
            this.created = created;
            return this;
        }

        public PostBuilder setPostcategoryId(int postcategoryId) {
            this.postcategoryId = postcategoryId;
            return this;
        }

        public PostBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }

    // Getters for the fields
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getCreated() { return created; }
    public int getPostcategoryId() { return postcategoryId; }
    public int getUserId() { return userId; }
}
