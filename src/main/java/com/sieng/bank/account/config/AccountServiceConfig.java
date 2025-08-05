package com.sieng.bank.account.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "account") // prefix in configuration
@Data
public class AccountServiceConfig {
	
	private String msg;
	private String buildVersion; // when write as properties (build-version)
	private Map<String, String> mailDetails;
	private List<String> activeBranches;

}
