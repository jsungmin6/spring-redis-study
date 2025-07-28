package org.study.springredisstudy.redis

import org.springframework.stereotype.Service

@Service
class RedisService(private val redisRepository: RedisRepository) {
    fun setValue(key: String, value: String) = redisRepository.set(key, value)

    fun getValue(key: String): String? = redisRepository.get(key)

    fun putHash(key: String, hashKey: String, value: String) =
        redisRepository.putHash(key, hashKey, value)

    fun getHash(key: String, hashKey: String): String? =
        redisRepository.getHash(key, hashKey)

    fun leftPush(key: String, value: String) = redisRepository.leftPush(key, value)

    fun rangeList(key: String, start: Long, end: Long): List<String> =
        redisRepository.rangeList(key, start, end)

    fun addSet(key: String, value: String) = redisRepository.addSet(key, value)

    fun members(key: String): Set<String> = redisRepository.members(key)

    fun addZSet(key: String, value: String, score: Double) =
        redisRepository.addZSet(key, value, score)

    fun rangeZSet(key: String, start: Long, end: Long): Set<String> =
        redisRepository.rangeZSet(key, start, end)

    fun saveUser(user: User) = redisRepository.saveUser(user)

    fun getUser(id: String): User? = redisRepository.findUser(id)
}
