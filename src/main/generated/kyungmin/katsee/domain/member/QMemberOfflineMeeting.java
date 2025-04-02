package kyungmin.katsee.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberOfflineMeeting is a Querydsl query type for MemberOfflineMeeting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberOfflineMeeting extends EntityPathBase<MemberOfflineMeeting> {

    private static final long serialVersionUID = 388806233L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberOfflineMeeting memberOfflineMeeting = new QMemberOfflineMeeting("memberOfflineMeeting");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<kyungmin.katsee.domain.member.enums.OfflineMeeting> isOffline = createEnum("isOffline", kyungmin.katsee.domain.member.enums.OfflineMeeting.class);

    public final QMember member;

    public QMemberOfflineMeeting(String variable) {
        this(MemberOfflineMeeting.class, forVariable(variable), INITS);
    }

    public QMemberOfflineMeeting(Path<? extends MemberOfflineMeeting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberOfflineMeeting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberOfflineMeeting(PathMetadata metadata, PathInits inits) {
        this(MemberOfflineMeeting.class, metadata, inits);
    }

    public QMemberOfflineMeeting(Class<? extends MemberOfflineMeeting> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

