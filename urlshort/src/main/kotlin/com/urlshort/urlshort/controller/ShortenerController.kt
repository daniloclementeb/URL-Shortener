package com.urlshort.urlshort.controller;

import com.urlshort.urlshort.model.ServiceResponse
import com.urlshort.urlshort.service.UrlServices
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.RequestContextHolder
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class ShortenerController {

    @Autowired
    lateinit var service:UrlServices

    @PutMapping
    fun put(@RequestBody request: Map<String, String>, http: HttpServletRequest): ResponseEntity<ServiceResponse> {
        var url = request.get("url")
        return service.generateShortUrl(url, http.requestURL.toString())
    }
    @GetMapping
    fun listAll():ResponseEntity<ServiceResponse> {
        return service.list()
    }

    @RequestMapping("{path}")
    fun get(@PathVariable path:String, http: HttpServletResponse): String {
        var url = service.url(path)
        if (url != null) {
            http.sendRedirect(url.url);
            return "sucess"
        } else {
            return "not found"
        }
    }



}
