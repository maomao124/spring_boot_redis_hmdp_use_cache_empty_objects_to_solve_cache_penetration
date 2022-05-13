package mao.spring_boot_redis_hmdp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mao.spring_boot_redis_hmdp.entity.VoucherOrder;
import mao.spring_boot_redis_hmdp.mapper.VoucherOrderMapper;
import mao.spring_boot_redis_hmdp.service.IVoucherOrderService;
import org.springframework.stereotype.Service;


@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService
{

}
