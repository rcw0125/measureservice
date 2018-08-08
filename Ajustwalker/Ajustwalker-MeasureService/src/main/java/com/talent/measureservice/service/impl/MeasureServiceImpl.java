package com.talent.measureservice.service.impl;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.talent.core.mybatis.pagehelper.StringUtil;
import com.talent.measureservice.model.Customer;
import com.talent.measureservice.model.Material;
import com.talent.measureservice.model.Measure;
import com.talent.measureservice.model.Trainmodel;
import com.talent.measureservice.service.BcommonService;
import com.talent.measureservice.service.MeasureService;
import com.talent.measureservice.service.mapper.BcommonMapper;
import com.talent.measureservice.service.mapper.ConfigcommonMapper;
import com.talent.measureservice.service.mapper.MeasureMapper;

@Service
@Transactional
public class MeasureServiceImpl implements MeasureService {

	@Resource
	private MeasureMapper measureMapper;
	@Resource
	private BcommonService bcommonService;
	@Resource
	private BcommonMapper bcommonMapper;
	@Resource
	private ConfigcommonMapper configMapper;

	/**
	 * 
	 */
	@Override
	public int insertMeasure(Measure measure) throws DataAccessException {
		int successcount = 0;
		int batchcode = Integer.parseInt(bcommonService.getBatchcode());
		if ("T".equals(measure.getCartype())) { // 为火车的时候生成火车批次号
			if ("T".equals(measure.getMeasurestate())) {// 皮重时生成组号放在皮重序号内
				measure.setTaregroupno(batchcode);
				measure.setMemo1("回毛");
			} else {// 计量毛重时生成毛重组号
				measure.setGrossgroupno(batchcode);
				measure.setMemo1("回皮");
			}
		}
		String currtime = configMapper.queryCurrtime();
		if ("T".equals(measure.getMeasurestate())) {// 皮重时写入皮重时间			
			measure.setTaretime(currtime);
			if (measure.getGross() > 0 && measure.getTare() > 0) {// 如果毛重、皮重大于0，写入净重时间
				measure.setSuttletime(currtime);
			}
		} else {// 毛重时写入毛重时间
			//String currtime = configMapper.queryCurrtime();
			measure.setGrosstime(currtime);
			if (measure.getGross() > 0 && measure.getTare() > 0) {// 如果毛重、皮重大于0 ，写入净重时间
				measure.setSuttletime(currtime);
			}
		}

		if ("".equals(measure.getMatchid())) {
			measure.setMatchid(bcommonService.getMatchid(measure.getOperatype()));
		}
		int flag = measureMapper.queryMeasureCount(measure);// 根据物流号查询在计量表中是否存在

		if (flag == 0) {

			measureMapper.insertMeasure(measure);// 添加计量表
		} else {

			measureMapper.updateMeasure(measure);// 修改计量表
		}
		int c = measureMapper.queryCurrCount(measure);
		
		if (c == 0) {
			measureMapper.insertCurrtemp(measure);// 添加业务临时表
		} else {
			measureMapper.updateCurrtemp(measure);// 修改业务临时表
		}
		
		if ("T".equals(measure.getMeasurestate())) {// 计皮时保存皮重表、皮重日志表
			int f = measureMapper.selectTareBycarno(measure); // 通过车号查询此车号是否保存皮重信息
			if (f == 0) {// 此车号在皮重表中没有信息在皮重表中保存一条皮重信息
				measureMapper.insertTare(measure);
			} else {// 车号已经存在，通过车号修改皮重信息
				measureMapper.updateTare(measure);
			}
			measureMapper.insertTareLog(measure);// 皮重表中保存一条数据信息
		}
		measureMapper.insertMeasureLog(measure);// 计量日志表中保存一条数据信息
		measureMapper.deleteMeasureTempBycarno(measure); // 通过车号删除临时表中的数据信息

		if ("T".equals(measure.getCartype())) { // 为火车的时候保存基础数据信息
			insertBaseData(measure);// 调用方法保存基础信息物料、供货、收货、发站、到站
		}
		successcount = 1;
		measure = null;
		return successcount;
	}

	/**
	 * 保存基础信息物料、供货、收货、发站、到站
	 * 
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public int insertBaseData(Measure measure) throws DataAccessException {
		int i = 0;
		if (StringUtil.isNotEmpty(measure.getMaterialname())) {// 物料不为空
			Material material = new Material();
			material.setMaterialname(measure.getMaterialname());
			int mflag = bcommonMapper.queryMaterialCount(material);// 根据物料名称查询是否已经存在
			if (mflag == 0) {
				material.setMaterialcode(bcommonService.getCode("MT"));// 获取物料编码
				bcommonMapper.insertMaterial(material);// 保存物料信息
			}
			material = null;
		}

		if (StringUtil.isNotEmpty(measure.getSourcename())) {// 供货单位不为空
			Customer customer = new Customer();
			customer.setCustomername(measure.getSourcename());
			int cflag = bcommonMapper.queryCustomerCount(customer);// 根据供应商名称查询是否
			if (cflag == 0) {
				customer.setCustomercode(bcommonService.getCode("CT"));// 获取供应商信息
				customer.setCustomertype("1");// 供应商
				bcommonMapper.insertCustomer(customer);// 保存供应商信息
			}
			customer = null;
		}
		if (StringUtil.isNotEmpty(measure.getTargetname())) {// 收货单位不为空的时候，验证收货单位信息
			Customer customer = new Customer();
			customer.setCustomername(measure.getTargetname());
			int tflag = bcommonMapper.queryCustomerCount(customer);// 根据名称查询在客户表中是否存在
			if (tflag == 0) {// 如果不存在，保存信息
				customer.setCustomercode(bcommonService.getCode("CT")); // 获取客户编码
				customer.setCustomertype("2");// 客户
				bcommonMapper.insertCustomer(customer);// 保存收货单位信息
			}
			customer = null;
		}
		if (StringUtil.isNotEmpty(measure.getSourceplace())) {// 发站不为空的时候，验证发站信息
			Customer customer = new Customer();
			customer.setCustomername(measure.getSourceplace());
			int sflag = bcommonMapper.querySationCount(customer);// 根据名称查询在站点表中是否存在
			if (sflag == 0) {// 如果不存在，保存信息
				customer.setCustomercode(bcommonService.getCode("ST")); // 获取站点编码
				customer.setCustomertype("1");// 站点
				bcommonMapper.insertSation(customer);// 保存站点信息
			}
			customer = null;
		}
		if (StringUtil.isNotEmpty(measure.getTargetplace())) {// 到站不为空的时候，验证到站信息
			Customer customer = new Customer();
			customer.setCustomername(measure.getTargetplace());
			int sflag = bcommonMapper.querySationCount(customer);// 根据名称查询在站点表中是否存在
			if (sflag == 0) {// 如果不存在，保存信息
				customer.setCustomercode(bcommonService.getCode("ST")); // 获取站点编码
				customer.setCustomertype("2");// 站点
				bcommonMapper.insertSation(customer);// 保存站点信息
			}
			customer = null;
		}
		if (StringUtil.isNotEmpty(measure.getMemo8())) {// 车型不为空的时候，保存车型信息
			Trainmodel tmomel = new Trainmodel();
			tmomel.setModel(measure.getMemo8());
			Trainmodel momel = bcommonMapper.queryTrainmodel(tmomel);// 根据名称查询在站点表中是否存在
			if (momel == null) {// 如果不存在，保存信息
				bcommonMapper.insertTrainmodel(tmomel);// 保存站点信息
			}
			tmomel = null;
		}
		return i;

	}

}
