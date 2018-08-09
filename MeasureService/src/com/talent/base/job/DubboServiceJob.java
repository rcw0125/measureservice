package com.talent.base.job;

import java.io.File;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.talent.measure.common.CommonTools;

@SuppressWarnings("resource")
public class DubboServiceJob implements Tasklet{

	private Properties messageSource = null;
	private static final Log logger = LogFactory.getLog(DubboServiceJob.class);
	public void setMessageSource(Properties messageSource) {
		this.messageSource = messageSource;
	}
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		String providerStr = messageSource.getProperty("dubbo.providers");
		logger.info("DubboServiceJob中的providerStr....................................."+providerStr);
		if(null != providerStr && !"".equalsIgnoreCase(providerStr)){
			String[] providersList =  providerStr.split(",");
			for(String provider : providersList){
				provider = File.separator + "providers" + File.separator + provider;
				ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{"/spring/applicationContext.xml","/spring/applicationContext-cache.xml","/spring/applicationContext-jdbc.xml",provider});
				classPathXmlApplicationContext.start();
			}
		}
		return RepeatStatus.FINISHED;
	}
}
