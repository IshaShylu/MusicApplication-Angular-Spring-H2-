package com.musicbeats.webservices.controller;

import java.util.HashMap;
import java.util.Map;

//import org.springframework.http.HttpStatus;
//simport org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.musicbeats.webservices.model.OTPSystem;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OTPSystemRestController {
	private Map<String,OTPSystem> otp_data=new  HashMap<>();
	private final static String ACCOUNT_ID="AC4087ee34da71235c797314739737cb8f";
	private final static String AUTH_ID="8d574cf13972a3ae03693f1c97aa6789";
	static{
		Twilio.init(ACCOUNT_ID,AUTH_ID);
	}
	@PostMapping(value="/mobilenumbers/{mobilenumber}")
	public boolean sendOTP(@PathVariable("mobilenumber") String mobilenumber){
		OTPSystem otpSystem=new OTPSystem();
		otpSystem.setMobilenumber(mobilenumber);
		otpSystem.setOtp(String.valueOf(((int)(Math.random()*(10000-1000)))+1000));
		otpSystem.setExpirytime(System.currentTimeMillis()+20000);
		otp_data.put(mobilenumber,otpSystem);
		Message.creator(new PhoneNumber(mobilenumber),new PhoneNumber("+16105578708"),"Your OTP is"+otpSystem.getOtp()).create();
		return true;
		//return new ResponseEntity<>("OTP is sent successfully",HttpStatus.OK);
	}
	
	@RequestMapping(value="/mobilenumbers/{mobilenumber}",method=RequestMethod.PUT)
	public boolean verifyOTP(@PathVariable("mobilenumber") String mobilenumber,@RequestBody OTPSystem requestBodyOTPSystem){
		
		if(requestBodyOTPSystem.getOtp()==null || requestBodyOTPSystem.getOtp().trim().length()==0){
			return false;
			//return new String("Please enter OTP");
		}
		if(otp_data.containsKey(mobilenumber)){
			OTPSystem otpsystem =otp_data.get(mobilenumber);
			if(otpsystem!=null){
				if(otpsystem.getExpirytime()>=System.currentTimeMillis()){
					if(requestBodyOTPSystem.getOtp().equals(otpsystem.getOtp())){
						otp_data.remove(mobilenumber);
						return true;
						//return new String("OTP successfully verified");
					}
					return false;
					//return new String("Invalid OTP");
				}
				return false;
				//return new String("OTP expired");
			}
			return false;
			//return new String("Oops!Something went wrong");
		}
		return false;
		//return new String("Mobile number not found");
	}
}

