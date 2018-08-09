package com.talent.measure.web;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.talent.base.annotation.Rule;
import com.talent.base.dao.PlatformDao;
import com.talent.base.model.Message;
import com.talent.base.util.BaseController;
import com.talent.base.util.BaseUtil;
import com.talent.base.util.CacheUtil;
import com.talent.base.util.SpringBeanUtil;
import com.talent.measure.common.BusinessConfig;
import com.talent.measure.common.CommonTools;
import com.talent.measure.dao.CommonDao;
import com.talent.measure.model.ConfigModel;
import com.talent.measure.model.FlowInParams;
import com.talent.measure.model.Measure;
import com.talent.measure.model.MeasureRuleDetail;
import com.talent.privilege.dao.PrivilegeDao;
import com.talent.privilege.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ToolsController extends BaseController {

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private PlatformDao platformDao;

	@Autowired
	private CacheUtil cacheUtil;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private SpringBeanUtil springBeanUtil;

	@Autowired
	private PrivilegeDao privilegeDao;
	
	@Autowired
	private CommonTools commonTools;
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/tools/jsonconvert.do", method = RequestMethod.POST)
	public Object jsonConvert(String jsonParams, ModelMap model, @RequestBody Measure measure) {

		Message msg = new Message();
		try {
			ObjectMapper om = new ObjectMapper();
			HashMap<String, String> d = om.readValue(jsonParams, HashMap.class);
			msg.setData(d);
			msg.setTotal(1);
			List<Object> l = new ArrayList<Object>();
			l.add(d);
			l.add(d);
			msg.setRows(l);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/configmodel/save.do")
	public Message saveConfigModel(ConfigModel configmodel) {
		Message msg = new Message();
		try {
			try {
				platformDao.insert(configmodel);
			} catch (DuplicateKeyException e) {
				platformDao.update(configmodel);
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/configmodel/query.do")
	public Message queryConfigModel(ConfigModel configmodel) {
		Message msg = new Message();
		try {
			msg.setRows(platformDao.queryList(configmodel));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！" + e.getMessage());
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/tools/measurerule.do")
	public Object measureRule(FlowInParams params) {

		Message msg = new Message();
		try {
			params.setMatchid("101512160004");
			params.setOptr("物资调拨先皮后毛计皮");
			long starttime = Calendar.getInstance().getTimeInMillis();
			long endtime = Calendar.getInstance().getTimeInMillis();
			msg.setData(endtime - starttime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/tools/hardwarectrl.do")
	public Object hardwareCtrl() {

		Message msg = new Message();
		try {
			msg.setMsg(commonDao.newMatchid("000"));
			FlowInParams params = new FlowInParams();
			params.setMatchid("00015103000360");
			System.out.println("测试测试测试测试测试");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/tools/bbbb.do")
	public Object bbbb(HttpServletRequest request) {

		Message msg = new Message();
		try {
			Job job = (Job) BaseUtil.getApplicationContext().getBean("ruleSyncJob");
			jobLauncher.run(job, new JobParametersBuilder()
					.addString("createTime", Calendar.getInstance().getTimeInMillis() + "").toJobParameters());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/tools/columns.do")
	public String getCurrColumns(ModelMap model) {
		List<HashMap<String, String>> r = commonDao.queryColumnsList();
		for (HashMap<String, String> m : r) {
			String column = m.get("COLUMN_NAME").toString();
			String type = m.get("DATA_TYPE").toString();
			String last = type.equals("NUMBER") ? " = 0" : " = \"\"";
			type = type.equals("NUMBER") ? "int" : "String";
			System.out.println("private " + type + " " + column.toLowerCase() + last + ";");
			System.out.println();
			// System.out.println(column + " as \"" + column.toLowerCase() +
			// "\",");
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/tools/mroles.do")
	public List<Map<String, Object>> getMeasureRules(ModelMap model) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		try {
			Method[] methods = springBeanUtil.getBean(BusinessConfig.class).getClass().getDeclaredMethods();
			String operateType = "90";
			for (Method method : methods) {
				if (method.isAnnotationPresent(Rule.class)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("methodname", method.getName());
					Annotation p = method.getAnnotation(Rule.class);
					Method name = p.getClass().getMethod("name");
					map.put("name", name.invoke(p));
					Method ctrlflag = p.getClass().getMethod("ctrlflag");
					Map<String, String> oc = (Map<String, String>) cacheUtil.getCache("operatesCache")
							.get("operateskey" + operateType);
					map.put("ctrlflag", ctrlflag.invoke(p));
					String ctrldesc = oc.get(ctrlflag.invoke(p));

					// 皮重超期后的处理方式，0：不检查，1：进行提示，2，进行选择，3禁止计量
					if ("0".equals(ctrldesc)) {
						ctrldesc = "不检查";
					} else if ("1".equals(ctrldesc)) {
						ctrldesc = "进行提示";
					} else if ("2".equals(ctrldesc)) {
						ctrldesc = "进行选择";
					} else if ("3".equals(ctrldesc)) {
						ctrldesc = "禁止计量";
					} else {
						ctrldesc = "";
					}

					map.put("ctrldesc", ctrldesc);
					Method memo = p.getClass().getMethod("memo");
					map.put("memo", memo.invoke(p));
					result.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/tools/reload.do")
	public Message reload(String key, ModelMap model) {

		Message bm = new Message();
		try {
			List<MeasureRuleDetail> list = cacheUtil.getCache("measureRuleDetailCache").list(new MeasureRuleDetail(),
					"measureRuleDetailKey");
			bm.setData(list);
			bm.setTotal(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bm;
	}

	@ResponseBody
	@RequestMapping(value = "/beans/reload.do")
	public Message beanLoad(ModelMap model) {

		Message bm = new Message();
		try {
			springBeanUtil.unregisterBean("businessConfig");
			springBeanUtil.registerBean("businessConfig", BusinessConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bm;
	}

	@RequestMapping(value = "/privilege/test.do")
	public String privilegeTest(ModelMap model) {
		User user = new User();
		user.setUsercode("lzy");
		System.out.println(privilegeDao.queryMeasureMenuStrByUsercode(user, ""));
		return "";
	}

	public static void main(String[] args) {
		try {
			sshShell("10.1.196.88","root","LESApp97w",21,"","");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 利用JSch包实现远程主机SHELL命令执行
	 * @param ip 主机IP
	 * @param user 主机登陆用户名
	 * @param psw 主机登陆密码
	 * @param port 主机ssh2登陆端口，如果取默认值，传-1
	 * @param privateKey 密钥文件路径
	 * @param passphrase 密钥的密码
	 */
	public static void sshShell(String ip, String user, String psw, int port, String privateKey, String passphrase) throws Exception {
		
		Session session = null;
		Channel channel = null;
		JSch jsch = new JSch();

		// 设置密钥和密码
		if (privateKey != null && !"".equals(privateKey)) {
			if (passphrase != null && "".equals(passphrase)) {
				// 设置带口令的密钥
				jsch.addIdentity(privateKey, passphrase);
			} else {
				// 设置不带口令的密钥
				jsch.addIdentity(privateKey);
			}
		}

		if (port <= 0) {
			// 连接服务器，采用默认端口
			session = jsch.getSession(user, ip);
		} else {
			// 采用指定的端口连接服务器
			session = jsch.getSession(user, ip, port);
		}

		// 如果服务器连接不上，则抛出异常
		if (session == null) {
			throw new Exception("session is null");
		}

		// 设置登陆主机的密码
		session.setPassword(psw);// 设置密码
		// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
		session.setConfig("StrictHostKeyChecking", "yes");
		// 设置登陆超时时间
		session.connect(30000);

		try {
			// 创建sftp通信通道
			channel = (Channel) session.openChannel("shell");
			channel.connect(1000);

			// 获取输入流和输出流
			InputStream instream = channel.getInputStream();
			OutputStream outstream = channel.getOutputStream();

			// 发送需要执行的SHELL命令，需要用\n结尾，表示回车
			String shellCommand = "ls \n";
			outstream.write(shellCommand.getBytes());
			outstream.flush();

			// 获取命令执行的结果
			if (instream.available() > 0) {
				byte[] data = new byte[instream.available()];
				int nLen = instream.read(data);
				if (nLen < 0) {
					throw new Exception("network error.");
				}
				// 转换输出结果并打印出来
				String temp = new String(data, 0, nLen, "iso8859-1");
				System.out.println(temp);
			}
			outstream.close();
			instream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.disconnect();
			channel.disconnect();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/tools/getOracleDateTime.do")
	public String getOracleDateTime() {

		String oracletime = "";
		try {
			oracletime = commonDao.getOracleDateTime();

		} catch (Exception e) {
			System.out.println("输出异常：" + e);
		}
		return oracletime;
	}
	
	@ResponseBody
	@RequestMapping(value = "/tools/refreshbasedata.do")
	public Message refreshBaseData() {
		try{
			commonTools.syncMeasureBaseData();
			return new Message();
		}catch(Exception e){
			Message msg = new Message();
			msg.setSuccess(false);
			msg.setMsg("刷新失败");
			return msg;
		}
	}
}
