package com.example.firstdemo.dao.mybatis;

import com.example.firstdemo.dao.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface AccountMapper {
    @Select("select * from account where username = #{username}")
    Account findByUsername(@Param("username") String username);

}
