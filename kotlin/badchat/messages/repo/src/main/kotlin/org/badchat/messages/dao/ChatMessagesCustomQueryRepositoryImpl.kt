package org.badchat.messages.dao

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.flow
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.query
import org.springframework.r2dbc.core.awaitSingle
import org.springframework.r2dbc.core.awaitSingleOrNull
import org.springframework.stereotype.Component

@Component
class ChatMessagesCustomQueryRepositoryImpl(
    @Autowired private val template: R2dbcEntityTemplate
) : ChatMessagesCustomQueryRepository {

    // for some reason I could convert a .matching to a flux to a flow
    // class reactor.core.publisher.FluxUsingWhen cannot be cast to class kotlinx.coroutines.flow.Flow
    override suspend fun customFindWithTextContaining(needle: String): List<ChatMessagePersistence> {
        return template.select(ChatMessagePersistence::class.java)
            .from(ChatMessagesTable.name)
            .matching(
                query(
                    where(ChatMessagesTable.Columns.text)
                        .like("%$needle%")
                )
                    .sort(Sort.by(Sort.Order.desc(ChatMessagesTable.Columns.createdAt)))
            )
            .all()
            .collectList()
            .awaitSingle()
    }

    override suspend fun findRandomId(): Long? {
        return template.databaseClient
            .sql("select id from ${ChatMessagesTable.name} order by random() limit 1;")
            .map { row ->
                (row["id"] as Int).toLong()
            }
            .awaitSingleOrNull()
    }
}