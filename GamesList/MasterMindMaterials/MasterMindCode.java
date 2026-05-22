package GamesList.MasterMindMaterials;

public class MasterMindCode {
    private char[] codeParts;
    public MasterMindCode(String letters)
    {
        codeParts = letters.toCharArray();
    }

    public MasterMindCode(int maxElement)
    {
        codeParts = "1111".toCharArray();
    }
}
