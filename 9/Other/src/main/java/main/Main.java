//package main;
//
//import interpreter.pal.PAL;
//import interpreter.utils.EnvStoreTuple;
//import org.json.simple.parser.JSONParser;
//import typechecker.parse.Parser;
//import typechecker.tpal.TPAL;
//import typechecker.utils.StandardLib;
//
//import java.io.FileReader;
//
//public class Main {
//    public static void main(String[] args) {
//        try {
//                Object obj = new JSONParser().parse("[\"call\",[\"fun*\",[[\"x\",\":\",[\"int\",\"cell\"]]],[[\"x\",\"=\",1],\"+\",[\"x\",\"!\"]]],[0,\"@\"]]");
//                try {
//                    // TODO: toJsonString();
//                    System.out.println(pal.interpret(EnvStoreTuple.stdLib()).getLeft().toJSONString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println(String.format("\"run-time error: %s\"", e.getMessage()));
//                }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
