package com.storehouse.service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.storehouse.model.ContainerDto;
import com.storehouse.model.ContainerMeasurementDto;

@Service
public class FullnessMeasurementImitatorImpl implements FullnessMeasurementImitator {
	
	@Value("${app.amount.containers: 10}")
	int nContainers;
	@Value("${app.min.percent: 0}")
	int minPercent;
	@Value("${app.max.percent: 100}")
	int maxPercent;
	ThreadLocalRandom tlr = ThreadLocalRandom.current();
	HashMap<Integer, Integer> containersStatus = new HashMap<>();
	
	@Override
	public ContainerMeasurementDto nextMeasurement() {
		ContainerDto container = getContainer(tlr.nextInt(1, nContainers));
		int status = getStatus(container.number);
		containersStatus.put(container.number, status);
		return new ContainerMeasurementDto(container, status);
	}

	private int getStatus(int number) {
		Integer value = containersStatus.get(number);
		return value == null ? tlr.nextInt(minPercent, maxPercent) : tlr.nextInt(minPercent, containersStatus.get(number));
	}

	private ContainerDto getContainer(int number) {
		String[] products = {"Apples", "Oranges", "Onions", "Milk", "Oil", "Eggs", "Cheese", "Bread", "Meat", "Fish"};
		String[] units= {"Kilo", "Kilo", "Kilo", "Bottles", "Bottles", "Units", "Packs", "Packs", "Kilo", "Kilo"};
		int[] capacities = {1000, 1000, 1000, 800, 700, 5000, 300, 600, 900, 600};
		ContainerDto res = new ContainerDto(number, products[number - 1], units[number - 1], capacities[number - 1]);
		return res;
	}

}
