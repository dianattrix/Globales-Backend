package com.globales.farmastock

import java.util.*

//      PRIVILEGE
data class PrivilegeInput(
    var id: Long? = null,
    var name: String? = null,
)

data class PrivilegeDetails(
    var id: Long? = null,
    var name: String? = null,
)

//      ROLE
data class RoleDetails(
    var id: Long? = null,
    var name: String? = null,
    var privilegeList: List<PrivilegeDetails>? = null,
)

data class RoleInput(
    var id: Long? = null,
    var name: String?=null,
)

//      USER
data class UserInput(
    var id: Long? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var enabled: Boolean? = null,
    var roleList: List<RoleDetails>? = null,
    var createDate: Date?=null,
)

data class UserResult(
    var id: Long? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var enabled: Boolean? = null,
    var tokenExpired: Boolean? = null,
    var createDate: Date? = null,
    var roleList: List<RoleDetails>?=null,
    var reminderList: List<ReminderDetails>?=null,
)

data class UserLoginInput(
    var username: String = "",
    var password: String = "",
)
data class UserSignUpInput(
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var password: String? = null,
)

data class MedicineInput(
    var id: Long? = null,
    var name: String? = null,
    var dose: Int? = null,
    var quantity: Int? = null
)
data class MedicineDetails(
    var id: Long? = null,
    var name: String? = null,
    var dose: Int? = null,
    var quantity: Int? = null
)

data class ReminderInput(
        var id: Long? = null,
        var create_date: Date? = null, // Date(year: Int, month: Int, day: Int, hour: Int, minute: Int)
        var description: String? = null,
        var status: Boolean? = null
)
data class ReminderDetails(
        var create_date: Date? = null, // Date(year: Int, month: Int, day: Int, hour: Int, minute: Int)
        var description: String? = null,
)



