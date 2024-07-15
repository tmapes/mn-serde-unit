package com.example

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.serde.annotation.Serdeable
import kotlinx.coroutines.delay
import java.util.UUID

@Controller("/test")
@Suppress("RedundantUnitReturnType")
class TestController {

    @Get("/serde")
    fun getSerdeObject(): MyCoolClazz {
        return MyCoolClazz(UUID.nameUUIDFromBytes("foo-bar-baz".toByteArray()))
    }

    @Delete("/blocking_unit")
    fun deleteAndReturnUnit(): Unit {
        Thread.sleep(100)
        return
    }

    @Delete("/suspend_unit")
    suspend fun deleteAndReturnUnitSuspend(): Unit {
        delay(100)
        return
    }


}

@Serdeable
data class MyCoolClazz(
    val id: UUID,
)
