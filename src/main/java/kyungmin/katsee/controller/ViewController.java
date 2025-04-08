package kyungmin.katsee.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kyungmin.katsee.api_response.exception.GeneralException;
import kyungmin.katsee.api_response.status.ErrorStatus;
import kyungmin.katsee.domain.member.Member;
import kyungmin.katsee.domain.member.enums.Role;
import kyungmin.katsee.domain.member.repository.MemberRepository;
import kyungmin.katsee.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

  @GetMapping(value = "/login")
  public String loginPage() {
    return "/user/Login";
  }

  @GetMapping(value = "/join-1")
  public String firstJoinPage() {
    return "/user/JoinFirst";
  }

  @GetMapping(value = "/join-2/{id}")
  public String secondJoinPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "/user/JoinSecond";
  }

  @GetMapping(value = "/join-3/{id}")
  public String thirdJoinPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "/user/JoinThird";
  }

  @GetMapping(value = "/my")
  public String myPage() {
    return "/user/MyPage";
  }

  @GetMapping(value = "/detail-1/{id}")
  public String detailFirstPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "/user/MemberFirstDetails";
  }

  @GetMapping(value = "/detail-2/{id}")
  public String detailSecondPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "/user/MemberSecondDetails";
  }

  @GetMapping(value = "/detail-3/{id}")
  public String detailThirdPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "/user/MemberThirdDetails";
  }

  @GetMapping(value = "/detail-4/{id}")
  public String detailFourthPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "/user/MemberFourthDetails";
  }

  @GetMapping(value = "/detail-5/{id}")
  public String detailFifthPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "/user/MemberFifthDetails";
  }

  @GetMapping(value = "/detail-6/{id}")
  public String detailSixthPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "/user/MemberSixthDetails";
  }

  @GetMapping(value = "/detail-7/{id}")
  public String detailSeventhPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "/user/MemberSeventhDetails";
  }

  @GetMapping(value = "/detail-8/{id}")
  public String detailEighthPage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "/user/MemberEighthDetails";
  }

  @GetMapping(value = "/")
  public String homePage() {
    return "/user/UserHome";
  }

  @GetMapping(value = "/notice")
  public String noticePage() {
    return "/user/UserNotice";
  }

  @GetMapping(value = "/notice/detail/{id}")
  public String noticeDetailPage(@PathVariable("id") String noticeId, Model model) {
    model.addAttribute("noticeId", noticeId);
    return "user/UserNoticeDetails";
  }

  @GetMapping(value = "/recommend")
  public String friendRecommendPage() {
    return "user/FriendRecommend";
  }

  @GetMapping(value = "/friend/detail/{id}/{fitness}")
  public String friendDetailPage(@PathVariable("id") String friendId, @PathVariable("fitness") String fitness, Model model) {
    model.addAttribute("friendId", friendId);
    model.addAttribute("fitness", fitness);
    return "user/FriendDetails";
  }

  @GetMapping(value = "/request/list")
  public String requestListPage() {
    return "/user/FriendRequestList";
  }

  @GetMapping(value = "/friend/detail/{id}")
  public String friendDetailPage(@PathVariable("id") String friendId, Model model) {
    model.addAttribute("friendId", friendId);
    return "user/FriendRequestDetails";
  }

  @GetMapping(value = "/admin")
  public String adminPage() {
    return "admin/AdminHome";
  }

  @GetMapping(value = "/admin/notice")
  public String adminNoticePage() {
    return "admin/AdminNotice";
  }

  @GetMapping(value = "/admin/notice/detail/{id}")
  public String adminNoticePage(@PathVariable("id") String noticeId, Model model) {
    model.addAttribute("noticeId", noticeId);
    return "admin/AdminNoticeDetails";
  }

  @GetMapping(value = "/admin/notice/update/{id}")
  public String noticeUpdatePage(@PathVariable("id") String noticeId, Model model) {
    model.addAttribute("noticeId", noticeId);
    return "admin/AdminNoticeModify";
  }

  @GetMapping(value = "/admin/notice/create")
  public String noticeCreatePage() {
    return "admin/AdminNoticeRegister";
  }

  @GetMapping(value = "/chat/list")
  public String chatListPage() {
    return "user/ChatList";
  }

  @GetMapping(value = "/member/detail/update/{id}")
  public String memberDetailUpdatePage(@PathVariable("id") String memberId, Model model) {
    model.addAttribute("memberId", memberId);
    return "user/JoinSecond";
  }

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
