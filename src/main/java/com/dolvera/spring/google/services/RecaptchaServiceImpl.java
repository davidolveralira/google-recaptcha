package com.dolvera.spring.google.services;

import com.dolvera.spring.google.models.response.RecaptchaResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaServiceImpl implements RecaptchaService{

    private static final String GOOGLE_RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";

    private final String RECAPTCHA_SECRET = "6LfKJeAmAAAAAAxaZgRToYjrvj8b3Z9r09jdx6U2";

    @Override
    public boolean validateCaptcha(String captcha){
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("secret", RECAPTCHA_SECRET);
        requestMap.add("response", captcha);

        RecaptchaResponse apiResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_ENDPOINT, requestMap, RecaptchaResponse.class);
        if(apiResponse == null){
            return false;
        }

        return Boolean.TRUE.equals(apiResponse.getSuccess());
    }

}
