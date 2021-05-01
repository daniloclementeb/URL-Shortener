package com.urlshort.urlshort.repository

import com.urlshort.urlshort.model.Url
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlRepository :CrudRepository<Url, Long>{
    fun findByUrl(url:String): Iterable<Url>
    fun findByShortUrl(shortUrl:String): Iterable<Url>
}