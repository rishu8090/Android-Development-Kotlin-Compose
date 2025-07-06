package com.example.week3.day2

fun main(){
// val users = Users("Rishu", 23)
// println(users.name)
//    println(users.age)

    val (name, age ) = Users("Rishu", 23)
//    println(name)
//    print(age)

    val list = listOf(1,2,3)
    val(first, second, third) = list
    println(first)
    println(second)
    println(third)
}

data class Users(val name :String, val age : Int)

// destructuring declaration.