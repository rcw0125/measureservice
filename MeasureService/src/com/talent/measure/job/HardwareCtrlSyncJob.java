package com.talent.measure.job;

import com.talent.measure.common.CommonTools;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class HardwareCtrlSyncJob implements Tasklet{

	@Autowired
	private CommonTools commonTools;
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		commonTools.syncDeviceConfigData();
		return RepeatStatus.FINISHED;
	}
}
