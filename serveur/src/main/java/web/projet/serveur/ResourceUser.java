package web.projet.serveur;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ResourceUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "resourceUser")
    @JsonIgnore
    private Collection<ResourceUsage> resourceUsages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<ResourceUsage> getResourceUsages() {
        return resourceUsages;
    }

    public void setResourceUsages(Collection<ResourceUsage> resourceUsages) {
        this.resourceUsages = resourceUsages;
    }

}
