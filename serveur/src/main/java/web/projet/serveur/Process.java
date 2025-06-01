package web.projet.serveur;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity
public class Process extends ResourceUser {

    @Column(name = "name", nullable = true)
    private String name;
    
    @Column(name = "pid", nullable = true)
    private Integer pid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    
}
