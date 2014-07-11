package info.elfapp.testsystem;

/**
 * Created by sosnov on 22.03.14.
 */
public class Consts {

    public static final String TYPE_TXT = "text";
    public static final String TYPE_CHOSE = "chose";

    public static class СolumnsName {
        public static class Users {
            public static final String ID = "ID";
            public static final String NAME = "Name";
            public static final String PASS = "Password";
            public static final String SESSION = "Session";
        }

        public static class Questions {
            public static final String ID = "ID";
            public static final String QUEST = "Question";
            public static final String ANSWER = "Answer";
            public static final String GROUP_ID = "GroupID";
        }

    }

    public static class Tables {
        public static final String USERS = "Users";
        public static final String QUESTIONS = "Questions";
    }

    public static class Keys {
        public static final String TYPE = "type";
        public static final String ANSWRS = "answers";
        public static final String QUESTION = "question";
        public static final String GROUP = "group";
    }

}
