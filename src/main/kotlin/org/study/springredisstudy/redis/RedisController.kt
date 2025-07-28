package org.study.springredisstudy.redis

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RedisController(private val redisService: RedisService) {
    @PostMapping("/redis/{key}")
    fun set(@PathVariable key: String, @RequestParam value: String) {
        redisService.setValue(key, value)
    }

    @GetMapping("/redis/{key}")
    fun get(@PathVariable key: String): String? = redisService.getValue(key)
}
