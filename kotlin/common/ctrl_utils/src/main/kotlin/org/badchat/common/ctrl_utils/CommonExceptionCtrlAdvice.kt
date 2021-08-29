package org.badchat.common.ctrl_utils

import org.badchat.common.exceptions.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class CommonExceptionCtrlAdvice {

    @ExceptionHandler(BaseApiException::class)
    fun myError(ex: Exception): ResponseEntity<BaseApiException>? =
        if (ex is BaseApiException) {
            val status = when (ex) {
                is BadRequestException -> HttpStatus.BAD_REQUEST
                is UnauthorizedException -> HttpStatus.UNAUTHORIZED
                is NotFoundException -> HttpStatus.NOT_FOUND
                is RequestTimeoutException -> HttpStatus.REQUEST_TIMEOUT
                is UnprocessableEntityException -> HttpStatus.UNPROCESSABLE_ENTITY
                is InternalServerException -> HttpStatus.INTERNAL_SERVER_ERROR
            }.value()
            ResponseEntity.status(status).body(ex)
        } else {
            null
        }
}
