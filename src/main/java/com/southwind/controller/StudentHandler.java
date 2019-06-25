package com.southwind.controller;

import com.southwind.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class StudentHandler {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/set")
    public void set(@RequestBody Student student){
        redisTemplate.opsForValue().set("stu",student);
    }

    @GetMapping("/get/{key}")
    public Student get(@PathVariable("key") String key){
        Student student = (Student) redisTemplate.opsForValue().get(key);
        return student;
    }

    @DeleteMapping("/delete/{key}")
    public boolean delete(@PathVariable("key") String key){
        redisTemplate.delete(key);
        return redisTemplate.hasKey(key);
    }

    /**
     * 字符串
     */
    @GetMapping("/string")
    public void stringTest(){
        redisTemplate.opsForValue().set("str","Hello World");
        System.out.println(redisTemplate.opsForValue().get("str"));
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public void listTest(){
        ListOperations<String,String> list = redisTemplate.opsForList();
        list.leftPush("list","Hello");
        list.leftPush("list","World");
        list.leftPush("list","Java");
        List<String> getList = list.range("list",0,2);
        for (String val:getList){
            System.out.println(val);
        }
    }

    /**
     * 集合
     */
    @GetMapping("/set")
    public void setTest(){
        SetOperations<String,String> set = redisTemplate.opsForSet();
        set.add("set","Hello");
        set.add("set","Hello");
        set.add("set","World");
        set.add("set","World");
        set.add("set","Java");
        set.add("set","Java");
        Set<String> getSet = set.members("set");
        for (String val:getSet){
            System.out.println(val);
        }
    }


    /**
     * 有序集合
     */
    @GetMapping("/zset")
    public void zsetTest(){
        ZSetOperations<String,String> zset = redisTemplate.opsForZSet();
        zset.add("zset","Hello",1);
        zset.add("zset","World",2);
        zset.add("zset","Java",3);
        Set<String> set = zset.range("zset",0,2);
        for(String val:set){
            System.out.println(val);
        }
    }

    /**
     * 哈希
     */
    @GetMapping("/hash")
    public void hashTest(){
        HashOperations<String,String,String> hash = redisTemplate.opsForHash();
        hash.put("key","hashkey","Hello");
        System.out.println(hash.get("key","hashkey"));
    }


}
