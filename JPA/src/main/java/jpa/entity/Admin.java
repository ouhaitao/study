package jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author parry
 * @date 2019/11/08
 */
@Data
@Entity
@Table(name = "admin2",schema = "test")
public class Admin implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(generator = "id_generator", strategy = GenerationType.AUTO)
//    @GenericGenerator(name = "id_generator", strategy = "native")
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "age")
    private Integer age;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "permissions")
    private String permissions;
    
    @Column(name = "username")
    private String username;
    
    @Override
    public String toString() {
        return "Admin{" +
            "id=" + id +
            ", age=" + age +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", permissions='" + permissions + '\'' +
            ", username='" + username + '\'' +
            '}';
    }
}
