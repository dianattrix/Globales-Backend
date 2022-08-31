package com.globales.farmastock

import org.springframework.context.annotation.Description
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional


@Repository
interface UserRepository: JpaRepository<User, Long>{
    fun findByEmail(@Param("email") email: String): Optional<User>
}
@Repository
interface RoleRepository: JpaRepository<Role,Long>{
    fun findByName(@Param("role") role: String): Optional<Role>
}

@Repository
interface PrivilegeRepository: JpaRepository<Privilege,Long>

@Repository
interface MedicineRepository: JpaRepository<Medicine,Long>

@Repository
interface ReminderRepository: JpaRepository<Reminder,Long>

