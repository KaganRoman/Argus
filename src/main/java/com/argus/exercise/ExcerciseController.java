package com.argus.exercise;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExcerciseController {
        private String _currentValue;

        @Value("${mirror}")
        private String _mirror;

        @RequestMapping(value="/api/resource", method=RequestMethod.POST)
        public void postResource(@RequestBody String payload) {
            if((_currentValue == null && payload != null) || !_currentValue.equals(payload)) {
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
                var url = "http://" + _mirror + "/api/resource";
                new RestTemplate().postForObject(url, _currentValue, String.class);
            }catch(Exception e){
                System.out.println(e);
            }
        }
}
