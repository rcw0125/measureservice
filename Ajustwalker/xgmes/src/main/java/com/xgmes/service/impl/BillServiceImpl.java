package com.xgmes.service.impl;

import java.util.Map;

import javax.annotation.Resource;


import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.talent.core.model.Message;
import com.xgmes.mapper.BillMapper;
import com.xgmes.model.Interface;
import com.xgmes.service.BCommonService;
import com.xgmes.service.BillService;

@Service
@Transactional
public class BillServiceImpl implements BillService  {
	@Resource
	private BillMapper billMapper;
	@Resource
	private BCommonService bcommonService;


	/* (non-Javadoc)
	 * @see com.talent.materialflow.service.impl.BillService#insertBill(com.talent.materialflow.model.Applicationbill)
	 */

	@Override
	public Message insertBill(Interface inter) throws DataAccessException {
		Message msg = new Message();
		String planid ="";
		if(inter.getTypeflag()==1){//到货
			planid=bcommonService.txPlanid("MH");
		}else{
			planid=bcommonService.txPlanid("MF");
		}
		
		inter.setPlanid(planid);
		billMapper.insertInterfaceinfo(inter);
		inter.setFid(inter.getId());
		for (int i = 0; i < inter.getExparams().size(); i++) {
			Map<String, String> map = inter.getExparams().get(i);
			inter.setCarno(map.get("carno"));
			inter.setMaterialcode(map.get("materialcode"));
			inter.setMaterialname(map.get("materialname"));
			inter.setMeasureunit(map.get("measureunit"));
			inter.setSteelname(map.get("steelname"));
			inter.setMaterialspec(map.get("materialspec"));
			inter.setMiddlemanname(map.get("middlemanname"));
			inter.setSnumber(StringUtils.isEmpty(map.get("snumber")) ? 0 : Long.parseLong(map.get("snumber")));
			inter.setSuttleapp(StringUtils.isEmpty(map.get("suttleapp")) ? 0 : Long.parseLong(map.get("suttleapp")));
            inter.setUsermemo(map.get("usermemo"));
			billMapper.insertInterfaceinfoitem(inter);
		}
		return msg;
	}

	/* (non-Javadoc)
	 * @see com.talent.materialflow.service.impl.BillService#updateBill(com.talent.materialflow.model.Applicationbill)
	 */

	@Override
	public Message updateBill(Interface inter) throws DataAccessException {
		Message msg = new Message();
		billMapper.cancelInterfaceinfoitem(inter);
		billMapper.updateInterfaceinfo(inter);
		for (int i = 0; i < inter.getExparams().size(); i++) {
			Map<String, String> map = inter.getExparams().get(i);
			inter.setId(Long.parseLong(map.get("id")));
			if(inter.getTypeflag()==1){
				inter.setCarno(map.get("carno"));
			}
			inter.setMaterialcode(map.get("materialcode"));
			inter.setMaterialname(map.get("materialname"));
			inter.setMeasureunit(map.get("measureunit"));
			inter.setSteelname(map.get("steelname"));
			inter.setMaterialspec(map.get("materialspec"));
			inter.setMiddlemanname(map.get("middlemanname"));
			inter.setSnumber(StringUtils.isEmpty(map.get("snumber")) ? 0 : Long.parseLong(map.get("snumber")));
			inter.setSuttleapp(StringUtils.isEmpty(map.get("suttleapp")) ? 0 : Long.parseLong(map.get("suttleapp")));
            inter.setUsermemo(map.get("usermemo"));
			int m = billMapper.updateInterfaceinfoitem(inter);
			if(m==0){
				billMapper.insertInterfaceinfoitem(inter);
			}
			
		}
		return msg;
	}

	/* (non-Javadoc)
	 * @see com.talent.materialflow.service.impl.BillService#cancelBill(com.talent.materialflow.model.Applicationbill)
	 */

	@Override
	public Message cancelBill(Interface inter) throws DataAccessException {
		Message msg = new Message();
		billMapper.cancelInterfaceinfo(inter);
		billMapper.cancelInterfaceinfoitem(inter);
		return msg;
	}


}
