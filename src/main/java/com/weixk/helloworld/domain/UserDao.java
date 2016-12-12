package com.weixk.helloworld.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * Created by weixk on 16/11/30.
 */
public interface UserDao extends CrudRepository<User, Long>{

    User findUserByEmail(String email);

    List<User> findUserByNickname(String name);
    @Query("select u from User u where u.nickname=:nickname")
    List<User> findUserByN(String nickname);
    @Query(value = "select * from user where email=?", nativeQuery = true)
    List<User> findUserByE(String email);
    @Query(value = "select * from user where nickname=?1 and email=?2", nativeQuery = true)
    List<User> findUserByNE(String nickname, String email);
}
