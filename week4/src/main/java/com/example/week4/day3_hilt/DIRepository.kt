package com.example.week4.day3_hilt

import javax.inject.Inject

class DIRepository @Inject constructor(){  // to make any class injectable by the hilt, we should annotate it with @Inject.
    fun doSomething(){  //  constructor is used to create a instance of class , which is after used by the DIViewModel.
        println("DIRepository: doSomething")
    }
}

class  AnotherDIRepository private constructor(){
                            // companion object is used to create static objects.
    companion object{  // static objects, are the objects which are created only once and used again and again, and you can access it without creating instance.
         var instance: AnotherDIRepository? = null
                                // you can't use fun name as getInstance bcz in Kotlin getInstance and setInstance are two methods which are used in background by kt, so you can't use them.
    fun getClassInstance(): AnotherDIRepository{
        if(instance == null){
            instance = AnotherDIRepository()
        }
        return instance!!  // !! is used to avoid null pointer exception and we guarantee to the compiler that it will never null.
      }
    }
    fun doSomething(){
        println("AnotherDIRepository: doSomething")
    }

}