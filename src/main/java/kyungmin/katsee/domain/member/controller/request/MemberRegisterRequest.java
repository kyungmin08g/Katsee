package kyungmin.katsee.domain.member.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "회원 등록 요청 객체")
public record MemberRegisterRequest(

  @Schema(description = "회원 ID")
  String memberId,

  @Schema(description = "비밀번호")
  String password,

  @Schema(description = "프로필 사진")
  String profileUrl,

  @Schema(description = "이름")
  String nickName,

  @Schema(description = "나이")
  String age,

  @Schema(description = "성별")
  String gender,

  @Schema(description = "소개")
  String introduction,

  @Schema(description = "관심사")
  List<String> interests
) { }
