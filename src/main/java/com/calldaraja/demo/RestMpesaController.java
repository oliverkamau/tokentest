package com.calldaraja.demo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@RestController
public class RestMpesaController {

    @Autowired
    MpesaRepo mpesaRepo;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/registerUrl")
    public String register(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ShortCode","600775");
        jsonObject.put("ResponseType","Confirmed");
        jsonObject.put("ConfirmationURL","https://830f348dcf1e.ngrok.io/confirmUrl");
        jsonObject.put("ValidationURL","https://830f348dcf1e.ngrok.io/validateUrl");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken());
        HttpEntity<String> request =
                new HttpEntity<>(jsonObject.toString(), headers);
     String url="https://sandbox.safaricom.co.ke/mpesa/c2b/v1/registerurl";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        return response.getBody();
    }
    @PostMapping("confirmUrl")
    public ResponseEntity<String> confirmUrl(@RequestBody String transDetails){
        System.out.println("Confirmation url.... "+transDetails);
        JSONObject jsonObject = new JSONObject(transDetails);
        C2BPaymentDetails c=new C2BPaymentDetails();
        c.setTransactionType(jsonObject.getString("TransactionType"));
        c.setTransID(jsonObject.getString("TransID"));
        c.setTransTime(jsonObject.getString("TransTime"));
        c.setTransAmount(jsonObject.getString("TransAmount"));
        c.setBusinessShortCode(jsonObject.getString("BusinessShortCode"));
        c.setBillRefNumber(jsonObject.getString("BillRefNumber"));
        c.setInvoiceNumber(jsonObject.getString("InvoiceNumber"));
        c.setOrgAccountBalance(jsonObject.getString("OrgAccountBalance"));
        c.setThirdPartyTransID(jsonObject.getString("ThirdPartyTransID"));
        c.setmSISDN(jsonObject.getString("MSISDN"));
        c.setFirstName(jsonObject.getString("FirstName"));
        c.setMiddleName(jsonObject.getString("MiddleName"));
        c.setLastName(jsonObject.getString("LastName"));
        mpesaRepo.save(c);
        return new ResponseEntity<String>("success", HttpStatus.OK);


    }
    @RequestMapping(value = "/validateUrl", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String validateUrl(@RequestBody String transactions){
        System.out.println("Validation url.... "+transactions);

        JSONObject jo = new JSONObject();
        jo.put("ResultCode",0);
        jo.put("ResultDesc", "Validation Successful");
        return jo.toString();

    }

    public String authToken(){
        String u="5qBZOKMtqhG2QG2G1ALTUCANs9kkMmx5";
        String p="PK5XnBGl3S20Xs4v";
        String credentials=u+":"+p;
        byte[] bytes=credentials.getBytes(StandardCharsets.ISO_8859_1);
        String auth= Base64.getEncoder().encodeToString(bytes);
        String authHeader = "Basic " + auth;
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("cache-control","no-cache");
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        String url="https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials";
        ResponseEntity<MpesaToken> response = restTemplate.exchange(url, HttpMethod.GET, request, MpesaToken.class);

        return Objects.requireNonNull(response.getBody()).getAccess_token();
    }
    @GetMapping("createTransaction")
    public String createTransaction() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ShortCode","600775");
        jsonObject.put("CommandID","CustomerPayBillOnline");
        jsonObject.put("Amount","100");
        jsonObject.put("Msisdn","254708374149");
        jsonObject.put("BillRefNumber","account");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken());
        HttpEntity<String> request =
                new HttpEntity<>(jsonObject.toString(),headers);

        String url="https://sandbox.safaricom.co.ke/mpesa/c2b/v1/simulate";


        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        return response.getBody();
    }
}
