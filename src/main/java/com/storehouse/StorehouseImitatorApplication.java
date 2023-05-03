package com.storehouse;

import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.storehouse.model.FullnessMeasurement;
import com.storehouse.service.FullnessMeasurementImitator;

@SpringBootApplication
public class StorehouseImitatorApplication {
	
	@Autowired
	FullnessMeasurementImitator imitator;

	public static void main(String[] args) {
		SpringApplication.run(StorehouseImitatorApplication.class, args);
	}
	
	@Bean
	Supplier<FullnessMeasurement> fullnessMeasurementSupplier() {
		return imitator::nextMeasurement;
	}

}
