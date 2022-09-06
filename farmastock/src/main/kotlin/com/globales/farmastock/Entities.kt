package com.globales.farmastock

import java.util.*
import javax.persistence.*


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?= null,
    var firstName: String?= null,
    var lastName: String?= null,
    var password: String?= null,
    var email: String?= null,
    var createDate: Date?= null,
    var enabled: Boolean,
    var tokenExpired: Boolean,
    // Entity Relationship
    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roleList: Set<Role>?= null,

    @OneToMany(mappedBy = "user")
    var reminderList: Set<Reminder>?= null

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (id != other.id) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + email.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(id=$id, firstName='$firstName', lastName='$lastName', password='$password', email='$email', createDate=$createDate, enabled=$enabled, tokenExpired=$tokenExpired, roleList=$roleList)"
    }
}

@Entity
@Table(name = "role")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var name: String?= null,
    // Entity Relationship
    @ManyToMany
    @JoinTable(
        name = "role_privilege",
        joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "privilege_id", referencedColumnName = "id")]
    )
    var privilegeList: Set<Privilege>? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Role) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Role(id=$id, name='$name', privilegeList=$privilegeList)"
    }

}

@Entity
@Table(name = "privilege")
data class Privilege(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long? = null,
    var name: String,
    // Entity Relationship
    @ManyToMany(mappedBy = "roleList", fetch = FetchType.LAZY)
    var userList: Set<User>? = null,
    @ManyToMany(mappedBy = "privilegeList", fetch = FetchType.LAZY)
    var roleList: Set<Role>? = null,

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Privilege) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Privilege(id=$id, name='$name', userList=$userList, roleList=$roleList)"
    }
}

@Entity
@Table(name="medicine")
data class Medicine(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var name: String?= null,
    var dose: Int?= null,
    var quantity: Int?= null,
    // Entity Relationship
    @OneToMany(mappedBy = "medicine")
    var reminderList: Set<Reminder>?= null
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Medicine) return false


        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Medicine(id=$id, name=$name, dose=$dose, quantity=$quantity)"
    }
}


@Entity
@Table(name="reminder")
data class Reminder(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var description: String?= null,
    var createDate: Date?= null,
    var state: Boolean?= null,

    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false, referencedColumnName = "id")
    var medicine: Medicine,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    var user: User,
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Reminder) return false


        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Reminder(id=$id, description=$description, createDate=$createDate, state=$state)"
    }
}






