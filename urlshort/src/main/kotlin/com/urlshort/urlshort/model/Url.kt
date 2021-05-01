package com.urlshort.urlshort.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Url {
    constructor(url: String,shortUrl: String){
        this.url = url
        this.shortUrl = shortUrl
    }

    @Id
    lateinit var url:String
    var shortUrl:String = ""
    val creationDate: Date = Calendar.getInstance().time
    var access: Int = 0


}