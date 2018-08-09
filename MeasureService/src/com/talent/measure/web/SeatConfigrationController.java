package com.talent.measure.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.measure.dao.SeatConfigrationDao;
import com.talent.measure.model.SeatConfigration;

@Controller
public class SeatConfigrationController {
	
	@Autowired
	private SeatConfigrationDao seatDao;
	@ResponseBody
	@RequestMapping(value = "/seatconfigration/getSeatList.do")
	public List<SeatConfigration> getSeatList(String equtype ){
		List<SeatConfigration> list =(List<SeatConfigration>) seatDao.getSeatList(equtype);
		return list;
	}
}
