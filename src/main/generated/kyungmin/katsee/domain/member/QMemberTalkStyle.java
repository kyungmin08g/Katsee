package kyungmin.katsee.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberTalkStyle is a Querydsl query type for MemberTalkStyle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberTalkStyle extends EntityPathBase<MemberTalkStyle> {

    private static final long serialVersionUID = 1690296388L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberTalkStyle memberTalkStyle = new QMemberTalkStyle("memberTalkStyle");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final EnumPath<kyungmin.katsee.domain.member.enums.TalkStyle> talkStyle = createEnum("talkStyle", kyungmin.katsee.domain.member.enums.TalkStyle.class);

    public QMemberTalkStyle(String variable) {
        this(MemberTalkStyle.class, forVariable(variable), INITS);
    }

    public QMemberTalkStyle(Path<? extends MemberTalkStyle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberTalkStyle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberTalkStyle(PathMetadata metadata, PathInits inits) {
        this(MemberTalkStyle.class, metadata, inits);
    }

    public QMemberTalkStyle(Class<? extends MemberTalkStyle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

