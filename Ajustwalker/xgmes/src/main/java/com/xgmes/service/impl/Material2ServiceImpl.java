package com.xgmes.service.impl;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talent.core.model.Message;
import com.talent.core.privilege.model.User;
import com.xgmes.mapper.MaterialMapper;
import com.xgmes.model.Material;
import com.xgmes.service.BCommonService;
import com.xgmes.service.Material2Service;

@Service
@Transactional
public class Material2ServiceImpl implements Material2Service {
	@Resource
	private MaterialMapper materialMapper;
	@Resource
	private BCommonService bcommonService;

	// 添加
	public Message insertMaterial(Material material) throws DataAccessException {
		Message msg = new Message();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			int i = materialMapper.queryCountAdd(material);
			if (material.getId() == -1) {
				if (i == 0) {
					if(material.getTypes() == 1){
						material.setMaterialcode(bcommonService.getCode("M"));
					} else if(material.getTypes() == 2) {
						material.setMaterialcode(bcommonService.getCode("TM"));
					}
					
					material.setCreator(user.getDisplayname());
					materialMapper.insertMaterial(material);
				} else {
					msg.setSuccess(false);
					msg.setMsg("同类型相同的物料名称已经存在！");
				}
			} else {				
				int j = materialMapper.queryCountUpdate(material);
				if (j == 0) {
					material.setUpdater(user.getDisplayname());
					materialMapper.updateMaterial(material);
				} else {
					msg.setSuccess(false);
					msg.setMsg("同类型相同的物料名称已经存在！");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

}
