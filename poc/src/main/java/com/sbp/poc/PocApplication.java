package com.sbp.poc;

import com.sbp.poc.singleton.SingletonApproach1;
import com.sbp.poc.singleton.SingletonApproach2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PocApplication {
	private static Logger Log = LoggerFactory.getLogger(PocApplication.class);

	public static void main(String[] args) {
		Log.info("Proof of concept");
		SpringApplication.run(PocApplication.class, args);
	}

}
