package org.study.springredisstudy.redis

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository

@Repository
class RedisRepository(private val redisTemplate: StringRedisTemplate) {
    fun set(key: String, value: String) {
        redisTemplate.opsForValue().set(key, value)
    }

    fun get(key: String): String? = redisTemplate.opsForValue().get(key)
}
