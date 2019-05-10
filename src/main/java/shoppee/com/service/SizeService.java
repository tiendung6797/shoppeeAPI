package shoppee.com.service;

import java.util.List;

import shoppee.com.entities.Size;

public interface SizeService {
	List<Size> getAllSize();
	List<Size> getSizeByProId(int proId);
	boolean addSize(Size objSize);
	boolean updateSize(int quantity, int size_id);
	int updateQuantity(Integer pro_id, String size_name);
}
