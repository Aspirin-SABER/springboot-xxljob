package com.taiji.sample.rpcsample;

import cn.com.taiji.sms.model.comm.protocol.SendSmsRequest;
import com.alibaba.fastjson.JSONObject;
import com.taiji.sample.rpcsample.dao.MonitorNcpDayRepo;
import com.taiji.sample.rpcsample.entity.MonitorNcpDay;
import com.taiji.sample.rpcsample.manage.SmsBinCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public  String helloGet(){
        int i  =1/0;
        return "Hello,Saber!!";
    }
    @Autowired
    private MonitorNcpDayRepo monitorNcpDayRepo;

    @GetMapping("/test")
    public  String testGet(){
        MonitorNcpDay monitorNcpDay = monitorNcpDayRepo.findById("95bb47389a8545ca83f4275ee2b76afd").orElse(null);

        return JSONObject.toJSONString(monitorNcpDay);
    }

    @Autowired
    private SmsBinCommService smsBinCommService;
    @GetMapping("/send")
    public void send(){
        sendMessage("347","17610826639","Hello,SABER");
    }


    private void sendMessage(String smsModel, String smsPhones,String info) {
        String[] phones = null;
        phones = smsPhones.split(",");
        if (info.length() != 0) {
            for (String o : phones) {
                SendSmsRequest request = new SendSmsRequest();
                request.setInfo(info);
                request.setModelId(smsModel);
                request.setMobile(o);
                try {
                    smsBinCommService.sendSms(request);

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }

    }
