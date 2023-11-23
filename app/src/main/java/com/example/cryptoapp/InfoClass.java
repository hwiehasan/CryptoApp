package com.example.cryptoapp;
// مهممممممممممممممممممممم: این کلسو فقط کپی کردم کاربردی تو برنامه نداره
public class InfoClass {

    private Integer image ;
    private String name ;
    private String description ;

    public InfoClass(String name , String description , Integer image)
    {
        this.name = name ;
        this.description = description ;
        this.image = image ;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
