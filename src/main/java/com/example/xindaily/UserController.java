package com.example.xindaily;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/getUsers",method = RequestMethod.POST)
    @ResponseBody
    public String getUsers(@RequestParam("uname") String uname){

        String sql = "select upasswd from user where uname = "+uname;
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        String result =list.get(0).get("uname").toString();
        return result;

//        for (Map<String, Object> stringObjectMap : list) {
////            if (stringObjectMap.containsValue(name)) {
////                return "success";
////            }
//        }
//        return "login failed";
    }
}
