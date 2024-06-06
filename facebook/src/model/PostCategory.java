/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class PostCategory {
    private int id;
    private String name;

    private PostCategory(PostCategoryBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static class PostCategoryBuilder {
        private int id;
        private String name;

        public PostCategoryBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public PostCategoryBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PostCategory build() {
            return new PostCategory(this);
        }
    }

    // Getters for the fields
    public int getId() { return id; }
    public String getName() { return name; }
}
