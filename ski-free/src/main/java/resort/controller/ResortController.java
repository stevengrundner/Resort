package resort.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import resort.controller.model.ResortData;
import resort.service.ResortService;

@RestController
@RequestMapping("/resort")
@Slf4j
public class ResortController {
	@Autowired
	private ResortService resortService;

	@PostMapping("/resort")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResortData insertResortData(@RequestBody ResortData resortData) {
		log.info("Creating Resort {}", resortData);
		return resortService.saveResort(resortData);
	}

	@PutMapping("/resort/{resortId}")
	public ResortData updateResortData(@PathVariable Long resortId, @RequestBody ResortData resortData) {
		resortData.setResortId(resortId);
		log.info("Updating Resort Id {}", resortData);
		return resortService.saveResort(resortData);
	}

}