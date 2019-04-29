package shoppee.com.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import shoppee.com.entities.File;
import shoppee.com.entities.Product;
import shoppee.com.entities.Size;
import shoppee.com.payload.UploadFileResponse;
import shoppee.com.service.FileStorageService;
import shoppee.com.service.impl.ProductServiceImpl;
import shoppee.com.service.impl.SizeSeviceImpl;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/size")
public class SizeController {
	@Autowired
	SizeSeviceImpl sizeSeviceImpl;
	
	@Autowired
	ProductServiceImpl productService;
	
	@Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, Product product) {
        File dbFile = fileStorageService.storeFile(file, product);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getFile_id())
                .toUriString();

        return new UploadFileResponse(dbFile.getFile_name(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	
	
	/*
	 * get all size
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/all", method = RequestMethod.GET) 
	public ResponseEntity<List<Size>> getAllProduct() {
		List<Size> listSize = sizeSeviceImpl.getAllSize();
		if (listSize.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Size>>(listSize, HttpStatus.OK);
	}
	
	/*
	 * add size
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/add", method = RequestMethod.POST) 
	public ResponseEntity addSize(@RequestParam("size-quantity") Integer[] listQuantity,
			@RequestParam("sizeType") String sizeType,
			@RequestParam("proId") int proId, 
			@RequestParam("files") MultipartFile[] files) {
	
		Product product = productService.getProductById(proId);
		
		if (listQuantity.length == 0) {
			return new ResponseEntity("Đã có lỗi xảy ra", HttpStatus.NO_CONTENT);
		}
		
		
		if ("freeSize".equalsIgnoreCase(sizeType)) {
			for (Integer quantity : listQuantity) {
				Size objSize = new Size(0, "free size", quantity, product);
				sizeSeviceImpl.addSize(objSize);
				break;
			}
			
		} else {
			if ("charSize".equalsIgnoreCase(sizeType)) {
				String[] listCharSize = {"S", "M", "L", "XL", "XXL"};
				
				for (int i = 0; i < listCharSize.length; i++) {
					Size objSize = new Size(0, listCharSize[i], listQuantity[i], product);
					sizeSeviceImpl.addSize(objSize);
				}
				
			} else {
				if ("numberSize".equalsIgnoreCase(sizeType)) {
					String[] listNumberSize = {"28", "29", "30", "31", "32"};
					for (int i = 0; i < listNumberSize.length; i++) {
						Size objSize = new Size(0, listNumberSize[i], listQuantity[i], product);
						sizeSeviceImpl.addSize(objSize);
					}
				}
			}
		}
		
		// upload file
		Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file, product))
                .collect(Collectors.toList());
		return new ResponseEntity("Thêm sản phẩm thành công!", HttpStatus.OK);
		//return new ResponseEntity("Đã có lỗi xảy ra", HttpStatus.NO_CONTENT);
	}
}
