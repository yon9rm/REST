package com.multi.contactsapp.view.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.multi.contactsapp.biz.domain.Contact;
import com.multi.contactsapp.biz.domain.ContactList;
import com.multi.contactsapp.biz.domain.ContactResult;
import com.multi.contactsapp.biz.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@Resource(name="xmlView")
	private View xmlView;
	
	@Resource(name="jsonView")
	private View jsonView;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView selectAll(
			@RequestParam(value="pageNo", required=false, defaultValue="0") int pageNo,
			@RequestParam(value="pageSize", required=false, defaultValue="2") int pageSize) { 
		
		ContactList contactList;
		
		if (pageNo == 0) {
			contactList = contactService.selectAll();
		} else {
			contactList = contactService.selectAll(pageNo, pageSize);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setView(jsonView);
		mav.addObject("data", contactList);
		
		return mav;
	}
	
	@RequestMapping(value="{no}", method=RequestMethod.GET)
	public ModelAndView selectOne(@PathVariable("no") int no) {
		
		Contact contact = new Contact();
		contact.setNo(no);
		Contact result = contactService.selectOne(contact);
		
		ModelAndView mav = new ModelAndView();
		mav.setView(xmlView);
		mav.addObject("data", result);
		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView insertContact(Contact c) { 
		
		ContactResult result = contactService.insert(c);
		
		ModelAndView mav = new ModelAndView();
		mav.setView(jsonView); 
		mav.addObject("data", result);
		
		return mav;
	}
	
	@RequestMapping(value="{no}", method=RequestMethod.PUT)
	public ModelAndView updateContact(@RequestBody Contact c, @PathVariable("no") int no) { 
		
		c.setNo(no);
		ContactResult result = contactService.update(c);
		
		ModelAndView mav = new ModelAndView();
		mav.setView(xmlView); 
		mav.addObject("data", result);
		
		return mav;
	}
	
	@RequestMapping(value="{no}", method=RequestMethod.DELETE)
	public ModelAndView deleteContact(@PathVariable("no") int no) { 
		
		Contact contact = new Contact();
		contact.setNo(no);
		ContactResult result = contactService.delete(contact);
		
		ModelAndView mav = new ModelAndView();
		mav.setView(xmlView); 
		mav.addObject("data", result);
		
		return mav;
	}
}
