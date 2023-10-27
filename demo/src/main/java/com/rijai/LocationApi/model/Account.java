package com.rijai.LocationApi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String username;
    protected String password;
    protected String email;
    protected long cellphoneNum;

    public Account () {}

    public Account (long id, String username, String password, String email, long cellphoneNum) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.cellphoneNum = cellphoneNum;
    }

    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public long getCellphoneNum () {
        return cellphoneNum;
    }

    public void setCellphoneNum (long cellphoneNum) {
        this.cellphoneNum = cellphoneNum;
    }

    public abstract Role getRole();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.username);
        hash = 79 * hash + Objects.hashCode(this.password);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.cellphoneNum);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.cellphoneNum, other.cellphoneNum)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", cellphoneNum=").append(cellphoneNum);
        sb.append('}');
        return sb.toString();
    }
}
