package web.projet.serveur;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Memberships")
public class Membership {
        @Id
        @Column(name = "id", nullable = false, unique = true)
        private Long id;

        @Column(name = "username", nullable = false)
        private String username;

        @Column(name = "idGroup", nullable = false)
        private Long idGroup;

        @Column(name = "role", nullable = false)
        private String role;

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public Long getIdGroup() {
            return idGroup;
        }
        public void setIdGroup(Long idGroup) {
            this.idGroup = idGroup;
        }
        public String getRole() {
            return role;
        }
        public void setRole(String role) {
            this.role = role;
        }
}
