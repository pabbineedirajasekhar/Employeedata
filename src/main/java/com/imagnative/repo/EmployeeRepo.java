package com.imagnative.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.imagnative.bo.EmployeeBO;

@Repository
public interface EmployeeRepo extends MongoRepository<EmployeeBO, String> {
	
	EmployeeBO findByEmpidAndEnabledIsTrue(String docId);

}
