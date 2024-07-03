package com.example.controlsystemsample.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DTOUtil {
	public static Map<String, Object> convertToMap(Object dto) {
		Map<String, Object> map = new HashMap<>();
		for (Field field : dto.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(dto));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public static List<Map<String, Object>> convertToMapList(List<?> dtoList) {
		return dtoList.stream()
			.map(DTOUtil::convertToMap)
			.collect(Collectors.toList());
	}
}
