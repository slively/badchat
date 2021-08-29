package org.badchat.messages.ctrl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.badchat.messages.dao.ChatMessagePersistence
import org.badchat.messages.dao.ChatMessagesRepository
import org.badchat.messages.public_api.ChatMessage
import org.badchat.messages.public_api.ChatMessageNotFoundException
import org.badchat.messages.public_api.InvalidChatMessageException
import org.badchat.messages.public_api.NewChatMessageRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class ChatsService(
    @Autowired private val dao: ChatMessagesRepository
) {
    suspend fun create(msg: NewChatMessageRequest): ChatMessage {
        if (msg.text.contains("cats")) {
            throw InvalidChatMessageException("no cats allowed")
        }

        return dao.save(msg.toPersistence()).toChatMessage().also {
            log.info("Created message {}", it)
        }
    }

    suspend fun findAll(): Flow<ChatMessage> {
        return dao.findAll().toChatMessages()
    }

    suspend fun findLatest(count: Int): Flow<ChatMessage> {
        return dao.findLatest(count).toChatMessages()
    }

    suspend fun findByCreatedAtGreaterThanEqual(createdAt: Instant): List<ChatMessage> {
        return dao.findByCreatedAtGreaterThanEqual(createdAt).toChatMessages()
    }

    suspend fun findWithTextContaining(needle: String): List<ChatMessage> {
        return dao.customFindWithTextContaining(needle).toChatMessages()
    }

    suspend fun findRandom(): ChatMessage {
        log.info("Finding random chat message...")
        return dao.findRandomId().let { id ->
            id?.let {
                dao.findById(it)?.toChatMessage() ?: throw ChatMessageNotFoundException(id)
            } ?: throw InvalidChatMessageException("There are no chat messages yet.")
        }
    }

    companion object {
        private val log = getLogger()
    }
}

fun NewChatMessageRequest.toPersistence() = ChatMessagePersistence.new(text)
fun ChatMessagePersistence.toChatMessage() = ChatMessage(id = checkNotNull(id), text = text)
fun Collection<ChatMessagePersistence>.toChatMessages() = map(ChatMessagePersistence::toChatMessage)
fun Flow<ChatMessagePersistence>.toChatMessages() = map(ChatMessagePersistence::toChatMessage)

// this should go in a utils package
inline fun <reified T : Any> T.getLogger(): Logger =
    LoggerFactory.getLogger(T::class.java)