package org.study.springredisstudy.redis

import org.springframework.stereotype.Service

@Service
class RedisService(private val redisRepository: RedisRepository) {
    fun setValue(key: String, value: String) = redisRepository.set(key, value)

    fun getValue(key: String): String? = redisRepository.get(key)
}
