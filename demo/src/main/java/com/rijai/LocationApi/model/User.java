package com.rijai.LocationApi.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

@Entity
@DiscriminatorValue("USER")
public class User extends Account {
    private String firstName;
    private String lastName;
    private Role role = Role.USER;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Dog> dogs = new ArrayList<>();

    public User() {}

    public User(long id, String username, String password, String email, long cellphoneNum, String firstName, String lastName) {
        super(id, username, password, email, cellphoneNum);
        this.firstName = firstName;
        this.lastName = lastName;
        
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    @Override
    public Role getRole(){
        return role;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 79 * hash + Objects.hashCode(this.firstName);
        hash = 79 * hash + Objects.hashCode(this.lastName);
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
    
        User user = (User) obj;
    
        if (!Objects.equals(this.firstName, user.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, user.lastName)) {
            return false;
        }
        return Objects.equals(this.role, user.role);
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", role=").append(role);
        return sb.toString();
    }
    
}
