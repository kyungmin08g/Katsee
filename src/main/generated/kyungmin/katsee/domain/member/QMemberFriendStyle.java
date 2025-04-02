package kyungmin.katsee.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberFriendStyle is a Querydsl query type for MemberFriendStyle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberFriendStyle extends EntityPathBase<MemberFriendStyle> {

    private static final long serialVersionUID = -1820690990L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberFriendStyle memberFriendStyle = new QMemberFriendStyle("memberFriendStyle");

    public final EnumPath<kyungmin.katsee.domain.member.enums.FriendStyle> friendStyle = createEnum("friendStyle", kyungmin.katsee.domain.member.enums.FriendStyle.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public QMemberFriendStyle(String variable) {
        this(MemberFriendStyle.class, forVariable(variable), INITS);
    }

    public QMemberFriendStyle(Path<? extends MemberFriendStyle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberFriendStyle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberFriendStyle(PathMetadata metadata, PathInits inits) {
        this(MemberFriendStyle.class, metadata, inits);
    }

    public QMemberFriendStyle(Class<? extends MemberFriendStyle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

