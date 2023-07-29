package com.example.b07project;

public class Account {
    private String type;
    private String email;
    private String password;

    public Account(String type, String email, String password){
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
