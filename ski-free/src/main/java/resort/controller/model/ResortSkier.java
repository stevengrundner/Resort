package resort.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import resort.entity.Resort;
import resort.entity.Skier;

@Data
@NoArgsConstructor
public class ResortSkier {

	private Long skierId;
	private String skierFirstName;
	private String skierLastName;

	private Set<Resort> skiResorts = new HashSet<>();

	public ResortSkier(Skier skier) {
		skierId = skier.getSkierId();
		skierFirstName = skier.getSkierFirstName();
		skierLastName = skier.getSkierLastName();
	}
}
