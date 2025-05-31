package web.projet.serveur;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity
public class Process extends ResourceUser {

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "pid", nullable = false)
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
