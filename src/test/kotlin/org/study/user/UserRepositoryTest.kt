package org.study.user

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import redis.embedded.RedisServer

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

    private lateinit var redisServer: RedisServer

    @Autowired
    lateinit var userRepository: UserRepository

    @BeforeAll
    fun startRedis() {
        redisServer = RedisServer()
        redisServer.start()
    }

    @AfterAll
    fun stopRedis() {
        redisServer.stop()
    }

    @Test
    fun `save and find user`() {
        val user = User("1", "tester")
        userRepository.save(user)
        val result = userRepository.find("1")
        Assertions.assertEquals(user, result)
    }

    @Test
    fun `exists and delete`() {
        val user = User("2", "remove")
        userRepository.save(user)
        Assertions.assertTrue(userRepository.exists("2"))
        userRepository.delete("2")
        Assertions.assertFalse(userRepository.exists("2"))
    }

    @Test
    fun `increment and decrement counter`() {
        Assertions.assertEquals(1, userRepository.incrementCounter("count"))
        Assertions.assertEquals(0, userRepository.decrementCounter("count"))
    }
}
