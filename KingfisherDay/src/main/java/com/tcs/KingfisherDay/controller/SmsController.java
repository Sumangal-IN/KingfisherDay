package com.tcs.KingfisherDay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class SmsController {

	@RequestMapping(value = "/sendOTP/{mobile}/{otp}", method = RequestMethod.GET, produces = "application/text")
	@ResponseBody
	public String updateFlag(@PathVariable("mobile") String mobile, @PathVariable("otp") String otp) {
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> result = restTemplate.getForEntity("http://api.msg91.com/api/sendhttp.php?authkey=130190AmlXD2ELBEmi581b5034&mobiles=91"
				+ mobile
				+ "&message="
				+ otp
				+ "&sender=KFSDAY&route=4&country=91", String.class);
		return result.getBody();
	}

}
