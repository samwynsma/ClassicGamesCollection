package GamesList.MasterMindMaterials;

import java.util.Random;

public class MasterMindCode {
    private char[] codeParts;
    public MasterMindCode(String letters)
    {
        codeParts = letters.toCharArray();
    }

    public MasterMindCode(int maxElement)
    {
        codeParts = new char[4];
        Random rand = new Random();
        for(int i = 0; i < 4; i++)
        {
            int pegVal = rand.nextInt(maxElement+1);
            codeParts[i] = (char)('0' + pegVal);
        }
    }

    public String DisplayCode() {
        StringBuilder sb = new StringBuilder();
        for(char ch : codeParts)
        {
            sb.append(ch);
        }
        return sb.toString();
    }

    public boolean equals(MasterMindCode code)
    {
        char[] otherParts = code.codeParts;
        for(int i = 0; i < codeParts.length; i++)
        {
            if(codeParts[i] != otherParts[i])
            {
                return false;
            }
        }
        return true;
    }
}
