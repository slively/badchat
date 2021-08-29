package org.badchat.messages.ctrl

import org.badchat.messages.public_api.ChatMessageNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CtrlAdvice {

//    @ExceptionHandler(ChatMessageNotFoundException::class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    fun cnfe(e: ChatMessageNotFoundException) = e
//
//    @ExceptionHandler(InvalidChatMessageException::class)
//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
//    fun icme(e: InvalidChatMessageException) = e
//
//    @ExceptionHandler(InvalidChatMessageException::class)
//    fun asdf(e: BaseE) = e
}