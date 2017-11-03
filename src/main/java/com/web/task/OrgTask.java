package com.web.task;

import com.web.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component("orgTask")
public class OrgTask {

    @Autowired
    private OrgService orgService;

    /**
     * 每天凌晨1点更新
     */
    @Scheduled(cron = "0 0 01 * * ?")
    public void updateorg() {
        System.out.println("task---------updateorg-----start");
        System.out.println("----updateDept----");
        orgService.updateDept();
        System.out.println("----updateEmp----");
        orgService.updateEmp();
        System.out.println("task---------updateorg-----end");
    }


}
