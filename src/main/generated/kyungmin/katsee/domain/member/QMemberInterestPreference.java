package kyungmin.katsee.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberInterestPreference is a Querydsl query type for MemberInterestPreference
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberInterestPreference extends EntityPathBase<MemberInterestPreference> {

    private static final long serialVersionUID = -664608090L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberInterestPreference memberInterestPreference = new QMemberInterestPreference("memberInterestPreference");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final EnumPath<kyungmin.katsee.domain.member.enums.InterestPreference> preference = createEnum("preference", kyungmin.katsee.domain.member.enums.InterestPreference.class);

    public QMemberInterestPreference(String variable) {
        this(MemberInterestPreference.class, forVariable(variable), INITS);
    }

    public QMemberInterestPreference(Path<? extends MemberInterestPreference> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberInterestPreference(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberInterestPreference(PathMetadata metadata, PathInits inits) {
        this(MemberInterestPreference.class, metadata, inits);
    }

    public QMemberInterestPreference(Class<? extends MemberInterestPreference> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

