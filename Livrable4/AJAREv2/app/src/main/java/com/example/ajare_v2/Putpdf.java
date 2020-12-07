package com.example.ajare_v2;

public class Putpdf {
    String name;
    String url;
    public Putpdf(String name,String url){
        this.name=name;
        this.url=url;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url=url;
    }
}
