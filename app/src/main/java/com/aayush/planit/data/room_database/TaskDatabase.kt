package com.aayush.planit.data.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Defines the Room database and specifies which entities belong to it.
// version = 1 is the current database schema version.
@Database(entities = [TaskItem::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    // Provides access to the DAO used to perform database operations.
    abstract fun taskDao(): TaskDao

    companion object {

        // Holds the single instance of the database.
        // @Volatile ensures that changes to INSTANCE are immediately
        // visible to all threads.
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        // Returns the existing database instance or creates it if needed.
        fun getDatabase(context: Context): TaskDatabase {

            // If INSTANCE already exists, return it.
            // Otherwise, create the database inside a synchronized block.
            return INSTANCE ?: synchronized(this) {

                // Builds the Room database using the application context.
                // Using applicationContext prevents the database from
                // accidentally holding a reference to an Activity.
                Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                )

                    // If the database schema changes without a migration,
                    // Room deletes the existing database and creates it again.
                    // This is convenient during development but can cause
                    // existing user data to be lost.
                    .fallbackToDestructiveMigration()

                    // Creates the database instance.
                    .build()

                    // Stores the newly created instance in INSTANCE.
                    .also { INSTANCE = it }
            }
        }
    }
}