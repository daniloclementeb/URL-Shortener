package com.urlshort.urlshort.service

import com.urlshort.urlshort.encodeToID
import com.urlshort.urlshort.model.ServiceResponse
import com.urlshort.urlshort.model.Url
import com.urlshort.urlshort.repository.UrlRepository
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class UrlServices {
    @Autowired
    lateinit var repository: UrlRepository
    fun generateShortUrl(url: String?, domain:String):ResponseEntity<ServiceResponse> {
        //valida regex para garantir que é uma URL
        if (url?.let { validateURL(it) } == true) {
            //valida se já está cadastrada a url
            var entity = repository.findByUrl(url)
            if (entity.iterator().hasNext()) {
                return ResponseEntity.ok().body(ServiceResponse("SERVICE:EXISTS:0001","already exists",entity.iterator().asSequence().toList()))
            } else {
                //encurta url
                //valida url encurtada já existe
                var truncateLenght = 6
                var shorturl = ""
                do {
                    shorturl = url.encodeToID(truncateLenght++)
                } while (repository.findByShortUrl(shorturl).iterator().hasNext())
                //grava url nova
                var newEntity = Url(url, shorturl)
                repository.save(newEntity)
                //adiciono o dominio para a url
                newEntity.shortUrl = domain+shorturl
                var list = ArrayList<Url>()
                list.add(newEntity)
                return ResponseEntity.status(HttpStatus.CREATED).body(ServiceResponse("SERVICE:OK:0001", message = "success", list))
            }
        } else {
            return ResponseEntity.badRequest().body(ServiceResponse("SERVICE:BAD:0001", "invalid request", null))
        }
    }



    private fun validateURL(url: String): Boolean {
        val regex = "\\b(http|https|ftp|file)?(://)?[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]\\.[-a-zA-Z0-9+&@#/%=~_|]*";
        val patt = Pattern.compile(regex);
        val matcher = patt.matcher(url);
        return matcher.matches();
    }

    fun url(path: String): Url? {
        val entity= repository.findByShortUrl(path)
        if (entity.iterator().hasNext()) {
            var url = entity.iterator().next()
            url.access++
            repository.save(url)
            return url
        } else {
            return null
        }
    }

    fun list(): ResponseEntity<ServiceResponse> {
        val entity = repository.findAll()
        return ResponseEntity.ok().body(ServiceResponse("SERVICE:LIST:0001", "list", entity.iterator().asSequence().toList()))
    }
}