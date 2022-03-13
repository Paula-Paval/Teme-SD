package com.sd.laborator

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class FirstController {
    val service:HelloService= HelloService()

    @RequestMapping(value=["/helloworld"], method=[RequestMethod.GET])
    @ResponseBody
    fun hello()=service.getHttello()
}