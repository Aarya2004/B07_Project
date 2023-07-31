package com.example.b07project;

public class Account {
    private String type;
    private String email;
    private String password;

    private String id;

    public Account(String id, String type, String email, String password){
        setId(id);
        setType(type);
        setEmail(email);
        setPass(password);
    }

    public String getType(){
        return type;
    }

    public String getEmail(){
        return email;
    }

    public String getPass(){
        return password;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public void setType(String type){
        this.type = type;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPass(String password){
        this.password = password;
    }

}
