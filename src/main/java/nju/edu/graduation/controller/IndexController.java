package nju.edu.graduation.controller;

import nju.edu.graduation.entity.Patent;
import nju.edu.graduation.entity.User;
import nju.edu.graduation.service.PatentService;
import nju.edu.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class IndexController {

    private final PatentService patentService;
    private final UserService userService;

    @Autowired
    public IndexController(PatentService patentService,UserService userService) {
        this.patentService = patentService;
        this.userService = userService;
    }

    @GetMapping(value = "/test")
    public String test() {
        return "测试前后端交互";
    }

    @PostMapping(value = "/info")
    public Map info(@RequestBody Map request){
        String UID = request.get("UID").toString();
        Patent patent = patentService.info(UID);
        int owner_id = patent.getOwner();
        User owner = userService.GetById(owner_id);
        Map<String,Object> result = new HashMap<>();
        result.put("id",patent.getId());
        result.put("name",patent.getName());
        result.put("UID",patent.getUID());
        result.put("owner",owner.getName());
        result.put("inventor",patent.getInventor());
        result.put("content",patent.getContent());
        return result;
    }
}
