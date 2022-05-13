package mao.spring_boot_redis_hmdp.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mao.spring_boot_redis_hmdp.dto.Result;
import mao.spring_boot_redis_hmdp.entity.Shop;
import mao.spring_boot_redis_hmdp.mapper.ShopMapper;
import mao.spring_boot_redis_hmdp.service.IShopService;
import mao.spring_boot_redis_hmdp.utils.RedisConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService
{

    @Resource
    StringRedisTemplate stringRedisTemplate;


    @Override
    public Result queryShopById(Long id)
    {
        //获取redisKey
        String redisKey = RedisConstants.CACHE_SHOP_KEY + id;
        //从redis中查询商户信息，根据id
        String shopJson = stringRedisTemplate.opsForValue().get(redisKey);
        //判断取出的数据是否为空
        if (StrUtil.isNotBlank(shopJson))
        {
            //不是空，redis里有，返回
            return Result.ok(JSONUtil.toBean(shopJson, Shop.class));
        }
        //是空串，不是null，返回
        if (shopJson != null)
        {
            return Result.fail("店铺信息不存在");
        }
        //null，查数据库
        Shop shop = this.getById(id);
        //判断数据库里的信息是否为空
        if (shop == null)
        {
            //空，将空值写入redis，返回错误
            stringRedisTemplate.opsForValue().set(redisKey, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
            return Result.fail("店铺信息不存在");
        }
        //存在，回写到redis里
        stringRedisTemplate.opsForValue().set(redisKey, JSONUtil.toJsonStr(shop), RedisConstants.CACHE_SHOP_TTL, TimeUnit.MINUTES);
        //返回数据
        return Result.ok(shop);
    }

    @Override
    public Result updateShop(Shop shop)
    {
        //获得id
        Long id = shop.getId();
        //判断是否为空
        if (id == null)
        {
            return Result.fail("商户id不能为空");
        }
        //不为空
        //先更新数据库
        boolean b = this.updateById(shop);
        //更新失败，返回
        if (!b)
        {
            return Result.fail("更新失败");
        }
        //更新没有失败
        //删除redis里的数据，下一次查询时自动添加进redis
        //redisKey
        String redisKey = RedisConstants.CACHE_SHOP_KEY + id;
        stringRedisTemplate.delete(redisKey);
        //返回响应
        return Result.ok();
    }
}
