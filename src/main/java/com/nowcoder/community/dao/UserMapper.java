package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    /**
     * 根据id查单个用户
     */
    User selectById(@Param("id") int id);

    User selectByName(@Param("username") String username);

    User selectByEmail(@Param("email") String email);

    void insertUser(User user);

    void updateStatus(@Param("id") int id,@Param("status")int status);

    @Update({
            "update user set header_url = #{headerUrl} where id = #{id}"
    })
    int updateHeader(int id,String headerUrl);

    @Update({
            "update user set password = #{password} where id = #{id}"
    })
    int updatePassword(int id,String password);
}
