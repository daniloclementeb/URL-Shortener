package com.urlshort.urlshort.model

data class ServiceResponse(val code:String, val message:String, val url: List<Url>?)
