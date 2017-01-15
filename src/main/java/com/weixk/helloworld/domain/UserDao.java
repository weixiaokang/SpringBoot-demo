package com.weixk.helloworld.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * Created by weixk on 16/11/30.
 */
public interface UserDao extends CrudRepository<User, Long>{

    List<User> findAll();
    User findUserByEmail(String email);
    @Query("select u from User u where u.nickname=?")
    List<User> findUserByNickname(String name);
    @Query("select u from User u where u.nickname=:nickname")
    List<User> findUserByN(String nickname);
    @Query(value = "select * from user as u where u.email=?", nativeQuery = true)
    List<User> findUserByE(String email);
    @Query(value = "select * from user where nickname=?1 and email=?2", nativeQuery = true)
    List<User> findUserByNE(String nickname, String email);

    List<User> findUserByNicknameAndEmail(String nickname, String email);
    List<User> findDistinctUserByNicknameAndEmailOrderByIdDesc(String nickname, String email);
    List<User> findUserByNickname(String nickname, Sort sort);
    List<User> findUserByNickname(String nickname, Pageable pageable);
    List<User> findUserByIdLessThan(long id, Pageable pageable);
}
