package org.study.springredisstudy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.study.springredisstudy.redis.RedisRepository
import org.study.springredisstudy.redis.RedisService
import org.study.springredisstudy.redis.User

class RedisServiceTest {

    private val repository: RedisRepository = mock(RedisRepository::class.java)
    private val service = RedisService(repository)

    @Test
    fun saveAndRetrieveUser() {
        val user = User("1", "Alice")
        `when`(repository.findUser("1")).thenReturn(user)

        service.saveUser(user)
        verify(repository).saveUser(user)

        val result = service.getUser("1")
        verify(repository).findUser("1")
        assertEquals(user, result)
    }
}
