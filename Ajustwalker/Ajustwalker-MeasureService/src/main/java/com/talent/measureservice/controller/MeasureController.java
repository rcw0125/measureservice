package com.talent.measureservice.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.core.log.annotation.Logger;
import com.talent.core.model.Message;
import com.talent.measureservice.model.ConfigModel;
import com.talent.measureservice.model.Measure;
import com.talent.measureservice.model.TareLog;
import com.talent.measureservice.model.TrainMeasure;
import com.talent.measureservice.model.Trainmodel;
import com.talent.measureservice.model.WeightBak;
import com.talent.measureservice.service.BcommonService;
import com.talent.measureservice.service.MeasureService;
import com.talent.measureservice.service.mapper.BcommonMapper;
import com.talent.measureservice.service.mapper.ConfigcommonMapper;
import com.talent.measureservice.service.mapper.MeasureMapper;
import com.talent.measureservice.service.mapper.TareMapper;

@Controller
public class MeasureController {

	@Resource
	private MeasureMapper measuremapper;

	@Resource
	private MeasureService measureService;

	@Resource
	private TareMapper taremapper;

	@Resource
	private BcommonMapper bcommonMapper;

	@Resource
	private ConfigcommonMapper configMapper;
	private ObjectMapper om = new ObjectMapper();

	@Resource
	private BcommonService bcommonService;

	// 根据秤体编码获取对应的参数信息
	/**
	 * carnos:查询的车号信息；caller：0终端，1坐席
	 * 
	 * @param carnos
	 * @param caller
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/MeasureController/getTrainInfo.do")
	@Logger()
	public Message getTrainInfo(String carnos, String caller) {// 参数是车号,通过火车的车号获取信息

		Message msg = new Message();
		try {
			TrainMeasure mea = new TrainMeasure();
			String carnolist[] = carnos.split(",");
			mea.setCarnos(carnolist);
			List<TrainMeasure> list = measuremapper.queryTraininfo(mea);
			if(list==null || list.size()==0){
				list = measuremapper.queryTraininfoPlan(mea);
			}
			msg.setRows(list);
			if (list != null && list.size() > 0) { // 业务信息不为空
				/**
				 * 查询页面显示信息
				 */
				ConfigModel cmodel = new ConfigModel();
				cmodel.setPoints(caller);
				cmodel.setOperatype(list.get(0).getOperatype());
				msg.setMores(configMapper.queryConfigmodel(cmodel));
				cmodel = null; // 清除对象
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("查询失败！" + e.getMessage());
		}
		carnos = null;
		msg.setData("");
		return msg;
	}

	// 根据秤体编码获取对应的参数信息

	@ResponseBody
	@RequestMapping(value = "/MeasureController/queryMeasureTemp.do")
	public Message queryMeasureTemp(String clientid) {// 参数是终端id,通过终端id获取信息
		Message msg = new Message();
		try {
			Measure mea = new Measure();
			mea.setMemo6(clientid);
			List<Measure> list = measuremapper.queryMeasureTemp(mea);
			msg.setRows(list);
			mea = null; // 清空生成对象
			if (list != null && list.size() > 0) { // 业务信息不为空
				/**
				 * 查询页面显示信息
				 */
				ConfigModel cmodel = new ConfigModel();
				cmodel.setPoints("1");
				cmodel.setOperatype(list.get(0).getOperatype());
				msg.setMores(configMapper.queryConfigmodel(cmodel));
				cmodel = null; // 清除对象
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("查询失败！" + e.getMessage());
		}
		clientid = null;
		return msg;
	}

	/**
	 * 
	 * @param jsonParams：坐席保存时读取里面的信息转换为集合保存数据
	 * @param clientid：终端保存时通过终端id查询临时表中的数据信息保存
	 * @param caller：0终端；1坐席
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/MeasureController/insertMeasure.do", method = RequestMethod.POST)
	public Message insertMeasure(@RequestBody String jsonParams) {
		Message msg = new Message();
		try {
			List<Measure> list = ((List<Measure>) om.readValue(jsonParams, List.class));
			for (int i = 0; i < list.size(); i++) {
				Measure measure = new Measure();
				BeanUtils.populate(measure, (Map<String, ? extends Object>) list.get(i));
				measureService.insertMeasure(measure);
			}
			list.clear();
			list = null;
			
		} catch (IOException e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("保存失败！"+e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("保存失败！"+e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("保存失败！"+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("保存失败！"+e.getMessage());
		}
		jsonParams = null;
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/MeasureController/insertMeasureTemp.do", method = RequestMethod.POST)
	public Message insertMeasureTemp(@RequestBody Measure measure) {
		Message msg = new Message();
		int flag=0;
		try {// memo6中存放clientid
			if ("".equals(measure.getMatchid())) {
				measure.setMatchid(bcommonService.getMatchid(measure.getOperatype()));
			}
			if ("G".equals(measure.getMeasurestate())) {
				measure.setMemo1("回皮");
			} else {
				measure.setMemo1("回毛");
			}
		
			int i = measuremapper.queryMeasureTempCount(measure);
			if(i==0){
				flag = measuremapper.insertMeasureTemp(measure);
			}else{
				flag=measuremapper.updateMeasureTemp(measure);
			}
			msg.setMsg(measure.getMatchid());
			if (flag == 0) {
				msg.setSuccess(false);
				msg.setMsg("保存失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("保存失败！" + e.getMessage());
		}
		measure = null;
		return msg;
	}
    /**
     * 作废火车临时表
     * @param measure
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/MeasureController/deleteMeasureTempBycarno.do")
	public Message deleteMeasureTempBycarno( Measure measure) {
		Message msg = new Message();
		try {
			int flag = measuremapper.deleteMeasureTempBycarno(measure);
			if (flag == 0) {
				msg.setSuccess(false);
				msg.setMsg("保存失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("保存失败！" + e.getMessage());
		}
		measure = null;
		return msg;
	}
	/**
	 * 车号和车辆类型： carno=测试005&cartype=C
	 * 
	 * @param tlog
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/MeasureController/getTarebyCarno")
	public Message getTarebyCarno(TareLog tlog) {// 参数是车号,通过火车的车号获取信息
		Message msg = new Message();
		try {
			msg.setRows(taremapper.getTarebyCarno(tlog));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("查询信息失败！" + e.getMessage());
		}
		tlog = null;
		return msg;
	}

	/**
	 * 参数车型：model ;createor:物料
	 * 
	 * @param tlog
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/MeasureController/queryTrainmodel")
	public Message queryTrainmodel(Trainmodel tmomel) {// 参数是车号,通过火车的车号获取信息
		Message msg = new Message();
		try {
			Trainmodel t = bcommonMapper.queryTrainmodel(tmomel);
			if (t != null) {
				msg.setData(t.getDeduction());
				msg.setMfunc("1");
			} else {
				msg.setData(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("获取车型失败！" + e.getMessage());
		}
		tmomel = null;
		return msg;
	}

	/**
	 * 动态衡重量数据备份
	 * 
	 * @param jsonParams
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/MeasureController/insertWeightbak.do", method = RequestMethod.POST)
	public Message insertWeightbak(@RequestBody String jsonParams) {
		Message msg = new Message();
		try {
			int flag=0;
			WeightBak weightbak = new WeightBak();
			List<WeightBak> list = ((List<WeightBak>)(om.readValue(jsonParams, List.class)));
			for (int i = 0; i < list.size(); i++) {
				BeanUtils.populate(weightbak, (Map<String, ? extends Object>) list.get(i));
				flag = measuremapper.insertWeightbak(weightbak);
			}
			
			if (flag == 0) {
				msg.setSuccess(false);
				msg.setMsg("保存失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("保存失败！" + e.getMessage());
		}
		return msg;
	}
}
