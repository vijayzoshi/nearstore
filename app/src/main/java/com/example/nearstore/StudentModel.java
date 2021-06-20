package com.example.nearstore;

public class StudentModel {

   String stuname, stuclass, homeadd,schooladd;

    public StudentModel(String stuname, String stuclass, String homeadd, String schooladd) {
        this.stuname = stuname;
        this.stuclass = stuclass;
        this.homeadd = homeadd;
        this.schooladd = schooladd;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }

    public String getHomeadd() {
        return homeadd;
    }

    public void setHomeadd(String homeadd) {
        this.homeadd = homeadd;
    }

    public String getSchooladd() {
        return schooladd;
    }

    public void setSchooladd(String schooladd) {
        this.schooladd = schooladd;
    }
}
