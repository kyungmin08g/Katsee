package kyungmin.katsee.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberOnlineTalkStyle is a Querydsl query type for MemberOnlineTalkStyle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberOnlineTalkStyle extends EntityPathBase<MemberOnlineTalkStyle> {

    private static final long serialVersionUID = 1862341617L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberOnlineTalkStyle memberOnlineTalkStyle = new QMemberOnlineTalkStyle("memberOnlineTalkStyle");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final EnumPath<kyungmin.katsee.domain.member.enums.OnlineTalkStyle> onlineTalkStyle = createEnum("onlineTalkStyle", kyungmin.katsee.domain.member.enums.OnlineTalkStyle.class);

    public QMemberOnlineTalkStyle(String variable) {
        this(MemberOnlineTalkStyle.class, forVariable(variable), INITS);
    }

    public QMemberOnlineTalkStyle(Path<? extends MemberOnlineTalkStyle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberOnlineTalkStyle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberOnlineTalkStyle(PathMetadata metadata, PathInits inits) {
        this(MemberOnlineTalkStyle.class, metadata, inits);
    }

    public QMemberOnlineTalkStyle(Class<? extends MemberOnlineTalkStyle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

