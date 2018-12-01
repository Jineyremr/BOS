package com.cc.bos.service.impl;

import com.cc.bos.dao.StaffDao;
import com.cc.bos.entity.Staff;
import com.cc.bos.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;

    @Override
    public void add(Staff model) {
        staffDao.save(model);
    }
}
