package resort.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Skier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long skierId;
	private String skierFirstName;
	private String skierLastName;

	//many skier's to many resorts
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "skiers", cascade = CascadeType.PERSIST)
	private Set<Resort> Resorts = new HashSet<>();
}
