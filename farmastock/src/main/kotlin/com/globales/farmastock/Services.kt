package com.globales.farmastock

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


//                                      PRIVILEGE
interface PrivilegeService {
    /**
     * Find all Privileges
     * @return a list of privileges
     */
    fun findAll(): Set<PrivilegeDetails>?

    /**
     * Get one role by id
     * @param id of the role
     * @return the role found
     */
    fun findById(id: Long): PrivilegeDetails?

    /**
     * Save and flush a privilege entity in the database
     * @param privilegeInput
     * @return the privilege created
     */
    fun create(privilegeInput: PrivilegeInput): PrivilegeDetails?

    /**
     * Update a privilege entity in the database
     * @param privilegeInput the dto input for role
     * @return the new privilege created
     */
    fun update(privilegeInput: PrivilegeInput): PrivilegeDetails?

    /**
     * Delete a privilege by id from Database
     * @param id of the privilege
     */
    fun deleteById(id: Long)

}
@Service
class AbstractPrivilegeService(
    @Autowired
    val privilegeRepository: PrivilegeRepository,
    @Autowired
    val privilegeMapper: PrivilegeMapper,
) : PrivilegeService {
    /**
     * Find all Privileges
     * @return a list of privileges
     */
    override fun findAll(): Set<PrivilegeDetails>? {
        return privilegeMapper.privilegeListToPrivilegeDetailsList(
            privilegeRepository.findAll()
        )
    }

    /**
     * Get one privilege by id
     * @param id of the privilege
     * @return the privilege found x
     */
    @Throws(java.util.NoSuchElementException::class)
    override fun findById(id: Long): PrivilegeDetails? {
        val privilege: Privilege  = privilegeRepository.findById(id).orElse(null)
            ?: throw java.util.NoSuchElementException(String.format("The role with the id: %s not found!", id))
        return privilegeMapper.privilegeToPrivilegeDetails(privilege)
    }

    /**
     * Save and flush a privilege entity in the database
     * @param privilegeInput
     * @return the privilege created
     */
    override fun create(privilegeInput: PrivilegeInput): PrivilegeDetails?{
        val privilege : Privilege = privilegeMapper.privilegeInputToPrivilege(privilegeInput)
        return privilegeMapper.privilegeToPrivilegeDetails(
            privilegeRepository.save(privilege)
        )
    }

    /**
     * Update a privilege entity in the database
     * @param privilegeInput the dto input for privilege
     * @return the new privilege created
     */
    @Throws(java.util.NoSuchElementException::class)
    override fun update(privilegeInput: PrivilegeInput): PrivilegeDetails? {
        val privilege : Privilege = privilegeRepository.findById(privilegeInput.id!!).orElse(null)
            ?: throw java.util.NoSuchElementException(
                String.format(
                    "The privilege with the id: %s not found!",
                    privilegeInput.id
                )
            )
        val privilegeUpdate: Privilege = privilege
        privilegeMapper.privilegeInputToPrivilege(privilegeInput)
        return privilegeMapper.privilegeToPrivilegeDetails(privilegeRepository.save(privilegeUpdate))
    }

    /**
     * Delete a privilege by id from Database
     * @param id of the privilege
     */
    @Throws(java.util.NoSuchElementException::class)
    override fun deleteById(id: Long) {
        privilegeRepository.findById(id).orElse(null)
            ?: throw java.util.NoSuchElementException(String.format("The privilege with the id: %s not found!", id))
        privilegeRepository.deleteById(id)
    }
}

//                                      ROLE
interface RoleService {
    /**
     * Find all Roles
     * @return a list of roles
     */
    fun findAll(): Set<RoleDetails>?

    /**
     * Get one role by id
     * @param id of the role
     * @return the role found
     */
    fun findById(id: Long): RoleDetails?

    /**
     * Save and flush a role entity in the database
     * @param roleInput
     * @return the role created
     */
    fun create(roleInput: RoleInput): RoleDetails?

    /**
     * Update a role entity in the database
     * @param roleInput the dto input for role
     * @return the new role created
     */
    fun update(roleInput: RoleInput): RoleDetails?

    /**
     * Delete a role by id from Database
     * @param id of the role
     */
    fun deleteById(id: Long)

}

@Service
class AbstractRoleService(
    @Autowired
    val roleRepository: RoleRepository,
    @Autowired
    val roleMapper: RoleMapper,
) : RoleService {
    /**
     * Find all roles
     * @return a list of roles
     */
    override fun findAll(): Set<RoleDetails>? {
        return roleMapper.roleListToRoleDetailsList(
            roleRepository.findAll()
        )
    }

    /**
     * Get one role by id
     * @param id of the role
     * @return the role found x
     */
    @Throws(java.util.NoSuchElementException::class)
    override fun findById(id: Long): RoleDetails? {
        val role : Role  = roleRepository.findById(id).orElse(null)
            ?: throw java.util.NoSuchElementException(String.format("The role with the id: %s not found!", id))
        return roleMapper.roleToRoleDetails(role)
    }

    /**
     * Save and flush a role entity in the database
     * @param roleInput
     * @return the role created
     */
    override fun create(roleInput: RoleInput): RoleDetails?{
        val role:Role = roleMapper.roleInputToRole(roleInput)
        return roleMapper.roleToRoleDetails(
            roleRepository.save(role)
        )
    }

    /**
     * Update a role entity in the database
     * @param userInput the dto input for role
     * @return the new role created
     */
    @Throws(java.util.NoSuchElementException::class)
    override fun update(roleInput: RoleInput): RoleDetails? {
        val role:Role = roleRepository.findById(roleInput.id!!).orElse(null)
            ?: throw java.util.NoSuchElementException(
                String.format(
                    "The role with the id: %s not found!",
                    roleInput.id
                )
            )
        val roleUpdate: Role = role
        roleMapper.roleInputToRole(roleInput)
        return roleMapper.roleToRoleDetails(roleRepository.save(roleUpdate))
    }

    /**
     * Delete a service by id from Database
     * @param id of the user
     */
    @Throws(java.util.NoSuchElementException::class)
    override fun deleteById(id: Long) {
        roleRepository.findById(id).orElse(null)
            ?: throw java.util.NoSuchElementException(String.format("The role with the id: %s not found!", id))
        roleRepository.deleteById(id)
    }
}

//                                      USER

interface UserService {
    /**
     * Find all user
     * @return a list of users
     */
    fun findAll(): List<UserResult>?

    /**
     * Get one user by id
     * @param id of the user
     * @return the user found
     */
    fun findById(id: Long): UserResult?

    /**
     * Get one user by id
     * @param email of the user
     * @return the user found
     */
    fun findByEmail(email: String): UserResult?

    /**
     * Save and flush a user entity in the database
     * @param userInput
     * @return the user created
     */
    fun create(userInput: UserInput): UserResult?

    /**
     * Update a user entity in the database
     * @param userInput the dto input for user
     * @return the new user created
     */
    fun update(userInput: UserInput): UserResult?

    /**
     * Delete a user by id from Database
     * @param id of the user
     */
    fun deleteById(id: Long)
}


@Service
class AbstractUserService(
    @Autowired
    val userRepository: UserRepository,
    @Autowired
    val userMapper: UserMapper ,
) : UserService {
    /**
     * Find all users
     * @return a list of users
     */
    override fun findAll(): List<UserResult>? {
        return userMapper.userListToUserListResult(
            userRepository.findAll()
        )
    }

    /**
     * Get one user by id
     * @param id of the user
     * @return the user found
     */
    @Throws(java.util.NoSuchElementException::class)
    override fun findById(id: Long): UserResult? {
        val user : User  = userRepository.findById(id).orElse(null)
            ?: throw java.util.NoSuchElementException(String.format("The user with the id: %s not found!", id))
        return userMapper.userToUserResult(user)
    }

    /**
     * Get one user by id
     * @param email of the user
     * @return the user found
     */
    @Throws(java.util.NoSuchElementException::class)
    override fun findByEmail(email: String): UserResult? {
        val user : User  = userRepository.findByEmail(email).orElse(null)
            ?: throw java.util.NoSuchElementException(String.format("The user with the email: %s not found!", email))
        return userMapper.userToUserResult(user)
    }

    /**
     * Save and flush a user entity in the database
     * @param userInput
     * @return the user created
     */
    override fun create(userInput: UserInput): UserResult? {
        val user:User = userMapper.userInputToUser(userInput)
        return userMapper.userToUserResult(
            userRepository.save(user)
        )
    }

    /**
     * Update a user entity in the database
     * @param userInput the dto input for user
     * @return the new user created
     */
    @Throws(java.util.NoSuchElementException::class)
    override fun update(userInput: UserInput): UserResult? {
        val user:User = userRepository.findById(userInput.id!!).orElse(null)
            ?: throw java.util.NoSuchElementException(
                String.format(
                    "The user with the id: %s not found!",
                    userInput.id
                )
            )
        val userUpdate: User = user
        userMapper.userInputToUser(userInput, userUpdate)
        return userMapper.userToUserResult(userRepository.save(userUpdate))
    }

    /**
     * Delete a user by id from Database
     * @param id of the user
     */
    @Throws(java.util.NoSuchElementException::class)
    override fun deleteById(id: Long) {
        userRepository.findById(id).orElse(null)
            ?: throw java.util.NoSuchElementException(String.format("The user with the id: %s not found!", id))
        userRepository.deleteById(id)
    }

}