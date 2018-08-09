package com.talent.measure.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talent.base.model.Message;
import com.talent.base.model.PageModel;
import com.talent.measure.dao.StatisticsMReportDao;
import com.talent.measure.model.Statistics;
import com.talent.measure.model.Taskinfo;

@Controller
public class StatisticsReportController {

	@Autowired
	private StatisticsMReportDao StatisticsDao;

	/**
	 * 
	 * @param tinfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/StatisticsReport/querytaskinfo.do")
	public List<Taskinfo> querytaskinfo(Taskinfo tinfo, String begintime) {
		System.out.println("begintime........." + begintime);
		List<Taskinfo> list = null;
		try {
			list = StatisticsDao.querytaskinfo(tinfo);

		} catch (Exception e) {
			list = new ArrayList<Taskinfo>();
		}

		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/StatisticsReport/queryavgtaskinfo.do")
	public Taskinfo queryavgtaskinfo(Taskinfo tinfo) {

		Taskinfo taskinfo = null;
		List<Taskinfo> list = null;
		try {
			taskinfo = StatisticsDao.queryavgtaskinfo(tinfo);
			list = StatisticsDao.querytaskinfo(tinfo);
			taskinfo.setList(list);

		} catch (Exception e) {

			taskinfo = new Taskinfo();
		}

		return taskinfo;
	}

	@ResponseBody
	@RequestMapping(value = "/StatisticsReport/querytaskdata.do")
	public Message querytaskdata(Taskinfo tinfo, PageModel pm) {
		Message msg = new Message();
		try {
			List<Taskinfo> list = StatisticsDao.querytaskdata(tinfo);

			msg.setTotal(list.size());
			msg.setRows(list);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}

		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/StatisticsReport/queryMeasureByOperatype.do")
	public List<Statistics> queryMeasureByOperatype(Statistics statis) {
		String monthtime = "";
		monthtime = statis.getDatetime().substring(0, 8) + "01";
		statis.setMonthtime(monthtime);
		return StatisticsDao.queryMeasureByOperatype(statis);
	}

	@ResponseBody
	@RequestMapping(value = "/StatisticsReport/queryunitweight.do")
	public List<Statistics> queryunitweight(Statistics statis) {
		List<Statistics> result = null;
		try {
			result = StatisticsDao.queryunitweight(statis);
		} catch (Exception e) {
			result = new ArrayList<Statistics>();
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/StatisticsReport/createunitweight.do")
	public Message createunitweight(Statistics statis) {

		Message msg = new Message();
		try {
			Statistics st = StatisticsDao.createunitweight(statis);
			int i = (Integer.parseInt(st.getFlag()));
			if (i > 0) {
				msg.setMsg("操作成功！");
			} else {
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

}
