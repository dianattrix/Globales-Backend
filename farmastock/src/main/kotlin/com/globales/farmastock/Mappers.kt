package com.globales.farmastock

import org.mapstruct.*
import java.time.LocalDateTime


//      PRIVILEGE
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface PrivilegeMapper {

    fun privilegeListToPrivilegeDetailsList(
        privilegeList: MutableList<Privilege>,
    ): Set<PrivilegeDetails>

    fun privilegeToPrivilegeDetails(
        privilege: Privilege
    ): PrivilegeDetails

    fun privilegeInputToPrivilege(
        privilegeInput: PrivilegeInput,
    ): Privilege
}

//      ROLE
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RoleMapper {
    fun roleListToRoleDetailsList(
        roleList: MutableList<Role>,
    ): Set<RoleDetails>

    fun roleToRoleDetails(
        role: Role,
    ): RoleDetails

    fun roleInputToRole(
        roleInput: RoleInput,
    ): Role
}

//      USER
@Mapper(
    imports = [LocalDateTime::class],
    componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface UserMapper {
    fun userToUserResult(
        user: User
    ): UserResult

    @Mapping(target = "createDate", defaultExpression = "java(new java.util.Date())")
    fun userInputToUser(
        userInput: UserInput
    ): User

    //Para los updates
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun userInputToUser(dto: UserInput, @MappingTarget user: User )

    fun userListToUserListResult(
        userList: List<User>,
    ): List<UserResult>

    fun userLoginInput(
        userLoginInput: UserLoginInput
    ): User

    fun userSignUpInput(
        userSignUpInput: UserSignUpInput
    ): User
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface MedicineMapper{
    fun medicineToMedicineDetails(
        medicine: Medicine
    ): MedicineDetails
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun medicineInputToMedicine(dto: MedicineInput, @MappingTarget medicine: Medicine )

    fun medicineListToMedicineListDetails(
        medicineList: List<Medicine>,
    ): List<MedicineDetails>

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun medicineInputToMedicine(
        medicineInput: MedicineInput,
    ): Medicine

}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ReminderMapper{
    fun reminderToReminderDetails(
            reminder: Reminder
    ): ReminderDetails
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun reminderInputToReminder(dto: ReminderInput, @MappingTarget reminder: Reminder )

    fun reminderListToReminderListDetails(
            reminderList: List<Reminder>,
    ): List<ReminderDetails>

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun reminderInputToReminder(
            reminderInput: ReminderInput,
    ): Reminder

}