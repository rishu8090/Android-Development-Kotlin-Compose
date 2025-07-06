package com.example.week4.day3_hilt


class ChildClass(val parent : ParentClass){
//    val parent = ParentClass("Rishu")
    fun printNames(){
        parent.printName()
    }
}

class ParentClass(val name: String){
    fun printName(){
        println("Parent Name $name")
    }
}

fun main(){
//    val childClass = ChildClass()
//    childClass.printName()

    val parentClass = ParentClass("Choudhary")
    val childClass = ChildClass(parentClass)
    childClass.printNames()
}