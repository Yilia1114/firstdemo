package com.example.firstdemo.dao.MyBatis;

import com.example.firstdemo.dao.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface AccountMapper {
    @Select("select * from account where username = #{username}")
    Account findByUsername(@Param("username") String username);

    @Select("select * from account where username = #{username} and password = #{password} ")
    Account findByConditions(@Param("username") String username, @Param("password") String password);
}
