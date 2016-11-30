package com.vivere.app.vivere.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vivere.app.vivere.models.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

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
            + COL_FACTOR + " TEXT NOT NULL, " + COL_VALUE + " TEXT NOT NULL, " + "PRIMARY KEY (" + COL_INAME + ", " + COL_FACTOR + ", " + COL_VALUE + "))";

    private static final String CREATE_MEDICATION = "CREATE TABLE " + TB_MEDICATION + " (" + COL_MNAME + " TEXT PRIMARY KEY NOT NULL, "
            + COL_DURATION + " INT NOT NULL, " + COL_FREQUENCY + " TEXT NOT NULL ," + COL_DOSE + " TEXT NOT NULL, " + COL_TIMESTAKEN
            + " INT NOT NULL, " + COL_USERNAME + " TEXT NOT NULL ," + COL_LASTUPDATED + " DATE NOT NULL " + ")";

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
     *
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
     *
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
     *
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
     *
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

    /***
     * This function returns all the habits stored in the local database.
     *
     * @return
     */
    public ArrayList<Habit> getHabits() {

        ArrayList<Habit> habits = new ArrayList<Habit>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `Habits`;";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            Habit habit = new Habit();
            habit.setHname(c.getString(0));
            habit.setType(c.getString(1));
            habit.setDaystogo(c.getInt(2));
            habit.setDaysdone(c.getInt(3));
            habit.setTimestamp(Timestamp.valueOf(c.getString(4)));
            habit.setLastupdated(Date.valueOf(c.getString(5)));
            habit.setUsername(c.getString(6));
            habits.add(habit);
            c.moveToNext();
        }
        c.close();

        return habits;
    }

    /**
     * Adds a new illness to the local database.
     *
     * @param l
     */
    public void addIllness(Illness l) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =
                "INSERT or replace INTO Illnesses (iname, factor, value) VALUES('" + l.getIname() + "', '"
                        + l.getFactor() + "', '" + l.getValue() + "')";
        db.execSQL(sql);
    }

    /**
     * Gets a specific illness (via iname) from the local database.
     *
     * @param iname
     * @return
     */
    public Illness getIllness(String iname) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `Illnesses` WHERE iname = '" + iname + "';";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        Illness illness = new Illness();
        illness.setIname(c.getString(0));
        illness.setFactor(c.getString(1));
        illness.setValue(c.getString(2));

        c.moveToNext();

        c.close();

        return illness;
    }

    /***
     * This function returns all the illnesses stored in the local database.
     *
     * @return
     */
    public ArrayList<Illness> getIlnesses() {

        ArrayList<Illness> illnesses = new ArrayList<Illness>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `Illnesses`;";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            Illness illness = new Illness();
            illness.setIname(c.getString(0));
            illness.setFactor(c.getString(1));
            illness.setValue(c.getString(2));
            illnesses.add(illness);
            c.moveToNext();
        }
        c.close();

        return illnesses;
    }

    /**
     * Adds a new medication to the local database.
     *
     * @param m
     */
    public void addMedication(Medication m) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =
                "INSERT or replace INTO Medication (mname, duration, frequency, dose, timestaken, "
                        + "username, lastupdated) VALUES('" + m.getName() + "', " + m.getDuration()
                        + ", '" + m.getFrequency() + "', " + m.getDose() + ", " + m.getTimestaken()
                        + ", '" + m.getUsername() + "', '" + m.getLastupdated() + "')";
        db.execSQL(sql);
    }

    /**
     * Gets a specific medication (via mname) of a specific user (via username) from the local
     * database.
     *
     * @param mname
     * @param username
     * @return
     */
    public Medication getMedication(String mname, String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `Medication` WHERE mname = '" + mname + "' AND username = '"
                + username + "';";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        Medication medication = new Medication();
        medication.setName(c.getString(0));
        medication.setDuration(c.getInt(1));
        medication.setFrequency(c.getString(2));
        medication.setDose(c.getInt(3));
        medication.setTimestaken(c.getInt(4));
        medication.setUsername(c.getString(5));
        medication.setLastupdated(Date.valueOf(c.getString(6)));

        c.moveToNext();

        c.close();

        return medication;
    }

    /***
     * This function returns all the medication stored in the local database.
     *
     * @return
     */
    public ArrayList<Medication> getMediations() {

        ArrayList<Medication> medications = new ArrayList<Medication>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `Medication`;";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            Medication medication = new Medication();
            medication.setName(c.getString(0));
            medication.setDuration(c.getInt(1));
            medication.setFrequency(c.getString(2));
            medication.setDose(c.getInt(3));
            medication.setTimestaken(c.getInt(4));
            medication.setUsername(c.getString(5));
            medication.setLastupdated(Date.valueOf(c.getString(6)));
            medications.add(medication);
            c.moveToNext();
        }
        c.close();

        return medications;
    }

    /**
     * Adds a new exam to the local database.
     *
     * @param m
     */
    public void addExam(Exam m) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =
                "INSERT or replace INTO Exam (id, type, results, timestamp, doctorsadvice, "
                        + "username, msusername) VALUES(" + m.getId() + ", '" + m.getType()
                        + "', '" + m.getResults() + "', '" + m.getTimestamp() + "', '"
                        + m.getAdvice() + "', '" + m.getUsername() + "', '" + m.getMsusername()
                        + "')";
        db.execSQL(sql);
    }

    /**
     * Gets a specific exam by given id from the local database.
     *
     * @param id
     * @return
     */
    public Exam getExam(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `Exam` WHERE id = '" + id + "';";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        Exam exam = new Exam();
        exam.setId(c.getInt(0));
        exam.setType(c.getString(1));
        exam.setResults(c.getString(2));
        exam.setTimestamp(Timestamp.valueOf(c.getString(3)));
        exam.setAdvice(c.getString(4));
        exam.setUsername(c.getString(5));
        exam.setMsusername(c.getString(6));

        c.moveToNext();

        c.close();

        return exam;
    }

    /***
     * This function returns all the exams stored in the local database.
     *
     * @return
     */
    public ArrayList<Exam> getExams() {

        ArrayList<Exam> exams = new ArrayList<Exam>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `Exam`;";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            Exam exam = new Exam();
            exam.setId(c.getInt(0));
            exam.setType(c.getString(1));
            exam.setResults(c.getString(2));
            exam.setTimestamp(Timestamp.valueOf(c.getString(3)));
            exam.setAdvice(c.getString(4));
            exam.setUsername(c.getString(5));
            exam.setMsusername(c.getString(6));
            exams.add(exam);
            c.moveToNext();
        }
        c.close();

        return exams;
    }


    /**
     * Adds a new Medical Specialist to the local database.
     *
     * @param m
     */
    public void addMedicalSpecialist(MedicalSpecialist m) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =
                "INSERT or replace INTO MedicalSpecialist (msusername, password, name, surname, "
                        + "speciality, address, telephone, type) VALUES('" + m.getMsusername() + "', '"
                        + m.getPassword() + "', '" + m.getName() + "', '" + m.getSurname() + "', '"
                        + m.getSpeciality() + "', '" + m.getAddress() + "', " + m.getTelephone()
                        + ", '" + m.getType() + "')";
        db.execSQL(sql);
    }

    /**
     * Gets a specific Medical Specialist (via msusername) from the local database.
     *
     * @param msusername
     * @return
     */
    public MedicalSpecialist getMedicalSpecialist(String msusername) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `MedicalSpecialist` WHERE msusername = '" + msusername + "';";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        MedicalSpecialist medicalSpecialist = new MedicalSpecialist();
        medicalSpecialist.setMsusername(c.getString(0));
        medicalSpecialist.setPassword(c.getString(1));
        medicalSpecialist.setName(c.getString(2));
        medicalSpecialist.setSurname(c.getString(3));
        medicalSpecialist.setSpeciality(c.getString(4));
        medicalSpecialist.setAddress(c.getString(5));
        medicalSpecialist.setTelephone(c.getInt(6));
        medicalSpecialist.setType(c.getString(7));

        c.moveToNext();

        c.close();

        return medicalSpecialist;
    }

    /***
     * This function returns all the medical specialist of a specific speciality stored in the
     * local database.
     *
     * @param speciality
     * @return
     */
    public ArrayList<MedicalSpecialist> getMedicalSpecialists(String speciality) {

        ArrayList<MedicalSpecialist> medicalSpecialists = new ArrayList<MedicalSpecialist>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `MedicalSpecialist` WHERE speciality = '" + speciality + "';";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            MedicalSpecialist medicalSpecialist = new MedicalSpecialist();
            medicalSpecialist.setMsusername(c.getString(0));
            medicalSpecialist.setPassword(c.getString(1));
            medicalSpecialist.setName(c.getString(2));
            medicalSpecialist.setSurname(c.getString(3));
            medicalSpecialist.setSpeciality(c.getString(4));
            medicalSpecialist.setAddress(c.getString(5));
            medicalSpecialist.setTelephone(c.getInt(6));
            medicalSpecialist.setType(c.getString(7));
            medicalSpecialists.add(medicalSpecialist);
            c.moveToNext();
        }
        c.close();

        return medicalSpecialists;
    }

    /***
     * This function returns all the medical specialist stored in the local database.
     *
     * @return
     */
    public ArrayList<MedicalSpecialist> getMedicalSpecialists() {

        ArrayList<MedicalSpecialist> medicalSpecialists = new ArrayList<MedicalSpecialist>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `MedicalSpecialist`;";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            MedicalSpecialist medicalSpecialist = new MedicalSpecialist();
            medicalSpecialist.setMsusername(c.getString(0));
            medicalSpecialist.setPassword(c.getString(1));
            medicalSpecialist.setName(c.getString(2));
            medicalSpecialist.setSurname(c.getString(3));
            medicalSpecialist.setSpeciality(c.getString(4));
            medicalSpecialist.setAddress(c.getString(5));
            medicalSpecialist.setTelephone(c.getInt(6));
            medicalSpecialist.setType(c.getString(7));
            medicalSpecialists.add(medicalSpecialist);
            c.moveToNext();
        }
        c.close();

        return medicalSpecialists;
    }

    /**
     * Adds a new appointment to the local database.
     *
     * @param a
     */
    public void addAppointment(Appointment a) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =
                "INSERT or replace INTO Appointment (description, timestamp, username, msusername) "
                        + "VALUES('" + a.getDescription() + "', '"
                        + a.getDate() + "', '" + a.getPatient() + "', '" + a.getDoctor() + "')";
        db.execSQL(sql);
    }

    /**
     * Gets a specific appointment (by given username, msusername and timestamp) from the local
     * database.
     *
     * @param username
     * @param msusername
     * @param timestamp
     * @return
     */
    public Appointment getAppointment(String username, String msusername, Timestamp timestamp) {

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `Appointment` WHERE username = '" + username
                + "' AND msusername = '" + msusername + "' AND timestamp = '" + timestamp + "';";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        Appointment appointment = new Appointment();
        appointment.setPatient(c.getString(0));
        appointment.setDoctor(c.getString(1));
        appointment.setDate(Timestamp.valueOf(c.getString(2)));
        appointment.setDescription(c.getString(3));

        c.moveToNext();

        c.close();

        return appointment;
    }

    /***
     * This function returns all the appointments/history
     * of a specific user
     *
     * @param username
     * @return
     */
    public ArrayList<Appointment> getAppointments(String username) {

        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `Appointment` WHERE username = '" + username + "';";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            Appointment appointment = new Appointment();
            appointment.setPatient(c.getString(0));
            appointment.setDoctor(c.getString(1));
            appointment.setDate(Timestamp.valueOf(c.getString(2)));
            appointment.setDescription(c.getString(3));
            appointments.add(appointment);
            c.moveToNext();
        }
        c.close();

        return appointments;
    }

    /**
     * Adds a new inheritance of a specific patient to the local database.
     *
     * @param n
     */
    public void addInheritance(Inheritance n) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =
                "INSERT or replace INTO Inheritance (iname, username) "
                        + "VALUES('" + n.getIname() + "', '" + n.getUsername() + "')";
        db.execSQL(sql);
    }

    /**
     * Gets a specific inheritance (by given iname and username) of a selected patient from the
     * local database.
     *
     * @param iname
     * @param username
     * @return
     */
    public Inheritance getInheritance(String iname, String username) {

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `Inheritance` WHERE username = '" + username
                + "' AND iname = '" + iname + "';";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        Inheritance inheritance = new Inheritance();
        inheritance.setUsername(c.getString(0));
        inheritance.setIname(c.getString(1));

        c.moveToNext();

        c.close();

        return inheritance;
    }

    /***
     * DELETE METHODS
     **/

    /**
     * Deletes a specific patient from the local database.
     *
     * @param username
     */
    public void deletePatient(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM `Patient` WHERE username = '" + username + "';";

        db.execSQL(sql);
    }

    /**
     * Deletes a specific habit from the local database.
     *
     * @param hname
     */
    public void deleteHabit(String hname) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM `Habits` WHERE hname = '" + hname + "';";

        db.execSQL(sql);
    }

    /**
     * Deletes a specific illness from the local database.
     *
     * @param iname
     */
    public void deleteIllness(String iname) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM `Illnesses` WHERE iname = '" + iname + "';";

        db.execSQL(sql);
    }

    /**
     * Deletes a specific medication from the local database.
     *
     * @param mname
     */
    public void deleteMedication(String mname) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM `Medication` WHERE mname = '" + mname + "';";

        db.execSQL(sql);
    }

    /**
     * Deletes a specific exam from the local database.
     *
     * @param id
     */
    public void deleteExam(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM `Exam` WHERE id = '" + id + "';";

        db.execSQL(sql);
    }

    /**
     * Deletes a specific exam by given msusername and timestamp from the local database.
     *
     * @param msusername
     * @param timestamp
     */
    public void deleteExam(String msusername, String timestamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM `Exam` WHERE msusername = '" + msusername + "' AND timestamp = '"
                + timestamp + "';";

        db.execSQL(sql);
    }

    /**
     * Deletes a specific medical specialist from the local database.
     *
     * @param msusername
     */
    public void deleteMedicalSpecialist(String msusername) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM `MedicalSpecialist` WHERE msusername = '" + msusername + "';";

        db.execSQL(sql);
    }

    /**
     * Deletes a specific inheritance from the local database.
     *
     * @param iname
     */
    public void deleteInheritance(String iname) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM `Inheritance` WHERE iname = '" + iname + "';";

        db.execSQL(sql);
    }

    /**
     * Deletes a specific appointment from the local database.
     *
     * @param username
     * @param msusername
     * @param timestamp
     */
    public void deleteAppointment(String username, String msusername, String timestamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM `Appointment` WHERE username = '" + username
                + " AND msusername = '" + msusername + "' AND timestamp = '" + timestamp + "';";

        db.execSQL(sql);
    }

    /**
     * Totally erases the data from the local database.
     */
    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "DELETE FROM `Habits`;";
        db.execSQL(sql);
        sql = "DELETE FROM `Illnesses`;";
        db.execSQL(sql);
        sql = "DELETE FROM `Medication`";
        db.execSQL(sql);
        sql = "DELETE FROM `Exam`;";
        db.execSQL(sql);
        sql = "DELETE FROM `Inheritance`;";
        db.execSQL(sql);
        sql = "DELETE FROM `Appointment`;";
        db.execSQL(sql);
        sql = "DELETE FROM `MedicalSpecialist`;";
        db.execSQL(sql);
        sql = "DELETE FROM `Patient`;";
        db.execSQL(sql);

    }

}
