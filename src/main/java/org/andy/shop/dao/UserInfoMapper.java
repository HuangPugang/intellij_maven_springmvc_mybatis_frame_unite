package org.andy.shop.dao;

import java.util.List;

import org.andy.shop.model.UserInfo;
import org.springframework.stereotype.Repository;

@Repository("userInfoMapper")
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    List<UserInfo> selectAll();
}