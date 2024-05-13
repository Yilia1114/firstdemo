package com.example.firstdemo.dao.Repository;

import com.example.firstdemo.dao.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {
    @Select("select * from account where user = #{user} and password = #{password} ")
    Account findByConditions(@Param("user") String user, @Param("password") String password);
}
