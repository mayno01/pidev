/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author tarek
 */
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.scene.control.Alert;

/**
 *
 * @author tarek
 */
public class MailSender {
    
        public static void sendPOST() throws IOException {
        final String USER_AGENT = "Mozilla/5.0";
         final String POST_URL = "http://127.0.0.1:8000/new";
        URL obj = new URL(POST_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        httpURLConnection.setDoOutput(true);
        OutputStream os = httpURLConnection.getOutputStream();
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = httpURLConnection.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Sucess");
		alert.setContentText("Formation added successfully, check your email!");

		alert.showAndWait();
            // print result
        
        } else {
            System.out.println("POST request not worked");
        }
    }

   
    
}
