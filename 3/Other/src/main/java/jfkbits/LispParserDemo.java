package jfkbits;
import jfkbits.ExprList;
import jfkbits.LispParser;
import jfkbits.LispParser.ParseException;
import jfkbits.LispTokenizer;

public class LispParserDemo
{
    public static void main(String args[])
    {

        LispTokenizer tzr = new LispTokenizer(
                "( 1 + 1 )");
        LispParser parser = new LispParser(tzr);

        try
        {
            LispParser.Expr result = parser.parseExpr();
            System.out.println(((ExprList)result).get(1).getClass());
            System.out.println(((ExprList)result).get(1) instanceof StringAtom);
            System.out.println(result);
        }
        catch (ParseException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}