package com.example.firstdemo.dao.MyBatis;

import com.example.firstdemo.dao.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface AccountMapper {
    @Select("select * from account where user = #{user}")
    Account findByUsername(@Param("user") String user);

    @Select("select * from account where user = #{user} and password = #{password} ")
    Account findByConditions(@Param("user") String user, @Param("password") String password);
}
