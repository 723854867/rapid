package org.rapid.web.util.bean.param;

import org.rapid.core.bean.model.param.Searcher;

public class LogAccessSearcher extends Searcher<LogAccessSearcher> {

	private static final long serialVersionUID = -2611602636223595634L;
	
	private String qip;
	private String id;
	private String desc;
	private String path;
	
	public String getQip() {
		return qip;
	}
	
	public void setQip(String qip) {
		this.qip = qip;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
}
