package com.calldaraja.demo;

import okhttp3.*;
import okhttp3.RequestBody;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class DarajaController {
     @Autowired
     MpesaRepo mpesaRepo;

    @Autowired
    RestTemplate template;

    @Autowired
    RestTemplate restTemplate;



      @GetMapping("/getHttpToken")
      public String registerUrl() throws IOException {
          OkHttpClient client = new OkHttpClient();

          MediaType mediaType = MediaType.parse("application/json");
          JSONObject jsonObject = new JSONObject();
          jsonObject.put("ShortCode","600610");
          jsonObject.put("ResponseType","Confirmed");
          jsonObject.put("ConfirmationURL","http://192.168.43.145:8080/confirmUrl");
          jsonObject.put("ValidationURL","http://192.168.43.145:8080/validateUrl");
          RequestBody body = RequestBody.create(jsonObject.toString(),mediaType);
          Request request = new Request.Builder()
                  .url("https://sandbox.safaricom.co.ke/mpesa/c2b/v1/registerurl")
                  .post(body)
                  .addHeader("authorization", "Bearer "+getHttpToken())
                  .addHeader("content-type", "application/json")
                  .build();

          Response response = client.newCall(request).execute();
          return response.body().string();
      }
       public String getHttpToken() throws IOException {
     Response http=getHeaders();
     http.body();
          JSONObject jsonObject=new JSONObject(Objects.requireNonNull(http.body()).string());
     return jsonObject.getString("access_token");
   }
     Response getHeaders() throws IOException {

        String u="5qBZOKMtqhG2QG2G1ALTUCANs9kkMmx5";
        String p="PK5XnBGl3S20Xs4v";
        String credentials=u+":"+p;
        byte[] bytes=credentials.getBytes(StandardCharsets.ISO_8859_1);
        String auth= Base64.getEncoder().encodeToString(bytes);
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                .get()
                .addHeader("authorization","Basic "+auth)
                .addHeader("cache-control","no-cache")
                .build();

        return client.newCall(request).execute();
    }
    //@GetMapping("/confirmUrl")
   // public String confirmUrl(String request) throws IOException {
      //  OkHttpClient client = new OkHttpClient();

       // MediaType mediaType = MediaType.parse("application/json");
       // JSONObject jsonObject = new JSONObject();
       // jsonObject.put("ResultCode",0);
       // jsonObject.put("ResultDesc","Validation of Transaction Successful");

        //RequestBody body = RequestBody.create(jsonObject.toString(),mediaType);
        //Request request = new Request.Builder()
            //    .url("https://sandbox.safaricom.co.ke/mpesa/c2b/v1/simulate")
             //   .post(body)
             //   .addHeader("authorization", "Bearer "+getHttpToken())
              //  .addHeader("content-type", "application/json")
              //  .build();

        //Response response = client.newCall(request).execute();
        //JSONObject obj=new JSONObject(request);
       // MpesaCredentials m=new MpesaCredentials();
       // m.setFirstName(obj.getString("TransID"));
       // m.setAmmount(obj.getString("TransAmount"));
       // m.setReference(obj.getString("BillRefNumber"));
       // m.setPhoneNumber(obj.getString("MSISDN"));
        //m.setBalance(obj.getString("BusinessShortCode"));
       // mpesaRepo.save(m);
       // return jsonObject.toString();

    //}
    //@GetMapping("createTransaction")
    public String createTransaction() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ShortCode","600610");
        jsonObject.put("CommandID","CustomerPayBillOnline");
        jsonObject.put("Amount","100");
        jsonObject.put("Msisdn","254708374149");
        jsonObject.put("BillRefNumber","account");
        RequestBody body = RequestBody.create(jsonObject.toString(),mediaType);
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/c2b/v1/simulate")
                .post(body)
                .addHeader("authorization", "Bearer "+getHttpToken())
                .addHeader("content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public String encryptInitiatorPassword(String securityCertificate, String password) {
        String encryptedPassword = "gabu@dev001";
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            byte[] input = password.getBytes();

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            FileInputStream fin = new FileInputStream(new File(securityCertificate));
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) cf.generateCertificate(fin);
            PublicKey pk = certificate.getPublicKey();
            cipher.init(Cipher.ENCRYPT_MODE, pk);

            byte[] cipherText = cipher.doFinal(input);

            // Convert the resulting encrypted byte array into a string using base64 encoding
            encryptedPassword = Base64.getEncoder().encodeToString(cipherText);

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.print(encryptedPassword);
        return encryptedPassword;
    }
   // @GetMapping("validateUrl" )
    //public String validateUrl(String request) throws IOException {
       // OkHttpClient client = new OkHttpClient();

       // MediaType mediaType = MediaType.parse("application/json");
        //JSONObject jsonObject = new JSONObject();
        //jsonObject.put("ResultCode",0);
        //jsonObject.put("ResultDesc","Validation of Transaction Successful");


       // RequestBody body = RequestBody.create(jsonObject.toString(),mediaType);
       // Request request = new Request.Builder()
            //    .url("https://sandbox.safaricom.co.ke/mpesa/c2b/v1/simulate")
              //  .post(body)
              //  .addHeader("authorization", "Bearer "+getHttpToken())
               // .addHeader("content-type", "application/json")
               // .build();
        //JSONObject obj=new JSONObject(request);
       // MpesaCredentials m=new MpesaCredentials();
       // m.setFirstName(obj.getString("TransID"));
        //m.setAmmount(obj.getString("TransAmount"));
        //m.setReference(obj.getString("BillRefNumber"));
       // m.setPhoneNumber(obj.getString("MSISDN"));
        //m.setBalance(obj.getString("BusinessShortCode"));
        //mpesaRepo.save(m);
       // return jsonObject.toString();

    //}

}
