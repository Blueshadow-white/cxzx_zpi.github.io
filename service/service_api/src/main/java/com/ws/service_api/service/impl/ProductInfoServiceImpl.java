package com.ws.service_api.service.impl;

import com.ws.service_api.entity.ProductInfo;
import com.ws.service_api.mapper.ProductInfoMapper;
import com.ws.service_api.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author 王帅
 * @since 2022-09-09
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

}
