package com.example.destressit;

public class TherapistClass {
    private String tname;
    private String temail;
    private String tphone;

    public TherapistClass(){

    }

    public TherapistClass(String tname, String temail, String tphone) {
        this.tname = tname;
        this.temail = temail;
        this.tphone = tphone;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTemail() {
        return temail;
    }

    public void setTemail(String temail) {
        this.temail = temail;
    }

    public String getTphone() {
        return tphone;
    }

    public void setTphone(String tphone) {
        this.tphone = tphone;
    }
}
