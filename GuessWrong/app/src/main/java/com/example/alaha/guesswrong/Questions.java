package com.example.alaha.guesswrong;

public class Questions {
    public String mQuestions[] = {
            "How long did the Hundred Years War last?",
            "Which country makes Panama Hats?",
            "From which animal do we get catgut?",
            "In which month do Russians celebrate the October Revolution?",
            "Which side of hand is your left hand?",
            "Which country is called Italy?",
            "The word Cricket has capital letter _",
            "The sun rises in the ___",
            "2 + 2 = ?",
            "100 > 50",
            "If A=5, B=5, A+B=",
            "7 * 7 = ?",
            "21 February is in which month?",
            "The song November Rain refers to which month?",
            "1 - 1 = ?",
            "In which game baseball is used?",
            "I am __",
            "What flies in the sky?",
            "1 > 0 Means 1 is _?",
            "5 - 5 = ?",
            "International Mothers Language Day tributes to?",
            "Which fruit Mango tree produces?",
            "Which letter is immediately after A",
            "Football is played by?",
            "Canada starts with ?",
            "If Rahim is Karim's Brother than Karim is Rahim's? ",
            "How many angles are there in Triangle?",
            "How many angles are there in Square?",
            "Opposite of FAST",
            "Opposite of Happy",
            "If A is 10, B is 100 who is older?",




    };


    private String mChoices[][] = {

            {"100","50"},
            {"Paraguay","Panama"},
            {"Cat","Cow"},
            {"October","November"},
            {"Left","Right"},
            {"Italy","India"},
            {"F","C"},
            {"WEST","EAST"},
            {"4","8"},
            {"TRUE","FALSE"},
            {"10","25"},
            {"49","14"},
            {"February","November"},
            {"October","November"},
            {"1","0"},
            {"baseball","hockey"},
            {"human","monkey"},
            {"cows","birds"},
            {"Bigger","Smaller"},
            {"10","0"},
            {"Mothers","Fathers"},
            {"Mango","Banana"},
            {"B","C"},
            {"Foot","Hand"},
            {"C","B"},
            {"Brother","Sister"},
            {"3","4"},
            {"3","4"},
            {"Faster","Slow"},
            {"Happiest","Unhappy"},
            {"10","100"}



    };

    private String mCorrectAnswers[]  = {

            "50","Paraguay","Cow","November","Right","India","C","WEST","8","FALSE","25","14","November","October","1","hockey","monkey",
            "cows","Smaller","10","Fathers","Banana","C","Hand","B","Sister","4","3","Faster","Happiest","10"
    } ;




    public String getQuestion(int a){
        String question = mQuestions[a];
        return question;

    }
    public String getChoice1(int a){
        String choice = mChoices[a][0];
        return choice;

    }
    public String getChoice2(int a){
        String choice = mChoices[a][1];
        return choice;

    }

    public String getCorrectAnswer (int a) {
        String answer = mCorrectAnswers[a];
        return answer;

    }



}

