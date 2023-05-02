package com.manu.listening;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.manu.model.Points;
import com.manu.services.ApproximationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {

    private ApproximationService service;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("points/solve")
    @ResponseBody
    public ObjectNode solve(@RequestBody Points points) {
        service = new ApproximationService(points);

        return service.processData();
    }

}
