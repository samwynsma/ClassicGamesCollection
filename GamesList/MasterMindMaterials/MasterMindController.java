package GamesList.MasterMindMaterials;

import java.util.*;

public class MasterMindController {

    private String diff;
    private MasterMindCode correctCode;
    private List<String> allowedDifficulties;
    private int guesses = 1;
    private int maxElement = 0;
    public MasterMindController(){
        diff = "Medium";
        allowedDifficulties = new ArrayList<String>();
        allowedDifficulties.add("easy");
        allowedDifficulties.add("medium");
        allowedDifficulties.add("hard");
        allowedDifficulties.add("brutal");
        allowedDifficulties.add("impossible");
    }

    public boolean SetDifficulty(String difficulty) {
        if(allowedDifficulties.contains(difficulty))
        {
            diff = difficulty;
            return true;
        }
        System.out.println(difficulty + " is not a valid difficulty option. Please enter easy, medium, hard, brutal, or impossible");
        return false;
    }

    public void SetUpGame()
    {
        if(diff == "easy")
        {
            maxElement = 4;
        }
        else if(diff == "medium")
        {
            maxElement = 5;
        }
        else if(diff == "hard")
        {
            maxElement = 6;
        }
        else if(diff == "brutal")
        {
            maxElement = 7;
        }
        else
        {
            maxElement = 9;
        }
        correctCode = new MasterMindCode(maxElement);
    }

    public void CheckValidity(String code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CheckValidity'");
    }

}
