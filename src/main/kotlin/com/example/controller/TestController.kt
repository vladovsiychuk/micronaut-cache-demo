package com.example.controller

import com.example.service.TestService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Flowable

@Controller("/test")
class TestController (private val service: TestService) {

    @Get("/")
    fun test() : Flowable<Pair<String,String>> {
        return service.test()
    }
}
