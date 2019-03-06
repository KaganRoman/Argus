package com.argus.exercise;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExcersiceController {
        private String _currentValue;

        @Value("${mirror}")
        private String _mirror;

        @RequestMapping(value="/api/resource", method=RequestMethod.POST)
        public void postResource(@RequestBody String payload) {
            if(_currentValue != payload) {
                _currentValue = payload;
                updateMirror();
            }
        }

        @RequestMapping(value="/api/resource", method=RequestMethod.GET)
        public String getResource() {
            return _currentValue;
        }

        private void updateMirror() {
            if(_mirror == null) return;
            try
            {
                URL url = new URL("http://" + _mirror + "/api/resource");
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                httpCon.setDoOutput(true);
                httpCon.setRequestMethod("POST");
                OutputStream os = httpCon.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
                osw.write(_currentValue);
                osw.flush();
                osw.close();
                os.close();
                httpCon.connect();
            }catch(MalformedURLException e){
            }catch(IOException e){
            }catch(Exception e){
            }
        }
}
