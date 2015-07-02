package com.codingtest.controller;

import com.codingtest.dto.Meta;
import com.codingtest.dto.Response;
import com.codingtest.exception.NotFoundException;
import com.codingtest.model.Article;
import com.codingtest.service.ArticleServiceMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by arahansa on 2015-06-28.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleServiceMock service;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listArticle(){
        return Response.makeResponseWith(Meta.SUCCESS_LIST, service.getList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getArticle(@PathVariable("id") Long id) throws NotFoundException {
        return Response.makeResponseWith(Meta.SUCCESS_READ, service.getArticle(id));
    }

    @RequestMapping(method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public ResponseEntity<?> writeArticle(
            @Valid @RequestBody Article article, BindingResult result) throws NotFoundException {
        if (result.hasErrors()) {
            String nameField = result.getFieldError().getField();
            return Response.makeResponseWith(Meta.ERROR_FIELDEMPTY, article, HttpStatus.INTERNAL_SERVER_ERROR, "empty field : "+nameField);
        }
        Long id = service.saveArticle(article);
        return Response.makeResponseWith(Meta.SUCCESS_CREATE, service.getArticle(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = {"Content-type=application/json"})
    public ResponseEntity<?>  updateArticle( @Valid @RequestBody Article article, @PathVariable("id") Long id,  BindingResult result) throws NotFoundException {
        if (result.hasErrors()) {
            String nameField = result.getFieldError().getField();
            return Response.makeResponseWith(Meta.ERROR_FIELDEMPTY, article, HttpStatus.INTERNAL_SERVER_ERROR, "empty field : "+nameField);
        }
        article.setId(id);
        Article returnArticle = service.updateArticle(article);
        return Response.makeResponseWith(Meta.SUCCESS_UPDATE, returnArticle);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public  ResponseEntity<?> deleteArticle(@PathVariable("id") Long id) throws NotFoundException {
        service.deleteArticle(id);
        return Response.makeResponseWith(Meta.SUCCESS_DEL, service.getList());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(Exception e){
        return Response.makeResponseWith(Meta.ERROR_NOTFOUND, null, HttpStatus.NOT_FOUND, e.getMessage());
    }





}
