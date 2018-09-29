package com.multi.contactsapp.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.contactsapp.biz.dao.ContactDAO;
import com.multi.contactsapp.biz.domain.Contact;
import com.multi.contactsapp.biz.domain.ContactList;
import com.multi.contactsapp.biz.domain.ContactResult;

@Service
public class ContactService {

	@Autowired
	private ContactDAO contactDAO;

	public ContactList selectAll() {
		List<Contact> contacts = contactDAO.selectAll();
		ContactList contactList = new ContactList(0, 0, contacts.size(), contacts);
		return contactList;
	}

	public ContactList selectAll(int pageno, int pagesize) {
		List<Contact> contacts = contactDAO.selectAll(pageno, pagesize);
		int totalCount = contactDAO.selectCnt();
		ContactList contactList = new ContactList(pageno, pagesize, totalCount, contacts);
		return contactList;
	}

	public Contact selectOne(Contact c) {
		return contactDAO.selectOne(c);
	}

	public ContactResult insert(Contact c) {
		ContactResult result = new ContactResult("success", "OK", 0);
		try {
			
			int no = contactDAO.insert(c);
			result.setNo(no);
			
		} catch (Exception ex) {
			result.setStatus("fail");
			result.setMessage(ex.getLocalizedMessage());
		}
		
		return result;
	}
	
	public ContactResult update(Contact c) {
		ContactResult result = new ContactResult("success", "OK", 0);
		try {
			
			int count = contactDAO.update(c);
			result.setNo(c.getNo());
			result.setMessage("" + count + "건의 연락처 변경");
		} catch (Exception ex) {
			result.setStatus("fail");
			result.setMessage(ex.getLocalizedMessage());
		}
		
		return result;
	}
	
	public ContactResult delete(Contact c) {
		ContactResult result = new ContactResult("success", "OK", 0);
		try {
			
			int count = contactDAO.delete(c);
			result.setNo(c.getNo());
			result.setMessage("" + count + "건의 연락처 삭제");
		} catch (Exception ex) {
			result.setStatus("fail");
			result.setMessage(ex.getLocalizedMessage());
		}
		
		return result;
	}
}
