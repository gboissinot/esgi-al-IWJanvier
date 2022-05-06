package com.esgi.alweb.tp1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class CacheAsideDemo {

    public static void main(String[] args) {
        final String result = getUser(123);
        System.out.println(result);
    }

    public static String getUser(int id) {
        Jedis jedis = new Jedis();

        var userKey = "users:" + id;
        final String result = jedis.get(userKey);
        if (result != null) {
            return result;
        }

        final User retrievedUser = users.get(id);
        Gson gson = new GsonBuilder().create();
        String serialized_user = gson.toJson(retrievedUser);
        jedis.set(userKey, serialized_user);
        jedis.expire(userKey, 30);
        return serialized_user;
    }

    public static Map<Integer, User> users = Map.of(
            123, new User(123, "Gregory"),
            124, new User(124, "Janie"),
            125, new User(125, "Willy")
    );
}
