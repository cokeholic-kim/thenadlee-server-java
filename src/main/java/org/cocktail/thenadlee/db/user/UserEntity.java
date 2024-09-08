package org.cocktail.thenadlee.db.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.cocktail.thenadlee.db.BaseEntity;
import org.cocktail.thenadlee.db.user.enums.LoginMethod;
import org.cocktail.thenadlee.db.user.enums.UserRole;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String nickName;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 50)
    private String password;

    @Column(columnDefinition = "varchar(255)",length = 50,nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(columnDefinition = "varchar(50)",length = 50,nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginMethod loginMethod = LoginMethod.APP;
}

