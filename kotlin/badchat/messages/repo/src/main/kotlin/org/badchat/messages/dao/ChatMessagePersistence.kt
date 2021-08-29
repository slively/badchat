package org.badchat.messages.dao

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table(ChatMessagesTable.name)
data class ChatMessagePersistence(
    @Id val id: Long?,
    // unnecessary @Column used as example
    @Column(ChatMessagesTable.Columns.text) val text: String,
    val createdAt: Instant
) {
    companion object {
        fun new(text: String) = ChatMessagePersistence(id = null, text = text, createdAt = Instant.now())
    }
}
