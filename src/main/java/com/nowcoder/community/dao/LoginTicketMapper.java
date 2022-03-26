package com.nowcoder.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nowcoder.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginTicketMapper  {
    @Insert({
            "insert into login_ticket (user_id,ticket,status,expired) ",
            "values (#{userId},#{ticket},#{status},#{expired})"
    })
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    @Select({
            "select * from login_ticket where ticket=#{ticket}"
    })
    LoginTicket selectByTicket(String ticket);

    @Update({
            "update login_ticket set status = #{status} where ticket=#{ticket}"
    })
    //修改状态
    int updateStatus(String ticket,int status);
}
