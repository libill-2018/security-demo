package com.daqsoft.service.impl;

import com.daqsoft.dao.OperateRepository;
import com.daqsoft.entity.Operate;
import com.daqsoft.service.OperateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 简单描述一下功能
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-11-17 10:26
 * @since JDK 1.8
 */
@Service
public class OperateServiceImpl implements OperateService {

    private final OperateRepository operateRepository;

    public OperateServiceImpl(OperateRepository operateRepository) {
        this.operateRepository = operateRepository;
    }

    @Override
    public Operate findByUrl(String url) {
        return operateRepository.findByUrl(url);
    }

    @Override
    public List<Operate> findAll() {
        return operateRepository.findAll();
    }
}
