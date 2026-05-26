package GamesList.MasterMindMaterials;

import java.util.*;

public class MasterMindController {

    private String diff;
    private MasterMindCode correctCode;
    private List<String> allowedDifficulties;
    private int guesses = 0;
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
        guesses++;
        return true;
    }

    public String GetCode()
    {
        return correctCode.DisplayCode();
    }

    public boolean CheckIfSolved(String code)
    {
        MasterMindCode guess = new MasterMindCode(code);
        if(guess.equals(correctCode))
        {
            System.out.println("Correct answer. The number of guesses it took you was " + guesses);
            return true;
        }
        else
        {
            System.out.println("Incorrect answer. Keep guessing.");
            return false;
        }
    }

    public void GiveHints(String code) {
        int rightPlace = 0;
        int wrongPlace = 0;
        String solutionCode = correctCode.DisplayCode();
        HashMap<Character, Integer> codeCounts = new HashMap<Character, Integer>();
        HashMap<Character, Integer> solutionCounts = new HashMap<Character, Integer>();
        for(int i = 0; i < code.length(); i++)
        {
            char codeChar = code.charAt(i);
            char solChar = solutionCode.charAt(i);
            codeCounts.put(codeChar, codeCounts.getOrDefault(codeChar, 0) + 1);
            solutionCounts.put(solChar, solutionCounts.getOrDefault(solChar, 0) + 1);
            if(codeChar == solChar)
            {
                rightPlace++;
            }
        }

        for(char key : codeCounts.keySet())
        {
            if(solutionCounts.containsKey(key))
            {
                wrongPlace += Math.min(codeCounts.get(key), solutionCounts.get(key));
            }
        }
        wrongPlace -= rightPlace;

        System.out.println("You have " + rightPlace + " pegs that are correct and in the right place, and " + wrongPlace + " pegs that are correct but in the wrong place.");
    }

}
