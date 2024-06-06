/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */

public class User {
    private int id;
    private String username;
    private String address;
    private String phone;
    private String email;
    private byte gender;
    private String profileImage;
    
    // Getters for the fields
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public byte getGender() { return gender; }
    public String getProfileImage() { return profileImage; }

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.address = builder.address;
        this.phone = builder.phone;
        this.email = builder.email;
        this.gender = builder.gender;
        this.profileImage = builder.profileImage;
    }

    public static class UserBuilder {
        private int id;
        private String username;
        private String address;
        private String phone;
        private String email;
        private byte gender;
        private String profileImage;

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setGender(byte gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder setProfileImage(String profileImage) {
            this.profileImage = profileImage;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}