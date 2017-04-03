package com.weixk.helloworld.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author weixk
 * @version Created time 17/4/3. Last-modified time 17/4/3.
 */
public interface UserMongoDao extends MongoRepository<User, Long>{

    List<User> findUserByNickname(String name);
}
