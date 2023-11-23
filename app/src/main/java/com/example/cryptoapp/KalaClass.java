package com.example.cryptoapp;
//فیلد هایی که برای کالا نیاز هست
//این کلس کارش اینه همه اطلاعات کالاهای مارو داخل خودش ذخیره کنه
public class KalaClass {

    //پرایوت تعریف کردم تا دسترسی غیرمجاز دیگه بهش نباشه جایی
    private int kID;
    private int price;
    private int weight;
    private int num;
    private int catID;
    private int userID;
    private int view;
    private String kName;
    private String color;
    private String image;
    private String description;
    private String submitDate;

    public int getkID() {
        return kID;
    }

    public void setkID(int kID) {
        this.kID = kID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getkName() {
        return kName;
    }

    public void setkName(String kName) {
        this.kName = kName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }
}
