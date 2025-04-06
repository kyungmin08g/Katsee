package kyungmin.katsee.controller;

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

  @GetMapping(value = "/join-2")
  public String secondJoinPage() {
    return "/user/JoinSecond";
  }

  @GetMapping(value = "/join-3")
  public String thirdJoinPage() {
    return "/user/JoinThird";
  }

  @GetMapping(value = "/my")
  public String myPage() {
    return "/user/MyPage";
  }

  @GetMapping(value = "/detail-1")
  public String detailFirstPage() {
    return "/user/MemberFirstDetails";
  }

  @GetMapping(value = "/detail-2")
  public String detailSecondPage() {
    return "/user/MemberSecondDetails";
  }

  @GetMapping(value = "/detail-3")
  public String detailThirdPage() {
    return "/user/MemberThirdDetails";
  }

  @GetMapping(value = "/detail-4")
  public String detailFourthPage() {
    return "/user/MemberFourthDetails";
  }

  @GetMapping(value = "/detail-5")
  public String detailFifthPage() {
    return "/user/MemberFifthDetails";
  }

  @GetMapping(value = "/detail-6")
  public String detailSixthPage() {
    return "/user/MemberSixthDetails";
  }

  @GetMapping(value = "/detail-7")
  public String detailSeventhPage() {
    return "/user/MemberSeventhDetails";
  }

  @GetMapping(value = "/detail-8")
  public String detailEighthPage() {
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
}
