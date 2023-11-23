package com.example.cryptoapp;


import android.text.TextUtils;
import android.util.Patterns;

public class UserClass {
    //شئی گرایی:این کلس رو برای امنیت بیشتر ساختم
    //روی فیلد های ثبت نام محدودیت گذاشتم


    //همه متغیر ها رو کردیم پرایوت تا دسترسی در سطح پکیج پابلیک نباشه
    private int userID;
    private String username;
    private String name;
    private String email;
    private String mobile;
    private String password;
    private String gender;
    private String address;
    private String profileImage;




    public UserClass()
    {
        //تابع سازنده پیش فرض خالی برای اینکه تو صفحه رجیستر ارور نگیریم
        /*
        //مشکل کتابخانه ی والی این هست که فیلد با مقدار نال رو به سرور نمیفرسته در نتیجه اینارو تعریف کردم
        userID = 0;
        username = "";
        name = "";
        email = "";
        mobile = "";
        password = "";
        gender = "";
        address = "";
        profileImage = "";
        */
    }




    //تابع سازنده:کلیک راست جنریت کانستراکتور
    public UserClass(int userID, String username, String name, String email, String mobile, String password, String gender, String address, String profileImage) {
        this.userID = userID;
        this.username = username;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.profileImage = profileImage;
    }



    //توابع ستر و گتر برای امنیت بیشتر
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }



    public Boolean setUsername(String username) {

        if(username.length() > 0 && username.length() <=30 )
        {
            this.username = username;
            return true;
        }
        else
            return false;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        //regex : regular expression : taaeen format khas baraye field
        if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            this.email = email;
            return true;
        }
        else return false;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public boolean setGender(String gender) {
        if (gender.equals("0") || gender.equals("1"))
        {
            this.gender = gender;
            return true;
        }
        else return false;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
