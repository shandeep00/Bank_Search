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
	
	@RequestMapping("/getBranchByIfsc")
	public Branch getBranchDetails(@RequestParam("ifsc")String ifsc) throws Exception
	{
		return db.getBranchIFSC(ifsc);
	}
	
	@RequestMapping("/getBranchesByNameAndCity")
	public List<Branch> getBranches(@RequestParam("name")String name,@RequestParam("city")String city,@RequestParam("limit")int limit,@RequestParam("offset")int offset) throws Exception
	{
		return db.getBranchNameandCity(name, city, limit, offset);
	}
}
