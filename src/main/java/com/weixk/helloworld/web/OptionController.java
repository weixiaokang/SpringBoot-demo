package com.weixk.helloworld.web;

import com.weixk.helloworld.domain.Option;
import com.weixk.helloworld.domain.OptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author weixk
 * @version Created time 16/12/27. Last-modified time 16/12/27.
 */
@RestController
@RequestMapping(value = "/option")
public class OptionController {

    @Autowired
    private OptionDao optionDao;
    @GetMapping(value = "/add")
    public Option add() {
        Option option = new Option(new Date(), "test");
        option = optionDao.save(option);
        return option;
    }

    @GetMapping(value = "/find/{id}")
    public Option find(@PathVariable(value = "id") long id) {
        return optionDao.findOne(id);
    }
}
