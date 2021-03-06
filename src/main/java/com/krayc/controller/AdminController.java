package com.krayc.controller;

import com.krayc.model.*;
import com.krayc.service.*;
import com.krayc.vo.*;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private VenueService venueService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private EventService eventService;

    @Autowired
    private LevelService levelService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "adminHome", method = RequestMethod.GET)
    public String adminHome() {
        return "admin/adminDetail";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView loginMember() {
        return new ModelAndView("admin/loginAdmin");
    }

    @RequestMapping(value = "loginPost", method = RequestMethod.POST)
    public ModelAndView loginMemberPost(@ModelAttribute("admin") AdminEntity adminEntity, HttpServletRequest request, ModelMap modelMap) {
        switch (adminService.login(adminEntity)) {
            case LOGIN_SUCCESS:
                HttpSession session = request.getSession(false);
                session.setAttribute("admin", adminEntity);
                modelMap.addAttribute("admin", adminEntity);
                return new ModelAndView("redirect:/admin/adminHome");
            default:
                MessageVO messageVO = new MessageVO(false, "用户名或密码错误");
                return this.handleMessage(messageVO, "admin/loginAdmin");
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logoutVenue(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("admin ", null);
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "venues/tobepassed", method = RequestMethod.GET)
    public String passVenues(ModelMap modelMap) {
        modelMap.addAttribute("venueList", venueService.findToBePassedVenues());
        return "admin/adminVenuePass";
    }

    @RequestMapping(value = "venues/good/{vid}", method = RequestMethod.GET)
    public String goodPassVenue(@PathVariable("vid") Integer vid) {
        venueService.passVenueOrNot(vid, true);
        return "redirect:/admin/venues/tobepassed";
    }

    @RequestMapping(value = "venues/bad/{vid}", method = RequestMethod.GET)
    public String badPassVenue(@PathVariable("vid") Integer vid) {
        venueService.passVenueOrNot(vid, false);
        return "redirect:/admin/venues/tobepassed";
    }

    @RequestMapping(value = "events", method = RequestMethod.GET)
    public String events(ModelMap modelMap) {
        ArrayList<EventVO> eventVOS = new ArrayList<EventVO>();
        for (EventEntity eventEntity : eventService.findAllEvents()) {
            eventVOS.add(new EventVO(eventEntity));
        }
        modelMap.addAttribute("eventList", eventVOS);
        return "admin/adminEvents";
    }

    @RequestMapping(value = "events/confirm/{eid}", method = RequestMethod.GET)
    public String confirmEvent(@PathVariable("eid") Integer eid) {
        EventEntity eventEntity = eventService.findByEid(eid);
        adminService.confirmEvent(eventEntity);
        return "redirect:/admin/events";
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String books(ModelMap modelMap) {
        ArrayList<AdminBookVO> adminBookVOS = new ArrayList<AdminBookVO>();
        for (AdminBookEntity entity : adminService.getAllAdminBooks()) {
            adminBookVOS.add(new AdminBookVO(entity));
        }
        modelMap.addAttribute("books", adminBookVOS);
        return "admin/adminBooks";
    }

    @RequestMapping(value = "members", method = RequestMethod.GET)
    public String members(ModelMap modelMap) {
        ArrayList<MemberInfoVO> memberInfoVOS = new ArrayList<MemberInfoVO>();
        for (MemberEntity memberEntity1 : memberService.findAllMembers()) {
            memberInfoVOS.add(new MemberInfoVO(memberEntity1,
                    levelService.findLevelEntityWithPoint(memberEntity1.getTotalPoint()).getDescription(),
                    memberService.findBalance(memberEntity1.getBankAccount())));
        }
        modelMap.addAttribute("memberList", memberInfoVOS);
        return "admin/adminMembers";
    }

    @RequestMapping(value = "venues", method = RequestMethod.GET)
    public String venues(ModelMap modelMap) {
        ArrayList<VenueInfoVO> venueInfoVOS = new ArrayList<VenueInfoVO>();
        for (VenueEntity venueEntity : venueService.findAllVenues()) {
            venueInfoVOS.add(new VenueInfoVO(venueEntity));
        }
        modelMap.addAttribute("venueList", venueInfoVOS);
        return "admin/adminVenues";
    }

    @RequestMapping(value = "members/orders/{mid}", method = RequestMethod.GET)
    public String memberOrders(@PathVariable("mid") Integer mid, ModelMap modelMap) {
        MemberEntity memberEntity = memberService.findByMid(mid);
        ArrayList<OrderVO> orderVOS = new ArrayList<OrderVO>();
        for (OrderEntity orderEntity : orderService.findOrderByMember(memberEntity)) {
            OrderVO orderVO = new OrderVO(orderEntity);
            orderVO.setTotalAmount(orderService.calculateTotalPriceOfOrder(orderEntity, memberEntity));
            orderVOS.add(orderVO);
        }
        modelMap.addAttribute("orders", orderVOS);
        return "admin/adminMemberOrders";
    }

    @RequestMapping(value = "venues/events/{vid}", method = RequestMethod.GET)
    public String venueEvents(@PathVariable("vid") Integer vid, ModelMap modelMap) {
        VenueEntity venueEntity = venueService.findByVid(vid);
        List<EventVO> eventVOs = new ArrayList<EventVO>();
        for (EventEntity eventEntity : eventService.findByVenueEntity(venueEntity)) {
            eventVOs.add(new EventVO(eventEntity));
        }
        modelMap.addAttribute("events", eventVOs);
        modelMap.addAttribute("venue", venueEntity);
        return "admin/adminVenueEvents";
    }

    @RequestMapping(value = "venues/events/{vid}/orders/{eid}", method = RequestMethod.GET)
    public String venueOrders(@PathVariable("vid") Integer vid, @PathVariable("eid") Integer eid, ModelMap modelMap) {
        VenueEntity venueEntity = venueService.findByVid(vid);
        EventEntity eventEntity = eventService.findByEid(eid);
        List<OrderVO> orderVOS = new ArrayList<OrderVO>();
        for (OrderEntity orderEntity : orderService.findOrderByEvent(eventEntity)) {
            OrderVO orderVO = new OrderVO(orderEntity);
            orderVO.setTotalAmount(orderService.calculateTotalPriceOfOrder(orderEntity, orderEntity.getMemberByMid()));
            orderVOS.add(orderVO);
        }
        modelMap.addAttribute("orders", orderVOS);
        modelMap.addAttribute("venue", venueEntity);
        return "admin/adminVenueOrders";
    }

}
