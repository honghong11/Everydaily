package com.example.xindaily;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/getUsers",method = RequestMethod.GET)
    @ResponseBody
    public int getUsers(String name,String passwd){

        String sql = "select * from user";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);

        for (Map<String, Object> stringObjectMap : list) {
            if (stringObjectMap.containsValue(name)) {
                return 200;
            }
        }
        return -1;
    }
}
