package com.example.insyte;

import android.provider.BaseColumns;

public final class CheckInContract {

    private CheckInContract(){}

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "check_in_questions";
        public static final String COLUMN_QUESTION = "questions";
        public static final String COLUMN_OEQ = "oeq";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_OPTION5 = "option5";
    }
}
