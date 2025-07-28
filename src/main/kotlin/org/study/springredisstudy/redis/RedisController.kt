package org.study.springredisstudy.redis

import org.springframework.web.bind.annotation.*

@RestController
class RedisController(private val redisService: RedisService) {
    @PostMapping("/redis/{key}")
    fun set(@PathVariable key: String, @RequestParam value: String) {
        redisService.setValue(key, value)
    }

    @GetMapping("/redis/{key}")
    fun get(@PathVariable key: String): String? = redisService.getValue(key)

    @PostMapping("/redis/user/{id}")
    fun saveUser(@PathVariable id: String, @RequestParam name: String) {
        redisService.saveUser(User(id, name))
    }

    @GetMapping("/redis/user/{id}")
    fun getUser(@PathVariable id: String): User? = redisService.getUser(id)
}
