package com.talent.materialflow.controller;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.model.QueryParams;
import com.talent.core.privilege.model.User;
import com.talent.materialflow.model.Customer;
import com.talent.materialflow.service.BCommonService;
import com.talent.materialflow.service.CustomerService;
import com.talent.materialflow.service.mapper.CustomerMapper;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController extends BaseController {

	@Autowired
	private CustomerService customerService;
	@Resource
	private CustomerMapper customerMapper;
	@Resource
	private BCommonService bcommonService;
	
	@RequestMapping(value = "/customer")  
	public String init(ModelMap model) {
		return "customer";
	}
	
	@RequestMapping(value = "/customer2")  
	public String init2(ModelMap model) {
		return "customer2";
	}
	
	@ResponseBody
	@RequestMapping(value = "/customer/list")  
	public Message queryPage(Customer customer,ModelMap model,Pagination<Customer> page) {
		try{
			QueryParams<Customer> params = new QueryParams<Customer>(customer);
			params.like("customercode", false);
			params.like("customername", false);
			params.like("queryword", false);
			return buildGridData(customerService.query(params,page));
		}catch (Exception e){	
			return new Message(false,"操作失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/customer2/list")
	public Message queryList(Customer customer,ModelMap model,Pagination<Customer> page) {
		try {
			return buildGridData(customerMapper.queryList(customer));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(false, Message.FAILURE_MESSAGE + e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/customer/edit")  
	public Message addorEdit(Customer customer,ModelMap model) {
		Message msg = new Message();
		try{
			customerService.insertOrUpdate(customer, true);
			msg.setTotal(customer.getId());
		}catch(PersistenceException ex){
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}catch(DuplicateKeyException e){
			msg.setSuccess(false);
			msg.setMsg("试图添加重复数据，操作失败！");
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/customer/delete")
	public Message del(Customer customer,ModelMap model) {
		Message msg = new Message();
		try{
			customerService.deleteByIds(customer);
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/customer/form")
	public Customer loadForm(Customer customer,ModelMap model) {
		try{
			if(-1 != customer.getId()){
				customer = customerService.findOne(customer.getId());
			}
			if(null == customer){
				customer = new Customer();
			}
		}catch (Exception e) {	
			customer = new Customer();
		}
		return customer; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/customer2/form")
	public Customer queryInfoById(Customer customer) {
		Customer customer2 = new Customer();
		try {
			if (customer.getId() != -1) {// 如果前面传递过来的id为-1，这说明是添加，则直接返回新对象sg
				customer2 = customerMapper.queryInfoById(customer);// 如果是修改，查询要修改的数据并赋值给对象sg
			}
			return customer2;
		} catch (Exception e) {
			return customer2;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/customer2/edit")
	public Message insertCustomer(Customer customer) {
		Message msg = new Message();
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			int i = customerMapper.queryCount(customer);
			int j = customerMapper.queryCountById(customer);
			if (customer.getId()==-1) {//添加
				if (i == 0) {
						customer.setCreator(user.getDisplayname());
						customer.setCustomercode(bcommonService.getCode("CT"));
						customerMapper.insertCustomer(customer);					
				} else {
					msg.setSuccess(false);
					msg.setMsg("相同类型的客商名称已经存在！");
				}
			} else {//修改
				if (j > 0) {
					msg.setSuccess(false);
					msg.setMsg("相同类型的客商名称已经存在！");
				} else {
					customer.setUpdater(user.getDisplayname());
					customerMapper.updateCustomer(customer);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/customer2/del")
	public Message cancelCustomer(Customer customer) {
		Message msg = new Message();
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			customer.setCanceler(user.getDisplayname());
			customerMapper.cancelCustomer(customer);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
}
