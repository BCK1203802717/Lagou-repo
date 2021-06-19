package com.lagou.dao;

import com.lagou.domain.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMapper {

    /*
    对Test表查询所有
     */
    public List<Test> findAllTest();
}
