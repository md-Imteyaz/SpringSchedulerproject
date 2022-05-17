package com.multitenant.schedulerT1toT2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.multitenant.feignService.FeignService;
import com.multitenant.model.Student;


@Component
public class T1toT2Scheduler {

	@Autowired
	FeignService feign;
	@Scheduled(fixedDelay = 6000)
	public void schedulingtest() {
		System.out.println("scheduler called"); 
		List<Student> studentlist=feign.getAll("tenant1");
		System.out.println("studentsize"+studentlist.size());
		if(studentlist!=null && studentlist.size()>0) {
			studentlist.stream().forEach(stu->{
			System.out.println("studentsize"+stu.getName());
			feign.save(stu, "tenant2");
			});
		}
	}
}
