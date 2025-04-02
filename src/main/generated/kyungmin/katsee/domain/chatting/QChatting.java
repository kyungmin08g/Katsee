package kyungmin.katsee.domain.chatting;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatting is a Querydsl query type for Chatting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatting extends EntityPathBase<Chatting> {

    private static final long serialVersionUID = -1803244511L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatting chatting = new QChatting("chatting");

    public final ListPath<ChatContent, QChatContent> chatContents = this.<ChatContent, QChatContent>createList("chatContents", ChatContent.class, QChatContent.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final kyungmin.katsee.domain.member.QMember friend;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QChatting(String variable) {
        this(Chatting.class, forVariable(variable), INITS);
    }

    public QChatting(Path<? extends Chatting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatting(PathMetadata metadata, PathInits inits) {
        this(Chatting.class, metadata, inits);
    }

    public QChatting(Class<? extends Chatting> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.friend = inits.isInitialized("friend") ? new kyungmin.katsee.domain.member.QMember(forProperty("friend")) : null;
    }

}

