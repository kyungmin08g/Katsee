package kyungmin.katsee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

  // 로그인 페이지
  @GetMapping(value = "/login")
  public String loginPage() {
    return "user/Login";
  }

  // 첫번째 회원가입 페이지
  @GetMapping(value = "/join-1")
  public String firstJoinPage() {
    return "user/JoinFirst";
  }

  // 두번째 회원가입 페이지
  @GetMapping(value = "/join-2/{id}")
  public String secondJoinPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "user/JoinSecond";
  }

  // 세번째 회원가입 페이지
  @GetMapping(value = "/join-3/{id}")
  public String thirdJoinPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "user/JoinThird";
  }

  // 마이페이지
  @GetMapping(value = "/my")
  public String myPage() {
    return "user/MyPage";
  }

  // 첫번째 회원 상세 정보 등록 페이지
  @GetMapping(value = "/detail-1/{id}")
  public String detailFirstPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "user/MemberFirstDetails";
  }

  // 두번째 회원 상세 정보 등록 페이지
  @GetMapping(value = "/detail-2/{id}")
  public String detailSecondPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "user/MemberSecondDetails";
  }

  // 세번째 회원 상세 정보 등록 페이지
  @GetMapping(value = "/detail-3/{id}")
  public String detailThirdPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "user/MemberThirdDetails";
  }

  // 네번째 회원 상세 정보 등록 페이지
  @GetMapping(value = "/detail-4/{id}")
  public String detailFourthPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "user/MemberFourthDetails";
  }

  // 다섯번째 회원 상세 정보 등록 페이지
  @GetMapping(value = "/detail-5/{id}")
  public String detailFifthPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "user/MemberFifthDetails";
  }

  // 여섯번째 회원 상세 정보 등록 페이지
  @GetMapping(value = "/detail-6/{id}")
  public String detailSixthPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "user/MemberSixthDetails";
  }

  // 일곱번째 회원 상세 정보 등록 페이지
  @GetMapping(value = "/detail-7/{id}")
  public String detailSeventhPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "user/MemberSeventhDetails";
  }

  // 여덟번째 회원 상세 정보 등록 페이지
  @GetMapping(value = "/detail-8/{id}")
  public String detailEighthPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "user/MemberEighthDetails";
  }

  // 홈 페이지
  @GetMapping(value = "/")
  public String homePage() {
    return "user/UserHome";
  }

  // 공지 페이지
  @GetMapping(value = "/notice")
  public String noticePage() {
    return "user/UserNotice";
  }

  // 공지 상세 페이지
  @GetMapping(value = "/notice/detail/{id}")
  public String noticeDetailPage(@PathVariable("id") String noticeId, Model model) {
    model.addAttribute("noticeId", noticeId);
    return "user/UserNoticeDetails";
  }

  // 친구 추천 페이지
  @GetMapping(value = "/recommend")
  public String friendRecommendPage() {
    return "user/FriendRecommend";
  }

  // 친구 상세 정보 페이지
  @GetMapping(value = "/friend/detail/{id}/{fitness}")
  public String friendDetailPage(@PathVariable("id") String friendId, @PathVariable("fitness") String fitness, Model model) {
    model.addAttribute("friendId", friendId);
    model.addAttribute("fitness", fitness);
    return "user/FriendDetails";
  }

  // 친구 요청 조회 페이지
  @GetMapping(value = "/request/list")
  public String requestListPage() {
    return "user/FriendRequestList";
  }

  // 요청 보낸 친구 상세 정보 페이지
  @GetMapping(value = "/friend/detail/{id}")
  public String friendDetailPage(@PathVariable("id") String friendId, Model model) {
    model.addAttribute("friendId", friendId);
    return "user/FriendRequestDetails";
  }

  // 관리자 페이지
  @GetMapping(value = "/admin")
  public String adminPage() {
    return "admin/AdminHome";
  }

  // 관리자 공지 페이지
  @GetMapping(value = "/admin/notice")
  public String adminNoticePage() {
    return "admin/AdminNotice";
  }

  // 관리자 공지 상세 페이지
  @GetMapping(value = "/admin/notice/detail/{id}")
  public String adminNoticePage(@PathVariable("id") String noticeId, Model model) {
    model.addAttribute("noticeId", noticeId);
    return "admin/AdminNoticeDetails";
  }

  // 관리자 공지 수정 페이지
  @GetMapping(value = "/admin/notice/update/{id}")
  public String noticeUpdatePage(@PathVariable("id") String noticeId, Model model) {
    model.addAttribute("noticeId", noticeId);
    return "admin/AdminNoticeModify";
  }

  // 관리자 공지 등록 페이지
  @GetMapping(value = "/admin/notice/create")
  public String noticeCreatePage() {
    return "admin/AdminNoticeRegister";
  }

  // 채팅반 목록 페이지
  @GetMapping(value = "/chat/list")
  public String chatListPage() {
    return "user/ChatList";
  }

  // 회원 정보 수정 페이지
  @GetMapping(value = "/member/detail/update/{id}")
  public String memberDetailUpdatePage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "user/JoinSecond";
  }

  // 채팅 페이지
  @GetMapping(value = "/chat/content/{roomId}/{friendId}/{memberId}")
  public String chattingPage(
    @PathVariable("roomId") String roomId,
    @PathVariable("friendId") String friendId,
    @PathVariable("memberId") String memberId,
    Model model
  ) {
    model.addAttribute("roomId", roomId);
    model.addAttribute("friendId", friendId);
    model.addAttribute("memberId", memberId);
    return "user/Chat";
  }
}
