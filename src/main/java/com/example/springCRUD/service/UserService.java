package com.example.springCRUD.service;

import com.example.springCRUD.pojo.User;
import com.example.springCRUD.repository.UserDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User addUser(User user) {
        User saveduser = userDao.save(user);
        return saveduser;

    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userDao.findById(id);
    }

    public User updateUser(User user) {
        Optional<User> saveduser = userDao.findById(user.getId());
        if (saveduser != null) {
            User savedUser = saveduser.get();
            savedUser.setName(user.getName());
            savedUser.setContact(user.getContact());
            savedUser.setAddress(user.getAddress());

            return userDao.save(savedUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
/*

    // To get all the names field data from the json data.
    public List getAllDetails() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.getForEntity("https://api.restful-api.dev/objects", List.class);


        ArrayList listOfName = new ArrayList();
        List listofData = (List) responseEntity.getBody();
        for (Object object : listofData) {
            Map map = (Map) object;
            String name = map.get("name").toString();
            listOfName.add(name);

        }
        return listOfName;
    }
*/

/*
   //getForObject() directly fetches and returns the body, avoiding the need for ResponseEntity.
    public List getAllDetails() {
        RestTemplate restTemplate = new RestTemplate();
      List list= restTemplate.getForObject("https://api.restful-api.dev/objects", List.class);
        return list;
    }*/

    //exchange
    public ResponseEntity<List> getAllDetails() {
        RestTemplate restTemplate = new RestTemplate();

       return   restTemplate.exchange("https://api.restful-api.dev/objects",HttpMethod.GET,null ,List.class);
    }

    public Map  addDetails(Map details) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
     HttpEntity requestEntity = new HttpEntity(details, headers);
        Map data = restTemplate.postForObject("https://api.restful-api.dev/objects", requestEntity, Map.class);
        return data;
    }





   /* public List<User> findAll() {
        ArrayList<User> users = new ArrayList<User>();
        User user1 = new User("1", "shah", "56565", "dewas");
        User user2 = new User("2", "shah", "56565", "dewas");
        User user3 = new User("3", "shah", "56565", "dewas");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }
    public Map getUser(String id,String name) {
        List<User> allUsers = findAll();
        for (User user : allUsers) {
            if (user.getId().equals(id) && user.getName().equals(name)) {
                return Map.of("message",user);
            }else{
                return Map.of("message","User not found");
            }
        }
        return Map.of("message","User not found");
    }*/
}
