package com.weixk.helloworld.dao;

import com.weixk.helloworld.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * Created by weixk on 16/11/30.
 */
public interface UserDao extends CrudRepository<User, Long>{

    List<User> findUserByName(String name);

    @Query("select u from User u where u.name=:name")
    List<User> findUserByN(String name);
    @Query(value = "select * from user where email=?", nativeQuery = true)
    List<User> findUserByE(String email);
    @Query(value = "select * from user where name=?1 and email=?2", nativeQuery = true)
    List<User> findUserByNE(String name, String email);
}
