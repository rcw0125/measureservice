package com.talent.base.job;

import java.util.ArrayList;
import java.util.List;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.talent.base.model.Logs;

public class ReadLogsJob implements ItemReader<Logs> {
	
	private static List<Logs> list = null;
	
	private static Logs logs = null;
	
    public ReadLogsJob() {
    	if(null == list){
    		list = new ArrayList<Logs>();
    	}
    	logs = new Logs();
		list.add(logs);
    }
	
	private String operator = ""; // ip地址

	private String inParams = ""; // 模块传入参数

	private String outResults = ""; // 输出结果
	
	private String createTime = "";
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		logs.setOperator(operator);
	}

	public String getInParams() {
		return inParams;
	}

	public void setInParams(String inParams) {
		logs.setInParams(inParams);
	}

	public String getOutResults() {
		return outResults;
	}

	public void setOutResults(String outResults) {
		logs.setOutResults(outResults);
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		logs.setCreateTime(createTime);
	}

	@Override
	public Logs read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (!list.isEmpty()) {
            return list.remove(0);
        }
        return null;
	}    
}