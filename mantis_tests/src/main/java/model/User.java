package model;

public record User(String name, String password) {

    public User(String name){
        this(name, "password");
    }
}
