package com.multi.contactsapp.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.contactsapp.biz.domain.Contact;
import com.multi.contactsapp.biz.domain.ContactList;
import com.multi.contactsapp.biz.domain.ContactResult;
import com.multi.contactsapp.biz.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ContactList selectAll(
			@RequestParam(value="pageNo", required=false, defaultValue="0") int pageNo,
			@RequestParam(value="pageSize", required=false, defaultValue="2") int pageSize) { 
		
		ContactList contactList;
		
		if (pageNo == 0) {
			contactList = contactService.selectAll();
		} else {
			contactList = contactService.selectAll(pageNo, pageSize);
		}
		
		return contactList;
	}
	
	@RequestMapping(value="{no}", method=RequestMethod.GET)
	@ResponseBody
	public Contact selectOne(@PathVariable("no") int no) {
		
		Contact contact = new Contact();
		contact.setNo(no);
		Contact result = contactService.selectOne(contact);
		
		return result;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ContactResult insertContact(@RequestBody Contact c) { 
		
		ContactResult result = contactService.insert(c);
		
		return result;
	}
	
	@RequestMapping(value="{no}", method=RequestMethod.PUT)
	@ResponseBody
	public ContactResult updateContact(@RequestBody Contact c, @PathVariable("no") int no) { 
		
		c.setNo(no);
		ContactResult result = contactService.update(c);
		
		return result;
	}
	
	@RequestMapping(value="{no}", method=RequestMethod.DELETE)
	@ResponseBody
	public ContactResult deleteContact(@PathVariable("no") int no) { 
		
		Contact contact = new Contact();
		contact.setNo(no);
		ContactResult result = contactService.delete(contact);
		
		return result;
	}
}
