package com.talent.materialflow.service.impl;

import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.talent.core.model.Message;
import com.talent.core.privilege.model.User;
import com.talent.materialflow.model.Storename;
import com.talent.materialflow.service.BCommonService;
import com.talent.materialflow.service.StorenameService;
import com.talent.materialflow.service.mapper.StorenameMapper;

@Service
@Transactional
public class StorenameServiceImpl implements StorenameService {
	@Resource
	private StorenameMapper storenameMapper;
	@Resource
	private BCommonService bcommonService;

	// 添加
		public Message insertStorename(Storename storename) throws DataAccessException {
			Message msg = new Message();
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			try {
				int i = storenameMapper.queryCountAdd(storename);
				if (storename.getId() == -1) {
					if (i == 0) {
						storename.setCreator(user.getDisplayname());
						storename.setStorecode(bcommonService.getCode("S"));
						storenameMapper.insertStorename(storename);
					} else {
						msg.setSuccess(false);
						msg.setMsg("相同的单位名称已经存在！");
					}
				} else {				
					int j = storenameMapper.queryCountUpdate(storename);
					if (j == 0) {
						storename.setUpdater(user.getDisplayname());
						storenameMapper.updateStorename(storename);
					} else {
						msg.setSuccess(false);
						msg.setMsg("相同的单位名称已经存在！");
					}
				}

			} catch (Exception e) {
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}
			return msg;
		}

}
