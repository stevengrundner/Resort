package resort.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import resort.entity.Run;

@Data
@NoArgsConstructor
public class ResortRun {

	private Long runId;
	private Long resortId;
	private String resortName;
	private String runName;
	private String elevation;
	private String runRating;
	private String hikeToRunTime;
	private String hikeBackTime;

	public ResortRun(Run run) {
		runId = run.getRunId();
		resortName = run.getResortName();
		runName = run.getRunName();
		elevation = run.getElevation();
		runRating = run.getRunRating();
		hikeToRunTime = run.getHikeToRunTime();
		hikeBackTime = run.getHikeBackTime();
	}
}
