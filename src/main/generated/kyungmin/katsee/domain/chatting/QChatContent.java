package kyungmin.katsee.domain.chatting;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatContent is a Querydsl query type for ChatContent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatContent extends EntityPathBase<ChatContent> {

    private static final long serialVersionUID = 627966534L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatContent chatContent = new QChatContent("chatContent");

    public final QChatting chatting;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final kyungmin.katsee.domain.member.QMember member;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QChatContent(String variable) {
        this(ChatContent.class, forVariable(variable), INITS);
    }

    public QChatContent(Path<? extends ChatContent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatContent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatContent(PathMetadata metadata, PathInits inits) {
        this(ChatContent.class, metadata, inits);
    }

    public QChatContent(Class<? extends ChatContent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatting = inits.isInitialized("chatting") ? new QChatting(forProperty("chatting"), inits.get("chatting")) : null;
        this.member = inits.isInitialized("member") ? new kyungmin.katsee.domain.member.QMember(forProperty("member")) : null;
    }

}

