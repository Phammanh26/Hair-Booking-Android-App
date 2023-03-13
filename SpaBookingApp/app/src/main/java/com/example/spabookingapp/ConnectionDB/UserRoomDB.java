package com.example.spabookingapp.ConnectionDB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.spabookingapp.DAO.UserDAO;
import com.example.spabookingapp.Entities.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserRoomDB extends RoomDatabase {

    public abstract UserDAO userDAO();

    private static volatile UserRoomDB INSTANCE;

    public static UserRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDB.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    UserRoomDB.class, "user_db")
                            .allowMainThreadQueries()
                            // Use a callback to seed data into the database
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // Define a callback to insert some sample data into the database
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    // If you want to keep data through app restarts,
                    // comment out the following block
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDAO mDao;

        PopulateDbAsync(UserRoomDB db) {
            mDao = db.userDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();
            mDao.insertAll(new User(1,"Nguyen Van A","1","123456","abc","abc",1));

            return null;
        }
    }
}