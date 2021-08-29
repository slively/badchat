package org.badchat.messages.public_api

import org.badchat.common.exceptions.NotFoundException
import org.badchat.common.exceptions.UnprocessableEntityException
import java.time.Instant

interface ChatMessagesApi {
    suspend fun create(msg: NewChatMessageRequest): ChatMessage
    suspend fun findAll(): List<ChatMessage>
    suspend fun findLatest(count: Int): List<ChatMessage>
    suspend fun findByCreatedAtGreaterThanEqual(createdAt: Instant): List<ChatMessage>
    suspend fun findWithTextContaining(needle: String): List<ChatMessage>
    suspend fun findRandom(): ChatMessage
}

data class NewChatMessageRequest(val text: String)

class ChatMessageNotFoundException(id: Long): NotFoundException("Chat message not found with id: $id")
class InvalidChatMessageException(reason: String): UnprocessableEntityException("Invalid chat message: $reason")
