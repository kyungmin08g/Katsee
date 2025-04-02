package kyungmin.katsee.domain.matching;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMatching is a Querydsl query type for Matching
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatching extends EntityPathBase<Matching> {

    private static final long serialVersionUID = -913593855L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMatching matching = new QMatching("matching");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final kyungmin.katsee.domain.member.QMember friend;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<kyungmin.katsee.domain.matching.enums.MatchStatus> matchStatus = createEnum("matchStatus", kyungmin.katsee.domain.matching.enums.MatchStatus.class);

    public final kyungmin.katsee.domain.member.QMember member;

    public QMatching(String variable) {
        this(Matching.class, forVariable(variable), INITS);
    }

    public QMatching(Path<? extends Matching> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMatching(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMatching(PathMetadata metadata, PathInits inits) {
        this(Matching.class, metadata, inits);
    }

    public QMatching(Class<? extends Matching> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.friend = inits.isInitialized("friend") ? new kyungmin.katsee.domain.member.QMember(forProperty("friend")) : null;
        this.member = inits.isInitialized("member") ? new kyungmin.katsee.domain.member.QMember(forProperty("member")) : null;
    }

}

