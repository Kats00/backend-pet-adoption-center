package com.rijai.LocationApi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Account {
    private String department;
    private Role role = Role.ADMIN;

    public Admin() {}

    public Admin(long id, String username, String password, String email, long cellphoneNum, String department) {
        super(id, username, password, email, cellphoneNum);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public Role getRole(){
        return role;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 79 * hash + Objects.hashCode(this.department);
        hash = 79 * hash + Objects.hashCode(this.role);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
    
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
    
        Admin admin = (Admin) obj;
    
        if (!Objects.equals(this.department, admin.department)) {
            return false;
        }
        return Objects.equals(this.role, admin.role);
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", department='").append(department).append('\'');
        sb.append(", role=").append(role);
        return sb.toString();
    }
    
}

