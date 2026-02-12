package sn.atos.ProjetJava17.dto;

import java.util.List;

public class EmailDto {
    private String subject;
    private List<String> to;
    private List<String> cci;
    private List<String> cc;
    private String body;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCci() {
        return cci;
    }

    public void setCci(List<String> cci) {
        this.cci = cci;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}