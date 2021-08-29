package org.badchat.messages.dao

import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface ChatMessagesRepository :
    CoroutineCrudRepository<ChatMessagePersistence, Long>,
    ChatMessagesCustomQueryRepository {

    suspend fun findByCreatedAtGreaterThanEqual(createdAt: Instant): List<ChatMessagePersistence>

    @Query("select * from ${ChatMessagesTable.name} order by ${ChatMessagesTable.Columns.createdAt} desc limit :count")
    suspend fun findLatest(count: Int): Flow<ChatMessagePersistence>
}

interface ChatMessagesCustomQueryRepository {
    suspend fun customFindWithTextContaining(needle: String): List<ChatMessagePersistence>
    suspend fun findRandomId(): Long?
}
