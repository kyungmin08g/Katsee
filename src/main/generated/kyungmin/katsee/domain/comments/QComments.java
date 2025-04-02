package kyungmin.katsee.domain.comments;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComments is a Querydsl query type for Comments
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComments extends EntityPathBase<Comments> {

    private static final long serialVersionUID = 1823404001L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComments comments = new QComments("comments");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final kyungmin.katsee.domain.member.QMember member;

    public final kyungmin.katsee.domain.notice.QNotice notice;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QComments(String variable) {
        this(Comments.class, forVariable(variable), INITS);
    }

    public QComments(Path<? extends Comments> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComments(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComments(PathMetadata metadata, PathInits inits) {
        this(Comments.class, metadata, inits);
    }

    public QComments(Class<? extends Comments> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new kyungmin.katsee.domain.member.QMember(forProperty("member")) : null;
        this.notice = inits.isInitialized("notice") ? new kyungmin.katsee.domain.notice.QNotice(forProperty("notice"), inits.get("notice")) : null;
    }

}

