package org.badchat.messages.dao

object ChatMessagesTable {
    const val name = "chat_messages"

    object Columns {
        const val id = "id"
        const val text = "text"
        const val createdAt = "created_at"
    }
}