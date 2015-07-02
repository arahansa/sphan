package com.codingtest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by arahansa on 2015-06-29.
 */
public class Meta {

    public static final String EMPTYARTICLE = "200E";
    public static final String SUCCESS_CREATE = "200C";
    public static final String SUCCESS_READ = "200R";
    public static final String SUCCESS_UPDATE = "200U";
    public static final String SUCCESS_DEL = "200D";
    public static final String SUCCESS_LIST = "200L";
    public static final String ERROR_NOTFOUND = "404";
    public static final String ERROR_INTERNAL = "500";
    public static final String ERROR_FIELDEMPTY = "500E";
    public static final String ERROR_NOTSUPPORT = "405";
    private Boolean ok;
    private String codeStatus;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;


    public String getCodeStatus() {return codeStatus;}
    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
        switch (codeStatus){
            case EMPTYARTICLE: this.message="there isn't any article(s)"; break;
            case SUCCESS_CREATE: this.message="Create article Success"; break;
            case SUCCESS_READ: this.message="Read Article Success";break;
            case SUCCESS_UPDATE: this.message="Update Article Success"; break;
            case SUCCESS_DEL: this.message="Delete Article Success"; break;
            case SUCCESS_LIST: this.message="Listing articles Success"; break;
            case ERROR_NOTFOUND: this.message="This address is not valid"; break;
            case ERROR_INTERNAL: this.message="Internal server error";break;
            case ERROR_FIELDEMPTY : this.message = "There is a empty field. you have to fill whole fields"; break;
            default : this.message="This is may be error situation"; break;
        }
    }
    public Boolean getOk() {return ok;}
    public void setOk(Boolean ok) {this.ok = ok;}
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}

}
