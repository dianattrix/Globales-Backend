package com.globales.farmastock

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

//      PRIVILEGE

@RestController
@RequestMapping("\${url.privileges}")
class PrivilegesController(private val privilegeService: PrivilegeService) {

    @GetMapping
    @ResponseBody
    fun findAll() = privilegeService.findAll()

    @Throws(NoSuchElementException::class)
    @GetMapping("{id}")
    @ResponseBody
    fun findById(@PathVariable id: Long) = privilegeService.findById(id)


    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody privilegeInput: PrivilegeInput): PrivilegeDetails? {
        return privilegeService.create(privilegeInput)
    }

    @Throws(NoSuchElementException::class)
    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody privilegeInput: PrivilegeInput): PrivilegeDetails? {
        return privilegeService.update(privilegeInput)
    }

    @Throws(NoSuchElementException::class)
    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteById(@PathVariable id: Long) {
        privilegeService.deleteById(id)
    }
}

//      ROLE
@RestController
@RequestMapping("\${url.roles}")
class RoleController(private val roleService: RoleService) {

    @GetMapping
    @ResponseBody
    fun findAll() = roleService.findAll()

    @Throws(NoSuchElementException::class)
    @GetMapping("{id}")
    @ResponseBody
    fun findById(@PathVariable id: Long) = roleService.findById(id)


    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody roleInput: RoleInput): RoleDetails? {
        return roleService.create(roleInput)
    }

    @Throws(NoSuchElementException::class)
    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody roleInput: RoleInput): RoleDetails? {
        return roleService.update(roleInput)
    }

    @Throws(NoSuchElementException::class)
    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteById(@PathVariable id: Long) {
        roleService.deleteById(id)
    }
}

//      USER
@RestController
@RequestMapping("\${url.users}")
class UserController(private val userService: UserService) {

    @GetMapping
    @ResponseBody
    fun findAll() = userService.findAll()

    @Throws(NoSuchElementException::class)
    @GetMapping("{id}")
    @ResponseBody
    fun findById(@PathVariable id: Long) = userService.findById(id)

    @Throws(NoSuchElementException::class)
    @GetMapping("email/{username}")
    @ResponseBody
    fun findByEmail(@PathVariable username: String) = userService.findByEmail(username)


    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody userInput: UserInput): UserResult? {
        return userService.create(userInput)
    }

    @Throws(NoSuchElementException::class)
    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody userInput: UserInput): UserResult? {
        return userService.update(userInput)
    }

    @Throws(NoSuchElementException::class)
    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteById(@PathVariable id: Long) {
        userService.deleteById(id)
    }
}

//      USER
@RestController
@RequestMapping("\${url.medicines}")
class MedicinesController(private val medicineService: medicineService) {

    @GetMapping
    @ResponseBody
    fun findAll() = medicineService.findAll()

    @Throws(NoSuchElementException::class)
    @GetMapping("{id}")
    @ResponseBody
    fun findById(@PathVariable id: Long) = medicineService.findById(id)


    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody medicineInput: MedicineInput): MedicineDetails? {
        return medicineService.create(medicineInput)
    }

    @Throws(NoSuchElementException::class)
    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody medicineInput: MedicineInput): MedicineDetails? {
        return medicineService.update(medicineInput)
    }

    @Throws(NoSuchElementException::class)
    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteById(@PathVariable id: Long) {
        medicineService.deleteById(id)
    }
}