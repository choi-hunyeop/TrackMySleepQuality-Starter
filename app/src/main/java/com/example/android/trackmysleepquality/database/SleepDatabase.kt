/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {

    //데이터베이스는 DAO에 대해 참조하고있어야함
    //여러 DAO를 가질수있음
    abstract val sleepDatabaseDao: SleepDatabaseDao

    /*
    이 추상 값 아래에 컴패니언 객체를 정의합니다.
    동반 객체를 사용하면 클라이언트가 클래스를 인스턴스화하지 않고도 데이터베이스를 만들거나 가져 오는 메서드에 액세스 할 수 있습니다.
    이 클래스의 유일한 목적은 데이터베이스를 제공하는 것이므로 인스턴스화 할 이유가 없습니다.
     */
    companion object {
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        fun getInstance(context: Context): SleepDatabase? {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    Room.databaseBuilder(
                            context.applicationContext,
                            SleepDatabase::class.java,
                            "sleep_history_database")
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }


}