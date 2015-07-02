package com.codingtest.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by arahansa on 2015-06-29.
 */
public class Response {
    Meta meta;
    Object data;

    private Response(String codeStatus, HttpStatus httpStatus, Object data) {
        this.meta = new Meta();
        meta.setOk(!(httpStatus.is4xxClientError() | httpStatus.is5xxServerError()));
        meta.setCodeStatus(codeStatus);
        this.data= data;
    }

    public static ResponseEntity<?> makeResponseWith(String codeStatus, Object data) {
        Response r = new Response(codeStatus, HttpStatus.OK, data);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    public static ResponseEntity<?> makeResponseWith(String codeStatus, Object data, HttpStatus statusHttp, String message) {
        Response r = new Response(codeStatus, statusHttp, data);
        r.getMeta().setMessage(message);
        return new ResponseEntity<>(r, statusHttp);
    }

    public Meta getMeta() {return meta;}
    public void setMeta(Meta meta) {this.meta = meta;}

    public Object getData() {return data;}
    public void setData(Object data) {this.data = data;}



}
