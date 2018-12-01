package com.cc.bos.web.action;

import com.cc.bos.entity.Staff;
import com.cc.bos.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StaffAction extends BaseAction<Staff> {
    @Autowired
    private StaffService staffService;

    public String add(){
        staffService.add(model);
        return LIST;
    }

}
