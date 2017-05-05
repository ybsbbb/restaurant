package cn.edu.bjtu.yb.restaurant.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.bjtu.yb.restaurant.service.StorageService;

/**
 * 文件存储的具体实现
 * @author 杨博
 *
 */
@Component
public class FileStorageServiceImpl implements StorageService {

	@Override
	public void store(File dest, MultipartFile file) {
		if(file.isEmpty()) {
			return;
		}
		
		if(!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String file) {

	}

}
