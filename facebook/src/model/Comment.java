/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class Comment {
    private int id;
    private String content;
    private String date;
    private int userCommentId;
    private int commentPostId;

    private Comment(CommentBuilder builder) {
        this.id = builder.id;
        this.content = builder.content;
        this.date = builder.date;
        this.userCommentId = builder.userCommentId;
        this.commentPostId = builder.commentPostId;
    }

    public static class CommentBuilder {
        private int id;
        private String content;
        private String date;
        private int userCommentId;
        private int commentPostId;

        public CommentBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public CommentBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public CommentBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public CommentBuilder setUserCommentId(int userCommentId) {
            this.userCommentId = userCommentId;
            return this;
        }

        public CommentBuilder setCommentPostId(int commentPostId) {
            this.commentPostId = commentPostId;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }

    // Getters for the fields
    public int getId() { return id; }
    public String getContent() { return content; }
    public String getDate() { return date; }
    public int getUserCommentId() { return userCommentId; }
    public int getCommentPostId() { return commentPostId; }
    
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", userCommentId=" + userCommentId +
                ", commentPostId=" + commentPostId +
                '}';
    }
}
