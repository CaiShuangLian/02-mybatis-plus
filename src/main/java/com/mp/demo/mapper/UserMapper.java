package com.mp.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.demo.data.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:CaiShuangLian
 * @FileName:
 * @Date:Created in  2022/8/1 12:19
 * @Version:
 * @Description:TODO
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
