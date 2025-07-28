package org.study.user

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class UserRepository(
    private val redisTemplate: RedisTemplate<String, User>
) {
    private val valueOps = redisTemplate.opsForValue()

    fun save(user: User) {
        valueOps.set(user.id, user)
    }

    fun saveIfAbsent(user: User): Boolean {
        return valueOps.setIfAbsent(user.id, user) ?: false
    }

    fun find(id: String): User? = valueOps.get(id)

    fun exists(id: String): Boolean = redisTemplate.hasKey(id)

    fun delete(id: String) {
        redisTemplate.delete(id)
    }

    fun expire(id: String, ttl: Duration): Boolean {
        return redisTemplate.expire(id, ttl)
    }

    fun incrementCounter(key: String): Long? {
        return redisTemplate.opsForValue().increment(key)
    }

    fun decrementCounter(key: String): Long? {
        return redisTemplate.opsForValue().decrement(key)
    }
}
