package com.nowcoder.community.config;


import com.nowcoder.community.quartz.AlphaJob;
import com.nowcoder.community.quartz.PostScoreQuartzJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

//配置 -> 初始化到数据库 ->调用
@Configuration
public class QuartzConfig {

    //FactoryBean可简化Bean的实例化过程：
    //1.通过FactoryBean封装Bean的实例化过程
    //2.将FactoryBean装配到Spring容器里。
    //3.将FactoryBean注入给其他的Bean。
    //4.该Bean得到的是FactoryBean所管理的对象实例。

    //配置JobDetail
    //@Bean
    public JobDetailFactoryBean alphaJobDetail(){
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(AlphaJob.class);
        factoryBean.setName("alphaJob");
        factoryBean.setGroup("alphaJobGroup");
        factoryBean.setDurability(true); //是否持久保存
        factoryBean.setRequestsRecovery(true); //是否可恢复
        return factoryBean;
    }

    //配置Trigger(SimpleTriggerFactoryBean,CronTriggerFactoryBean)
    //@Bean
    public SimpleTriggerFactoryBean alphaTrigger(JobDetail alphaJobDetail){
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(alphaJobDetail);
        factoryBean.setName("alphaTrigger");
        factoryBean.setGroup("alphaTriggerGroup");
        factoryBean.setRepeatInterval(3000); //多久执行一次
        factoryBean.setJobDataMap(new JobDataMap());  //job的状态
        return factoryBean;
    }


    //刷新帖子分数任务
    @Bean
    public JobDetailFactoryBean postScoreRefreshJobDetail(){
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(PostScoreQuartzJob.class);
        factoryBean.setName("postScoreRefresh");
        factoryBean.setGroup("communityJobGroup");
        factoryBean.setDurability(true); //是否持久保存
        factoryBean.setRequestsRecovery(true); //是否可恢复
        return factoryBean;
    }

    //配置Trigger(SimpleTriggerFactoryBean,CronTriggerFactoryBean)
    @Bean
    public SimpleTriggerFactoryBean postScoreRefreshTrigger(JobDetail postScoreRefreshJobDetail){
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(postScoreRefreshJobDetail);
        factoryBean.setName("postScoreRefreshTrigger");
        factoryBean.setGroup("communityTriggerGroup");
        factoryBean.setRepeatInterval(1000 * 60 * 5); //多久执行一次
        factoryBean.setJobDataMap(new JobDataMap());  //job的状态
        return factoryBean;
    }


}
