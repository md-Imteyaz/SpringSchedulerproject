package com.multitenant.feignService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.multitenant.model.Student;

@FeignClient(name = "student", url = "http://localhost:8089")
public interface FeignService {

	@GetMapping("/getStudent/all")
	List<Student> getAll(@RequestHeader(name = "X-TenantID") String XTenantId);

	@PostMapping(value = "/createStudent", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<HttpStatus> save(@RequestBody Student student,
			@RequestHeader(name = "X-TenantID") String XTenantId);
}
