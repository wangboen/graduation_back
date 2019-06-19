package nju.edu.graduation.controller;

import nju.edu.graduation.entity.Authorization;
import nju.edu.graduation.entity.Order;
import nju.edu.graduation.entity.Patent;
import nju.edu.graduation.entity.User;
import nju.edu.graduation.service.AuthorizationService;
import nju.edu.graduation.service.OrderService;
import nju.edu.graduation.service.PatentService;
import nju.edu.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class IndexController {

    private final PatentService patentService;
    private final UserService userService;
    private final OrderService orderService;
    private final AuthorizationService authorizationService;

    @Autowired
    public IndexController(PatentService patentService,UserService userService,OrderService orderService,AuthorizationService authorizationService) {
        this.patentService = patentService;
        this.userService = userService;
        this.orderService = orderService;
        this.authorizationService = authorizationService;
    }

    @GetMapping(value = "/test")
    public String test() {
        return "测试前后端交互";
    }

    @PostMapping(value = "/info")
    public Map info(@RequestBody Map request,HttpSession httpSession){
        String UID = request.get("UID").toString();
        Patent patent = patentService.info(UID);
        if (patent==null){
            return null;
        }else {
            httpSession.setAttribute("patent_id",patent.getId());
            int owner_id = patent.getOwner();
            User owner = userService.GetById(owner_id);
            Map<String,Object> result = new HashMap<>();
            result.put("id",patent.getId());
            result.put("name",patent.getName());
            result.put("UID",patent.getUID());
            result.put("owner",owner.getName());
            result.put("inventor",patent.getInventor());
            result.put("content",patent.getContent());
            result.put("upload",patent.getUpload());
            return result;
        }
    }

    @PostMapping(value = "/transactionlist")
    public List<Map> transactionlist(@RequestBody Map request, HttpSession httpSession){
        String UID = request.get("UID").toString();
        Patent patent = patentService.info(UID);
        if (patent==null){
            return null;
        }else {
            int patent_id = patent.getId();
            List<Order> orders = orderService.list(patent_id);
            List<Map> maps = new ArrayList<>();
            for (Order order : orders) {
                int from_id = order.getFrom();
                User user = userService.GetById(from_id);
                String from = user.getName();
                Map<String, Object> map = new HashMap<>();
                map.put("id",order.getId());
                map.put("from",from);
                map.put("date",order.getDate());
                map.put("amount",order.getAmount());
                maps.add(map);
            }
            return maps;
        }
    }

    @PostMapping(value = "/authorizationlist")
    public List<Map> authorizationlist(@RequestBody Map request,HttpSession httpSession){
        String UID = request.get("UID").toString();
        Patent patent = patentService.info(UID);
        if (patent==null){
            return null;
        }else {
            int patent_id = patent.getId();
            List<Authorization> authorizations = authorizationService.list(patent_id);
            List<Map> maps = new ArrayList<>();
            for (Authorization authorization : authorizations) {
                int from_id = authorization.getFrom();
                User user = userService.GetById(from_id);
                String from = user.getName();
                Map<String, Object> map = new HashMap<>();
                map.put("id",authorization.getId());
                map.put("from",from);
                map.put("date",authorization.getDate());
                map.put("begin",authorization.getBegin());
                map.put("end",authorization.getEnd());
                map.put("amount",authorization.getAmount());
                maps.add(map);
            }
            return maps;
        }
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody Map request, HttpSession httpSession){
        String name = request.get("name").toString();
        String password = request.get("password").toString();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user = userService.Login(user);
        if (user==null){
            return "failed";
        }else {
            httpSession.setAttribute("user_id",user.getId());
            System.out.println(httpSession.getAttribute("user_id"));
            return "success";
        }
    }

    @PostMapping(value = "/transaction")
    public String transaction(@RequestBody Map request,HttpSession httpSession){
        int patent_id = Integer.parseInt(httpSession.getAttribute("patent_id").toString());
        Patent patent = patentService.GetById(patent_id);
        int to_id = patent.getOwner();
        int from_id = Integer.parseInt(httpSession.getAttribute("user_id").toString());
        Date date = new Date();
        float amount = Float.parseFloat(request.get("amount").toString());
        Map<String,Object> map = new HashMap<>();
        map.put("id",from_id);
        map.put("amount",amount);
        userService.deposit(map);
        Order order = new Order();
        order.setPatent(patent_id);
        order.setFrom(from_id);
        order.setTo(to_id);
        order.setDate(date);
        order.setAmount(amount);
        orderService.AddOrder(order);
        return "success";
    }

    @PostMapping(value = "/authorization")
    public String authorization(@RequestBody Map request, HttpSession httpSession) throws ParseException {
        int patent_id = Integer.parseInt(httpSession.getAttribute("patent_id").toString());
        Patent patent = patentService.GetById(patent_id);
        int to_id = patent.getOwner();
        int from_id = Integer.parseInt(httpSession.getAttribute("user_id").toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = simpleDateFormat.parse(request.get("begin").toString());
        Date end = simpleDateFormat.parse(request.get("end").toString());
        float amount = Float.parseFloat(request.get("amount").toString());
        Date date = new Date();
        Map<String,Object> map = new HashMap<>();
        map.put("id",from_id);
        map.put("amount",amount);
        userService.deposit(map);
        Authorization authorization = new Authorization();
        authorization.setPatent(patent_id);
        authorization.setFrom(from_id);
        authorization.setTo(to_id);
        authorization.setBegin(begin);
        authorization.setEnd(end);
        authorization.setDate(date);
        authorization.setAmount(amount);
        authorizationService.AddAuthorization(authorization);
        return "success";
    }

    @GetMapping(value = "/getTransactionList")
    public List getTransactionList(HttpSession httpSession) {
        int to_id = Integer.parseInt(httpSession.getAttribute("user_id").toString());
        List<Order> orders = orderService.getList(to_id);
        List<Map> maps = new ArrayList<>();
        for (Order order : orders) {
            int from_id = order.getFrom();
            int patent_id = order.getPatent();
            User user = userService.GetById(from_id);
            Patent patent = patentService.GetById(patent_id);
            String from = user.getName();
            String name = patent.getName();
            Map<String, Object> map = new HashMap<>();
            map.put("id", order.getId());
            map.put("name",name);
            map.put("from",from);
            map.put("date",order.getDate());
            map.put("amount",order.getAmount());
            maps.add(map);
        }
        return maps;
    }

    @PostMapping(value = "/confirmTransaction")
    public String confirmTransaction(@RequestBody Map request){
        int order_id = Integer.parseInt(request.get("order_id").toString());
        String patent_name = request.get("patent_name").toString();
        Patent patent = patentService.GetByName(patent_name);
        int patent_id = patent.getId();
        List<Order> orders = orderService.GetByPatent(patent_id);
        for (Order order : orders) {
            int id = order.getId();
            float amount = order.getAmount();
            Map<String,Object> map = new HashMap<>();
            if (id==order_id){
                int to_id = order.getTo();
                map.put("id",to_id);
                map.put("amount",amount);
            }else {
                int from_id = order.getFrom();
                map.put("id",from_id);
                map.put("amount",amount);
            }
            userService.get(map);
        }
        orderService.confirm(order_id,patent_id);
        Order order = orderService.GetById(order_id);
        int from_id = order.getFrom();
        authorizationService.change(patent_id,from_id);
        return "success";
    }

    @PostMapping(value = "/cancelTransaction")
    public String cancelTransaction(@RequestBody Map request){
        int order_id = Integer.parseInt(request.get("order_id").toString());
        orderService.cancel(order_id);
        Order order = orderService.GetById(order_id);
        int from_id = order.getFrom();
        float amount = order.getAmount();
        Map<String,Object> map = new HashMap<>();
        map.put("id",from_id);
        map.put("amount",amount);
        userService.get(map);
        return "success";
    }

    @GetMapping(value = "/getAuthorizationList")
    public List getAuthorizationList(HttpSession httpSession) {
        int to_id = Integer.parseInt(httpSession.getAttribute("user_id").toString());
        List<Authorization> authorizations = authorizationService.getList(to_id);
        List<Map> maps = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Authorization authorization : authorizations) {
            int from_id = authorization.getFrom();
            int patent_id = authorization.getPatent();
            User user = userService.GetById(from_id);
            Patent patent = patentService.GetById(patent_id);
            String from = user.getName();
            String name = patent.getName();
            Map<String, Object> map = new HashMap<>();
            map.put("id", authorization.getId());
            map.put("name",name);
            map.put("from",from);
            map.put("date",authorization.getDate());
            map.put("begin",simpleDateFormat.format(authorization.getBegin()));
            map.put("end",simpleDateFormat.format(authorization.getEnd()));
            map.put("amount",authorization.getAmount());
            maps.add(map);
        }
        return maps;
    }

    @PostMapping(value = "/confirmAuthorization")
    public String confirmAuthorization(@RequestBody Map request){
        int authorization_id = Integer.parseInt(request.get("authorization_id").toString());
        authorizationService.confirm(authorization_id);
        Authorization authorization = authorizationService.GetById(authorization_id);
        int to_id = authorization.getTo();
        float amount = authorization.getAmount();
        Map<String,Object> map = new HashMap<>();
        map.put("id",to_id);
        map.put("amount",amount);
        userService.get(map);
        return "success";
    }

    @PostMapping(value = "/cancelAuthorization")
    public String cancelAuthorization(@RequestBody Map request){
        int authorization_id = Integer.parseInt(request.get("authorization_id").toString());
        authorizationService.cancel(authorization_id);
        Authorization authorization = authorizationService.GetById(authorization_id);
        int from_id = authorization.getFrom();
        float amount = authorization.getAmount();
        Map<String,Object> map = new HashMap<>();
        map.put("id",from_id);
        map.put("amount",amount);
        userService.get(map);
        return "success";
    }

    @GetMapping(value = "/getOther")
    public List<User> getOther(HttpSession httpSession){
        int user_id = Integer.parseInt(httpSession.getAttribute("user_id").toString());
        List<User> users = userService.getOther(user_id);
        return  users;
    }
}
