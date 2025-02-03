package com.example.springCRUD.controller;

import com.example.springCRUD.pojo.User;
import com.example.springCRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
   private UserService userService;

// @RestController annotation is the combination of @ResponseBody and @Controller annotation.

   @GetMapping("/home")
    public String print(){
        System.out.println("In UserController");
        return "Hello World";
    }

    @PostMapping("/save")
    public User addUser(@RequestBody User user){
       System.out.println("In UserController");
       User savedUser= userService.addUser(user);
       return savedUser;
    }

    @GetMapping("/findAll")
   public List<User> getAllUsers(){
       System.out.println("In UserController");
       return userService.getAllUsers();
   }

   @GetMapping("/find/{id}")
   public Optional<User> getUserById(@PathVariable Long id){
      return userService.getUserById(id);
   }

   @PostMapping("/delete/{id}")
   public String deleteUserById(@PathVariable Long id){
       userService.deleteUser(id);
       return "User Deleted Successfully";
   }

   @PostMapping("/update")
   public ResponseEntity updateUserById(@RequestBody User user){
       User savedUser=userService.updateUser(user);
       if (savedUser!=null){
           return new ResponseEntity<>(savedUser, HttpStatus.OK);
       }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
   }

    @GetMapping("/getalldetails")
    public ResponseEntity<List> getdetails(){
        return userService.getAllDetails();
    }

   @PostMapping("/saveData")
    public Map addDetails(@RequestBody Map details){
        return userService.addDetails(details);
    }





   /* @GetMapping("/user/{name}")
    public Map getUser(@RequestParam String id, @PathVariable String name){
        System.out.println("In controller User Method");

        Map map=userService.getUser(id, name);


        return map;
    }*/

 /*   @PostMapping("/user/save")
    public String getNewUser(@RequestBody Map map){
        userService.getUser();
        System.out.println("In controller User Method"+map);
        return map.get("name").toString();
    }
*/

   /* @PostMapping("/user/save")
    public ResponseEntity<Map> getNewUser(@RequestBody Map map){
       // userService.getUser();
        System.out.println("In controller User Method");
        return ResponseEntity.ok(Map.of("success", "true"));
    }*/


   /* public String saveuser(@RequestParam String id,@RequestParam String name){
        Map map=new HashMap();
        map.put("id",id);
        map.put("name",name);

        return id;
    }*/


}
