package resort.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import resort.controller.model.ResortData;
import resort.dao.ResortDao;
import resort.entity.Resort;

@Service
public class ResortService {

	@Autowired
	private ResortDao resortDao;

	@Transactional
	public ResortData saveResort(ResortData resortData) {
		Long resortId = resortData.getResortId();
		Resort resort = findOrCreateResort(resortId);
		copyResortFields(resort, resortData);

		return new ResortData(resortDao.save(resort));
	}

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

	private Resort findOrCreateResort(Long resortId) {
		Resort resort;

		if (Objects.isNull(resortId)) {
			resort = new Resort();
		} else {
			resort = findResortById(resortId);
		}
		return resort;
	}

	private Resort findResortById(Long resortId) {
		return resortDao.findById(resortId)
				.orElseThrow(() -> new NoSuchElementException("Resort with ID=" + resortId + " was not found.)"));
	}

}
