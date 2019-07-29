package com.shandeep.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class BankServices
{
	DatabaseConnection db = new DatabaseConnection();
	
	@RequestMapping("/get1/{ifsc}")
	public Branch getBranchDetails(@PathVariable("ifsc")String ifsc)
	{
		return db.getBranchIFSC(ifsc);
	}
	
	@RequestMapping("/get2/{name}/{city}")
	public List<Branch> getBranches(@PathVariable("name")String name,@PathVariable("city")String city,@RequestParam("limit")int limit,@RequestParam("offset")int offset)
	{
		return db.getBranchNameandCity(name, city, limit, offset);
	}
}
