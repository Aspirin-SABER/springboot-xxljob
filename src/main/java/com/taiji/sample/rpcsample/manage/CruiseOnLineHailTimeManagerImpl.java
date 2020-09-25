package com.taiji.sample.rpcsample.manage;

import cn.com.taiji.sms.model.comm.protocol.SendSmsRequest;
import com.taiji.sample.rpcsample.dao.AbstractRepo;
import com.taiji.sample.rpcsample.dao.CruiseOnLineHailTimeRepo;
import com.taiji.sample.rpcsample.entity.CruiseOnLineHailTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CruiseOnLineHailTimeManagerImpl implements CruiseOnLineHailTimeManager {
    @Autowired
    private AbstractRepo repo;
    @Autowired
    private CruiseOnLineHailTimeRepo hailTimeRepo;

    @Autowired
    private SmsBinCommService smsBinCommService;

    private String smsModel="347";
//    @Value("#{commProperties.smsPhoneHG}")
    private String smsPhones="17610826639";
    @Override
    public void execute(LocalDateTime startTime, LocalDateTime endTime) {
        StringBuffer sb  =new StringBuffer();

        String TimeInterval = startTime.getHour()+"-"+(endTime.getHour()==23?24:endTime.getHour());
        LocalDateTime statTime = startTime.withHour(0);
        System.out.println(TimeInterval);
        Long cruiseData = repo.findByTimeInterval(TimeInterval,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(statTime));
        System.out.print(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(statTime));
        //去年巡游车出车数
        LocalDateTime lastStatTime = statTime.minusYears(1);
        //去年同步
        Long lastCruiseData = null;
        lastCruiseData = hailTimeRepo.findByTimeInterval(TimeInterval,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(lastStatTime));
        if(lastCruiseData==null){
            lastCruiseData = repo.findByTimeInterval(TimeInterval,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(lastStatTime));
        }
        Double lastYearRate = 0D;
        lastYearRate =  new BigDecimal((cruiseData-lastCruiseData)).
                divide(new BigDecimal(lastCruiseData), 4, BigDecimal.ROUND_HALF_UP).doubleValue();
        //昨日巡游车出车数
        LocalDateTime yestStatTime = statTime.minusDays(1);
        Long yestCruiseData = null;

        yestCruiseData = hailTimeRepo.findByTimeInterval(TimeInterval,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(yestStatTime));
        if(yestCruiseData==null){
            yestCruiseData = repo.findByTimeInterval(TimeInterval,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(yestStatTime));
        }
        //昨天同步
        Double yestdayRate = 0D;
        if(yestCruiseData!=0D){
            yestdayRate =  new BigDecimal((cruiseData-yestCruiseData)).
                    divide(new BigDecimal(yestCruiseData), 4, BigDecimal.ROUND_HALF_UP).doubleValue();

        }

//		2月3日7点至9点数据统计：巡游车出车数XX辆，同比去年上升X%，环比昨日上升X%；
//		网约车活跃车辆数XX辆，同比上周上升X%，环比昨日上升X%；
        sb.append(startTime.getMonthValue()
                +"月"+startTime.getDayOfMonth()+"日"+
                startTime.getHour()+"点"+"至"+(endTime.getHour()==23?24:endTime.getHour())+"点数据统计：巡游车出车数"+cruiseData+"辆，同比去年");
        if(lastYearRate.doubleValue()>0){
            sb.append("上升"+new BigDecimal(lastYearRate*100).abs().
                    setScale(2, BigDecimal.ROUND_HALF_UP)+"%，环比昨日");

        }else{
            sb.append("下降"+new BigDecimal(lastYearRate*100).abs().
                    setScale(2, BigDecimal.ROUND_HALF_UP)+"%，环比昨日");
        }
        if(yestdayRate.doubleValue()>0){
            sb.append("上升"+new BigDecimal(yestdayRate*100).abs().
                    setScale(2, BigDecimal.ROUND_HALF_UP)+"%；");

        }else{
            sb.append("下降"+new BigDecimal(yestdayRate*100).abs().
                    setScale(2, BigDecimal.ROUND_HALF_UP)+"%；");
        }

//		2月3日7点至9点数据统计：巡游车出车数XX辆，同比去年上升X%，环比昨日上升X%；
//		网约车活跃车辆数XX辆，同比上周上升X%，环比昨日上升X%；
        //网约车出车数
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        DateTimeFormatter dt = DateTimeFormatter.ofPattern("MM月dd日HH点");
        Long onLineData =repo.findWycVehicle(startTime,endTime,statTime.format(dtf));
        System.out.println("当前网约车出车数onLineData"+onLineData);
        //上周出车数
        LocalDateTime lastWeekStartTime = startTime.minusDays(7);
        LocalDateTime lastWeekEndTime = endTime.minusDays(7);
        LocalDateTime lastWeekStatTime = lastWeekStartTime.withHour(0);
        Long lastWeekOnLineData  = null;
        lastWeekOnLineData = hailTimeRepo.findByTimeIntervalWyc(TimeInterval,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(lastWeekStatTime));
        if(lastWeekOnLineData==null||lastWeekOnLineData==0L){
            lastWeekOnLineData =repo.findWycVehicle(lastWeekStartTime,lastWeekEndTime,lastWeekStartTime.format(dtf));
        }
        System.out.println("上周网约车出车数lastWeekOnLineData"+lastWeekOnLineData);

        //同比上周
        Double lastWeekOnLineRate = 0D;
        lastWeekOnLineRate =  new BigDecimal((onLineData-lastWeekOnLineData)).
                divide(new BigDecimal(lastWeekOnLineData), 4, BigDecimal.ROUND_HALF_UP).doubleValue();
        //昨日出车数
        LocalDateTime yestdayStartTime = startTime.minusDays(1);
        LocalDateTime yestdayEndTime = endTime.minusDays(1);
        LocalDateTime yestdayStatTime = yestdayStartTime.withHour(0);
        Long yestdayOnLineData = null;
        yestdayOnLineData = hailTimeRepo.findByTimeIntervalWyc(TimeInterval,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(yestdayStatTime));
        if(yestdayOnLineData== null||yestdayOnLineData==0L){
            yestdayOnLineData =repo.findWycVehicle(yestdayStartTime,yestdayEndTime,yestdayStartTime.format(dtf));
        }

        System.out.println("昨日网约车出车数yestdayOnLineData"+yestdayOnLineData);
        //同比昨日
        Double yestdayOnLineRate = 0D;
        yestdayOnLineRate =  new BigDecimal((onLineData-yestdayOnLineData)).
                divide(new BigDecimal(yestdayOnLineData), 4, BigDecimal.ROUND_HALF_UP).doubleValue();
//		网约车活跃车辆数XX辆，同比上周上升X%，环比昨日上升X%；
        sb.append("网约车活跃车辆数"+onLineData+"辆，同比上周");
        if(lastWeekOnLineRate.doubleValue()>0){
            sb.append("上升"+new BigDecimal(lastWeekOnLineRate*100).abs().
                    setScale(2, BigDecimal.ROUND_HALF_UP)+"%，环比昨日");

        }else{
            sb.append("下降"+new BigDecimal(lastWeekOnLineRate*100).abs().
                    setScale(2, BigDecimal.ROUND_HALF_UP)+"%，环比昨日");
        }
        if(yestdayOnLineRate.doubleValue()>0){
            sb.append("上升"+new BigDecimal(yestdayOnLineRate*100).abs().
                    setScale(2, BigDecimal.ROUND_HALF_UP)+"%；");

        }else{
            sb.append("下降"+new BigDecimal(yestdayOnLineRate*100).abs().
                    setScale(2, BigDecimal.ROUND_HALF_UP)+"%；");
        }

        System.out.println(sb.toString());
        System.out.println(smsPhones);
        if(sb.length()>0){
            sendMessage(smsModel,smsPhones,sb.toString());
            System.out.println("短信发送成功");
        }

        String interval = hailTimeRepo.findByTime(TimeInterval,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(statTime));
        if(!StringUtils.hasText(interval)){
            CruiseOnLineHailTime hailTime = new  CruiseOnLineHailTime( TimeInterval,cruiseData,  lastCruiseData,  yestCruiseData,
                    onLineData,  lastWeekOnLineData,  yestdayOnLineData,  statTime);
            hailTimeRepo.save(hailTime);
        }

    }
    private StringBuffer getStringBuffer(Integer data, StringBuffer sb, String string) {
        if(!(data!=null&&data>0)){
            if(sb.length()>0)
                sb.append(","+string);
            else {
                sb.append(string);
            }
        }
        return sb;
    }
    private void sendMessage(String smsModel, String smsPhones,String info) {
        String[] phones=null;
        phones=smsPhones.split(",");
        if(info.length()!=0){
            for(String o:phones){
                SendSmsRequest request=new SendSmsRequest();
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
