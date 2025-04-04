package kyungmin.katsee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
