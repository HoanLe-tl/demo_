package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Password")
    private String password;

    @Column(name = "Fullname")
    private String fullName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Admin")
    private boolean admin;

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                '}';
    }
}
