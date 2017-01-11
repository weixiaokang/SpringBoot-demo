package com.weixk.helloworld.web;

import com.weixk.helloworld.domain.Opt;
import com.weixk.helloworld.domain.OptionDao;
import com.weixk.helloworld.domain.User;
import com.weixk.helloworld.domain.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author weixk
 * @version Created time 16/12/27. Last-modified time 16/12/27.
 */
@RestController
@RequestMapping(value = "/option")
public class OptionController {

    @Autowired
    private OptionDao optionDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping(value = "/add")
    public Opt add() {
        Opt opt = new Opt(new Date(), "test");
        opt = optionDao.save(opt);
        return opt;
    }

    @GetMapping(value = "/find/{id}")
    public Opt find(@PathVariable(value = "id") long id) {
        return optionDao.findOne(id);
    }

    @GetMapping(value = "/find/jdbc")
    public void find() {
        jdbcTemplate.query("select * from opt", new Object[]{}, (rs, rowNum) -> new Opt(rs.getLong("id"), rs.getTimestamp("createtime"), rs.getString("msg")))
        .forEach(opt -> System.out.println(opt.toString()));
    }

    @GetMapping(value = "/test")
    public void test() {
        List<Object[]> list = entityManager.createNativeQuery("select user.*, opt.createtime as createtime from user, opt where user.id=1 and opt.id=1", "user_opt").getResultList();
        list.forEach(n->{
            for (Object o : n) {
//                if (o instanceof User)
//                    System.out.println(((User)o).toString());
//                System.out.println(o);
                System.out.println(o.toString());
            }
        });
    }
}
