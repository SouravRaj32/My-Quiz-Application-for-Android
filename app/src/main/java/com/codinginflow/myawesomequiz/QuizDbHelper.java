package com.codinginflow.myawesomequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codinginflow.myawesomequiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        insertCategory(c1);
        Category c2 = new Category("Geography");
        insertCategory(c2);
        Category c3 = new Category("Math");
        insertCategory(c3);
    }
    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }
    public void addCategories(List<Category> categories) {
        db = getWritableDatabase();
        for (Category category : categories) {
            insertCategory(category);
        }
    }
    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Who is the creator of C Programming Language?",
                "Dennis Richie", "Guido Van Rossum", "James Gosling", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);

        insertQuestion(q1);
        Question q7 = new Question("What is the name of Earth's Natural Satellite ?",
                "Moon", "Europa", "Mars", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);

        insertQuestion(q7);
        Question q8 = new Question("Which is the closest Planet to The Sun  ?",
                "Venus", "Saturn", "Uranus", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);

        insertQuestion(q8);

        Question q9 = new Question("Who was the first person to walk on the moon? ?",
                "Neil ArmStrong", "Buzz Aldrin", "Michael Collins", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);

        insertQuestion(q9);

        Question q10 = new Question("Who is called the father of Indian Space Programme ?",
                "Dr. Vikram Sarabhai", "Dr. Satish Dhawan", "Dr. A.P.J Abdul Kalam", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q10);


        Question q5 = new Question("Which of these is not a Programming Language ?",
                "HTML", "JavaScript", "Python", 1,
                Question.DIFFICULTY_MEDIUM, 1);
        insertQuestion(q5);
        Question q11 = new Question("Who is called The Father of Computer?",
                "Linus Torvalds", "Hugo Barra", "Chales Babbage", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q11);
        Question q12 = new Question("Which is the Largest Racket ever built ?",
                "Delta Heavy ", "Falcon Heavy", "Saturn V", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q12);
        Question q13 = new Question("When was Apollo 11 Launched ?",
                "1969", "1980", "2002", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q13);
        Question q14 = new Question("Who is Called The Father of Modern computing ?",
                "Alan Turning", "David J. Malan", "None of The Above", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q14);



        Question q15 = new Question("Who wrote the Linux Kernel ?",
                "Linus Torvalds", "Steve Ballmer ", "None of The Above", 1,
                Question.DIFFICULTY_HARD, 1);
        insertQuestion(q15);
        Question q16 = new Question("When Was The Voyager Space Ship Launches into Space?",
                "1977", "2000", "2005", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q16);
        Question q17 = new Question("Which of these is Jupiter's Moon ?",
                "Europa ", "Titan", "Ceres", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q17);
        Question q18 = new Question("How many Planets are there in Our Solar System ?",
                "8", "9", "2", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q18);
        Question q19 = new Question("Who is the Founder of Space X ?",
                "Elon Musk", "Jim Brindestin", "Scott Manley", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q19);






        Question q20 = new Question("What is The Capital of The United States of America ?",
                "New York", "Washington D.C.", "Los Angelas", 2,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q20);
        Question q21 = new Question("What is The Capital of Canada ?",
                "Montreal", "Vancouver", "Ottawa", 3,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q21);
        Question q22 = new Question("What is The Capital of Australia ?",
                "Canberra", "Perth", "Sydney", 1,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q22);
        Question q23 = new Question("What is The Capital of New ZeaLand ?",
                "Wellington", "Auckland", "ChristChurch", 1,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q23);
        Question q24 = new Question("What is The Capital of Switzerland ?",
                "Bern", "Zurich", "Geneva", 1,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q24);



        Question q2 = new Question("What is the Highest Mountain Peak in The World ?",
                "K2", "Mount Everest", "Alps : Mont Blanc", 2,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q2);
        Question q25 = new Question("What is The Capital of France ?",
                "Paris", "Nice", "IstanBul", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q25);
        Question q26 = new Question("What is The Capital of Germany ?",
                "Berlin ", "Frankfurt", "None of The Above", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q26);
        Question q27 = new Question("What is The Capital of UK ?",
                "London", "Glascow", "Menchester", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q27);
        Question q28 = new Question("What is The Capital of India ?",
                "New Delhi", "Mumbai", "Allahabad", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q28);



        Question q29 = new Question("What is The Capital of Japan ?",
                "Osaka", "kyoto", "Tokyo", 3,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        insertQuestion(q29);
        Question q30 = new Question("What is The Capital of South Korea ?",
                "Busan", "Ho chi Minh", "Seoul", 3,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        insertQuestion(q30);
        Question q31 = new Question("What is The Capital of Philipines ?",
                "Manila", "Laos", "Saigon", 1,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        insertQuestion(q31);
        Question q32 = new Question("What is The Capital of Russia ?",
                "Moscow", "St. Peterbergs", "Novosibirsk", 1,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        insertQuestion(q32);
        Question q33 = new Question("What is The Capital of Netherlands ?",
                "The Hague", "Amsterdam", "None of the Above", 1,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        insertQuestion(q33);


        Question q4 = new Question("2+2=?",
                "4", "7", "5", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q4);
        Question q34 = new Question("2*3= ?",
                "6", "0", "8", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q34);
        Question q35 = new Question("3+6= ?",
                "9", "2", "7", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q35);
        Question q36 = new Question("9 Divided by 3= ?",
                "6", "2", "0", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q36);
        Question q37 = new Question("8+5 = ?",
                "13", "2", "0", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q37);



        Question q38 = new Question("8+2 = ?",
                "10", "2", "0", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q38);
        Question q39 = new Question("0+5 = ?",
                "5", "2", "0", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q39);
        Question q40 = new Question("8+22 = ?",
                "30", "2", "0", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q40);
        Question q41 = new Question("8+92 = ?",
                "100", "2", "0", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q41);
        Question q42 = new Question("8+51 = ?",
                "59", "2", "0", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q42);











        Question q3 = new Question("Which of these Mathematician gave the concept of ZERO ? ",
                "Archimedes", "Euclid", "AryaBhata", 3,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q3);
        Question q43 = new Question("Square Root of 9 = ?",
                "3", "2", "0", 1,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q43);
        Question q44 = new Question("Square Root of 64  = ?",
                "8", "2", "0", 1,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q44);
        Question q45 = new Question("Cube Root of 27 =  ?",
                "3", "2", "0", 1,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q45);
        Question q46 = new Question("Do You Like The Quiz ?",
                "Liked A Lot", "Not Much ", "Hated it", 1,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q46);





    }
    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }
    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();
        for (Question question : questions) {
            insertQuestion(question);
        }
    }



    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }
    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};
        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}