package org.rapid.core.bean.model.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.rapid.core.Assert;
import org.rapid.core.bean.model.Paginate;
import org.rapid.core.bean.model.code.Code;
import org.rapid.util.Callback;

import com.github.pagehelper.Page;

public class Pager<T> implements Serializable {

	private static final long serialVersionUID = 1923248246312665488L;

	private long total;			// 总数据
	private long pages;			// 总页数
	private List<T> list;
	
	public Pager() {}
	
	/**
	 * 使用 PageHelper 分页返回的是一个继承了 List 的 {@link Page} 对象，改对象中包含了一些分页的属性，比如总页数什么的；
	 * 如果使用 json 序列化，会丢失掉这些属性，因此要转一下
	 * 
	 * @param list
	 */
	public Pager(List<T> list) {
		if (list instanceof Page) {
			this.list = new ArrayList<T>(list.size());
			list.forEach(item -> this.list.add(item));
			Page<T> page = (Page<T>) list;
			this.total = page.getTotal();
			this.pages = page.getPages();
			page.close();
		} else if (list instanceof Paginate) {
			this.list = new ArrayList<T>(list.size());
			list.forEach(item -> this.list.add(item));
			Paginate<T> page = (Paginate<T>) list;
			this.total = page.getTotal();
			this.pages = page.getPages();
		} else
			this.list = list;
	}
	
	public Pager(List<T> list, org.rapid.core.bean.model.param.Page page) {
		this.list = list;
		this.total = page.getTotal();
		this.pages = page.getPages();
	}
	
	public long getTotal() {
		return total;
	}
	
	public void setTotal(long total) {
		this.total = total;
	}
	
	public long getPages() {
		return pages;
	}
	
	public void setPages(long pages) {
		this.pages = pages;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public static final <T, K> Pager<T> convert(List<K> list, Callback<List<K>, List<T>> callback) {
		Pager<T> pager = new Pager<T>();
		Assert.isTrue(Code.SYS_ERROR, list instanceof Page);
		Page<K> page = (Page<K>)list;
		pager.setPages(page.getPages());
		pager.setTotal(page.getTotal());
		pager.setList(callback.invoke(page));
		page.close();
		return pager;
	}
	
	public static final <T> Pager<T> empty() {
		return new Pager<T>();
	}
}
