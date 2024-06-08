package com.wangshijia.service.superAdmin;

import java.util.List;

import com.wangshijia.model.CapacityFocusCate;

public interface CapacityFocusCateService {
	List<CapacityFocusCate> getCapacityFocusCates();
	void saveCapacityFocusCates(List<CapacityFocusCate> CapacityFocusCates);
}
