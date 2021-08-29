package org.badchat.messages.ctrl

import kotlinx.coroutines.flow.toList
import org.badchat.messages.public_api.ChatMessage
import org.badchat.messages.public_api.ChatMessagesApi
import org.badchat.messages.public_api.NewChatMessageRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/api/messages")
class ChatMessagesCtrl(
    @Autowired private val svc: ChatsService
) : ChatMessagesApi {

    @PostMapping
    override suspend fun create(@RequestBody msg: NewChatMessageRequest): ChatMessage {
        return svc.create(msg)
    }

    @GetMapping
    override suspend fun findAll() =
        svc.findAll().toList()

    @GetMapping("/latest/{count}")
    override suspend fun findLatest(@PathVariable("count") count: Int) =
        svc.findLatest(count).toList()

    @GetMapping("/since/{createdAt}")
    override suspend fun findByCreatedAtGreaterThanEqual(
        @PathVariable("createdAt") createdAt: Instant
    ) = svc.findByCreatedAtGreaterThanEqual(createdAt).toList()

    @GetMapping("/containing/{needle}")
    override suspend fun findWithTextContaining(@PathVariable("needle") needle: String) =
        svc.findWithTextContaining(needle)

    @GetMapping("/random")
    override suspend fun findRandom() = svc.findRandom()
}
