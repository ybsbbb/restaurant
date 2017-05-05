package cn.edu.bjtu.yb.restaurant.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/**
 * 有关文件存储的service接口
 * @author 杨博
 *
 */
public interface StorageService {

	/**
	 * 存储文件
	 * @param dest 目标文件
	 * @param file 源文件
	 */
	public void store(File dest, MultipartFile file);
	/**
	 * 删除指定文件
	 * @param file
	 */
	public void delete(String file);
}
