package com.example.bancoudi.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ModelBancoDados extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "UdiBancoDeDados";
    private static final int DATABASE_VERSION = 1;

    public static final String NOME_TABELA = "Dados Bancarios";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_TITULAR = "titular";
    public static final String COLUNA_SALDO= "saldo";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + NOME_TABELA + " (" +
                    COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_TITULAR + " VARCHAR(80)," +
                    COLUNA_SALDO + "DECIMAL);";

    public ModelBancoDados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
        onCreate(sqLiteDatabase);
    }
}
