package com.vivere.app.vivere.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vivere.app.vivere.models.*;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by kyria_000 on 23/11/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //DATABASE NAME DECLARATION
    public static final String DATABASE_NAME = "vivere.db";
    //TABLES DECLARATION
    private static final String TB_PATIENT = "Patient";
    private static final String TB_HABITS = "Habits";
    private static final String TB_ILLNESSES = "Illnesses";
    private static final String TB_MEDICATION = "Medication";
    private static final String TB_MEDSPEC = "MedicalSpecialist";
    private static final String TB_EXAM = "Exam";
    private static final String TB_INHERITANCE = "Inheritance";
    private static final String TB_APPOINTMENT = "Appointment";

    //FIELD DECLARATION
    //TABLE Patient - stores all the data of each patient as a single record
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";
    private static final String COL_NAME = "name";
    private static final String COL_SURNAME = "surname";
    private static final String COL_GENDER = "gender";
    private static final String COL_NATIONALITY = "nationality";
    private static final String COL_COUNTRY = "country";
    private static final String COL_AGE = "age";

    //TABLE Habits
    private static final String COL_HNAME = "hname";
    private static final String COL_TYPE = "type";
    private static final String COL_DAYSTOGO = "daystogo";
    private static final String COL_DAYSDONE = "daysdone";
    private static final String COL_TIMESTAMP = "timestamp";
    private static final String COL_LASTUPDATED = "lastupdated";
    //private static final String COL_USERNAME = "username";

    //TABLE Illnesses
    private static final String COL_INAME = "iname";
    private static final String COL_FACTOR = "factor";
    private static final String COL_VALUE = "value";

    //TABLE Medication
    private static final String COL_MNAME = "mname";
    private static final String COL_DURATION = "duration"; //in days
    private static final String COL_FREQUENCY = "frequency";
    private static final String COL_DOSE = "dose";
    private static final String COL_TIMESTAKEN = "timestaken";
    //private static final String COL_USERNAME = "username";
    //private static final String COL_LASTUPDATED = "lastupdated";

    //TABLE Exam
    private static final String COL_ID = "id";
    //private static final String COL_TYPE = "type";
    private static final String COL_RESULTS = "results";
    //private static final String COL_TIMESTAMP = "timestamp";
    private static final String COL_DOCTORSADVICE = "doctorsadvice";
    //private static final String COL_USERNAME = "username";
    //private static final String COL_MSUSERNAME= "msusername";

    //TABLE Medical Specialist
    private static final String COL_MSUSERNAME = "msusername";
    //private static final String COL_PASSWORD = "password";
    //private static final String COL_NAME = "name";
    //private static final String COL_SURNAME = "surname";
    private static final String COL_SPECIALITY = "speciality";
    private static final String COL_ADDRESS = "address";
    private static final String COL_TELEPHONE = "telephone";
    //private static final String COL_TYPE = "type";

    //TABLE Inheritance
    //private static final String COL_INAME = "iname";
    //private static final String COL_USERNAME = "username";

    //TABLE Appointment
    private static final String COL_DESCRIPTION = "description";
    //private static final String COL_TIMESTAMP = "timestamp";
    //private static final String COL_USERNAME = "username";
    //private static final String COL_MSUSERNAME= "msusername";

    //TABLE CREATION QUERIES
    private static final String CREATE_PATIENT = "CREATE TABLE " + TB_PATIENT + " (" + COL_USERNAME + " TEXT PRIMARY KEY NOT NULL, "
            + COL_PASSWORD + " TEXT NOT NULL, " + COL_NAME + " TEXT NOT NULL ," + COL_SURNAME + " TEXT NOT NULL, " + COL_GENDER
            + " TEXT NOT NULL, " + COL_NATIONALITY + " TEXT NOT NULL, " + COL_COUNTRY + " TEXT NOT NULL, " + COL_AGE + " INT NOT NULL " + ")";

    private static final String CREATE_HABITS = "CREATE TABLE " + TB_HABITS + " (" + COL_HNAME + " TEXT PRIMARY KEY NOT NULL, "
            + COL_TYPE + " TEXT NOT NULL, " + COL_DAYSTOGO + " INT NOT NULL ," + COL_DAYSDONE + " INT NOT NULL, " + COL_TIMESTAMP
            + " DATE NOT NULL, " + COL_LASTUPDATED + " DATE NOT NULL, " + COL_USERNAME + " TEXT NOT NULL REFERENCES " + TB_PATIENT + "(" + COL_USERNAME + "))";

    private static final String CREATE_ILLNESSES = "CREATE TABLE " + TB_ILLNESSES + " (" + COL_INAME + " TEXT NOT NULL, "
            + COL_TYPE + " TEXT NOT NULL, " + COL_VALUE + " TEXT NOT NULL, " + "PRIMARY KEY (" + COL_INAME + ", " + COL_TYPE + ", " + COL_VALUE + "))";

    private static final String CREATE_MEDICATION = "CREATE TABLE " + TB_MEDICATION + " (" + COL_MNAME + " TEXT PRIMARY KEY NOT NULL, "
            + COL_DURATION + " INT NOT NULL, " + COL_FREQUENCY + " TEXT NOT NULL ," + COL_DOSE + " TEXT NOT NULL, " + COL_TIMESTAKEN
            + " INT NOT NULL, " + COL_LASTUPDATED + " DATE NOT NULL " + ")";

    private static final String CREATE_EXAM = "CREATE TABLE " + TB_EXAM + " (" + COL_ID + " INT PRIMARY KEY NOT NULL, "
            + COL_TYPE + " TEXT NOT NULL, " + COL_RESULTS + " TEXT NOT NULL ," + COL_TIMESTAMP + " DATE NOT NULL, " + COL_DOCTORSADVICE
            + " TEXT NOT NULL, " + COL_USERNAME + " TEXT NOT NULL REFERENCES " + TB_PATIENT + "(" + COL_USERNAME + "), "
            + COL_MSUSERNAME + " TEXT NOT NULL REFERENCES " + TB_MEDSPEC + "(" + COL_MSUSERNAME + ")" + ")";

    private static final String CREATE_MEDSPEC = "CREATE TABLE " + TB_MEDSPEC + " (" + COL_MSUSERNAME + " TEXT PRIMARY KEY NOT NULL, "
            + COL_PASSWORD + " TEXT NOT NULL, " + COL_NAME + " TEXT NOT NULL ," + COL_SURNAME + " TEXT NOT NULL, " + COL_SPECIALITY
            + " TEXT NOT NULL, " + COL_ADDRESS + " TEXT NOT NULL, " + COL_TELEPHONE + " INT NOT NULL, " + COL_TYPE + " TEXT NOT NULL " + ")";

    private static final String CREATE_INHERITANCE = "CREATE TABLE " + TB_INHERITANCE + " (" + COL_USERNAME + " TEXT NOT NULL REFERENCES "
            + TB_PATIENT + "(" + COL_USERNAME + "), " + COL_INAME + " TEXT NOT NULL REFERENCES " + TB_ILLNESSES + "(" + COL_INAME + "), "
            + "PRIMARY KEY (" + COL_USERNAME + ", " + COL_INAME + ")" + ")";

    private static final String CREATE_APPOINTMENT = "CREATE TABLE " + TB_APPOINTMENT + " (" + COL_USERNAME + " TEXT NOT NULL REFERENCES "
            + TB_PATIENT + "(" + COL_USERNAME + "), " + COL_MSUSERNAME + " TEXT NOT NULL REFERENCES " + TB_MEDSPEC + "(" + COL_MSUSERNAME + "), "
            + COL_TIMESTAMP + " DATE NOT NULL, " + COL_DESCRIPTION + " TEXT NOT NULL, "
            + "PRIMARY KEY (" + COL_USERNAME + ", " + COL_MSUSERNAME + ", " + COL_TIMESTAMP + ")" + ")";

    /*Database Constructor
        Creates the database.
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * Creates all the tables, their fields and types.
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PATIENT);
        db.execSQL(CREATE_HABITS);
        db.execSQL(CREATE_ILLNESSES);
        db.execSQL(CREATE_MEDICATION);
        db.execSQL(CREATE_EXAM);
        db.execSQL(CREATE_MEDSPEC);
        db.execSQL(CREATE_INHERITANCE);
        db.execSQL(CREATE_APPOINTMENT);
    }

    /**
     * Upgrades the database by dropping and re-creating all the necessary tables and fields.
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_HABITS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_ILLNESSES);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_MEDICATION);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_EXAM);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_INHERITANCE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_APPOINTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_MEDSPEC);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_PATIENT);
        onCreate(db);
    }

    /**
     * Adds an individual Patient to the local database.
     * @param p
     */
    public void addPatient(Patient p) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =
                "INSERT or replace INTO Patient (username, password, name, surname, gender, "
                        + "nationality, country, age) VALUES('" + p.getUsername()
                        + "', '" + p.getPassword() + "', '" + p.getName() + "', '" + p.getSurname()
                        + "', '" + p.getGender() + "', '" + p.getNationality() + "', '"
                        + p.getCoutnry() + "', " + p.getAge() + ")";
        db.execSQL(sql);
    }

    /**
     * Gets an individual patient from the local database.
     * @param username
     * @return
     */
    public Patient getPatient(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `Patient` WHERE username = '" + username + "';";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        Patient patient = new Patient();
        patient.setUsername(c.getString(0));
        patient.setPassword(c.getString(1));
        patient.setName(c.getString(2));
        patient.setSurname(c.getString(3));
        patient.setGender(c.getString(4));
        patient.setNationality(c.getString(5));
        patient.setCoutnry(c.getString(6));
        patient.setAge(c.getInt(7));
        c.moveToNext();

        c.close();

        return patient;
    }

    /**
     * Adds an individual habit for a specific patient (via username) to the local database.
     * @param h
     */
    public void addHabit(Habit h) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =
                "INSERT or replace INTO Habits (hname, type, daystogo, daysdone, timestamp,"
                        + " lastupdated, username) VALUES('" + h.getHname() + "', '"
                        + h.getType() + "', " + h.getDaystogo() + ", " + h.getDaysdone() + ", '"
                        + h.getTimestamp() + "', '" + h.getLastupdated() + "', '" + h.getUsername()
                        + "')";
        db.execSQL(sql);
    }

    /**
     * Gets an individual habit by current habit name of a specific patient (via username) from the
     * local database.
     * @param hname
     * @param username
     * @return
     */
    public Habit getHabit(String hname, String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `Habits` WHERE username = '" + username + "' AND hname = '"
                + hname + "';";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        Habit habit = new Habit();
        habit.setHname(c.getString(0));
        habit.setType(c.getString(1));
        habit.setDaystogo(c.getInt(2));
        habit.setDaysdone(c.getInt(3));
        habit.setTimestamp(Timestamp.valueOf(c.getString(4)));
        habit.setLastupdated(Date.valueOf(c.getString(5)));
        habit.setUsername(c.getString(6));

        c.moveToNext();

        c.close();

        return habit;
    }

}
