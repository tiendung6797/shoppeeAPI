package shoppee.com.service;

import java.util.List;

import shoppee.com.entities.Size;

public interface SizeService {
	List<Size> getAllSize();
	boolean addSize(Size objSize);

}
