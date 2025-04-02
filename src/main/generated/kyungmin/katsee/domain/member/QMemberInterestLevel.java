package kyungmin.katsee.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberInterestLevel is a Querydsl query type for MemberInterestLevel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberInterestLevel extends EntityPathBase<MemberInterestLevel> {

    private static final long serialVersionUID = 76006105L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberInterestLevel memberInterestLevel = new QMemberInterestLevel("memberInterestLevel");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<kyungmin.katsee.domain.member.enums.InterestLevel> level = createEnum("level", kyungmin.katsee.domain.member.enums.InterestLevel.class);

    public final QMember member;

    public QMemberInterestLevel(String variable) {
        this(MemberInterestLevel.class, forVariable(variable), INITS);
    }

    public QMemberInterestLevel(Path<? extends MemberInterestLevel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberInterestLevel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberInterestLevel(PathMetadata metadata, PathInits inits) {
        this(MemberInterestLevel.class, metadata, inits);
    }

    public QMemberInterestLevel(Class<? extends MemberInterestLevel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

