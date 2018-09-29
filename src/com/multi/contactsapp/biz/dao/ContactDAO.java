package com.multi.contactsapp.biz.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.multi.contactsapp.biz.domain.Contact;

@Repository
public class ContactDAO extends SqlMapClientDaoSupport {

	@Autowired
	public void setSqlMapClientWorkAround(SqlMapClient sqlMapClient) {
		this.setSqlMapClient(sqlMapClient);
	}
	
	@SuppressWarnings("unchecked")
	public List<Contact> selectAll() {
		return (List<Contact>)this.getSqlMapClientTemplate().queryForList("contact.selectall");
	}
	
	@SuppressWarnings("unchecked")
	public List<Contact> selectAll(int pageno, int pagesize) {
		int skip = (pageno-1) * pagesize;
		int max = pagesize;
		return (List<Contact>)this.getSqlMapClientTemplate().queryForList("contact.selectall", skip, max);
	}
	
	public Contact selectOne(Contact c){
		return (Contact) this.getSqlMapClientTemplate().queryForObject("contact.selectone", c);
	}
	
	public int selectCnt() {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("contact.selectcnt");
	}
	
	public int insert(Contact c) {
		return (Integer) this.getSqlMapClientTemplate().insert("contact.insert", c);
	}
	
	public int update(Contact c) {
		return this.getSqlMapClientTemplate().update("contact.update", c);
	}
	
	public int delete(Contact c) {
		return this.getSqlMapClientTemplate().delete("contact.delete", c);
	}
}
