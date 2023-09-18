package com.dongyang.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dongyang.core.common.CoReCommonRoot;
import com.dongyang.core.domain.CoReDomainRoot;
import com.dongyang.core.external.CoReExternalRoot;

@SpringBootApplication(scanBasePackageClasses = {
	CoReDomainRoot.class,
	CoReCommonRoot.class,
	CoReExternalRoot.class,
	CoReApplication.class
})
public class CoReApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoReApplication.class, args);
	}

}
