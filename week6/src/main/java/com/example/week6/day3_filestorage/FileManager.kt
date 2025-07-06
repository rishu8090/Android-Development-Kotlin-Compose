package com.example.week6.day3_filestorage

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

enum class DirectoryType{
    Cache,   // cache is for smaller files, but somewhat faster than the files directory.      // when we clear cache from app in setting , then cache of both type deleted.  and clear storage will deleted all this four type data.
    Files    // files is for larger files, but slower than the cache directory.      // when app removed from RAM , not any type of data will be deleted.
}

enum class  StorageType{
    Internal,     // internal storage of app not seen by other's app is internal.
    External    // external storage is internal storage of phone , which is seen by other's app.
}

object FileReader{
    fun readFromFile(
        context: Context,
        fileName: String,
        directoryType: DirectoryType,
        storageType: StorageType
    ): String? {
        when(directoryType){   // here we determine which directory we want to use.
            DirectoryType.Cache -> {
                val cacheDir = if(storageType == StorageType.Internal) context.cacheDir else context.externalCacheDir   // here we determine external or internal.
                val file = File(cacheDir,fileName)
                    return readFromFile(file)
            }

            DirectoryType.Files -> {
                val fileDir = if (storageType == StorageType.Internal) context.filesDir else context.getExternalFilesDir(
                    Environment.DIRECTORY_DOCUMENTS
                )
                val file = File(fileDir,fileName)  // it is just a reference, not that file is created.
                return readFromFile(file)
            }
        }
    }

    private fun readFromFile(file: File): String? {
        val reader = FileReader(file)
        try {
            val content = reader.readText()
            reader.close()
            return content
        } catch (e: Exception) {
           return null
        } finally {
            reader.close()
        }
    }
}



object FileWriter {
    fun writeToFile(
        context: Context,
        fileName: String,
        content: String,
        directoryType: DirectoryType,
        storageType: StorageType
    ){
        when(directoryType){   // here we determine which directory we want to use.
            DirectoryType.Cache -> {
                val cacheDir = if(storageType == StorageType.Internal) context.cacheDir else context.externalCacheDir   // here we determine external or internal.
                val file = File(cacheDir,fileName)
                writeToFile(file,content)
            }

            DirectoryType.Files -> {
                val fileDir = if (storageType == StorageType.Internal) context.filesDir else context.getExternalFilesDir(
                    Environment.DIRECTORY_DOCUMENTS
                )
                val file = File(fileDir,fileName)  // it is just a reference, not that file is created.
                writeToFile(file,content)
            }
        }
    }

    private fun writeToFile(file: File, content: String){
            val writer = FileWriter(file)
        try {    // ctrl + alt + t to use it.  // it is always a better use to use try catch in this type of I\O operations.
            writer.write(content)
            writer.close()   // close() is used to close the file and free up system resources.
        } catch (e: IOException) {
            Log.d("TAG", "Failed to write to file: ${file.name}")
        }finally {
            writer.close()
        }
    }
}

data class Video(
    val id : Long,
    val name : String,
    val uri : Uri
)

fun Context.getVideoFromStorage(): MutableList<Video> {   // context is used to use contentResolver.
    val videos = mutableListOf<Video>()   // list of videos
    val uri = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)  // to Read data from the external storage you must have to add permission in manifest.   /// all media of video type in phone internal storage.
    val projection = arrayOf(MediaStore.Video.Media._ID, MediaStore.Video.Media.DISPLAY_NAME)   // in projection we need videos id and name of all videos in phone.
    val selection = "${MediaStore.Video.Media.DURATION} BETWEEN ? AND ?"   ///  for duration videos.
    val selectionArgs = arrayOf("11000", "20000") // 11 sec to 20 sec
    val sortOrder = "${MediaStore.Video.Media.DISPLAY_NAME} ASC"

    // !! guarantees that the cursor will never be null,
    val cursor = this.contentResolver.query(uri,projection, selection,selectionArgs, sortOrder)!!  // we get list of videos and our cursor will point to one by one to this videos in the dataTable.
    val idColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)  // here we find cursor position of id
    val idNameIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)  //here we find cursor position of name

    while (cursor.moveToNext()){   // as long as our cursor will move we have to retrieve video and add them into the videos list.
        val id = cursor.getLong(idColumnIndex)
        val name = cursor.getString(idNameIndex)
        val uri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,id)  /// by this we get uri of all videos by the help of their id.

        val video = Video(id,name,uri)
        videos.add(video)
    }
    cursor.close()
    return videos
}