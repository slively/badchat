package org.badchat.common.exceptions

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.StdSerializer

/**
 * Base Exception class used for any exceptions that a public/private api could throw.
 * Has mapping in controllers and web clients for status codes.
 */
@JsonSerialize(using = ApiExceptionSerializer::class)
sealed class BaseApiException(
    override val message: String
) : RuntimeException(message)

object ApiExceptionSerializer : StdSerializer<BaseApiException>(BaseApiException::class.java) {
    override fun serialize(value: BaseApiException, jgen: JsonGenerator, provider: SerializerProvider?) {
        jgen.writeStartObject()
        jgen.writeStringField("message", value.message)
        jgen.writeEndObject()
    }
}

open class BadRequestException(message: String = "Bad Request") : BaseApiException(message)
open class UnauthorizedException(message: String = "Unauthorized") : BaseApiException(message)
open class NotFoundException(message: String = "Not Found") : BaseApiException(message)
open class RequestTimeoutException(message: String = "Request Timeout") : BaseApiException(message)
open class UnprocessableEntityException(message: String = "UnprocessableEntity") : BaseApiException(message)
open class InternalServerException(message: String = "Internal Server Error") : BaseApiException(message)
