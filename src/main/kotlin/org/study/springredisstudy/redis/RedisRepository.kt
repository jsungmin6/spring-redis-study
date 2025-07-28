package org.study.springredisstudy.redis

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository

/**
 * Repository exposing basic Redis operations using Lettuce.
 */
@Repository
class RedisRepository(private val redisTemplate: StringRedisTemplate) {

    /* Value operations */
    fun set(key: String, value: String) {
        redisTemplate.opsForValue().set(key, value)
    }

    fun get(key: String): String? = redisTemplate.opsForValue().get(key)

    /* Hash operations */
    fun putHash(key: String, hashKey: String, value: String) {
        redisTemplate.opsForHash<String, String>().put(key, hashKey, value)
    }

    fun getHash(key: String, hashKey: String): String? =
        redisTemplate.opsForHash<String, String>()[key, hashKey]

    /* List operations */
    fun leftPush(key: String, value: String) {
        redisTemplate.opsForList().leftPush(key, value)
    }

    fun rangeList(key: String, start: Long, end: Long): List<String> =
        redisTemplate.opsForList().range(key, start, end) ?: emptyList()

    /* Set operations */
    fun addSet(key: String, value: String) {
        redisTemplate.opsForSet().add(key, value)
    }

    fun members(key: String): Set<String> = redisTemplate.opsForSet().members(key) ?: emptySet()

    /* ZSet operations */
    fun addZSet(key: String, value: String, score: Double) {
        redisTemplate.opsForZSet().add(key, value, score)
    }

    fun rangeZSet(key: String, start: Long, end: Long): Set<String> =
        redisTemplate.opsForZSet().range(key, start, end) ?: emptySet()

    /* User operations */
    fun saveUser(user: User) {
        val key = "user:${'$'}{user.id}"
        redisTemplate.opsForHash<String, String>().put(key, "id", user.id)
        redisTemplate.opsForHash<String, String>().put(key, "name", user.name)
    }

    fun findUser(id: String): User? {
        val key = "user:${'$'}id"
        val map = redisTemplate.opsForHash<String, String>().entries(key)
        val userId = map["id"]
        val name = map["name"]
        return if (userId != null && name != null) User(userId, name) else null
    }
}
