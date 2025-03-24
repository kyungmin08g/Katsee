package kyungmin.katsee.domain.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kyungmin.katsee.domain.member.*;
import kyungmin.katsee.domain.member.controller.response.GetMemberDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static kyungmin.katsee.domain.member.QMember.member;

@Repository
@RequiredArgsConstructor
public class DetailMemberRepository {
  private final JPAQueryFactory queryFactory;

  public GetMemberDetailResponse getMemberDetail(String memberId) {
    return queryFactory.select(
      Projections.fields(
        GetMemberDetailResponse.class,
        member.memberId,
        member.profileUrl,
        member.nickName,
        member.age,
        member.gender,
        member.introduction
      )
    )
    .from(member)
    .where(member.memberId.eq(memberId))
    .fetchOne();
  }

  public List<MemberInterest> getInterests(String memberId) {
    return queryFactory.select(
        member.interest
      )
      .from(member)
      .where(member.memberId.eq(memberId))
      .fetchOne();
  }

  public List<List<MemberPersonalityType>> getPersonalityTypes(String memberId) {
    return queryFactory.select(
        member.personalityType
      )
      .from(member)
      .where(member.memberId.eq(memberId))
      .fetch();
  }

  public List<List<MemberTalkStyle>> getTalkStyles(String memberId) {
    return queryFactory.select(
        member.talkStyle
      )
      .from(member)
      .where(member.memberId.eq(memberId))
      .fetch();
  }

  public List<List<MemberFriendStyle>> getFriendStyles(String memberId) {
    return queryFactory.select(
        member.friendStyle
      )
      .from(member)
      .where(member.memberId.eq(memberId))
      .fetch();
  }

  public List<MemberRelationshipDepth> getRelationshipDepths(String memberId) {
    return queryFactory.select(
        member.relationshipDepth
      )
      .from(member)
      .where(member.memberId.eq(memberId))
      .fetchOne();
  }

  public List<MemberOfflineMeeting> getOfflineMeetings(String memberId) {
    return queryFactory.select(
        member.isOfflineMeeting
      )
      .from(member)
      .where(member.memberId.eq(memberId))
      .fetchOne();
  }

  public List<List<MemberOnlineTalkStyle>> getOnlineTalkStyles(String memberId) {
    return queryFactory.select(
        member.onlineTalkStyle
      )
      .from(member)
      .where(member.memberId.eq(memberId))
      .fetch();
  }

  public List<MemberInterestPreference> getInterestPreferences(String memberId) {
    return queryFactory.select(
        member.interestPreference
      )
      .from(member)
      .where(member.memberId.eq(memberId))
      .fetchOne();
  }

  public List<MemberInterestLevel> getInterestLevels(String memberId) {
    return queryFactory.select(
        member.interestLevel
      )
      .from(member)
      .where(member.memberId.eq(memberId))
      .fetchOne();
  }
}
