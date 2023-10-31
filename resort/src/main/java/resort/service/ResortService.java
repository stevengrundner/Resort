package resort.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import resort.controller.model.ResortData;
import resort.controller.model.ResortRun;
import resort.controller.model.ResortSkier;
import resort.dao.ResortDao;
import resort.dao.RunDao;
import resort.dao.SkierDao;
import resort.entity.Resort;
import resort.entity.Run;
import resort.entity.Skier;

@Service
public class ResortService {

	@Autowired
	private ResortDao resortDao;

	@Autowired
	private RunDao runDao;

	@Autowired
	private SkierDao skierDao;

	// RESORT INFORMATION

	//saving a resort
	@Transactional
	public ResortData saveResort(ResortData resortData) {
		Long resortId = resortData.getResortId();
		Resort resort = findOrCreateResort(resortId);
		copyResortFields(resort, resortData);

		return new ResortData(resortDao.save(resort));
	}
	
	//copying all resort fields
	private void copyResortFields(Resort resort, ResortData resortData) {
		resort.setResortId(resortData.getResortId());
		resort.setResortName(resortData.getResortName());
		resort.setResortAddress(resortData.getResortAddress());
		resort.setResortCity(resortData.getResortCity());
		resort.setResortState(resortData.getResortState());
		resort.setResortZip(resortData.getResortZip());
		resort.setResortWebsite(resortData.getResortWebsite());
		resort.setResortApres(resortData.getResortApres());
	}
	
	//finding or creating a resort
	private Resort findOrCreateResort(Long resortId) {
		Resort resort;

		if (Objects.isNull(resortId)) {
			resort = new Resort();
		} else {
			resort = findResortById(resortId);
		}
		return resort;
	}

	//finding a resort by it's ID
	private Resort findResortById(Long resortId) {

		return resortDao.findById(resortId)
				.orElseThrow(() -> new NoSuchElementException("Resort with ID=" + resortId + " was not found.)"));
	}

	//retrieving all resorts
	@Transactional
	public List<ResortData> retrieveAllResorts() {
		List<ResortData> result = new LinkedList<>();
		List<Resort> resorts = resortDao.findAll();

		for (Resort resort : resorts) {
			ResortData rd = new ResortData(resort);

			rd.getSkiers().clear();
			rd.getRuns().clear();
			result.add(rd);
		}
		return result;
	}

	//retrieving a resort by its ID
	@Transactional
	public ResortData retrieveResortById(Long resortId) {
		Resort resort = resortDao.findById(resortId)
				.orElseThrow(() -> new NoSuchElementException("Resort ID=" + resortId + " does not exist."));

		return new ResortData(resort);
	}

	public void deleteResortById(Long resortId) {
		Resort resort = findResortById(resortId);
		resortDao.delete(resort);
	}

	// RUN INFORMATION

	//saving a run
	@Transactional(readOnly = false)
	public ResortRun saveRun(Long resortId, ResortRun resortRun) {
		Resort resort = findResortById(resortId);
		Long runId = resortRun.getRunId();
		Run run = findOrCreateRun(resortId, runId);
		copyRunFields(run, resortRun);
		run.setResort(resort);
		resort.getRuns().add(run);

		return new ResortRun(runDao.save(run));
	}

	//finding or creating a run
	private Run findOrCreateRun(Long resortId, Long runId) {
		Run run;

		if (Objects.isNull(runId)) {
			run = new Run();

		} else {

			run = findRunById(resortId, runId);
		}
		return run;
	}

	//finding a run by it's ID
	private Run findRunById(Long resortId, Long runId) {
		Run run = runDao.findById(runId)
				.orElseThrow(() -> new NoSuchElementException("Run ID = " + runId + " does not exist."));
		if (run.getResort().getResortId() != resortId) {
			throw new IllegalArgumentException("Run ID= " + runId + " does not exist at Resort ID=" + resortId);
		}
		return run;
	}

	//coping all fields of a run
	private void copyRunFields(Run run, ResortRun resortRun) {
		run.setRunId(resortRun.getRunId());
		run.setResortName(resortRun.getResortName());
		run.setRunName(resortRun.getRunName());
		run.setElevation(resortRun.getElevation());
		run.setRunRating(resortRun.getRunRating());
		run.setHikeToRunTime(resortRun.getHikeToRunTime());
		run.setHikeBackTime(resortRun.getHikeBackTime());
	}

	// SKIER INFORMATION

	//saving a skier's information
	@Transactional(readOnly = false)
	public ResortSkier saveSkier(Long resortId, ResortSkier resortSkier) {
		Resort resort = findResortById(resortId);
		Long skierId = resortSkier.getSkierId();

		Skier skier = findOrCreateSkier(resortId, skierId);
		copySkierFields(skier, resortSkier);

		skier.getResorts().add(resort);
		resort.getSkiers().add(skier);

		return new ResortSkier(skierDao.save(skier));
	}

	//coping all fields of a skier
	private void copySkierFields(Skier skier, ResortSkier resortSkier) {
		skier.setSkierId(resortSkier.getSkierId());
		skier.setSkierFirstName(resortSkier.getSkierFirstName());
		skier.setSkierLastName(resortSkier.getSkierLastName());
	}

	//finding or creating a skier
	private Skier findOrCreateSkier(Long resortId, Long skierId) {
		Skier skier;

		if (Objects.isNull(skierId)) {

			skier = new Skier();

		} else {

			skier = findSkierById(resortId, skierId);
		}
		return skier;
	}

	//finding a skier by ID information
	private Skier findSkierById(Long resortId, Long skierId) {
		Skier skier = skierDao.findById(skierId)
				.orElseThrow(() -> new NoSuchElementException("Skier ID = " + skierId + " does not exist."));
		if (((ResortData) skier.getResorts()).getResortId() != resortId) {
			throw new IllegalArgumentException("Skier ID= " + skierId + " does not ski at Ski Resort ID= " + resortId);
		}
		return skier;
	}

}
