package mao.spring_boot_redis_hmdp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mao.spring_boot_redis_hmdp.entity.Follow;
import mao.spring_boot_redis_hmdp.mapper.FollowMapper;
import mao.spring_boot_redis_hmdp.service.IFollowService;
import org.springframework.stereotype.Service;


@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService
{

}
