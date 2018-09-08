package com.tcs.KingfisherDay.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tcs.KingfisherDay.model.Employee;
import com.tcs.KingfisherDay.service.UserService;

@Controller
public class UserController {

	private static final int IMG_WIDTH = 120;
	private static final int IMG_HEIGHT = 160;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/registerEmployee/{name}/{emailID}/{foodPreference}/{password}/{mobile}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Employee registerEmployee(@PathVariable("name") String name, @PathVariable("emailID") String emailID,
			@PathVariable("foodPreference") String foodPreference, @PathVariable("password") String password,
			@PathVariable("mobile") String mobile, @RequestParam("photoFile") MultipartFile photoFile) {
		String photo = null;
		if (photoFile.isEmpty()) {
			return null;
		}
		try {
			byte[] bytes = photoFile.getBytes();
			photo = resizeImage(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return userService.register(name, emailID, foodPreference, password, mobile, photo);
	}

	private static String resizeImage(byte[] image) throws IOException {
		BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(image));
		int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(resizedImage, "jpg", bos);
		return Base64Utils.encodeToString(bos.toByteArray());
	}

	@RequestMapping(value = "/getEmployee/{emailID}/{password}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Employee getEmployee(@PathVariable("emailID") String emailID, @PathVariable("password") String password) {
		return userService.getEmployee(emailID, password);
	}

	@RequestMapping(value = "/isExistsEmployee/{emailID}/{mobile}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Boolean isExistsEmployee(@PathVariable("emailID") String emailID, @PathVariable("mobile") String mobile) {
		return userService.isExistsEmployee(emailID, mobile);
	}

}
