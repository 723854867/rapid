package org.rapid.soa.config.bean.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.rapid.core.bean.model.Identifiable;

/**
 * 模块配置
 * 
 * @author lynn
 */
public class CfgModular implements Identifiable<Integer> {

	private static final long serialVersionUID = 5520570929665364987L;

	@Id
	@GeneratedValue
	private int id;
	private int left;
	private int right;
	private int layer;
	private int trunk;
	private String name;
	private int created;
	private int updated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public int getTrunk() {
		return trunk;
	}

	public void setTrunk(int trunk) {
		this.trunk = trunk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	@Override
	public Integer identity() {
		return this.id;
	}
}
