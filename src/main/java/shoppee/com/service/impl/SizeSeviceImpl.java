package shoppee.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppee.com.entities.Size;
import shoppee.com.repository.SizeRepository;
import shoppee.com.service.SizeService;

@Service
public class SizeSeviceImpl implements SizeService {
	
	@Autowired
	SizeRepository sizeRepository;
	
	@Override
	public List<Size> getAllSize() {
		return sizeRepository.findAll();
	}

	@Override
	public boolean addSize(Size objSize) {
		Size size = sizeRepository.save(objSize);
		if(size != null){
			return true;
		}
		return false;
	}

	
}
