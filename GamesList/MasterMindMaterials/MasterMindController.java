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
        if(diff.equals("easy"))
        {
            maxElement = 4;
        }
        else if(diff.equals("medium"))
        {
            maxElement = 5;
        }
        else if(diff.equals("hard"))
        {
            maxElement = 6;
        }
        else if(diff.equals("brutal"))
        {
            maxElement = 7;
        }
        else
        {
            maxElement = 9;
        }
        correctCode = new MasterMindCode(maxElement);
        System.out.println(correctCode.DisplayCode());
    }

    public boolean CheckValidity(String code) {
        for(int i = 0; i < code.length(); i++)
        {
            int peg = (int)code.charAt(i) - 48;
            if(peg > maxElement)
            {
                System.out.println("That code is invalid. Please make sure the pegs are between 0 and " + maxElement);
                return false;
            }
        }
        return true;
    }

}
