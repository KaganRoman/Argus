package com.argus.exercise;

import org.springframework.web.bind.annotation.*;

@RestController
public class ExcersiceController {
        private String _currentValue;

        @RequestMapping(value="/api/resource", method=RequestMethod.POST)
        public void postResource(@RequestBody String payload) {
            _currentValue = payload;
        }

        @RequestMapping(value="/api/resource", method=RequestMethod.GET)
        public String getResource() {
            return _currentValue;
        }

}
