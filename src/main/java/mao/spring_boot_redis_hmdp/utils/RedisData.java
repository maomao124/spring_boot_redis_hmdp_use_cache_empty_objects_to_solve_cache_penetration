package mao.spring_boot_redis_hmdp.utils;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RedisData
{
    private LocalDateTime expireTime;
    private Object data;
}
