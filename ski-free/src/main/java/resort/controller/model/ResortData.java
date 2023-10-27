package resort.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import resort.entity.Resort;
import resort.entity.Run;
import resort.entity.Skier;

@Data
@NoArgsConstructor
public class ResortData {

	private Long resortId;
	private String resortName;
	private String resortAddress;
	private String resortCity;
	private String resortState;
	private String resortZip;
	private String resortWebsite;
	private String resortApres;

	private Set<ResortSkier> skiers = new HashSet<>();

	private Set<ResortRun> runs = new HashSet<>();

	public ResortData(Resort resort) {
		resortId = resort.getResortId();
		resortName = resort.getResortName();
		resortAddress = resort.getResortAddress();
		resortCity = resort.getResortCity();
		resortState = resort.getResortState();
		resortZip = resort.getResortState();
		resortWebsite = resort.getResortWebsite();
		resortApres = resort.getResortApres();

		for (Run run : resort.getRuns()) {
			runs.add(new ResortRun(run));
		}

		for (Skier skier : resort.getSkiers()) {
			skiers.add(new ResortSkier(skier));

		}
	}

}
