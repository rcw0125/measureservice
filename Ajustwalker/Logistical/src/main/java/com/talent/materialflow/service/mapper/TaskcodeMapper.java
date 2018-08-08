package com.talent.materialflow.service.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.talent.materialflow.model.Taskcode;

public interface TaskcodeMapper {

	public List<Taskcode> queryList(Taskcode taskcode) throws DataAccessException;

	public Taskcode queryInfoBytaskcode(Taskcode taskcode) throws DataAccessException;

	public int insertTaskcode(Taskcode taskcode) throws DataAccessException;

	public int updateTaskcode(Taskcode taskcode) throws DataAccessException;

	public int cancelTaskcode(Taskcode taskcode) throws DataAccessException;

	public int queryCount(Taskcode taskcode) throws DataAccessException;
	
	public int queryCountbyTaskcode(Taskcode taskcode) throws DataAccessException;
	

}
