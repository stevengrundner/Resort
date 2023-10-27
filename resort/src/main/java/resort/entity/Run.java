package resort.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Run {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long runId;
	private String resortName;
	private String runName;
	private String elevation;
	private String runRating;
	private String hikeToRunTime;
	private String hikeBackTime;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resort_id")
	private Resort resort;
}
