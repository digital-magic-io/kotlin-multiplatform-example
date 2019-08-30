package io.digitalmagic

annotation class ToTypescript

@ToTypescript
data class Account(val person: Person, val users: List<User>, val organization: List<Organization>)

data class User(val username: String, val password: String, val roles: List<Role>)

data class Person(val firstname: String, val lastname: String)

data class Organization(val name: String, val code: String)

enum class Role {
    USER,
    MANAGER,
    ADMIN
}
