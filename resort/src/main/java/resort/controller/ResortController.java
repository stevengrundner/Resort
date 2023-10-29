package resort.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import resort.controller.model.ResortData;
import resort.controller.model.ResortRun;
import resort.controller.model.ResortSkier;
import resort.service.ResortService;

@RestController
@RequestMapping("/resort")
@Slf4j
public class ResortController {
	@Autowired
	private ResortService resortService;

	// RESORT INFORMATION

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

	@GetMapping("/resort")
	public List<ResortData> retrieveAllResorts() {
		log.info("Retrieve all Resorts called.");
		return resortService.retrieveAllResorts();
	}

	@GetMapping("/resort/{resortId}")
	public ResortData retrieveResortById(@PathVariable Long resortId) {
		log.info("Retrieving resort by ID={}", resortId);
		return resortService.retrieveResortById(resortId);
	}

	@DeleteMapping("/resort/{resortId}")
	public Map<String, String> deleteResortById(@PathVariable Long resortId) {
		log.info("Deleting a resort by ID={}", resortId);

		resortService.deleteResortById(resortId);

		return Map.of("message", "Deletion of resort with ID=" + resortId + " was successful");
	}

	// RUN INFORMATION

	@PostMapping("/resort/{resortId}/run")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResortRun insertResortRun(@PathVariable Long resortId, @RequestBody ResortRun resortRun) {
		log.info("Creating ski run {} for Ski Resort with ID={}", resortId, resortRun);
		return resortService.saveRun(resortId, resortRun);
	}

	// SKIER INFORMATION
	
	@PostMapping("resort/{resortId}/skier")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResortSkier insertResortSkier(@PathVariable Long resortId, @RequestBody ResortSkier resortSkier) {
		log.info("Creating skier {} for Resort ID={}", resortId, resortSkier);
		return resortService.saveSkier(resortId, resortSkier);
	}

	

}
