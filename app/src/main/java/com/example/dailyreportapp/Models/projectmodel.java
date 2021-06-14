package com.example.dailyreportapp.Models;

public class projectmodel {
    String projecttitle;
    String projectdesc;
    String projectlink;
    String projectimgurl;
    public projectmodel() {
    }

    public projectmodel(String projecttitle,String projectlink,String projectdesc, String projectimgurl) {
       this.projecttitle=projecttitle;
       this.projectlink=projectlink;
       this.projectdesc=projectdesc;
       this.projectimgurl=projectimgurl;
    }
    public String getProjecttitle() {
        return projecttitle;
    }

    public void setProjecttitle(String projecttitle) {
        this.projecttitle = projecttitle;
    }

    public String getProjectdesc() {
        return projectdesc;
    }

    public void setProjectdesc(String projectdesc) {
        this.projectdesc = projectdesc;
    }

    public String getProjectlink() {
        return projectlink;
    }

    public void setProjectlink(String projectlink) {
        this.projectlink = projectlink;
    }

    public String getProjectimgurl() {
        return projectimgurl;
    }

    public void setProjectimgurl(String projectimgurl) {
        this.projectimgurl = projectimgurl;
    }


}
