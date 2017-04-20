package cn.edu.bjtu.yb.restaurant.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	public void store(File dest, MultipartFile file);
	public void delete(String file);
}
