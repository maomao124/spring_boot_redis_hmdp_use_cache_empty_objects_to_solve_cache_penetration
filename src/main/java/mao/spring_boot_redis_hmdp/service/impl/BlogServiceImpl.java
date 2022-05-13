package mao.spring_boot_redis_hmdp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mao.spring_boot_redis_hmdp.entity.Blog;
import mao.spring_boot_redis_hmdp.mapper.BlogMapper;
import mao.spring_boot_redis_hmdp.service.IBlogService;
import org.springframework.stereotype.Service;


@Service("blogService")
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService
{

}
