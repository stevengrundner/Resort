package resort.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Resort {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long resortId;
	private String resortName;
	private String resortAddress;
	private String resortCity;
	private String resortState;
	private String resortZip;
	private String resortWebsite;
	private String resortApres;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "resort_skier", joinColumns = @JoinColumn(name = "resort_id"), inverseJoinColumns = @JoinColumn(name = "skier_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Skier> skiers = new HashSet<>();

	@OneToMany(mappedBy = "resort", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Run> Runs = new HashSet<>();

}
