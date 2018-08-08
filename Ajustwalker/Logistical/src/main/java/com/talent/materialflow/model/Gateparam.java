package com.talent.materialflow.model;

import org.apache.ibatis.type.Alias;


@Alias("gateparam")
public class Gateparam  {
	
    private long id;
	private String gatecode ;//     大门编码
	private String gateid  ;//       大门id（需要关联到 大门主表id 大门编码与大门名称可以不需要）
	private String gatename  ;//       大门名称
	private int ictype  ;//       ic卡类型 例如 0 玺瑞  1 mc9500 2 移动读卡等
	private String iccom  ;//       ic com口
	private String icbruad  ;//       ic 波特率
	private String icno ;//       ic 设备号
	private String icmemo1  ;//       ic 备用1
	private String icmemo2  ;//       ic 备用2
	private int icvalidflag  ;//       ic是否启用 0 不启用 1 启用
	private int dztype  ;//        道闸类型  例如 0 玺瑞  1 roadrate 等
	private String dzcom ;//       道闸  com口
	private String dzbruad  ;//        道闸  波特率
	private String dzno  ;//    道闸  设备号
	private String dzaddress ;//       道闸  地址
	private String dzmemo1 ;//      道闸  备用1
	private String dzmemo2  ;//        道闸  备用2
	private int dzvalidflag ;//      道闸是否启用 0 不启用 1 启用
	private int  inouttype ;//       进出厂类型 0 进厂 1 出厂
	public String getGatecode() {
		return gatecode;
	}
	public void setGatecode(String gatecode) {
		this.gatecode = gatecode;
	}
	public String getGateid() {
		return gateid;
	}
	public void setGateid(String gateid) {
		this.gateid = gateid;
	}
	public String getGatename() {
		return gatename;
	}
	public void setGatename(String gatename) {
		this.gatename = gatename;
	}
	public int getIctype() {
		return ictype;
	}
	public void setIctype(int ictype) {
		this.ictype = ictype;
	}
	public String getIccom() {
		return iccom;
	}
	public void setIccom(String iccom) {
		this.iccom = iccom;
	}
	public String getIcbruad() {
		return icbruad;
	}
	public void setIcbruad(String icbruad) {
		this.icbruad = icbruad;
	}
	public String getIcno() {
		return icno;
	}
	public void setIcno(String icno) {
		this.icno = icno;
	}
	public String getIcmemo1() {
		return icmemo1;
	}
	public void setIcmemo1(String icmemo1) {
		this.icmemo1 = icmemo1;
	}
	public String getIcmemo2() {
		return icmemo2;
	}
	public void setIcmemo2(String icmemo2) {
		this.icmemo2 = icmemo2;
	}
	public int getIcvalidflag() {
		return icvalidflag;
	}
	public void setIcvalidflag(int icvalidflag) {
		this.icvalidflag = icvalidflag;
	}
	public int getDztype() {
		return dztype;
	}
	public void setDztype(int dztype) {
		this.dztype = dztype;
	}
	public String getDzcom() {
		return dzcom;
	}
	public void setDzcom(String dzcom) {
		this.dzcom = dzcom;
	}
	public String getDzbruad() {
		return dzbruad;
	}
	public void setDzbruad(String dzbruad) {
		this.dzbruad = dzbruad;
	}
	public String getDzno() {
		return dzno;
	}
	public void setDzno(String dzno) {
		this.dzno = dzno;
	}
	public String getDzaddress() {
		return dzaddress;
	}
	public void setDzaddress(String dzaddress) {
		this.dzaddress = dzaddress;
	}
	public String getDzmemo1() {
		return dzmemo1;
	}
	public void setDzmemo1(String dzmemo1) {
		this.dzmemo1 = dzmemo1;
	}
	public String getDzmemo2() {
		return dzmemo2;
	}
	public void setDzmemo2(String dzmemo2) {
		this.dzmemo2 = dzmemo2;
	}
	public int getDzvalidflag() {
		return dzvalidflag;
	}
	public void setDzvalidflag(int dzvalidflag) {
		this.dzvalidflag = dzvalidflag;
	}
	public int getInouttype() {
		return inouttype;
	}
	public void setInouttype(int inouttype) {
		this.inouttype = inouttype;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


	
}
