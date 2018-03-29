package org.rapid.soa.config.bean.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.rapid.soa.config.bean.entity.CfgModular;

public class ModularInfo implements Serializable {

	private static final long serialVersionUID = -2400688553636310550L;

	private int id;
	private int layer;
	private String name;
	private List<ModularInfo> childrens;
	
	public ModularInfo() {}
	
	public ModularInfo(CfgModular modular) {
		this.id = modular.getId();
		this.name = modular.getName();
		this.layer = modular.getLayer();
		this.childrens = new ArrayList<ModularInfo>();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getLayer() {
		return layer;
	}
	
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<ModularInfo> getChildrens() {
		return childrens;
	}
	
	public void setChildrens(List<ModularInfo> childrens) {
		this.childrens = childrens;
	}
	
	public ModularInfo append(CfgModular modular) { 
		ModularInfo info = new ModularInfo(modular);
		this.childrens.add(info);
		return info;
	}
}
