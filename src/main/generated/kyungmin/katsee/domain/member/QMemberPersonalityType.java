package kyungmin.katsee.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberPersonalityType is a Querydsl query type for MemberPersonalityType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberPersonalityType extends EntityPathBase<MemberPersonalityType> {

    private static final long serialVersionUID = 1483022183L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberPersonalityType memberPersonalityType = new QMemberPersonalityType("memberPersonalityType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final EnumPath<kyungmin.katsee.domain.member.enums.PersonalityType> personality = createEnum("personality", kyungmin.katsee.domain.member.enums.PersonalityType.class);

    public QMemberPersonalityType(String variable) {
        this(MemberPersonalityType.class, forVariable(variable), INITS);
    }

    public QMemberPersonalityType(Path<? extends MemberPersonalityType> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberPersonalityType(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberPersonalityType(PathMetadata metadata, PathInits inits) {
        this(MemberPersonalityType.class, metadata, inits);
    }

    public QMemberPersonalityType(Class<? extends MemberPersonalityType> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

