package com.wangshijia.service.superAdmin;

import java.util.List;

import com.wangshijia.model.CapacityFocus;


public interface CapacityFocusService {
	List<CapacityFocus> getCapacityFocuses();
	void saveCapacityFocuses(List<CapacityFocus> CapacityFocus);
}
