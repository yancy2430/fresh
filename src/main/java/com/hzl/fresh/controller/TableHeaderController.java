package com.hzl.fresh.controller;

import com.hzl.fresh.core.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@RestController
public class TableHeaderController extends ApiController {
    @Autowired
    HttpServletRequest request;
}
