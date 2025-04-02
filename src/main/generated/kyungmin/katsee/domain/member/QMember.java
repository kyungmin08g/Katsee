package kyungmin.katsee.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -2136606431L;

    public static final QMember member = new QMember("member1");

    public final StringPath age = createString("age");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final ListPath<MemberFriendStyle, QMemberFriendStyle> friendStyle = this.<MemberFriendStyle, QMemberFriendStyle>createList("friendStyle", MemberFriendStyle.class, QMemberFriendStyle.class, PathInits.DIRECT2);

    public final EnumPath<kyungmin.katsee.domain.member.enums.Gender> gender = createEnum("gender", kyungmin.katsee.domain.member.enums.Gender.class);

    public final ListPath<MemberInterest, QMemberInterest> interest = this.<MemberInterest, QMemberInterest>createList("interest", MemberInterest.class, QMemberInterest.class, PathInits.DIRECT2);

    public final ListPath<MemberInterestLevel, QMemberInterestLevel> interestLevel = this.<MemberInterestLevel, QMemberInterestLevel>createList("interestLevel", MemberInterestLevel.class, QMemberInterestLevel.class, PathInits.DIRECT2);

    public final ListPath<MemberInterestPreference, QMemberInterestPreference> interestPreference = this.<MemberInterestPreference, QMemberInterestPreference>createList("interestPreference", MemberInterestPreference.class, QMemberInterestPreference.class, PathInits.DIRECT2);

    public final StringPath introduction = createString("introduction");

    public final ListPath<MemberOfflineMeeting, QMemberOfflineMeeting> isOfflineMeeting = this.<MemberOfflineMeeting, QMemberOfflineMeeting>createList("isOfflineMeeting", MemberOfflineMeeting.class, QMemberOfflineMeeting.class, PathInits.DIRECT2);

    public final StringPath memberId = createString("memberId");

    public final StringPath nickName = createString("nickName");

    public final ListPath<MemberOnlineTalkStyle, QMemberOnlineTalkStyle> onlineTalkStyle = this.<MemberOnlineTalkStyle, QMemberOnlineTalkStyle>createList("onlineTalkStyle", MemberOnlineTalkStyle.class, QMemberOnlineTalkStyle.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final ListPath<MemberPersonalityType, QMemberPersonalityType> personalityType = this.<MemberPersonalityType, QMemberPersonalityType>createList("personalityType", MemberPersonalityType.class, QMemberPersonalityType.class, PathInits.DIRECT2);

    public final StringPath profileUrl = createString("profileUrl");

    public final ListPath<MemberRelationshipDepth, QMemberRelationshipDepth> relationshipDepth = this.<MemberRelationshipDepth, QMemberRelationshipDepth>createList("relationshipDepth", MemberRelationshipDepth.class, QMemberRelationshipDepth.class, PathInits.DIRECT2);

    public final EnumPath<kyungmin.katsee.domain.member.enums.Role> role = createEnum("role", kyungmin.katsee.domain.member.enums.Role.class);

    public final ListPath<MemberTalkStyle, QMemberTalkStyle> talkStyle = this.<MemberTalkStyle, QMemberTalkStyle>createList("talkStyle", MemberTalkStyle.class, QMemberTalkStyle.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

