package com.talent.measure.web;

import com.talent.base.model.Message;
import com.talent.base.util.BaseController;
import com.talent.measure.dao.CardDao;
import com.talent.measure.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CardController extends BaseController {

	@Autowired
	private CardDao cardDao;

	/**
	 * 卡的状态判断 返回值为：车号、IC卡号、RFID卡号、状态、提示信息
	 * 
	 * @param card
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/card/getCardInfo.do")
	public Object getCardInfo(Card card) {

		// System.out.println("kk...."+card.getCardid());
		Message msg = new Message();
		String carid = "卡号：";
		try {
			if (card.getCardid() != null && !card.getCardid().equals("") && card.getRecordtype() != null
					&& !card.getRecordtype().equals("")) {
				carid = card.getCardid() + " ";
				Card d = new Card();
				if (!card.getRecordtype().equals("2")) {// 传入参数是卡号
					// 通过卡号获取卡，查看卡号是否在黑名单里面
					int isblack = cardDao.isBlack(card);
					if (isblack == 0) {// 表示不在黑名单
						Card c = cardDao.queryCardInfo(card);// 查询是否发卡
						if (c != null) {// 查询发卡信息不为空
							msg.setMfunc(c.getCartype());
							if (3 == c.getValidflag()) {// 卡状态已发放到司机手中
								d.setRecordtype("2"); // 设置类型是2,表示传入进去的是车号
								d.setCardid(c.getCarno());
								int isblack2 = cardDao.isBlack(d);// 判断车号是否在黑名单中
								if (isblack2 == 0) {// 车号不在黑名单中，获取另一个类型卡的卡号（如果只用一个卡的，此处是否不需要再进行查询）
									/**
									 * 添加监秤信息
									 */
									if("2".equals(c.getCartype())){
										card.setCarno(c.getCarno());
										cardDao.insertmonitorweight(card);
									}
									
									/**
									 * 建议把下面两个挪到条件下面
									 */
									d.setFlag(1);
									d.setCarno(c.getCarno());
									if (card.getIcflag() == 3) {
										// 通过车号获取ＩＣ或者ＲＦＩＤ卡号
										if (card.getRecordtype().equals("0")) { // 如果传入的卡号是ic卡，那么根据车号查询rfid卡号
											d.setRecordtype("1");// 表示FRID卡

										} else if (card.getRecordtype().equals("1")) {// 如果传入的是rfid卡号，那么查询ic卡号
											d.setRecordtype("0"); // 表示 IC卡
										}
										Card cd = cardDao.getCardIDbyCarno(d);
										if (cd != null) {// 表示获取到另一个卡号
											if (card.getRecordtype().equals("0")) {// IC卡
												d.setRfidid(cd.getCardid());
											} else if (card.getRecordtype().equals("1")) {// RFID卡
												d.setIcid(cd.getCardid());
											}
											d.setFlag(1);
										} else {
											d.setFlag(0);
											if (card.getRecordtype().equals("0")) {// IC卡
												d.setMsg(carid + "RFID卡未发卡！");
											} else if (card.getRecordtype().equals("1")) {// RFID卡
												d.setMsg(carid + "IC卡未发卡！");
											}

										}
									}
								} else {
									d.setCarno(c.getCarno());
									d.setFlag(0);
									d.setMsg("车号在黑名单中！");
								}
							} else if (0 == c.getValidflag()) {
								d.setFlag(0);
								if (card.getRecordtype().equals("0")) { // 如果传入的卡号是ic卡
									d.setMsg(carid + "IC卡已挂失！");

								} else if (card.getRecordtype().equals("1")) {// 如果传入的是rfid卡号
									d.setMsg(carid + "RFID卡已挂失！");
								}
							} else if ((1 == c.getValidflag()) || (2 == c.getValidflag())) {
								d.setFlag(0);
								if (card.getRecordtype().equals("0")) { // 如果传入的卡号是ic卡
									d.setMsg(carid + "IC卡未进行发卡操作！");

								} else if (card.getRecordtype().equals("1")) {// 如果传入的是rfid卡号
									d.setMsg(carid + "RFID卡未进行发卡操作！");
								}
							}
						} else {
							d.setFlag(0);
							if (card.getRecordtype().equals("0")) { // 如果传入的卡号是ic卡
								d.setMsg(carid + "IC卡未进行初始化操作！");

							} else if (card.getRecordtype().equals("1")) {// 如果传入的是rfid卡号
								d.setMsg(carid + "RFID卡未进行初始化操作！");
							}
						}

					} else if (isblack >= 0) {
						d.setFlag(0);
						d.setMsg("卡号在黑名单中！");

					}
					if (card.getRecordtype().equals("0")) {// IC卡
						d.setIcid(card.getCardid());
					} else if (card.getRecordtype().equals("1")) {// RFID卡
						d.setRfidid(card.getCardid());
					}

				} else {// 传入的是车号

					int iscar = cardDao.isBlack(card); // 判断车号是否在黑名单中
					if (iscar == 0) {// 表示车辆不在黑名单中
						d.setFlag(1);
					} else if (iscar > 0) {
						d.setFlag(0);
						d.setMsg("车号在黑名单中！");
					}
					d.setCarno(card.getCardid());
				}

				msg.setData(d);
			} else {
				msg.setSuccess(false);
				msg.setMsg("传入参数有问题，操作失败！");
			}

		} catch (Exception e) {
			// System.out.println("抛出异常错误。。。。。。。。。。。");
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

}
