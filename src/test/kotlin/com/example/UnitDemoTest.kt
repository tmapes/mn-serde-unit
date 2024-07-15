package com.example

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import kotlin.jvm.optionals.getOrNull

@MicronautTest
class UnitDemoTest {

    @Inject
    @Client("/")
    lateinit var client: HttpClient

    @Test
    fun test_getSerdeObject() {
        val request = HttpRequest.GET<Unit>("/test/serde")
        val response = client.toBlocking().exchange(request, String::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(
            """{"id":"4c437936-87f9-3717-8a91-49ad391cbf70"}""",
            response.getBody(String::class.java).getOrNull()
        )
    }

    @Test
    fun test_deleteAndReturnUnit() {
        val request = HttpRequest.DELETE<Unit>("/test/blocking_unit")
        val response = client.toBlocking().exchange(request, String::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNull(response.getBody(String::class.java).getOrNull())
    }

    @Test
    fun test_deleteAndReturnUnitdeleteAndReturnUnitSuspend() {
        val request = HttpRequest.DELETE<Unit>("/test/suspend_unit")
        val response = client.toBlocking().exchange(request, String::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNull(response.getBody(String::class.java).getOrNull())
    }

}
