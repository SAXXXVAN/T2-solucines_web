package com.lectorium.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class UserRolePK {
    @ManyToOne
    @JoinColumn(name="id_user", foreignKey = @ForeignKey(name="FK_USER_ROLE_U"))
    private User user;

    @ManyToOne
    @JoinColumn(name="id_role", foreignKey = @ForeignKey(name="FK_USER_ROLE_R"))
    private Role role;
}
