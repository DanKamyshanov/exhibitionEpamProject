package com.epam.exhibitions.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

public class VerifyReCaptcha {
    private static final Logger logger = Logger.getLogger(VerifyReCaptcha.class);

    public static final String SITE_VERIFY = "https://www.google.com/recaptcha/api/siteverify";
    private static final String SITE_KEY = "6LfKrIAkAAAAAJdpYcMuK0Lc16WoJK_1di15543q";

    public static boolean verify(String gRecaptchaResponse){
        if(gRecaptchaResponse == null || gRecaptchaResponse.length() == 0){
            return false;
        }

        try{
            URL verifyUrl = new URL(SITE_VERIFY);
            HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String postParams = "secret=" + SITE_KEY + "&response=" + gRecaptchaResponse;
            conn.setDoOutput(true);
            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());
            outStream.flush();
            outStream.close();

            int responseCode = conn.getResponseCode();
            logger.warn("Response code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while((line = in.readLine()) != null){
                sb.append(line);
            }
            in.close();
            Gson gson = new Gson();
            logger.warn("JSON: " + sb);
            JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
            boolean success = jsonObject.get("success").getAsBoolean();
            logger.warn("Verified: " + success);
            return success;
        } catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            return false;
        }
    }
}
