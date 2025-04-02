package kyungmin.katsee.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberRelationshipDepth is a Querydsl query type for MemberRelationshipDepth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberRelationshipDepth extends EntityPathBase<MemberRelationshipDepth> {

    private static final long serialVersionUID = -772795318L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberRelationshipDepth memberRelationshipDepth = new QMemberRelationshipDepth("memberRelationshipDepth");

    public final EnumPath<kyungmin.katsee.domain.member.enums.RelationshipDepth> depth = createEnum("depth", kyungmin.katsee.domain.member.enums.RelationshipDepth.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public QMemberRelationshipDepth(String variable) {
        this(MemberRelationshipDepth.class, forVariable(variable), INITS);
    }

    public QMemberRelationshipDepth(Path<? extends MemberRelationshipDepth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberRelationshipDepth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberRelationshipDepth(PathMetadata metadata, PathInits inits) {
        this(MemberRelationshipDepth.class, metadata, inits);
    }

    public QMemberRelationshipDepth(Class<? extends MemberRelationshipDepth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

